package com.sign.mapper;

import java.util.List;

import com.sign.po.Message;
import com.sign.po.SignDetail;

/**
* @Title: SignMapper
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:05
*/
public interface SignMapper {
	
	int publishSign(SignDetail signDetail);
	
	boolean submitSign(SignDetail signDetail);
	
	boolean saveMessage(Message message);
	
	boolean setSignInfo(SignDetail signDetail);
	
	boolean setSignInfoValue(SignDetail signDetail);
	
	SignDetail getSignBySignId(SignDetail signDetail);
		
	List<SignDetail> getMySign(SignDetail signDetail);
	
	List<Message> getMessageByUserId(SignDetail signDetail);
		
	boolean readMessage(int id);
	
	List<SignDetail> getSignUserInfo(int id);
	
}
