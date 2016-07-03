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
		// �Ѽ�������ֵ���� 1
		hitCount++;

		// ���������
		System.out.println("��վ����ͳ�ƣ�" + hitCount);

		// �����󴫻ص���������
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// ���õ��������
		hitCount = 0;
	}

}
