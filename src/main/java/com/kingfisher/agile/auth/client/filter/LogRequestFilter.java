package com.kingfisher.agile.auth.client.filter;

import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildAccessLog;
import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildMetaData;
import static com.kingfisher.agile.auth.client.builder.DataBuilders.extractMeta;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.service.AccessLogService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogRequestFilter implements Filter {

	AccessLogService accessLogService;

	public LogRequestFilter(AccessLogService accessLogService) {
		this.accessLogService = accessLogService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestURI = ((HttpServletRequest) request).getRequestURI();
		log.info("request URI: {}", requestURI);
		if (isLoginRequest(requestURI)) {
			log.info("loging request ...");
			accessLogService.logRequested(buildAccessLog(buildMetaData(extractMeta(requestURI))));
			log.info("request logged ...");
		}
		chain.doFilter(request, response);
	}

	private static boolean isLoginRequest(String requestURI) {
		return requestURI.startsWith(
				ApplicationConstant.CONTEXT_PATH_SEPARATOR.concat(ApplicationConstant.CONTEXT_PATH_LOGIN_REQUEST)
						.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR));
	}

}