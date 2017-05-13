package com.sign.Util.Exception;


/**
* @Title: WeChatReqException
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:25:39
*/
public class WeChatReqException extends Exception {

	private static final long serialVersionUID = 1L;

	public WeChatReqException(String message){
		super(message);
	}
	
	public WeChatReqException(Throwable cause)
	{
		super(cause);
	}
	
	public WeChatReqException(String message,Throwable cause)
	{
		super(message,cause);
	}
}