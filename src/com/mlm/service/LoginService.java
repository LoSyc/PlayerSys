package com.mlm.service;


import com.mlm.model.Login;
import com.mlm.utils.MD5;

public class LoginService {
	private Login lo;
	
	public Login getLogin(){
		return lo;
	}
	
	public boolean checkUserId(String id){
		lo = Login.login.findById(id);
		if(null != lo){
			return true;
		}
		return false;
	}
	
	public boolean checkPwd(String pwd){
		if(null != lo){
			if(lo.getStr("password").equals(MD5.md5Value(pwd))){
				return true;
			}
		}
		return false;
	}
	
	
}
