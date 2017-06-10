package com.baomidou.springboot.controller;

import com.baomidou.framework.controller.SuperController;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.springboot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 账户相关操作
 * </p>
 *
 * @author CuiCan
 * @Date 2017-06-10
 */
@Controller
@RequestMapping("/account")
public class AccountController extends SuperController {

    @Autowired
    protected IUserService userService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    public String index(Model model) {
        if (isPost()) {
            String errorMsg = "用户名或密码错误";
            /**
             * 过滤 XSS SQL 注入
             */
            WafRequestWrapper wr = new WafRequestWrapper(request);
            String ctoken = wr.getParameter("ctoken");
            if (StringUtils.isNotBlank(ctoken)) {
                String loginName = wr.getParameter("loginName");
                String password = wr.getParameter("password");
                /**
                 * <p>
                 * 用户存在，签名合法，登录成功
                 * <br>
                 * 进入后台
                 * </p>
                 */
//                User user = userService.selectByLoginName(loginName);
                /*if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)) {
                    SSOToken st = new SSOToken(request);
                    st.setId(user.getId());
                    st.setData(loginName);

                    // 记住密码，设置 cookie 时长 1 周 = 604800 秒
                    String rememberMe = wr.getParameter("rememberMe");
                    if ("on".equals(rememberMe)) {
                        request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
                    }

                    SSOHelper.setSSOCookie(request, response, st, true);}*/
                    return "index";

            } else {
                errorMsg = "验证码错误";
            }
            model.addAttribute("errorMsg", errorMsg);
        }
        return "/index";
    }


    /**
     * 退出
     */
    @RequestMapping("/logout")
    public String logout(Model model) {
        SSOHelper.clearLogin(request, response);
        return redirectTo("/account/login.html");
    }

}
