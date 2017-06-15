package com.baomidou.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 18:31
 * @Description: 审批类 前端控制器
 */
@RestController
@RequestMapping("review")
public class ReviewController extends BaseController {

    @Value(value = "${finalReview_http_id}")
    private String finalReview_http_id;

    @RequestMapping("/finalReview")
    public String finalReview() {
        return parseHttpJsonResult(finalReview_http_id);
    }


}
