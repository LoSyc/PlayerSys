package com.mlm.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.mlm.model.Athlete;
import com.mlm.model.Judge;
import com.mlm.model.Login;
import com.mlm.model.Unit;
import com.mlm.service.LoginService;

@Before(IocInterceptor.class)
public class LoginController extends Controller{
	
	@Inject.BY_NAME
	private LoginService loginService;
	
	@ActionKey("/login")
	public void login(){
		render("login.html");
	}
	
	public void index(){
		render("index.html");
	}
	
	
	@ActionKey("/toMain")
	public void toMain(){
		Login lo = getModel(Login.class,"login");
		if(loginService.checkUserId(lo.getStr("user_id"))&&loginService.checkPwd(lo.getStr("password"))){
			Login login = loginService.getLogin();
			setAttr("login", login);
			switch (login.getStr("authority")) {
				case "运动员":
					Athlete athlete = Athlete.athlete.findById(login.getStr("user_id"));
					setAttr("athlete", athlete);
					break;
	
				case "参赛单位" :
					Unit unit = Unit.unit.findById(login.getStr("user_id"));
					setAttr("unit", unit);
					break;
					
				case "裁判" :
					Judge judge = Judge.judge.findById(login.getStr("user_id"));
					setAttr("judge", judge);
					break;
			}
			index();
			return ;
		}
		login();
	}
	
	
	
	

}
