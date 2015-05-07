package com.mlm.service;

import java.util.ArrayList;
import java.util.List;

import com.mlm.model.Athlete;
import com.mlm.model.AthleteProject;
import com.mlm.model.Project;
import com.mlm.model.UnitView;

public class UnitService {
	
	public List<UnitView> findUnitView(String id){
		List<UnitView> uvs = UnitView.uv.find("select * from unitview where unit_id = ?",id);
		return uvs;
	}
	
	public List<Athlete> findAthletes(String unitId){
		List<Athlete> athletes = Athlete.athlete.find("select * from athlete where unit_id = ?",unitId);
		for(Athlete ath : athletes){
			System.out.println(ath.getStr("id"));
		}
		return athletes;
	}
	
	public List<AthleteProject> findAthletesPro(List<Athlete> athletes){
		List<AthleteProject> athletePros = new ArrayList<AthleteProject>();
		AthleteProject athleteProject = null;
		for(Athlete athlete : athletes){
			for(AthleteProject ap : AthleteProject.athleteProject.find("select *,count(distinct athlete_id) from athlete_has_project where athlete_id = ? group by athlete_id ",athlete.getStr("id"))){
				athletePros.add(ap);
			}
		}
		return athletePros;
	}
	
	public List<Project> findProjects(List<AthleteProject> athletePros){
		List<Project> projects = new ArrayList<Project>();
		Project project = null;
		for(AthleteProject ap : athletePros){
			System.out.println(ap.getStr("athlete_id")+"----");
			project = Project.project.findById(ap.getStr("project_id"));
			projects.add(project);
		}
		
		for(Project pro : projects){
			System.out.println(pro.getStr("name"));
		}
		
		return projects;
	}
	
	
	
	public void insertAthlete(){
		
	}
}
