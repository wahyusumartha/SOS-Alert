package com.appspot.sosalert.channel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.sosalert.dao.ReportDAO;
import com.appspot.sosalert.model.Report;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.repackaged.org.json.JSONArray;

public class SosChannelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReportDAO reportDAO = new ReportDAO();
		List<Report> reports = reportDAO.allReports();
		JSONArray jsonReports = new JSONArray(reports);
		
		resp.setContentType("application/json");
		resp.getWriter().write("ok");
	}

}
