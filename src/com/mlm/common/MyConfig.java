package com.mlm.common;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.spring.SpringPlugin;
import com.mlm.controller.AdministratorController;
import com.mlm.controller.AthleteController;
import com.mlm.controller.CommonController;
import com.mlm.controller.JudgeController;
import com.mlm.controller.LoginController;
import com.mlm.controller.UnitController;
import com.mlm.model.Athlete;
import com.mlm.model.AthleteProject;
import com.mlm.model.AthleteView;
import com.mlm.model.Judge;
import com.mlm.model.JudgeView;
import com.mlm.model.Login;
import com.mlm.model.NewAthlete;
import com.mlm.model.Project;
import com.mlm.model.Proschedule;
import com.mlm.model.ProscheduleView;
import com.mlm.model.Score;
import com.mlm.model.Unit;
import com.mlm.model.UnitView;

public class MyConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("db.properties");
		me.setDevMode(true);
		me.setMainRenderFactory(new BeetlRenderFactory());
		Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {e.printStackTrace();}
        WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
        resourceLoader.setRoot(getProperty("viewUrl"));
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, cfg);
        
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/view",LoginController.class);
		me.add("/athlete",AthleteController.class,"/view");
		me.add("/unit",UnitController.class,"/view");
		me.add("/judge",JudgeController.class,"/view");
		me.add("/admin",AdministratorController.class,"/view");
		me.add("/common",CommonController.class,"/view");
	}

	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("db.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"),getProperty("password").trim());
				me.add(c3p0Plugin);
				ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
				me.add(arp);
				arp.addMapping("login","user_id",Login.class);
				arp.addMapping("athlete", Athlete.class);
				arp.addMapping("unitview","unit_id",UnitView.class);
				arp.addMapping("judgeview","judge_id",JudgeView.class);
				arp.addMapping("athleteview",AthleteView.class);
				arp.addMapping("judge",Judge.class);
				arp.addMapping("unit",Unit.class);
				arp.addMapping("project",Project.class);
				arp.addMapping("score",Score.class);
				arp.addMapping("proschedule", Proschedule.class);
				arp.addMapping("proscheduleview","pro_id", ProscheduleView.class);
				arp.addMapping("newathlete","athl_id",NewAthlete.class);
				arp.addMapping("athlete_has_project", AthleteProject.class);
				me.add(new SpringPlugin());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
