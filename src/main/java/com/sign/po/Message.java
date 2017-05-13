/**
* @Title: Message.java
* @Description: 
* @Copyright: Copyright (c) 2017
* @author LJW831
* @date  2017年5月8日 上午12:00:19
*/
package com.sign.po;

/**
 * @Title: Message
 * @Description: 
 * @author  LJW831
 * @date  2017年5月8日 上午12:00:19
 */
public class Message {
	/** 
	 * Message.java 
	 * @author 作者 E-mail: 
	 * @version 创建时间：2017年5月8日 上午12:00:19 
	 * $
	 */
	
	//1: read 
	//0: not read
	private int isRead;
	
	private int id;
	
	private int signId;
	
	private String userId;
		
	private String messageContent;
	
	private String messageKey;

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public int getSignId() {
		return signId;
	}

	public void setSignId(int signId) {
		this.signId = signId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
