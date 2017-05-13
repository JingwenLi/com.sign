package com.sign.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.sign.po.WeChatReq;
import com.sign.Util.Exception.WeChatReqException;


/**
* @Title: WeChatReqService
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:51
*/
public interface WeChatReqService {

	public JSONObject doWeinxinReqJson(WeChatReq weChatReq) throws WeChatReqException;
	
	public String doWeinxinReq(WeChatReq weChatReq);
	
	public void getUserInfo(WeChatReq weChatReq);
	
	public String getWeChatLoginUrl();

	public Map<String, String> getJsParamOfWeChat(String url);
	
	public void initAccessToken();
	
	public void initJsapiTicket();
	
}
