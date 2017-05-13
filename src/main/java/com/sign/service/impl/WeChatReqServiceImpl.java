package com.sign.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.sign.mapper.WeChatMapper;
import com.sign.po.AccessTokenParam;
import com.sign.po.WeChatReq;
import com.sign.po.WeChatUser;
import com.sign.service.SystemService;
import com.sign.service.WeChatReqService;
import com.sign.Util.Constant;
import com.sign.Util.JsapiSign;
import com.sign.Util.WeChatReqHandler;
import com.sign.Util.WeChatReqUtil;
import com.sign.Util.Exception.WeChatReqException;


/**
* @Title: WeChatReqServiceImpl
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:25:06
*/
@SuppressWarnings("rawtypes")
public class WeChatReqServiceImpl implements WeChatReqService {

	@Autowired
	private SystemService systemService;

	@Autowired
	private WeChatReqUtil weChatReqUtil;
	
	@Autowired
	private WeChatMapper weChatMapper;
	
	private static String access_token = null;
	
	private static String jsapi_ticket = null;

	@Override
	public JSONObject doWeinxinReqJson(WeChatReq weChatReq)
			throws WeChatReqException, NumberFormatException {
		// TODO Auto-generated method stub
		String strResult = this.doWeinxinReq(weChatReq);
		JSONObject result = JSONObject.fromObject(strResult);
		Object error = result.get(Constant.Wx.RETURN_ERROR_INFO_CODE);
		if (error != null && Integer.parseInt(error.toString()) != 0) {
			throw new WeChatReqException(result.toString());
		}
		return result;
	}

	@Override
	public String doWeinxinReq(WeChatReq weChatReq) {
		// TODO Auto-generated method stub
		String strReturnInfo = "";
		WeChatReqHandler handler = new WeChatReqHandler();
		strReturnInfo = handler.doRequest(weChatReq);
		return strReturnInfo;
	}

	
	public void initAccessToken() 
	{
		try {
			AccessTokenParam accessTokenParam = weChatMapper
					.getWeChatParamOfAccessToken();
			Map<String, String> param = new HashMap<String, String>();
			param.put("grant_type", Constant.Wx.CLIENT_CREDENTIAL);
			param.put("Appid", accessTokenParam.getAppid());
			param.put("Secret", accessTokenParam.getSecret());			
			WeChatReq weChatReq = weChatReqUtil.REQ_MAPPING.get("access_token");			
			weChatReq.setParam(param);
			JSONObject result = doWeinxinReqJson(weChatReq);
			WeChatReqServiceImpl.access_token = result.getString("access_token");
			System.out.println("access_token:" + WeChatReqServiceImpl.access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void initJsapiTicket() 
	{
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("access_token", WeChatReqServiceImpl.access_token);
			param.put("type","jsapi");			
			WeChatReq weChatReq = weChatReqUtil.REQ_MAPPING.get("jsapi_ticket");			
			weChatReq.setParam(param);
			JSONObject result = doWeinxinReqJson(weChatReq);
			WeChatReqServiceImpl.jsapi_ticket = result.getString("ticket");		
			System.out.println("jsapi_ticket:" + WeChatReqServiceImpl.jsapi_ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getUserInfo(WeChatReq weChatReq) {
		// TODO Auto-generated method stub
		try {
			//通过code换取网页授权access_token
			Map param = new HashMap<String, String>();
			AccessTokenParam accessTokenParam = weChatMapper.getWeChatParamOfAccessToken();
			param.put("appid", accessTokenParam.getAppid());
			param.put("secret", accessTokenParam.getSecret());
			param.put("code", weChatReq.getParam().get("code"));
			param.put("grant_type", Constant.Wx.AUTHORIAZTION_Code);	
			
			weChatReq = weChatReqUtil.REQ_MAPPING.get("weChatLogin_getAccessToken");			
			weChatReq.setParam(param);			
			JSONObject result = doWeinxinReqJson(weChatReq);	
		
			//通过access_token换取用户信息
			param = new HashMap<String, String>();
			param.put("access_token", result.getString("access_token"));
			param.put("openid", result.getString("openid"));
			param.put("lang", "zh_CN");
			
			weChatReq = weChatReqUtil.REQ_MAPPING.get("weChatLogin_getUserInfo");			
			weChatReq.setParam(param);
			result = doWeinxinReqJson(weChatReq);
			Gson gson = new Gson();
			WeChatUser weChatUser = gson.fromJson(result.toString(),WeChatUser.class);	
			weChatUser.setId(UUID.randomUUID().toString());
			
			//保存或更新用户信息
			weChatMapper.saveOrUpdateWeChatUser(weChatUser);
			weChatUser.setId(weChatMapper.getUserIdByOpenId(weChatUser));
			
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("user", weChatUser);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, String> getJsParamOfWeChat(String url)
	{	
		url = "http://1663c6a809.iok.la:44045/com.sign/sign.html";
        Map<String, String> ret = JsapiSign.sign(WeChatReqServiceImpl.jsapi_ticket, url);
		return ret;
	}
	
	@Override
	public String getWeChatLoginUrl() {
		// TODO Auto-generated method stub
		AccessTokenParam accessTokenParam = weChatMapper.getWeChatParamOfAccessToken();
		String url = weChatReqUtil.REQ_MAPPING.get(
				"weChatLogin_getCodeAndState").getUrl();
		url += "appid=" + accessTokenParam.getAppid();
		url += "&redirect_uri=http%3A%2F%2F1663c6a809.iok.la%3A44045%2Fcom.sign%2FloginCallBack&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		return url;
	}
}
