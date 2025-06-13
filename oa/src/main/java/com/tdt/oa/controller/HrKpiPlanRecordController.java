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
import com.tdt.oa.domain.HrKpiPlanRecord;
import com.tdt.oa.service.IHrKpiPlanRecordService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 人力资源考核详情Controller
 * 
 * @author hxw
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/oa/record")
public class HrKpiPlanRecordController extends BaseController
{
    @Autowired
    private IHrKpiPlanRecordService hrKpiPlanRecordService;

    /**
     * 查询人力资源考核详情列表
     */
    @PreAuthorize("@ss.hasPermi('oa:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrKpiPlanRecord hrKpiPlanRecord)
    {
        startPage();
        List<HrKpiPlanRecord> list = hrKpiPlanRecordService.selectHrKpiPlanRecordList(hrKpiPlanRecord);
        return getDataTable(list);
    }

    /**
     * 导出人力资源考核详情列表
     */
    @PreAuthorize("@ss.hasPermi('oa:record:export')")
    @Log(title = "人力资源考核详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrKpiPlanRecord hrKpiPlanRecord)
    {
        List<HrKpiPlanRecord> list = hrKpiPlanRecordService.selectHrKpiPlanRecordList(hrKpiPlanRecord);
        ExcelUtil<HrKpiPlanRecord> util = new ExcelUtil<HrKpiPlanRecord>(HrKpiPlanRecord.class);
        util.exportExcel(response, list, "人力资源考核详情数据");
    }

    /**
     * 获取人力资源考核详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hrKpiPlanRecordService.selectHrKpiPlanRecordById(id));
    }

    /**
     * 新增人力资源考核详情
     */
    @PreAuthorize("@ss.hasPermi('oa:record:add')")
    @Log(title = "人力资源考核详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrKpiPlanRecord hrKpiPlanRecord)
    {
        return toAjax(hrKpiPlanRecordService.insertHrKpiPlanRecord(hrKpiPlanRecord));
    }

    /**
     * 修改人力资源考核详情
     */
    @PreAuthorize("@ss.hasPermi('oa:record:edit')")
    @Log(title = "人力资源考核详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrKpiPlanRecord hrKpiPlanRecord)
    {
        return toAjax(hrKpiPlanRecordService.updateHrKpiPlanRecord(hrKpiPlanRecord));
    }

    /**
     * 删除人力资源考核详情
     */
    @PreAuthorize("@ss.hasPermi('oa:record:remove')")
    @Log(title = "人力资源考核详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hrKpiPlanRecordService.deleteHrKpiPlanRecordByIds(ids));
    }
}
