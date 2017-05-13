package com.sign.Util;

/**
 * @Title: Constant
 * @Description:
 * @author LJW831
 * @date 2017年4月29日 下午1:25:12
 */
public class Constant {

	public static class Wx {
		/**
		 * JSON数据格式类型
		 */
		public static String JSON_DATA_TYPE = "JSON";

		/**
		 * keg--value参数类型
		 */
		public static String PARAM_DATA_TYPE = "PARAM";

		/**
		 * post请求类型
		 */
		public static String REQUEST_POST = "POST";

		/**
		 * get请求类型
		 */
		public static String REQUEST_GET = "GET";

		/**
		 * 返回的错误代码信息
		 */
		public static String RETURN_ERROR_INFO_CODE = "errcode";

		/**
		 * 返回的错误信息
		 */
		public static String RETURN_ERROR_INFO_MSG = "errmsg";

		/**
		 * WeChat grant type
		 */
		public static String CLIENT_CREDENTIAL = "client_credential";

		/**
		 * WeChat grant type
		 */
		public static String AUTHORIAZTION_Code = "authorization_code";

	}

	public static class Sign {

		/**
		 * Sign publish success
		 */
		public static String SIGN_PUBLISH_SUCCESS = "您的签到 [ {0} ] 已发布成功";

		/**
		 * Sign publish failed Of Location
		 */
		public static String SIGN_PUBLISH_FAILEDOFLOCATION = "您的签到 [ {0} ] 已发布，由于系统无法识别您的位置，对于签到者的位置限制将失效";

		/**
		 * Sign submit success
		 */
		public static String SIGN_SUBMIT_SUCCESS = "您的签到 [ {0} ] 已提交成功";

		/**
		 * Sign submit failed Of Location
		 */
		public static String SIGN_SUBMIT_FAILEDOFLOCATION = "抱歉，您的地理位置不符合签到要求，签到 [ {0} ] 无法提交";
	}

}
