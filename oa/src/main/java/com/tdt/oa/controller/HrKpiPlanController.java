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
import com.tdt.oa.domain.HrKpiPlan;
import com.tdt.oa.service.IHrKpiPlanService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 考核计划Controller
 * 
 * @author hxw
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/oa/plan")
public class HrKpiPlanController extends BaseController
{
    @Autowired
    private IHrKpiPlanService hrKpiPlanService;

    /**
     * 查询考核计划列表
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrKpiPlan hrKpiPlan)
    {
        startPage();
        List<HrKpiPlan> list = hrKpiPlanService.selectHrKpiPlanList(hrKpiPlan);
        return getDataTable(list);
    }

    /**
     * 导出考核计划列表
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:export')")
    @Log(title = "考核计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrKpiPlan hrKpiPlan)
    {
        List<HrKpiPlan> list = hrKpiPlanService.selectHrKpiPlanList(hrKpiPlan);
        ExcelUtil<HrKpiPlan> util = new ExcelUtil<HrKpiPlan>(HrKpiPlan.class);
        util.exportExcel(response, list, "考核计划数据");
    }

    /**
     * 获取考核计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hrKpiPlanService.selectHrKpiPlanById(id));
    }

    /**
     * 新增考核计划
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:add')")
    @Log(title = "考核计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrKpiPlan hrKpiPlan)
    {
        return toAjax(hrKpiPlanService.insertHrKpiPlan(hrKpiPlan));
    }

    /**
     * 修改考核计划
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:edit')")
    @Log(title = "考核计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrKpiPlan hrKpiPlan)
    {
        return toAjax(hrKpiPlanService.updateHrKpiPlan(hrKpiPlan));
    }

    /**
     * 删除考核计划
     */
    @PreAuthorize("@ss.hasPermi('oa:plan:remove')")
    @Log(title = "考核计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hrKpiPlanService.deleteHrKpiPlanByIds(ids));
    }
}
