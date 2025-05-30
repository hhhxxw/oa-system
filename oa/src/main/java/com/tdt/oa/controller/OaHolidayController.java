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
import com.tdt.oa.domain.OaHoliday;
import com.tdt.oa.service.IOaHolidayService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 节假日Controller
 * 
 * @author hxw
 * @date 2025-05-30
 */
@RestController
@RequestMapping("/oa/holiday")
public class OaHolidayController extends BaseController
{
    @Autowired
    private IOaHolidayService oaHolidayService;

    /**
     * 查询节假日列表
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaHoliday oaHoliday)
    {
        startPage();
        List<OaHoliday> list = oaHolidayService.selectOaHolidayList(oaHoliday);
        return getDataTable(list);
    }

    /**
     * 导出节假日列表
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:export')")
    @Log(title = "节假日", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaHoliday oaHoliday)
    {
        List<OaHoliday> list = oaHolidayService.selectOaHolidayList(oaHoliday);
        ExcelUtil<OaHoliday> util = new ExcelUtil<OaHoliday>(OaHoliday.class);
        util.exportExcel(response, list, "节假日数据");
    }

    /**
     * 获取节假日详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaHolidayService.selectOaHolidayById(id));
    }

    /**
     * 新增节假日
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:add')")
    @Log(title = "节假日", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaHoliday oaHoliday)
    {
        return toAjax(oaHolidayService.insertOaHoliday(oaHoliday));
    }

    /**
     * 修改节假日
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:edit')")
    @Log(title = "节假日", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaHoliday oaHoliday)
    {
        return toAjax(oaHolidayService.updateOaHoliday(oaHoliday));
    }

    /**
     * 删除节假日
     */
    @PreAuthorize("@ss.hasPermi('oa:holiday:remove')")
    @Log(title = "节假日", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaHolidayService.deleteOaHolidayByIds(ids));
    }
}
