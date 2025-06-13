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
import com.tdt.oa.domain.HrKpiItem;
import com.tdt.oa.service.IHrKpiItemService;
import com.tdt.common.utils.poi.ExcelUtil;
import com.tdt.common.core.page.TableDataInfo;

/**
 * 人力考核指标集Controller
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/oa/item")
public class HrKpiItemController extends BaseController
{
    @Autowired
    private IHrKpiItemService hrKpiItemService;

    /**
     * 查询人力考核指标集列表
     */
    @PreAuthorize("@ss.hasPermi('oa:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrKpiItem hrKpiItem)
    {
        startPage();
        List<HrKpiItem> list = hrKpiItemService.selectHrKpiItemList(hrKpiItem);
        return getDataTable(list);
    }

    /**
     * 导出人力考核指标集列表
     */
    @PreAuthorize("@ss.hasPermi('oa:item:export')")
    @Log(title = "人力考核指标集", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrKpiItem hrKpiItem)
    {
        List<HrKpiItem> list = hrKpiItemService.selectHrKpiItemList(hrKpiItem);
        ExcelUtil<HrKpiItem> util = new ExcelUtil<HrKpiItem>(HrKpiItem.class);
        util.exportExcel(response, list, "人力考核指标集数据");
    }

    /**
     * 获取人力考核指标集详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hrKpiItemService.selectHrKpiItemById(id));
    }

    /**
     * 新增人力考核指标集
     */
    @PreAuthorize("@ss.hasPermi('oa:item:add')")
    @Log(title = "人力考核指标集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrKpiItem hrKpiItem)
    {
        return toAjax(hrKpiItemService.insertHrKpiItem(hrKpiItem));
    }

    /**
     * 修改人力考核指标集
     */
    @PreAuthorize("@ss.hasPermi('oa:item:edit')")
    @Log(title = "人力考核指标集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrKpiItem hrKpiItem)
    {
        return toAjax(hrKpiItemService.updateHrKpiItem(hrKpiItem));
    }

    /**
     * 删除人力考核指标集
     */
    @PreAuthorize("@ss.hasPermi('oa:item:remove')")
    @Log(title = "人力考核指标集", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hrKpiItemService.deleteHrKpiItemByIds(ids));
    }
}
