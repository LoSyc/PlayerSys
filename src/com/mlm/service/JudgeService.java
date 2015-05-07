package com.mlm.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mlm.model.JudgeView;
import com.mlm.model.Proschedule;
import com.mlm.model.Unit;
import com.mlm.model.UnitView;


public class JudgeService {
	
	public List<JudgeView> findJudgeView(String id){
		List<JudgeView> jvs = JudgeView.judgeView.find("select * from judgeview where judge_id = ?",id);
		return jvs;
	}
	
	public Page<Unit> findUnit(Integer pno,Integer pageSize){
		if(null==pno){
			pno = 1;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("from unit where 1=1");
		Page<Unit> uvPage = Unit.unit.paginate(pno, pageSize, "select *",sql.toString());
		return uvPage;
	}
	
	
	public long getTotalItemNumber(){
		long totalItemNum = Db.queryLong("select count(*) from unit");
		return totalItemNum;
	}
	
	public Integer getTotalPage(Integer pageSize) {
		long totalItemNumber = getTotalItemNumber();
		int pageNum = (int)totalItemNumber/pageSize;
		if((int)totalItemNumber%pageSize!=0){
			pageNum++;
		}
		return pageNum;
	}
	
	public Integer getTotalItemNum() {
		long totalItemNumber = getTotalItemNumber();
		return (int) totalItemNumber;
	}
	
//	public List<Proschedule> getProschedule(String id){
//		List<Proschedule> pros = Proschedule.proschedule.find("select * from proschedule");
//		return pros;
//	}
	
	public List<JudgeView> findTheJudgeView(String id){
		List<JudgeView> jvs = JudgeView.judgeView.find("select *,count(distinct time,schedule_name,category_name) from judgeview where judge_id = ? group by time,schedule_name,category_name",id);
		return jvs;
	}

}
