package com.baomidou.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.common.util.DateUtil;
import com.baomidou.framework.controller.SuperController;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springboot.excel.ExcelContext;
import com.baomidou.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * <p>
 * 基础控制器
 * </p>
 * 
 * @author CuiCan
 * @Date 2017-06-10
 */
public class BaseController extends SuperController implements HandlerInterceptor {

	@Autowired
	protected IUserService userService;

	@Autowired
	protected ExcelContext excelContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		/*
		 * 方法调用后调用该方法，返回数据给请求页
		 */
		if (isLegalView(modelAndView)) {
			modelAndView.addObject("currentUser", userService.selectById(getCurrentUserId()));
//			modelAndView.addObject("menuList", privilegeService.selectMenuVOByUserId(getCurrentUserId()));
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 判断是否为合法的视图地址
	 * <p>
	 * 
	 * @param modelAndView
	 *            spring 视图对象
	 * @return boolean
	 */
	protected boolean isLegalView(ModelAndView modelAndView) {
		boolean legal = false;
		if (modelAndView != null) {
			String viewUrl = modelAndView.getViewName();
			if (viewUrl != null && viewUrl.contains("redirect:")) {
				legal = false;
			} else {
				legal = true;
			}
		}
		return legal;
	}

	/**
	 * <p>
	 * 转换为 bootstrap-table 需要的分页格式 JSON
	 * </p>
	 * 
	 * @param page
	 *            分页对象
	 * @return
	 */
	protected String jsonPage(Page<?> page) {
		JSONObject jo = new JSONObject();
		jo.put("total", page.getTotal());
		jo.put("rows", page.getRecords());
		return toJson(jo);
	}

	@Override
	protected <T> Page<T> getPage(int size) {
		int _size = size, _index = 1;
		if (request.getParameter("_size") != null) {
			_size = Integer.parseInt(request.getParameter("_size"));
		}
		if (request.getParameter("_index") != null) {
			int _offset = Integer.parseInt(request.getParameter("_index"));
			_index = _offset / _size + 1;
		}
		return new Page<T>(_index, _size);
	}

	protected String booleanToString(boolean rlt) {
		return rlt ? "true" : "false";
	}

	/**
	 * <p>
	 * 上传文件存放目录
	 * </p>
	 */
	protected static String getSaveDir() {
		StringBuffer filePath = new StringBuffer("/opt/upload");
		filePath.append(File.separator);
		filePath.append(DateUtil.format(new Date(), "yyyy-MM"));
		filePath.append(File.separator);
		File file = new File(filePath.toString());
		if ( !file.exists() ) {
			file.mkdirs();
		}
		return file.getPath();
	}

}
