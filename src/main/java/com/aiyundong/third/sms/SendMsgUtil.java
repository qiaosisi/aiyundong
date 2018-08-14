package com.aiyundong.third.sms;

import com.aiyundong.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SendMsgUtil {

	private static final Logger logger = LoggerFactory.getLogger(SendMsgUtil.class);
	
	public static boolean SendMsg(String mobiles, String content, ConfigService configService){
		return SendM(mobiles,content,"",configService);
	}



	public static boolean SendM(String mobiles,String content,String ext,ConfigService configService){

		MandaoClient client = new MandaoClient();
		String result_mt = client.mdsmssend(mobiles, content, ext, "", "",configService);

		if(result_mt.startsWith("-")||result_mt.equals("")){
			//发送短信，如果是以负号开头就是发送失败。
			//request.getSession(true).setAttribute("authentiCode", content);
			logger.info("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return false;
		}else{
			//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
			logger.info("发送成功，返回值为："+result_mt);
			return true;
		}

	}

}