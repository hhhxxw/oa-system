package com.tdt.process.controller;

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
import com.tdt.process.domain.ProcessType;
import com.tdt.process.service.IProcessTypeService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 审批类型Controller
 * 
 * @author hxw
 * @date 2025-06-19
 */
@RestController
@RequestMapping("/process/type")
public class ProcessTypeController extends BaseController
{
    @Autowired
    private IProcessTypeService processTypeService;

    /**
     * 查询审批类型列表
     */
    @PreAuthorize("@ss.hasPermi('process:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessType processType)
    {
        startPage();
        List<ProcessType> list = processTypeService.selectProcessTypeList(processType);
        return getDataTable(list);
    }

    /**
     * 导出审批类型列表
     */
    @PreAuthorize("@ss.hasPermi('process:type:export')")
    @Log(title = "审批类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessType processType)
    {
        List<ProcessType> list = processTypeService.selectProcessTypeList(processType);
        ExcelUtil<ProcessType> util = new ExcelUtil<ProcessType>(ProcessType.class);
        util.exportExcel(response, list, "审批类型数据");
    }

    /**
     * 获取审批类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processTypeService.selectProcessTypeById(id));
    }

    /**
     * 新增审批类型
     */
    @PreAuthorize("@ss.hasPermi('process:type:add')")
    @Log(title = "审批类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessType processType)
    {
        return toAjax(processTypeService.insertProcessType(processType));
    }

    /**
     * 修改审批类型
     */
    @PreAuthorize("@ss.hasPermi('process:type:edit')")
    @Log(title = "审批类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessType processType)
    {
        return toAjax(processTypeService.updateProcessType(processType));
    }

    /**
     * 删除审批类型
     */
    @PreAuthorize("@ss.hasPermi('process:type:remove')")
    @Log(title = "审批类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processTypeService.deleteProcessTypeByIds(ids));
    }
}
