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

		final String sender = System.getenv("hotmail_username"); // ��ϵͳ���������л�ȡhotmail����
		final String password = System.getenv("hotmail_password"); // ��ϵͳ���������л�ȡhotmail����

		String recipient1 = "mikelkl@foxmail.com"; // �ռ���1
		String recipient2 = "mikelkl@126.com"; // �ռ���2
		String recipient3 = "mikelkl@sina.com"; // �ռ���3

		// Set the properties for Mailbox
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); // ������Ҫ��֤����ΪTrue
		properties.put("mail.smtp.starttls.enable", "true"); // hotmail ��Ҫ��tls
		properties.put("mail.smtp.host", "smtp.live.com");
		properties.put("mail.smtp.port", "587"); // tls�Ķ˿�Ϊ587

		// ��ȡsession����
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password); // �������ʼ��û���������
			}
		});

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// Set the message for Email
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));

			// Ⱥ���ʼ�
			Address[] addresses = { new InternetAddress(recipient1),
					new InternetAddress(recipient2),
					new InternetAddress(recipient3) };
			msg.addRecipients(Message.RecipientType.TO, addresses);

			msg.setSentDate(new Date());
			msg.setSubject("ʹ��javaMail���ʹ��ı�email");
			msg.setText("���ı���Ϣ������");

			Transport.send(msg);

			String title = "���͵����ʼ�";
			String res = "�ɹ�������Ϣ...";
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
