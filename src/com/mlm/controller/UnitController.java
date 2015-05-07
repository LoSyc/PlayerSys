package com.mlm.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.mlm.model.Athlete;
import com.mlm.model.AthleteProject;
import com.mlm.model.Login;
import com.mlm.model.NewAthlete;
import com.mlm.model.Project;
import com.mlm.model.Unit;
import com.mlm.model.UnitView;
import com.mlm.service.UnitService;
import com.mlm.utils.MD5;

@Before(IocInterceptor.class)
public class UnitController extends Controller{
	
	@Inject.BY_NAME
	private UnitService unitService;
	
	@ActionKey("/findUnitAthlete")
	public void findUnitAthlete(){
		List<Athlete> athletes = unitService.findAthletes(getPara("id"));
		List<AthleteProject> athleteProjects = unitService.findAthletesPro(athletes);
		List<Project> projects = unitService.findProjects(athleteProjects);
		setAttr("athletes", athletes);
		setAttr("athleteProjects", athleteProjects);
		setAttr("projects", projects);
		setAttr("unitid", getPara("id"));
		render("unitfindathlete.html");
	}
	
	@ActionKey("/findUnitAthletePro")
	public void findUnitAthletePro(){
		List<UnitView> uvs = unitService.findUnitView(getPara("id"));
		setAttr("unitview", uvs);
		render("unitfindathletepro.html");
	}
	
	@ActionKey("/insertAthlete")
	public void insertAthlete(){
		String unitid = getPara("id");
		String athlId = getPara("athlid");
		String athlName = getPara("athlname");
		String athlSex = getPara("athlsex");
		String athlAge = getPara("athlage");
		String athlHeight = getPara("athlheight");
		String athlWeight = getPara("athlweight");
		String athlPhone = getPara("athlphone");
		String athlProname = getPara("athlproname");
		
		Athlete athlete = Athlete.athlete.findById(athlId);
		Unit unit = Unit.unit.findById(unitid);
		if(athlete == null){
			new Athlete().set("id",athlId).set("name", athlName).set("sex", athlSex)
				.set("age", athlAge).set("height",athlHeight + "cm").set("weight", athlWeight + "kg")
				.set("phone", athlPhone).set("unit_id", unitid).save();
			Record athleteProject = null;
			switch (athlProname) {
				case "男子400米" : 
					athleteProject = new Record().set("athlete_id", athlId).set("project_id", "01").set("schedule_id", "01").set("category_id", "01");
					break;
	
				case "男子110米栏" : 
					athleteProject = new Record().set("athlete_id", athlId).set("project_id", "02").set("schedule_id", "01").set("category_id", "01");
					break;
					
				case "男子十项全能1500米" : 
					athleteProject = new Record().set("athlete_id", athlId).set("project_id", "03").set("schedule_id", "01").set("category_id", "01");
					break;
			}
			Db.save("athlete_has_project", athleteProject);
			new NewAthlete().set("athl_id",athlId).save();
			new Login().set("user_id",athlId).set("",MD5.md5Value("123456")).set("authority","运动员").save();
			unit.set("population",unit.getInt("population")+1).update();
		}else {
			athlete.set("name",athlName).set("sex",athlSex).set("age",athlAge)
					.set("height",athlHeight).set("weight",athlWeight)
					.set("phone",athlPhone).update();
		}
		
		findUnitAthlete();
	}
	
	@ActionKey("/deleteAthlete")
	public void deleteAthlete(){
		String athId = getPara("athid");
		String proId = getPara("proid");
		
		Db.deleteById("athlete", athId);
		Db.deleteById("athlete_has_project", proId);
		
		findUnitAthlete();
	}
	
	@ActionKey("/toInsertAthlete")
	public void toInsertAthlete(){
		String unitid = getPara("id");
		setAttr("unitid", unitid);
		render("insertathletealert.html");
	}
	
}
