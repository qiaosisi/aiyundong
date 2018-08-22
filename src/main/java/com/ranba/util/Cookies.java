package com.ranba.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Cookies {
	private static final Logger logger = LoggerFactory.getLogger(Cookies.class);
	// 写Cookie
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {

		Cookie cookie;

		try {

			cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			//cookie.setSecure(true);

			if (maxAge > 0){
				cookie.setMaxAge(maxAge * 60 * 60 * 24);
				cookie.setPath("/");
			}else{
				cookie.setMaxAge(0);
			}

			response.addCookie(cookie);

		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}

	}

	// 写Cookie
	public static void addCookieSetHours(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {

		Cookie cookie;

		try {

			cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			//cookie.setSecure(true);

			if (maxAge > 0){
				cookie.setMaxAge(maxAge * 60 * 60 );
				cookie.setPath("/");
			}else{
				cookie.setMaxAge(0);
			}

			response.addCookie(cookie);

		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}

	}

	// 读指定Cookie
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			try {
				return URLDecoder.decode(cookie.getValue(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return null;
	}

	// 读所有cookies
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {

		if (cookies == null){
			return defaultValue;
		}

		Cookie cookie;
		for (int i = 0; i < cookies.length; i++) {

			cookie = cookies[i];

			if (cookieName.equals(cookie.getName())){
				return cookie.getValue();
			}
		}

		return defaultValue;
	}

}
