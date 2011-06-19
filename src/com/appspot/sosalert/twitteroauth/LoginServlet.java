package com.appspot.sosalert.twitteroauth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.sosalert.dao.TwitterDAO;
import com.appspot.sosalert.model.TwitterModel;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TwitterConfiguration twitterConfig = new TwitterConfiguration();
		
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("NyvASiKhSvpEHFUvCBPHA", "JAGZsZL7RKZl3jwTJgGxgqm97jFW5cFdgfd3bP6k82M");
		
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			
			String token = requestToken.getToken();
			String tokenSecret =  requestToken.getTokenSecret();
			
			TwitterModel twitterModel = new TwitterModel();
			twitterModel.setToken(token);
			twitterModel.setTokenSecret(tokenSecret);
			
			TwitterDAO twitterDAO = new TwitterDAO();
			twitterDAO.save(twitterModel);
			
			HttpSession session = req.getSession();
			session.setAttribute("token", token);
			session.setAttribute("tokenSecret", tokenSecret);
			
			String authUrl = requestToken.getAuthorizationURL();
			
			req.setAttribute("authUrl", authUrl);
			RequestDispatcher rd = req.getRequestDispatcher("twitter-login.jsp");
			rd.forward(req, resp);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
	
}
