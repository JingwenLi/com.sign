package com.sign.Util;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sign.po.WeChatReq;


/**
* @Title: WeChatReqHandler
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:25:27
*/
public class WeChatReqHandler {

	private static Logger logger = Logger.getLogger(WeChatReqHandler.class);

	public String doRequest(WeChatReq weChatReq) {
		// TODO Auto-generated method stub
		String strReturnInfo = "";
		String reqUrl = weChatReq.getUrl();
		String method = weChatReq.getMethod();
		String datatype = weChatReq.getDatatype();
		if (Constant.Wx.JSON_DATA_TYPE.equalsIgnoreCase(datatype)) {			
			Gson gson = new Gson();
			String jsonData = gson.toJson((weChatReq.getParam()));
			strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, weChatReq.getParam(),
					jsonData);
		} else {
			if (Constant.Wx.REQUEST_GET.equalsIgnoreCase(method)) {
				strReturnInfo = HttpRequestProxy.doGet(reqUrl, weChatReq.getParam(),
						"UTF-8");
			} else {
				strReturnInfo = HttpRequestProxy.doPost(reqUrl, weChatReq.getParam(),
						"UTF-8");
			}
		}
		return strReturnInfo;
	}

}
