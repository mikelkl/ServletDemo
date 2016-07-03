package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import com.mysql.jdbc.Driver;

public class DatabaseAccess extends HttpServlet {

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
		// JDBC ���������ƺ����ݿ�� URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/work";

		// ���ݿ��ƾ��
		final String USER = "root";
		final String PASS = "1234";

		Connection conn = null;
		Statement stmt = null;

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "���ݿ���";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n");

		try {
			// ע�� JDBC ������
			Class.forName("com.mysql.jdbc.Driver");

			// ��һ������
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// ִ�� SQL ��ѯ
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * from user";
			ResultSet rs = stmt.executeQuery(sql);

			// �ӽ��������ȡ����
			while (rs.next()) {
				// ���������Ƽ���
				String id = rs.getString("studentId");
				String userName = rs.getString("userName");
				String duty = rs.getString("duty");
				int score = rs.getInt("score");

				// ��ʾֵ
				out.println("ID: " + id);
				out.println(", userName: " + userName);
				out.println(", duty: " + duty);
				out.println(", score: " + score + "<br>");
			}
			out.println("</body></html>");

			// ������
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// ��������ڹر���Դ�Ŀ�
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

}
