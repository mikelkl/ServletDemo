package com.milkelkl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SiteHitCounter implements Filter {
	private int hitCount;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 把计数器的值增加 1
		hitCount++;

		// 输出计数器
		System.out.println("网站访问统计：" + hitCount);

		// 把请求传回到过滤器链
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// 重置点击计数器
		hitCount = 0;
	}

}
