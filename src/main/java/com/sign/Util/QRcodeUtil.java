/**
 * @Title: QRcodeUtil.java
 * @Description: 
 * @Copyright: Copyright (c) 2017
 * @author LJW831
 * @date  2017年5月1日 上午11:04:01
 */
package com.sign.Util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @Title: QRcodeUtil
 * @Description:
 * @author LJW831
 * @date 2017年5月1日 上午11:04:01
 */
public class QRcodeUtil {

	public static void createQRcode(String url, HttpServletResponse response) {
		int width = 500;
		int height = 500;
		String format = "gif";
		Hashtable hints = new Hashtable();
		try {
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
					BarcodeFormat.QR_CODE, width, height, hints);
			OutputStream out = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
			out.flush();
			out.close();
		} catch (IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
