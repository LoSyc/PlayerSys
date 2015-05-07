package com.mlm.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.mlm.model.Athlete;
import com.mlm.model.AthleteView;
import com.mlm.model.Judge;
import com.mlm.model.UnitView;
import com.mlm.service.AthleteService;

@Before(IocInterceptor.class)
public class AthleteController extends Controller{
	
	@Inject.BY_NAME
	private AthleteService athleteService;
	
	@ActionKey("/findProfile")
	public void findProfile(){
		Athlete athlete = athleteService.findProfile(getPara("id"));
		UnitView uv = athleteService.getUnitView(athlete.getStr("unit_id"));
		setAttr("unitview", uv);
		render("profile.html");
	}
	
	@ActionKey("/findProject")
	public void findProject(){
		List<AthleteView> av = athleteService.getAthleteView(getPara("id"));
		List<Judge> judges = athleteService.getJudges(av);
		setAttr("judges", judges);
		setAttr("athleteview", av);
		render("athleteproject.html");
	}
	
	@ActionKey("/findSource")
	public void findSource(){
		List<AthleteView> av = athleteService.getAthleteView(getPara("id"));
		setAttr("athleteview", av);
		render("athletesource.html");
	}

}
