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
		// 获取客户机的 IP 地址
		String ipAddress = request.getRemoteAddr();

		// 记录 IP 地址和当前时间戳
		System.out.println("IP " + ipAddress + ", Time "
				+ new Date().toString());

		// 把请求传回过滤链
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 获取初始化参数
		String testParam = config.getInitParameter("test-param");

		// 输出初始化参数
		System.out.println("Test Param: " + testParam);
	}

}
