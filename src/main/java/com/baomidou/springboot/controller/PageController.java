package com.baomidou.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017-6-5
 * @Time: 20:31
 * @Description: 页面跳转控制器
 */
@Controller
public class PageController {


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/hello")
    public String helloGet(Map<String, Object> map) {
        map.put("name", "HowieLi");
        return "hello";
    }


}
