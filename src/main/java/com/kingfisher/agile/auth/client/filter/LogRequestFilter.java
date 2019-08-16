package com.kingfisher.agile.auth.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogRequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		log.info(req.getRequestURI());
		if (isLoginRequest(req)) {
			log.info("loging request ...");
			
			log.info("request logged ...");
		}
		chain.doFilter(request, response);
	}

	private static boolean isLoginRequest(HttpServletRequest request) {
		return request.getRequestURI()
				.startsWith(ApplicationConstant.CONTEXT_PATH_SEPARATOR
						.concat(ApplicationConstant.CONTEXT_PATH_LOGIN_REQUEST)
						.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR));
	}

}