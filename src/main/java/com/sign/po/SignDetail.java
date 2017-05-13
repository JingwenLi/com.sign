/**
* @Title: sign.java
* @Description: 
* @Copyright: Copyright (c) 2017
* @author LJW831
* @date  2017年4月30日 下午3:20:55
*/
package com.sign.po;

import java.util.List;

/**
 * @Title: sign
 * @Description: 
 * @author  LJW831
 * @date  2017年4月30日 下午3:20:55
 */
@SuppressWarnings("rawtypes")
public class SignDetail {

	private int id;
	
	private String userId;
	
	private String userName;
	
	private String name;
	
	private int locationRange;
	
	private int effectiveTime;
	
	private String signTime;
	
	private String publishTime;
	
	private String[] tags;
	
	private String[] defines;
	
	private double longitude;
	
	private double latitude;
	
	private String messageContent;
	
	private List<SignInfo> signInfos;
	
	public int getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(int date) {
		this.effectiveTime = date;
	}

	public int getLocationRange() {
		return locationRange;
	}

	public void setLocationRange(int locationRange) {
		this.locationRange = locationRange;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getDefines() {
		return defines;
	}

	public void setDefines(String[] defines) {
		this.defines = defines;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public List<SignInfo> getSignInfos() {
		return signInfos;
	}

	public void setSignInfos(List<SignInfo> signInfos) {
		this.signInfos = signInfos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
