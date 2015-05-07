package com.mlm.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.mlm.service.AdministratorService;

@Before(IocInterceptor.class)
public class AdministratorController extends Controller{
	
	@Inject.BY_NAME
	private AdministratorService adminService;

}
