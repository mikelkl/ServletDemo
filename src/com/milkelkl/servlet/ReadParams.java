package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadParams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ReadParams() {
		super();
	}

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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "��ȡ���еı�����";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n"
				+ "<head><meta charset=\"utf-8\"><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<table width=\"100%\" border=\"1\" align=\"center\">\n"
				+ "<tr bgcolor=\"#949494\">\n" + "<th>��������</th><th>����ֵ</th>\n"
				+ "</tr>\n");

		Enumeration paraNames = request.getParameterNames();

		while (paraNames.hasMoreElements()) {
			String paramName = (String) paraNames.nextElement();
			out.print("<tr><td>" + paramName + "</td>\n");
			String[] paramVaules = request.getParameterValues(paramName);
			// ��ȡ����ֵ������
			if (paramVaules.length == 1) {
				String paramValue = paramVaules[0];
				if (paramValue.length()==0) {
					out.println("<td><i>û��ֵ</i></td>");
				}
				else {
					out.println("<td>" + paramValue + "</td>");
				}
			} else {
				// ��ȡ���ֵ������
				out.println("<td><ul>");
				for (int i = 0; i < paramVaules.length; i++) {
					out.println("<li>"+paramVaules[i]);
				}
				out.println("</ul></td>");
			}
			out.print("</tr>");
		}
		out.println("\n</table>\n</body></html>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
