package com.sign.mapper;

import com.sign.po.AccessTokenParam;
import com.sign.po.WeChatUser;


/**
* @Title: WeChatMapper
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:16
*/
public interface WeChatMapper {

	boolean saveOrUpdateWeChatUser(WeChatUser weChatUser);
	
	String getUserIdByOpenId(WeChatUser weChatUser);
	
	AccessTokenParam getWeChatParamOfAccessToken();

}