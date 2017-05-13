package com.sign.po;

import java.util.List;


/**
* @Title: WeChatUser
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:24:34
*/
public class WeChatUser {

	private String id;
	
	private int count;
	
	private String openid;
	
	private String nickname;
	
	private String sex;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String headimgurl;
	
	private String unionid;
	
	private Object privilege;
	
	private String signTime;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Object getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Object privilege) {
		this.privilege = privilege;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	
}
