package com.sign.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.WriterException;
import com.sign.Util.Constant;
import com.sign.Util.LocationUtil;
import com.sign.Util.QRcodeUtil;
import com.sign.Util.WeChatReqUtil;
import com.sign.mapper.SignMapper;
import com.sign.po.Message;
import com.sign.po.SignDetail;
import com.sign.po.WeChatUser;
import com.sign.service.SignService;

/**
 * @Title: SignServiceImpl
 * @Description:
 * @author LJW831
 * @date 2017年4月29日 下午1:24:56
 */
public class SignServiceImpl implements SignService {

	@Autowired
	private SignMapper signMapper;

	/**
	 * @Title: saveSignDetail
	 * @Description:
	 * @param signDetail
	 * @return
	 * @see com.sign.service.SignService#saveSignDetail(com.sign.po.SignDetail)
	 */
	public String publishSign(SignDetail signDetail) {
		// TODO Auto-generated method stub
		String messageContant = "";
		try {
			 //For official
			 Subject subject = SecurityUtils.getSubject();
			 WeChatUser weChatUser = (WeChatUser)
			 subject.getSession().getAttribute("user");
			 signDetail.setUserId(weChatUser.getId());

			// For test
			//signDetail.setUserId("Test1s");

			signMapper.publishSign(signDetail);

			if (signDetail.getSignInfos() != null
					&& signDetail.getSignInfos().size() > 0) {
				signMapper.setSignInfo(signDetail);
			}

			if (signDetail.getLongitude() == 0.0
					&& signDetail.getLatitude() == 0.0) {
				messageContant = saveMessage(
						Constant.Sign.SIGN_PUBLISH_FAILEDOFLOCATION, signDetail);
			} else {
				messageContant = saveMessage(
						Constant.Sign.SIGN_PUBLISH_SUCCESS, signDetail);
			}
		} catch (Exception e) {

		}
		return messageContant;
	}

	/**
	 * @Title: getSignDetail
	 * @Description:
	 * @param signDetail
	 * @return
	 * @see com.sign.service.SignService#getSignDetail(com.sign.po.SignDetail)
	 */
	@Override
	public SignDetail getSignDetail(SignDetail signDetail) {
		// TODO Auto-generated method stub
		try {
			signDetail = signMapper.getSignBySignId(signDetail);
		} catch (Exception e) {

		}
		return signDetail;
	}

	/**
	 * @Title: submitSign
	 * @Description:
	 * @param signDetail
	 * @return
	 * @see com.sign.service.SignService#submitSign(com.sign.po.SignDetail)
	 */
	@Override
	public String submitSign(SignDetail signDetail) {
		// TODO Auto-generated method stub
		String messageContent = "";
		try {
			// For official
			Subject subject = SecurityUtils.getSubject();
			WeChatUser weChatUser = (WeChatUser)
			subject.getSession().getAttribute("user");
			signDetail.setUserId(weChatUser.getId());

			// For test
			// SignDetail.setUserId("Test1s");

			// Submit Sign
			signMapper.submitSign(signDetail);

			// Submit signInfos
			if (signDetail.getSignInfos() != null
					&& signDetail.getSignInfos().size() > 0) {
				signMapper.setSignInfoValue(signDetail);
			}

			// Get distance;
			SignDetail publishedSignDetail = getSignDetail(signDetail);
			double distance = LocationUtil.LantitudeLongitudeDist(
					publishedSignDetail.getLongitude(),
					publishedSignDetail.getLatitude(),
					signDetail.getLongitude(), signDetail.getLatitude());

			// Set messageContent
			if (signDetail.getLongitude() == 0.0
					&& signDetail.getLatitude() == 0.0) {
				messageContent = saveMessage(
						Constant.Sign.SIGN_SUBMIT_FAILEDOFLOCATION, signDetail);
			} else {
				if (distance > signDetail.getLocationRange()) {
					messageContent = saveMessage(
							Constant.Sign.SIGN_SUBMIT_FAILEDOFLOCATION,
							signDetail);
				} else {
					messageContent = saveMessage(
							Constant.Sign.SIGN_SUBMIT_SUCCESS, signDetail);
				}
			}

		} catch (Exception e) {

		}
		return messageContent;
	}

	/**
	 * @Title: getMySign
	 * @Description:
	 * @return
	 * @see com.sign.service.SignService#getMySign()
	 */
	@Override
	public List<SignDetail> getMySign() {
		// TODO Auto-generated method stub
		List<SignDetail> results = null;
		try {
			// For official
			SignDetail signDetail = new SignDetail();
			Subject subject = SecurityUtils.getSubject();
			WeChatUser weChatUser = (WeChatUser)
			subject.getSession().getAttribute("user");
			signDetail.setUserId(weChatUser.getId());

			// For test
			// SignDetail signDetail = new SignDetail();
			// signDetail.setUserId("Test1s");
			
			results = signMapper.getMySign(signDetail);
		} catch (Exception e) {

		}
		return results;
	}

	/**
	 * @Title: getMessage
	 * @Description:
	 * @return
	 * @see com.sign.service.SignService#getMessage()
	 */
	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		List<Message> results = null;
		try {
			// For official
			// Subject subject = SecurityUtils.getSubject();
			// WeChatUser weChatUser = (WeChatUser)
			// subject.getSession().getAttribute("user");
			// signDetail.setUserId(weChatUser.getId());

			// For test			
			SignDetail signDetail = new SignDetail();
			signDetail.setUserId("ljwTest1s");
			
			results = signMapper.getMessageByUserId(signDetail);
		} catch (Exception e) {

		}
		return results;
	}

	/**
	 * 
	 * @Title: saveMessage
	 * @Description:
	 * @param messageKey
	 * @param signDetail
	 * @return
	 */
	@Override
	public String saveMessage(String messageTemp, SignDetail signDetail) {
		// TODO Auto-generated method stub
		Message message = new Message();
		try {
			message.setSignId(signDetail.getId());
			message.setUserId(signDetail.getUserId());
			// set MessageContent
			MessageFormat form = new MessageFormat(messageTemp);
			String[] messageArgs = new String[] { signDetail.getName() };
			message.setMessageContent(form.format(messageArgs));
			// save Message
			signMapper.saveMessage(message);
		} catch (Exception e) {

		}
		return message.getMessageContent();
	}

	/** 
	* @Title: readMessage
	* @Description: 
	* @param id
	* @return
	* @see com.sign.service.SignService#readMessage(int)
	*/
	@Override
	public boolean readMessage(int id) {
		// TODO Auto-generated method stub
		return signMapper.readMessage(id);
	}

	/** 
	* @Title: getSignUserInfo
	* @Description: 
	* @param signDetail
	* @return
	* @see com.sign.service.SignService#getSignUserInfo(com.sign.po.SignDetail)
	*/
	@Override
	public List<SignDetail> getSignUserInfo(int id)  {
		// TODO Auto-generated method stub
		return signMapper.getSignUserInfo(id);
	}
}
