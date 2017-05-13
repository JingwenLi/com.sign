package com.sign.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sign.Util.QRcodeUtil;
import com.sign.po.Message;
import com.sign.po.SignDetail;
import com.sign.service.SignService;

/**
 * @Title: SignControl
 * @Description:
 * @author LJW831
 * @date 2017年4月29日 下午1:23:53
 */
@Controller
public class SignControl {

	@Autowired
	private SignService signService;

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	@ResponseBody
	public String publishSign(@RequestBody SignDetail signDetail,
			HttpServletResponse response) throws ParseException {
		signDetail.setMessageContent(signService.publishSign(signDetail));
		return JSONObject.fromObject(signDetail).toString();
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submitSign(@RequestBody SignDetail signDetail,
			HttpServletResponse response) throws ParseException {
		signDetail.setMessageContent(signService.submitSign(signDetail));
		return JSONObject.fromObject(signDetail).toString();
	}

	@RequestMapping(value = "/getQRcode")
	public void getQRcode(int signId, HttpServletResponse response) {
		QRcodeUtil.createQRcode(
				"http://1663c6a809.iok.la:44045/com.sign/getSignDetail?signId="
						+ signId, response);
	}

	@RequestMapping(value = "/getSignDetail")
	@ResponseBody
	public ModelAndView getSignDetail(String signId,
			HttpServletResponse response) {
		SignDetail signDetail = new SignDetail();
		signDetail.setId(Integer.parseInt(signId));
		ModelAndView mv = new ModelAndView();
		mv.addObject("result",
				JSONArray.fromObject(signService.getSignDetail(signDetail)));
		mv.setViewName("/sign_submit.html");
		return mv;
	}

	@RequestMapping(value = "/getMySign")
	@ResponseBody
	public List<SignDetail> getMySign() {
		List<SignDetail> signs = new ArrayList<SignDetail>();
		signs = signService.getMySign();
		return signs;
	}

	@RequestMapping(value = "/getMyMessage")
	@ResponseBody
	public List<Message> getMyMessage() {
		List<Message> messages = new ArrayList<Message>();
		messages = signService.getMessage();
		return messages;
	}

	@RequestMapping(value = "/readMessage")
	@ResponseBody
	public String readMessage(int id) {
		signService.readMessage(id);
		return "{\"res\":\"success\"}";
	}

	@RequestMapping(value = "/getSignUserInfo")
	@ResponseBody
	public List<SignDetail> getSignUserInfo(int id) {
		List<SignDetail> signUsers = new ArrayList<SignDetail>();
		signUsers = signService.getSignUserInfo(id);
		return signUsers;
	}
}