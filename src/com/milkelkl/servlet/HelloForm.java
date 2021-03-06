package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用 Servlet 读取表单数据
 * 
 * @author lkl
 * 
 */

@WebServlet("/HelloForm")
public class HelloForm extends HttpServlet {
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
		// 为名字和姓氏创建 Cookies
		Cookie firstName = new Cookie("first_name",
				request.getParameter("first_name"));
		Cookie lastName = new Cookie("last_name",
				request.getParameter("last_name"));

		// 为两个 Cookies 设置过期日期为 24 小时后
		firstName.setMaxAge(60 * 60 * 24);
		lastName.setMaxAge(60 * 60 * 24);

		// 在响应头中添加两个 Cookies
		response.addCookie(firstName);
		response.addCookie(lastName);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "设置 Cookies 实例";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n"
				+ "  <li><b>名字</b>：" + request.getParameter("first_name")
				+ "\n</li>" + "  <li><b>姓氏</b>："
				+ request.getParameter("last_name") + "\n</li>" + "</ul>\n"
				+ "</body></html>");
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
