package com.baomidou.springboot.excel;

import com.baomidou.springboot.excel.config.ExcelDefinition;

import java.util.Map;

/**
 * Excel定义接口
 * @author lisuo
 *
 */
public interface ExcelDefinitionReader {
	/**
	 * 获取 ExcelDefinition注册信息
	 * @return
	 */
	Map<String, ExcelDefinition> getRegistry();
}
