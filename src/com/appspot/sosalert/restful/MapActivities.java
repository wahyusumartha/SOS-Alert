package com.appspot.sosalert.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import pubnub.Pubnub;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import com.appspot.sosalert.dao.ReportDAO;
import com.appspot.sosalert.model.Report;

@Path("/activitieslist")
public class MapActivities {

	@GET
	@Path("example")
	@Produces("text/plain")
	public String getItems() {
		return "restful web service example";
	}

	@GET
	@Path("example2")
	@Produces("text/plain")
	public String getExample2() {
		return "Do you see this texts?";
	}

	@POST
	@Path("postreport")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveReport(Report report) {
		try {
			ReportDAO reportDAO = new ReportDAO();

			// save data first
			report.setTimeStamp("" + new java.util.Date().getTime() + "");
			reportDAO.save(report);

			// publish
			Pubnub pubnub = new Pubnub(
					"pub-22f6936b-db26-4a11-8a1c-068b30893ebe",
					"sub-f0821917-97fc-11e0-80d9-5793e1e53f35");
			String channel = "sos_report";

			JSONObject reportJSON = new JSONObject(report);

			JSONArray response = pubnub.publish(channel, reportJSON);
			
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
				Status status = twitter.updateStatus(report.getDescription() + "#sos");
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "ok";
	}

	@GET
	@Path("report")
	@Produces(MediaType.APPLICATION_JSON)
	public Report getReport() {
		Report report = new Report();
		report.setId(1L);
		report.setName("Wahyu Sumartha");
		report.setDescription("Earthquake at Sibolga");
		report.setLatitude("3.15232");
		report.setLongitude("104.2312321");
		report.setPriority("high");
		report.setTimeStamp("" + new java.util.Date().getTime() + "");
		return report;
	}

	@GET
	@Path("reports")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getReports() {
		ReportDAO reportDAO = new ReportDAO();
		List<Report> reports = reportDAO.allReports();
		return reports;
	}
}
