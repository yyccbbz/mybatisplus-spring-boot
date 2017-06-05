package com.baomidou.springboot.controller;

import com.baomidou.springboot.exception.MyException;
import com.baomidou.springboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/2
 * @Time: 18:33
 * @Description:
 */
@RequestMapping("/hello")
@Controller
public class HelloController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

    @ApiOperation(value = "测试页面，用户列表",notes = "")
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public ModelAndView getUserTable(){
        ModelAndView mv = new ModelAndView("userList");
        mv.addObject("users",userService.selectList(null));
        return mv;
    }
}
