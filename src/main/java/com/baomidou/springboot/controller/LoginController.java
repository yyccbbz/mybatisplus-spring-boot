package com.baomidou.springboot.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/", "/login", "/home"})
    public String toPage(){
        return "login";
    }

    //localhost:8080/test/login?username=test&password=123
    @RequestMapping("sign_in")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");

        System.err.println("loginname = " + loginname);
        System.err.println("password = " + password);

        if (StringUtils.isNotEmpty(loginname) && StringUtils.isNotEmpty(password)) {
            User u = new User();
            u.setLoginName(loginname);
            u.setPassword(password);
            User selectOne = userService.selectOne(new EntityWrapper<>(u));
            if(selectOne != null){
                HttpSession session = request.getSession();
                Cookie cookie = new Cookie(session.getId(), "hi");
                response.addCookie(cookie);
                //创建session 保存两个小时
                session.setAttribute("hi", selectOne);
                session.setMaxInactiveInterval(2 * 3);
                return "index";
            }
        }

        return "index";
    }

}
