package com.mlm.service;

import java.util.ArrayList;
import java.util.List;

import com.mlm.model.Athlete;
import com.mlm.model.AthleteProject;
import com.mlm.model.AthleteView;
import com.mlm.model.Judge;
import com.mlm.model.Proschedule;
import com.mlm.model.UnitView;

public class AthleteService {
	
	public Athlete findProfile(String id){
		Athlete athlete = Athlete.athlete.findById(id);
		return athlete;
	}
	
	public UnitView getUnitView(String id){
		UnitView uv = UnitView.uv.findById(id);
		return uv;
	}
	
	public List<AthleteView> getAthleteView(String id){
		List<AthleteView> av = AthleteView.av.find("select * from athleteview where id=?",id);
		return av;
	}
	
	
	public List<AthleteProject> getAthleteProjects(String id){
		List<AthleteProject> aps = AthleteProject.athleteProject.find("select * from athlete_has_project where athlete_id = ?",id);
		return aps;
	}
	
	public List<Judge> getJudges(List<AthleteView> avs){
		List<Judge> judges = new ArrayList<Judge>();
		Judge judge = null;
		for(AthleteView av :avs){
			judge = Judge.judge.findById(av.getStr("judge_id"));
			judges.add(judge);
		}
		return judges;
	}
}
