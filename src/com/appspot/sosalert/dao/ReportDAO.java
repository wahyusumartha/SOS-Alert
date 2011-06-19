package com.appspot.sosalert.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.sosalert.PMF;
import com.appspot.sosalert.model.Report;

public class ReportDAO {
	
	
	public void save(Report report){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(report);
		}finally{
			pm.close();
		}
	}
	
	public List<Report> allReports(){
		List<Report> reports = new ArrayList<Report>();
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Report.class);
		query.setOrdering("timestamp descending");
		
		try{
			reports = (List<Report>) query.execute();
		}finally{
			query.closeAll();
		}
		
		return reports;
	}
}
