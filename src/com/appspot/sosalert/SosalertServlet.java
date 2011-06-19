package com.appspot.sosalert;

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SosalertServlet extends HttpServlet {

	public static String message;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		FileReader reader = new FileReader("index.html");
		CharBuffer buffer = CharBuffer.allocate(16384);
		reader.read(buffer);
		String index = new String(buffer.array());
		resp.setContentType("text/html");
		resp.getWriter().write(index);
	}

}
