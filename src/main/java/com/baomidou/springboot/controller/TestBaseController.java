package com.baomidou.springboot.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huanglijun on 2017/6/10.
 */
public class TestBaseController {

    public boolean checkLogin(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(request.getSession().getId())){
                Object object = request.getSession().getAttribute(cookie.getValue());
                if (object != null)
                    return true;
            }
        }
        return false;
    }
}
