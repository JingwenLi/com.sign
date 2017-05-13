package com.sign.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;

import com.sign.po.WeChatReq;


/**
* @Title: WeChatReqUtil
* @Description: 
* @author  LJW831
* @date  2017年4月29日 下午1:25:32
*/
public class WeChatReqUtil {

	private String configFilePath;

	public Map<String, WeChatReq> REQ_MAPPING;
	
	private static Logger logger = Logger.getLogger(WeChatReqUtil.class);

	public WeChatReqUtil(String configFilePath) {
		// TODO Set request mapping according to Wechat config File
		this.configFilePath = configFilePath;
		try {
			REQ_MAPPING = new HashMap<String, WeChatReq>();
			InputStream is = new FileInputStream(
					ResourceUtils.getFile(configFilePath));
			SAXBuilder xmlBuilder = new SAXBuilder();
			Document doc = xmlBuilder.build(is);
			Element objRoot = doc.getRootElement();
			List<Element> listMapping = objRoot.getChildren("req");
			WeChatReq objConfig = null;
			for (Element mapping : listMapping) {
				objConfig = new WeChatReq();
				objConfig.setKey(mapping.getAttribute("key").getValue());
				objConfig.setMethod(mapping.getAttribute("method").getValue());
				objConfig.setUrl(mapping.getAttribute("url").getValue());
				objConfig.setDatatype(mapping.getAttribute("datatype")
						.getValue());
				REQ_MAPPING.put(objConfig.getKey(), objConfig);
			}
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public String getConfigFilePath() {
		return configFilePath;
	}

	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}

}
