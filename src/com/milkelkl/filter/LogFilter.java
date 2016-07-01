package com.milkelkl.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.jstl.core.Config;

public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// ��ȡ�ͻ����� IP ��ַ
		String ipAddress = request.getRemoteAddr();

		// ��¼ IP ��ַ�͵�ǰʱ���
		System.out.println("IP " + ipAddress + ", Time "
				+ new Date().toString());

		// �����󴫻ع�����
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// ��ȡ��ʼ������
		String testParam = config.getInitParameter("test-param");

		// �����ʼ������
		System.out.println("Test Param: " + testParam);
	}

}
