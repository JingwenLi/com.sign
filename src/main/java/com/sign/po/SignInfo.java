/**
* @Title: SignDefineOrTag.java
* @Description: 
* @Copyright: Copyright (c) 2017
* @author LJW831
* @date  2017年5月3日 下午10:00:43
*/
package com.sign.po;

/**
 * @Title: SignDefineOrTag
 * @Description: 
 * @author  LJW831
 * @date  2017年5月3日 下午10:00:43
 */
public class SignInfo {
	
	/** 
	 * SignDefineOrTag.java 
	 * @author 作者 E-mail: 
	 * @version 创建时间：2017年5月3日 下午10:00:43 
	 * $
	 */	
	private int id;
	
	private String value;
	
	private String name;
	
	//1:define 2:tag
	private int flag;
	
	private int countInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountInfo() {
		return countInfo;
	}

	public void setCountInfo(int countInfo) {
		this.countInfo = countInfo;
	}
}
