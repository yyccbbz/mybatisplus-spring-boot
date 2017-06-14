package com.baomidou.springboot.entity.review;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 15:32
 * @Description: 终审类
 */
public class ReportFinialReview {

    // 时间段(小时）
    private String hours_desc;
    // 终审新增待审笔数
    private String finial_review_add_amt;
    // 终审新增待审金额
    private String finial_review_finish_cnt;
    // 终审完成笔数
    private String finial_review_finish_amt;
    // 终审完成金额
    private String finial_review_refuse_cnt;
    // 终审拒绝笔数
    private String finial_review_refuse_amt;
    // 终审拒绝金额
    private String finial_review_add_cnt;

    public ReportFinialReview() {
    }

    public ReportFinialReview(String hours_desc, String finial_review_add_amt, String finial_review_finish_cnt,
                              String finial_review_finish_amt, String finial_review_refuse_cnt,
                              String finial_review_refuse_amt, String finial_review_add_cnt) {
        this.hours_desc = hours_desc;
        this.finial_review_add_amt = finial_review_add_amt;
        this.finial_review_finish_cnt = finial_review_finish_cnt;
        this.finial_review_finish_amt = finial_review_finish_amt;
        this.finial_review_refuse_cnt = finial_review_refuse_cnt;
        this.finial_review_refuse_amt = finial_review_refuse_amt;
        this.finial_review_add_cnt = finial_review_add_cnt;
    }

    public String getHours_desc() {
        return hours_desc;
    }

    public void setHours_desc(String hours_desc) {
        this.hours_desc = hours_desc;
    }

    public String getFinial_review_add_amt() {
        return finial_review_add_amt;
    }

    public void setFinial_review_add_amt(String finial_review_add_amt) {
        this.finial_review_add_amt = finial_review_add_amt;
    }

    public String getFinial_review_finish_cnt() {
        return finial_review_finish_cnt;
    }

    public void setFinial_review_finish_cnt(String finial_review_finish_cnt) {
        this.finial_review_finish_cnt = finial_review_finish_cnt;
    }

    public String getFinial_review_finish_amt() {
        return finial_review_finish_amt;
    }

    public void setFinial_review_finish_amt(String finial_review_finish_amt) {
        this.finial_review_finish_amt = finial_review_finish_amt;
    }

    public String getFinial_review_refuse_cnt() {
        return finial_review_refuse_cnt;
    }

    public void setFinial_review_refuse_cnt(String finial_review_refuse_cnt) {
        this.finial_review_refuse_cnt = finial_review_refuse_cnt;
    }

    public String getFinial_review_refuse_amt() {
        return finial_review_refuse_amt;
    }

    public void setFinial_review_refuse_amt(String finial_review_refuse_amt) {
        this.finial_review_refuse_amt = finial_review_refuse_amt;
    }

    public String getFinial_review_add_cnt() {
        return finial_review_add_cnt;
    }

    public void setFinial_review_add_cnt(String finial_review_add_cnt) {
        this.finial_review_add_cnt = finial_review_add_cnt;
    }
}
