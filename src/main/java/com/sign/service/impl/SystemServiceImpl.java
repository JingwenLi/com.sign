package com.sign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sign.mapper.SystemMapper;
import com.sign.service.SystemService;


/**
* @Title: SystemServiceImpl
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:25:01
*/
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemMapper systemMapper;
	
}
