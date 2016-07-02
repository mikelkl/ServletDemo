package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCookies extends HttpServlet {

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

		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "Delete Cookies Example";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n");
		if (cookies != null) {
			out.println("<h2>Cookies ���ƺ�ֵ</h2>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().compareTo("first_name") == 0) {
					/**
					 * ɾ��Cookieʱ��ֻ����maxAge=0�����ܹ����������ɾ��cookie,
					 * ��Ϊһ��CookieӦ������һ��path��domain������ɾ��ʱ��Cookie������������Ҳ�������á�
					 */

					// �ص�������1,��������domain���Ե�ֵ
					cookie.setDomain("localhost");

					// �ص�������2,��������path���Ե�ֵ
					cookie.setPath("/ServletDemo");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					out.print("��ɾ���� cookie��" + cookie.getName() + " "
							+ cookie.getValue() + "<br/>");
				}
				out.print("���ƣ�" + cookie.getName() + "��");
				out.print("ֵ��" + cookie.getValue() + " <br/>");
			}
		} else {
			out.println("<h2 class='tutheader'>No cookies founds</h2>");
		}
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}
}
