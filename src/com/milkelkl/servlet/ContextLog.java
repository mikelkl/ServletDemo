package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextLog extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String par = request.getParameter("par1");
		// 调用两个 ServletContext.log 方法
		ServletContext context = getServletContext();

		if (par == null || par.equals("")) {
			// 通过 Throwable 参数记录版本
			context.log("No message received:", new IllegalStateException(
					"Missing parameter"));
		} else {
			context.log("Here is the visitor's message: " + par);
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Context Log";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<h2 align=\"center\">Messages sent</h2>\n"
				+ "</body></html>");
		out.flush();
		out.close();
	}

}
