package com.sign.po;

import java.util.Map;


/**
* @Title: WeChatReq
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:29
*/
@SuppressWarnings("rawtypes")
public class WeChatReq {

	private String accessToken;

	private String key;

	private String url;

	private String method;

	private String datatype;

	private Map param;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}
}
