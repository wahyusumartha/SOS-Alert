package com.appspot.sosalert.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.sosalert.PMF;
import com.appspot.sosalert.model.Report;
import com.appspot.sosalert.model.TwitterModel;

public class TwitterDAO {
	
	public void save(TwitterModel twitter){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(twitter);
		}finally{
			pm.close();
		}
	}
	
	public List<TwitterModel> getLastData(){
		List<TwitterModel> twitterModels = new ArrayList<TwitterModel>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(TwitterModel.class);
		
		try{
			twitterModels = (List<TwitterModel>) query.execute();
			
		}finally{
			query.closeAll();
		}
		
		return twitterModels;
	}
}
