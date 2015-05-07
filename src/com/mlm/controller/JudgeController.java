package com.mlm.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.mlm.model.JudgeView;
import com.mlm.model.Project;
import com.mlm.model.Proschedule;
import com.mlm.model.Score;
import com.mlm.model.Unit;
import com.mlm.model.UnitView;
import com.mlm.service.JudgeService;

@Before(IocInterceptor.class)
public class JudgeController extends Controller {
	
	@Inject.BY_NAME
	private JudgeService judgeService;
	
	
	@ActionKey("/findAllAthlete")
	public void findAllAthlete(){
		List<JudgeView> jvs = judgeService.findJudgeView(getPara("id"));
		setAttr("judgeview", jvs);
		render("judgefindathlete.html");
	}
	
	@ActionKey("/findAllUnit")
	public void findAllUnit(){
		Integer pno = getParaToInt("pno");
		Page<Unit> pages = judgeService.findUnit(pno, 10);
		List<Unit> uvs = pages.getList();
		Integer pageNum = judgeService.getTotalPage(10);//总页数
		Integer totalItemNum = judgeService.getTotalItemNum();
		setAttr("uvs", uvs);
		setAttr("pageNum", pageNum);
		setAttr("totalRecords", totalItemNum);
		render("judgefindunit.html");
	}
	
	@ActionKey("/insertschedule")
	public void insertschedule(){
		String projectId=getPara("projectid");
		String projectName=getPara("projectname");
		String projectTime=getPara("projecttime");
		String judgeId=getPara("judgeid");
		String timeid1="01";
		String timeid2="01";
		Project project=Project.project.findById(projectId);
		if(project==null){
			new Project().set("id",projectId).set("name", projectName).save();
			new Proschedule().set("project_id", projectId).set("schedule_id",timeid1).set("category_id", timeid2).set("time", projectTime).set("judge_id", judgeId).save();
			
		}else{
			project.set("name", projectName).update();
		}
		
	}

	@ActionKey("/toinsertschedule")
	public void toinsertschedule(){
		render("insertschedule.html");
	}
	
	
	@ActionKey("/findAllPro")
	public void findAllPro(){
		String id = getPara("id");
		List<JudgeView> pros = judgeService.findTheJudgeView(id);
		setAttr("pros", pros);
		render("judgepro.html");
	}
	
	@ActionKey("/insertAthleteScore")
	public void insertAthleteScore(){
		String judgeId = getPara("id");
		String scoreId = getPara("scoreid");
		String athlId = getPara("athlid");
		String athlProname = getPara("proname");
		String athlSchedule = getPara("schedulename");
		String athlCate = getPara("categoryname");
		String athlRank = getPara("rank");
		String athlScore = getPara("score");
		
		Score score = Score.score.findById(scoreId);
		if(score == null){
			Record scoreRecord = new Record().set("athlete_has_project_athlete_id", athlId)
					.set("rank", athlRank).set("score", athlScore).set("judge_id", judgeId);
			switch (athlProname) {
			case "男子400米" : 
				scoreRecord.set("athlete_has_project_project_id", "01");
				break;
			case "男子110米栏" : 
				scoreRecord.set("athlete_has_project_project_id", "02");
				break;
			case "男子十项全能1500米" : 
				scoreRecord.set("athlete_has_project_project_id", "03");
				break;
			}
			switch (athlSchedule) {
			case "预赛" : 
				scoreRecord.set("athlete_has_project_schedule_id", "01");
				break;
			case "准决赛" : 
				scoreRecord.set("athlete_has_project_schedule_id", "02");
				break;
			case "总决赛" : 
				scoreRecord.set("athlete_has_project_schedule_id", "03");
				break;
			}
			switch (athlCate) {
			case "第一组" : 
				scoreRecord.set("athlete_has_project_category_id", "01");
				break;
			case "第二组" : 
				scoreRecord.set("athlete_has_project_category_id", "02");
				break;
			case "第三组" : 
				scoreRecord.set("athlete_has_project_category_id", "03");
				break;
			case "第四组" : 
				scoreRecord.set("athlete_has_project_category_id", "04");
				break;
			case "第五组" : 
				scoreRecord.set("athlete_has_project_category_id", "05");
				break;
			case "第六组" : 
				scoreRecord.set("athlete_has_project_category_id", "06");
				break;
			case "第七组" : 
				scoreRecord.set("athlete_has_project_category_id", "07");
				break;
			case "第八组" : 
				scoreRecord.set("athlete_has_project_category_id", "08");
				break;
			}
			Db.save("score", scoreRecord);
		}else {
			switch (athlProname) {
			case "男子400米" : 
				score.set("athlete_has_project_project_id", "01");
				break;
			case "男子110米栏" : 
				score.set("athlete_has_project_project_id", "02");
				break;
			case "男子十项全能1500米" : 
				score.set("athlete_has_project_project_id", "03");
				break;
			}
			switch (athlSchedule) {
			case "预赛" : 
				score.set("athlete_has_project_schedule_id", "01");
				break;
			case "准决赛" : 
				score.set("athlete_has_project_schedule_id", "02");
				break;
			case "总决赛" : 
				score.set("athlete_has_project_schedule_id", "03");
				break;
			}
			switch (athlCate) {
			case "第一组" : 
				score.set("athlete_has_project_category_id", "01");
				break;
			case "第二组" : 
				score.set("athlete_has_project_category_id", "02");
				break;
			case "第三组" : 
				score.set("athlete_has_project_category_id", "03");
				break;
			case "第四组" : 
				score.set("athlete_has_project_category_id", "04");
				break;
			case "第五组" : 
				score.set("athlete_has_project_category_id", "05");
				break;
			case "第六组" : 
				score.set("athlete_has_project_category_id", "06");
				break;
			case "第七组" : 
				score.set("athlete_has_project_category_id", "07");
				break;
			case "第八组" : 
				score.set("athlete_has_project_category_id", "08");
				break;
			}
			score.set("athlete_has_project_athlete_id", athlId).set("rank", athlRank)
			.set("score", athlScore).update();
			findAllAthlete();
		}
	}
	
	@ActionKey("/toInsertAthleteScore")
	public void toInsertAthleteScore(){
		String judgeId = getPara("id");
		setAttr("judgeId", judgeId);
		render("insertathletescore.html");
	}
	
}
