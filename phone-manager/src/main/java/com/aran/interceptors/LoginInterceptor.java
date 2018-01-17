package com.aran.interceptors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aran.entity.LoginBean;
import com.aran.server.LoginBeanHolder;
import com.aran.utils.Base64;
import com.aran.utils.CookieUtil;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

@Component
public class LoginInterceptor extends ControllerInterceptorAdapter {

	@Autowired
	private LoginBeanHolder loginBeanHolder;
	
	{
		setName(this.getClass().getSimpleName());
		setPriority(800);
	}
	
	@Override
	protected Object before(Invocation inv) throws Exception {
		System.out.println("进入拦截器");
		//获取cookie值
		String string=CookieUtil.getCookie(inv.getRequest());
		if(StringUtils.isEmpty(string)){
			return "r:/login";
			}
		//解析后的cookieValue
		String str1=CookieUtil.checkCookie(string);
		if(StringUtils.isNotEmpty(str1)){
			JSONObject jsonObject=JSONObject.parseObject(str1);
			LoginBean loginBean = new LoginBean();
			loginBean.setId(jsonObject.getIntValue("id"));
			loginBean.setPassword(jsonObject.getString("password"));
			loginBean.setRealName(jsonObject.getString("realName"));
			loginBeanHolder.setLoginBean(loginBean);
			System.out.println("离开拦截器：true");
			return true;
		}
		System.out.println("离开拦截器：false");
		return "r:"+inv.getRequestPath().getCtxpath()+"/login";
	}
}
