package com.ranba.third.sms;

import com.ranba.service.ConfigService;
import com.ranba.util.CodecUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MandaoClient {

	private static final Logger logger = LoggerFactory.getLogger(MandaoClient.class);

	/**
	 * 发送短信
	 * @param mobile 手机号
	 * @param content 内容
	 * @param ext 扩展码
	 * @param stime 定时时间
	 * @param rrid 唯一标识
	 */
	public String mdsmssend(String mobile, String content, String ext, String stime, String rrid,ConfigService configService)  {

		String url = configService.getMsgclient_serviceurl1();
		String url2 = configService.getMsgclient_serviceurl2();//备用地址
		String sn = configService.getMsgclient_sn();// 序列号
		String password = configService.getMsgclient_password();// 密码
        String pwd = CodecUtil.md5Hex(sn + password);

		List<NameValuePair> params = new ArrayList();
		params.add(new BasicNameValuePair("sn", sn));
		params.add(new BasicNameValuePair("pwd", pwd.toUpperCase()));
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("ext", ext));
		params.add(new BasicNameValuePair("stime", stime));
		params.add(new BasicNameValuePair("rrid", rrid));

        String result = postMessage(url, url2, params);

		if(StringUtils.isBlank(result)){
			return "";
		}

		try {

			Document doc = DocumentHelper.parseText(result);
			Element root = doc.getRootElement();

			return (root == null) ? "" : root.getText();

		} catch (DocumentException e) {
			logger.error(e.getMessage(),e);
		}

        return "";
		
	}


	private String postMessage(String url, String url2, List<NameValuePair> params) {

		HttpClient httpclient = HttpClients.custom()
				.setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
        HttpPost httpPost = getHttpPost(url, params);

        HttpResponse response;
        HttpEntity entity;
        String result = null;

        try {
            response = httpclient.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {

            logger.error(e.getMessage(), e);
            logger.error("短信平台主地址失效");

            try {

                httpPost = getHttpPost(url2, params);
                response = httpclient.execute(httpPost);

                entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }

                } catch (IOException e1) {
                    logger.error(e1.getMessage(), e1);
                    logger.error("短信平台副地址失效");
                }

        }finally {

            if (httpPost != null) {
                httpPost.releaseConnection();
            }

        }

        return result;
	}

	private HttpPost getHttpPost(String url, List<NameValuePair> params){

		HttpPost httppost = new HttpPost(url + "/mdSmsSend");

		try {

			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);
			// 设置请求与数据处理的超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
			httppost.setConfig(requestConfig);
			return httppost;

		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
            return null;
		}

	}

}
