package com.baomidou.springboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springboot.common.utils.DateUtil;
import com.baomidou.springboot.entity.EmployeeCreditReport;
import com.baomidou.springboot.service.IEmployeeCreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 员工贷报表（贷前日报） 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-08
 */
@Controller
@RequestMapping("/dailyList")
public class EmployeeCreditReportController extends BaseController {

    @Autowired
    private IEmployeeCreditReportService employeeCreditReportService;


    @ResponseBody
    @RequestMapping("getList")
    public List<EmployeeCreditReport> getList(){
        Page<EmployeeCreditReport> page = getPage();
        Page<EmployeeCreditReport> list = employeeCreditReportService.selectPage(page, null);
        return employeeCreditReportService.selectList(null);
    }

    /**
     * 添加测试数据
     * @return
     */
    @GetMapping("addTestData")
    public String addTestData() {

        List<EmployeeCreditReport> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            EmployeeCreditReport ecr = new EmployeeCreditReport(
                    (long) i, DateUtil.randomDate("2017-01-01", "2017-05-01"), "[" + i + "," + (i + 1) + ")",
                    i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i,
                    new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(),
                    i, i, i, new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(),
                    i, new Random().nextDouble(), i, new Random().nextDouble()
            );

            list.add(ecr);
        }
        Boolean b = employeeCreditReportService.insertBatch(list);
        return b.toString();
    }

}
