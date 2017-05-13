/**
 * @Title: WeChatTriggerJob.java
 * @Description: 
 * @Copyright: Copyright (c) 2017
 * @author LJW831
 * @date  2017年5月13日 下午2:03:53
 */
package com.sign.Util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoaderListener;

import com.sign.service.WeChatReqService;

/**
 * @Title: WeChatTriggerJob
 * @Description:
 * @author LJW831
 * @date 2017年5月13日 下午2:03:53
 */
public class WeChatTriggerJob extends QuartzJobBean {

	/**
	 * @Title: executeInternal
	 * @Description:
	 * @param arg0
	 * @throws JobExecutionException
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		WeChatReqService weChatReqService = (WeChatReqService) ContextLoaderListener
				.getCurrentWebApplicationContext().getBean("weChatReqService");
		weChatReqService.initAccessToken();
		weChatReqService.initJsapiTicket();
	}
}
