package com.aran.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderEventListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aran.entity.LoginBean;
import com.aran.entity.Page;
import com.aran.entity.Phone;
import com.aran.entity.User;
import com.aran.server.LoginBeanHolder;
import com.aran.service.PhoneService;
import com.aran.service.UserService;
import com.aran.utils.CookieUtil;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Intercepted;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;


@Component
@Path("/")
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PhoneService phoneService;
	@Autowired
	private LoginBeanHolder loginBeanHolder;
	
	@Get("login")
	@Post("login")
	@Intercepted(deny={"LoginInterceptor"})
	public String login(){
		return "/login.jsp";
	}
	
	@Post("register")
	@Get("register")
	public String register(@Param("username")String username
			,@Param("realname")String realname
			,@Param("password")String password){
		User user = new User();
		user.setUserName(username);
		user.setRealName(realname);
		user.setPassword(password);
		userService.addUser(user);
		System.out.println("注册成功");
		return "/login.jsp";
	}
	
	@Post("index")
	@Get("index")
	@Intercepted(deny={"LoginInterceptor"})
	public String index(@Param("username")String username,
			@Param("password")String password,
			Invocation inv){
		User user =null; 
		if(StringUtils.isNotEmpty(username)){
			user = userService.checkUser(username);
		}
		if(user == null || !user.getPassword().equals(password) ){
			//验证不通过
			inv.addModel("message","账号或密码不正确");
			return "/login.jsp";
		}else{
			//密码验证通过
			String cookieValue=JSON.toJSONString(user);
			CookieUtil.setCookie(inv.getResponse(),cookieValue, 60*60*24);
			inv.addModel("user",user);
		}
		return "r:/page?num=0&size=5";
	}
	
	@Post("exit")
	@Get("exit")
	public String exit(Invocation inv){
		CookieUtil.clearCookie(inv.getResponse());
		return "r:/login";
	}
	
	@Post("page")
	@Get("page")
	public String pageToIndex(Invocation inv,@Param("num") int num
			,@Param("size") int size
			){
		//解码后的cookievalue
		String cookieValue=CookieUtil.checkCookie(CookieUtil.getCookie(inv.getRequest()));
		if(StringUtils.isNotEmpty(cookieValue)){
			JSONObject jsonObject=JSONObject.parseObject(cookieValue);
			inv.addModel("id",jsonObject.getInteger("id"));
			inv.addModel("username",jsonObject.getString("realName"));
		}else{
			System.out.println("cookie失效");
			return "r:/login.jsp";
		}
//		Page<List<Phone>> page =new Page<List<Phone>>(pageCount, total, size, phone)
		int count = phoneService.count();//total
		int maxNum =(count%size==0)?(count/size):(count/size+1);//最大页码
		Page<Phone> page=phoneService.getAllByPage(num, size, count, maxNum);//已经分页的数据
		inv.addModel("page",page);
		return "/phonebook.jsp";
	}
	
	@Post("insert")
	@Get("insert")
	public String insertPhone(@Param("name") String name
			,@Param("phonenumber") int phonenumber
			,@Param("age") int age
			,@Param("company") String company
			,@Param("email") String email
			,Invocation inv){
		try {
			inv.getRequest().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		Phone phone =new Phone();
		phone.setAge(age);
		phone.setCompany(company);
		phone.setEmail(email);
		phone.setName(name);
		phone.setPhonenumber(phonenumber);
		phoneService.addPhone(phone);;
//		if(temp==0){
//			System.out.println("添加失败");
//		}
		return "r:/page?num=0&size=5";
	}
	
	@Post("delete")
	@Get("delete")
	public String deletePhone(@Param("ids")String ids ){
		phoneService.deletePhoneByIds(ids);
		return "r:page?num=0&size=5";
	}
	@Get("search")
	public String searchPhone(@Param("text") String text){
		if(StringUtils.isNotEmpty(text)){
			List<Phone> list= phoneService.getLikePhone(text);
			if(list.size()>=5)list=list.subList(0,5);
			String json= JSONArray.toJSONString(list);
			return "@"+json+"";
		}else return null;
	}
}
