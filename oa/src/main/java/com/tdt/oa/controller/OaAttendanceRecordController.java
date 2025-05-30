package com.tdt.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tdt.common.annotation.Log;
import com.tdt.common.core.controller.BaseController;
import com.tdt.common.core.domain.AjaxResult;
import com.tdt.common.enums.BusinessType;
import com.tdt.oa.domain.OaAttendanceRecord;
import com.tdt.oa.service.IOaAttendanceRecordService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 打卡记录Controller
 * 
 * @author hxw
 * @date 2025-05-30
 */
@RestController
@RequestMapping("/oa/record")
public class OaAttendanceRecordController extends BaseController
{
    @Autowired
    private IOaAttendanceRecordService oaAttendanceRecordService;

    /**
     * 查询打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('oa:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaAttendanceRecord oaAttendanceRecord)
    {
        startPage();
        List<OaAttendanceRecord> list = oaAttendanceRecordService.selectOaAttendanceRecordList(oaAttendanceRecord);
        return getDataTable(list);
    }

    /**
     * 导出打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('oa:record:export')")
    @Log(title = "打卡记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaAttendanceRecord oaAttendanceRecord)
    {
        List<OaAttendanceRecord> list = oaAttendanceRecordService.selectOaAttendanceRecordList(oaAttendanceRecord);
        ExcelUtil<OaAttendanceRecord> util = new ExcelUtil<OaAttendanceRecord>(OaAttendanceRecord.class);
        util.exportExcel(response, list, "打卡记录数据");
    }

    /**
     * 获取打卡记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(oaAttendanceRecordService.selectOaAttendanceRecordById(id));
    }

    /**
     * 新增打卡记录
     */
    @PreAuthorize("@ss.hasPermi('oa:record:add')")
    @Log(title = "打卡记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaAttendanceRecord oaAttendanceRecord)
    {
        return toAjax(oaAttendanceRecordService.insertOaAttendanceRecord(oaAttendanceRecord));
    }

    /**
     * 修改打卡记录
     */
    @PreAuthorize("@ss.hasPermi('oa:record:edit')")
    @Log(title = "打卡记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaAttendanceRecord oaAttendanceRecord)
    {
        return toAjax(oaAttendanceRecordService.updateOaAttendanceRecord(oaAttendanceRecord));
    }

    /**
     * 删除打卡记录
     */
    @PreAuthorize("@ss.hasPermi('oa:record:remove')")
    @Log(title = "打卡记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaAttendanceRecordService.deleteOaAttendanceRecordByIds(ids));
    }
}
