package com.baomidou.springboot.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springboot.common.AjaxResult;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("home")
    public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {

        AjaxResult ajaxResult = new AjaxResult();

        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.err.println("loginname = " + loginname);
        System.err.println("password = " + password);
        System.err.println("rememberMe = " + rememberMe);

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

                ajaxResult.setCode(1).setMsg("登录成功").setObj("index.html");
                return ajaxResult;
            }
        }
        ajaxResult.setCode(0).setMsg("用户名或密码错误").setObj("login");
        return ajaxResult;
    }

}
