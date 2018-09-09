package com.ranba.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;


public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final int TIME_OUT = 1000 * 20;

    private static final int TIME_OUT_DOWNLOAD = 1000 * 60 * 5;


    private CloseableHttpClient httpClient;

    public HttpClientUtil() {
        httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    }

    public HttpClientUtil(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    //GET请求
    public Map<Object, Object> get(String url) {

        Map<Object, Object> map = null;
        HttpGet getMethod = new HttpGet(url);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT).build();
        getMethod.setConfig(requestConfig);

        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(getMethod);

            HttpEntity httpentity = response.getEntity();
            if (httpentity != null) {
                ObjectMapper mapper = new ObjectMapper();
                map = mapper.readValue(EntityUtils.toString(httpentity, "UTF-8"), Map.class);
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                logger.warn("httpClient.close失败");
            }
        }

        return map;
    }

    //post请求
    public String post(String url, Map<String, String> params) {

        HttpPost httppost = new HttpPost(url);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT).build();
        httppost.setConfig(requestConfig);

        //放入请求参数
        List<NameValuePair> list = new ArrayList<>();
        Iterator<?> iterator = params.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }

        CloseableHttpResponse response = null;
        String result = null;

        try {

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httppost.setEntity(entity);

            response = httpClient.execute(httppost);
            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                result = EntityUtils.toString(httpentity, "UTF-8");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                logger.warn("httpClient.close失败");
            }
        }

        return result;
    }

    public String postRequest(String url, String Str) {

        HttpPost httpPost = new HttpPost(url);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT).build();
        httpPost.setConfig(requestConfig);

        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(Str, "UTF-8"));

        CloseableHttpResponse response = null;
        String result = null;

        try {

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                logger.warn("httpClient.close失败");
            }
        }
        logger.info("\n请求数据:"+Str+"\n请求结果:"+result);

        return result;
    }


    //得到微信返回的Xml文件
    public Map<String, String> postRequestXml(String url, String Str) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();

        HttpPost httpPost = new HttpPost(url);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT).build();
        httpPost.setConfig(requestConfig);

        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(Str, "UTF-8"));

        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);
            InputStream inputStream = response.getEntity().getContent();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
            // 释放资源
            inputStream.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (DocumentException d) {
            logger.error(d.getMessage(), d);
        }

        return map;
    }

    // 下载远程文件到指定目录
    public int downloadRemoteFile(String urlStr, String path) {

        HttpGet getReq = new HttpGet(urlStr);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT_DOWNLOAD).setConnectTimeout(TIME_OUT_DOWNLOAD).setConnectionRequestTimeout(TIME_OUT_DOWNLOAD).build();
        getReq.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        int result = 0;

        try {

            response = httpClient.execute(getReq);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return 0;
            }

            Header contentHeader = response.getFirstHeader("Content-Disposition");
            if (contentHeader == null || contentHeader.getValue() == null) {
                return 0;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return 0;
            }

            //获取文件名称
            String contentDes = contentHeader.getValue();
            String fileName = contentDes.substring(contentDes.indexOf("filename") + 10, contentDes.length() - 1);
            if (fileName == null) {
                return 0;
            }

            //拷贝文件到指定目录
            byte[] apply_file_doc = EntityUtils.toByteArray(entity);
            FileCopyUtils.copy(apply_file_doc, new File(path + fileName));

            return 1;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                logger.warn("httpClient.close失败");
            }
        }

        return result;
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /***
     * 服务器是否是本机
     * @param request
     * @return
     */
    public static boolean isLocalIp(HttpServletRequest request){
        String ip=getClientIpAddr(request);
        return ip.equals("127.0.0.1")||ip.equals("localhost")||ip.equals("0:0:0:0:0:0:0:1");
    }

    // postToken请求
    public String postToken(String url, Map<String, String> params) {

        HttpPost httppost = new HttpPost(url);

        // 设置请求与数据处理的超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT)
                .setConnectionRequestTimeout(TIME_OUT)
                .build();
        httppost.setConfig(requestConfig);
        httppost.addHeader("Authorization", getHeader());

        //放入请求参数
        List<NameValuePair> list = new ArrayList<>();
        Iterator<?> iterator = params.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }

        CloseableHttpResponse response = null;
        String result = null;

        try {

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httppost.setEntity(entity);

            response = httpClient.execute(httppost);
            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                result = EntityUtils.toString(httpentity, "UTF-8");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                logger.warn("httpClient.close失败");
            }
        }

        return result;
    }

    /**
     * 构造Basic Auth认证头信息
     *
     * @return
     */
    private String getHeader() {
        String auth = "devglan-client" + ":" + "devglan-secret";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }

}
