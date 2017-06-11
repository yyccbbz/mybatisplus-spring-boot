package com.baomidou.springboot.controller;

import com.baomidou.springboot.entity.UserHLJ;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

/**
 * Created by huanglijun on 2017/6/10.
 */
@RequestMapping("/test")
@Controller
public class TestController extends TestBaseController{
    Map<String,String> map = new HashMap<String,String>();


    @RequestMapping(value = "/1")
    public String toPageData(){
        return "indexTest";
    }

    @RequestMapping(value = "/toPage")
    public String toPage(HttpServletRequest request){
        if (checkLogin(request)){
            return "test";
        };
        return "fail";
    }

    //localhost:8080/test/login?username=test&password=123
    @RequestMapping("/login")
    public String index(HttpServletRequest request, HttpServletResponse response,Model model) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserHLJ user = new UserHLJ(username,password);
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty() && username.endsWith("test") && password.endsWith("test")){
            HttpSession session = request.getSession();
            Cookie cookie = new Cookie(session.getId(), "hi");
            response.addCookie(cookie);
            //创建session 保存两个小时
            session.setAttribute("hi",user);
            session.setMaxInactiveInterval(2 * 3);

            return "success";
        }
        return "fail";
    }

}
