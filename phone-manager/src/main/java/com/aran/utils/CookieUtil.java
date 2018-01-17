package com.aran.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class CookieUtil {

	private final static String COOKIE_USER="user";
	
	private final static String COOKIE_KEY="webkey";
	
	private final static String COOKIE_PATH="/";
	
	/**
	 * 设置cookie
	 * @param response
	 * @param jsonObject
	 * @param cookieMaxAge
	 */
	public static void setCookie(HttpServletResponse response,String cookieValue,int cookieMaxAge){
		String hmac= getHmac(cookieValue, COOKIE_KEY);
		addCookie(response, COOKIE_USER, hmac2CookieValue(cookieValue, hmac), cookieMaxAge, "/");
	}
	
	private static String getHmac(String str,String key){
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append(str).append(key);
		return SHAUtils.SHA256(sBuffer.toString());
	}
	
	private static String hmac2CookieValue(String str,String hmac){
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append(str).append("&&").append(hmac);
		return new String(Base64.encode(sBuffer.toString().getBytes()));
	}
	
	
	/**
	 * 添加cookie
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param maxAge
	 * @param path
	 */
	private static void addCookie(HttpServletResponse response,String cookieName,String cookieValue,int maxAge,String path){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		response.addCookie(cookie);
		return;
	}
	
	/**
	 * 获取cookie值
	 * @param request
	 * @param cookieName
	 * @return
	 */
	private static String getCookieValue(HttpServletRequest request,String cookieName){
		String cookieValue=null;
		Cookie [] cookies=request.getCookies();
		if(cookies != null){
			for(Cookie cookie:cookies){
				if(cookieName.equals(cookie.getName())){
					cookieValue=cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}
	
	/**
	 * 检查cookie
	 * @param request
	 * @return
	 */
	public static String getCookie(HttpServletRequest request){
		String cookieValue=getCookieValue(request, COOKIE_USER);
		if(cookieValue!=null){
			return cookieValue;
		}
		return null;
	}
	
	public static void clearCookie(HttpServletResponse response){
		Cookie cookie=new Cookie(COOKIE_USER, null);
		cookie.setMaxAge(0);
		cookie.setPath(COOKIE_PATH);
		response.addCookie(cookie);
	}
	
	/**
	 *	检测后返回解码后的cookie
	 **/
	public static String checkCookie(String cookieValue){
		byte bytes[]=Base64.decode(cookieValue);
		if(bytes==null||bytes.length==0){
			return null;
		}
		String str="";
		try {
			str=new String(Base64.decode(cookieValue), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("cookie解码失败");
		}
		// 0 cookievalue 1hmac
		String strs[]=str.split("&&");
		String temp=getHmac(strs[0], COOKIE_KEY);
		if(StringUtils.isNotEmpty(temp)&&temp.equals(strs[1])){
			return strs[0];
		}
		return null;
	}
	
}
