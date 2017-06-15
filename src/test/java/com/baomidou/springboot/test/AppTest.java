package com.baomidou.springboot.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.springboot.common.http.HttpAPIService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/2
 * @Time: 18:33
 * @Description:
 */
public class AppTest {

    @Resource
    private HttpAPIService httpAPIService;

    public static void main(String[] args) {

//        String str = httpAPIService.doGet("http://ds.idc.xiwanglife.com/dataservice/getconfig.do?id=164&date=2017-06-14");
//        JSONArray values = JSON.parseObject(str).getJSONObject("details").getJSONObject("list").getJSONArray("values");
//        System.err.println("values = " + values);
//        System.err.println("values = " + values.toJSONString());

    }


}
