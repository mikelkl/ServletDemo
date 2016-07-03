package com.milkelkl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmail extends HttpServlet {

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

		final String sender = System.getenv("hotmail_username"); // 从系统环境变量中获取hotmail邮箱
		final String password = System.getenv("hotmail_password"); // 从系统环境变量中获取hotmail密码

		String recipient1 = "mikelkl@foxmail.com"; // 收件人1
		String recipient2 = "mikelkl@126.com"; // 收件人2
		String recipient3 = "mikelkl@sina.com"; // 收件人3

		// Set the properties for Mailbox
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); // 邮箱需要验证设置为True
		properties.put("mail.smtp.starttls.enable", "true"); // hotmail 需要起tls
		properties.put("mail.smtp.host", "smtp.live.com");
		properties.put("mail.smtp.port", "587"); // tls的端口为587

		// 获取session对象
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password); // 发件人邮件用户名、密码
			}
		});

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// Set the message for Email
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));

			// 群发邮件
			Address[] addresses = { new InternetAddress(recipient1),
					new InternetAddress(recipient2),
					new InternetAddress(recipient3) };
			msg.addRecipients(Message.RecipientType.TO, addresses);

			msg.setSentDate(new Date());
			msg.setSubject("使用javaMail发送纯文本email");
			msg.setText("纯文本信息。。。");

			Transport.send(msg);

			String title = "发送电子邮件";
			String res = "成功发送消息...";
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
					+ "transitional//en\">\n";
			out.println(docType + "<html>\n" + "<head><title>" + title
					+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
					+ "<h1 align=\"center\">" + title + "</h1>\n"
					+ "<p align=\"center\">" + res + "</p>\n"
					+ "</body></html>");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
