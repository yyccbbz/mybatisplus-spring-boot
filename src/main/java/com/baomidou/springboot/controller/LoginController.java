package com.baomidou.springboot.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
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

    /**
     * 登录 （注解跳过权限验证）
     *//*
    @Login(action = Action.Skip)
    @RequestMapping("/login")
    public String login() {
        SSOToken st = SSOHelper.getToken(request);
        if (st != null) {
            return redirectTo("/index.html");
        }
        return "/login";
    }*/

    /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/loginpost")
    public String loginpost() {
        /**
         * 生产环境需要过滤sql注入
         */
        WafRequestWrapper req = new WafRequestWrapper(request);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("kisso".equals(username) && "123".equals(password)) {

			/*
			 * authSSOCookie 设置 cookie 同时改变 jsessionId
			 */
            SSOToken st = new SSOToken(request);
            st.setId(12306L);
            st.setUid("12306");
            st.setType(1);

            //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
            //String rememberMe = req.getParameter("rememberMe");
            //if ( "on".equals(rememberMe) ) {
            //	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
            //}
            SSOHelper.setSSOCookie(request, response, st, true);

			/*
			 * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
			 * HttpUtil.decodeURL(xx) 解码后重定向
			 */
            return redirectTo("/index.html");
        }
        return "/login";
    }


    @Login(action = Action.Skip)
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
                session.setMaxInactiveInterval(2 * 30);

                ajaxResult.setCode(1).setMsg("登录成功").setObj("index.html");
                return ajaxResult;
            }
        }
        ajaxResult.setCode(0).setMsg("用户名或密码错误").setObj("login");
        return ajaxResult;
    }

}
