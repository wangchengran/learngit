package com.aran.server;

import org.springframework.stereotype.Component;

import com.aran.entity.LoginBean;

@Component
public class LoginBeanHolder {
	
	private static ThreadLocal<LoginBean> adminLocal =new ThreadLocal<LoginBean>();

	public LoginBean getLoginBean() {
		return adminLocal.get();
	}
	
	public void setLoginBean(LoginBean loginBean) {
		adminLocal.set(loginBean);
	}
	
	public void clean(){
		adminLocal.remove();
	}
}
