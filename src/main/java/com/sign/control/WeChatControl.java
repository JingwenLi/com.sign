package com.sign.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sign.po.WeChatReq;
import com.sign.service.WeChatReqService;

/**
 * @Title: WeChatControl
 * @Description:
 * @author LJW831
 * @date 2017年4月29日 下午1:23:58
 */
@Controller
public class WeChatControl {

	@Autowired
	private WeChatReqService weChatReqService;

	@RequestMapping(value = "/loginCallBack")
	public String weChatLoginCallBack(String code, String state) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", code);
		WeChatReq weChatReq = new WeChatReq();
		weChatReq.setParam(param);
		weChatReqService.getUserInfo(weChatReq);
		return "redirect:/sign.html";
	}

	@RequestMapping(value = "/login")
	public ModelAndView weChatLogin() {
		return new ModelAndView(new RedirectView(
				weChatReqService.getWeChatLoginUrl()));
	}
	
	@RequestMapping(value = "/getJsParamOfWeChat", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getJsParamOfWeChat(HttpServletRequest request) {	
        return weChatReqService.getJsParamOfWeChat(request.getRequestURL().toString());  
	}
}