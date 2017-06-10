package com.baomidou.springboot.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.springboot.entity.TestUser;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/2
 * @Time: 18:33
 * @Description:
 */
public class AppTest {


    public static void main(String[] args) {

        TestUser u = new TestUser();

        u.setTestId(3L);
        u.setAge(30);
        u.setName("张三丰");
        u.setPhone("13688881114");
        u.setRole(2L);
        u.setTestDate(new Date());
        u.setTestType(1);

        System.out.println("u = " + JSONObject.toJSONString(u));

    }



}
