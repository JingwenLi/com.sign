package com.sign.service;

import java.util.List;

import com.sign.po.Message;
import com.sign.po.SignDetail;

/**
* @Title: SignService
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:40
*/
public interface SignService {

	public String publishSign(SignDetail signDetail);
	
	public String submitSign(SignDetail signDetail);
	
	public String saveMessage(String messageKey,SignDetail signDetail);
	
	public SignDetail getSignDetail(SignDetail signDetail);
	
	public List<SignDetail> getMySign();
	
	public List<Message> getMessage();
	
	public boolean readMessage(int id);
	
	public List<SignDetail> getSignUserInfo(int id);
	
}
