package com.tdt.controller;

import io.swagger.annotations.Api;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-06-18 14:19
 */
@RestController
@RequestMapping("/get/office")
// 解决跨域的问题
@CrossOrigin
@Api(value = "行政GetController", tags = "行政获取数据接口")
public class ApiGetOfficeController {

}
