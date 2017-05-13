package com.sign.po;


/**
* @Title: AccessTokenParam
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:23
*/
public class AccessTokenParam extends WeChatReq{

	/**
	 * 第三方用户唯一凭证
	 */
	private String appid;
	
	/**
	 * 第三方用户唯一凭证密钥，即appsecret
	 */
	private String secret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	 
}

