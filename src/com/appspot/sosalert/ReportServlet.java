package com.appspot.sosalert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import pubnub.Pubnub;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import com.appspot.sosalert.dao.ReportDAO;
import com.appspot.sosalert.model.Report;

public class ReportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Pubnub pubnub = new Pubnub("pub-22f6936b-db26-4a11-8a1c-068b30893ebe", "sub-f0821917-97fc-11e0-80d9-5793e1e53f35");
		String channel = "sos_report";
		
		Report report = new Report();
		report.setId(1L);
		report.setName("Wahyu Sumartha");
		report.setDescription("Earthquake at Sibolga");
		report.setLatitude("3.15232");
		report.setLongitude("104.2312321");
		report.setPriority("high");
		report.setTimeStamp("" + new java.util.Date().getTime() + "");
		
		JSONObject message = new JSONObject(report);
//		try { message.put( "some_val", "Hello World!" ); }
//		catch (org.json.JSONException jsonError) {}

		// Publish Message
		JSONArray response  = pubnub.publish(channel, message);
		
		// Tweet Something
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("NyvASiKhSvpEHFUvCBPHA");
		cb.setOAuthConsumerSecret("JAGZsZL7RKZl3jwTJgGxgqm97jFW5cFdgfd3bP6k82M");
		cb.setOAuthAccessToken("319659071-iuGAEuqlFhyX6Ep6lijFIjWIQOTKLpkx5ne9MTpk");
		cb.setOAuthAccessTokenSecret("8UGUHbu692RbIZrGGF91d7kvmyuY9R81jsYw9Px4");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		try {
			User user = twitter.verifyCredentials();
			Status status = twitter.updateStatus("This is From Google App Engine");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print Response from PubNub JSONP REST Service
		System.out.println(response);
		resp.setContentType("text/plain");
		resp.getWriter().write(response.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReportDAO reportDao = new ReportDAO();
		
		Report report = new Report();
		report.setName("Yoze Hariando");
		report.setDescription("Kebakaran di Taman Sering Ukay");
		report.setLatitude("200.12212");
		report.setLongitude("142.2321");
		report.setPriority("high");
		report.setTimeStamp("" + new java.util.Date().getTime() + "");
		
		reportDao.save(report);
		
		resp.sendRedirect("/index.html");
	}

}
