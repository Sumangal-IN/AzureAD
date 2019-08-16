package com.kingfisher.agile.auth.client.service;

import java.util.Date;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.model.AccessLog;
import com.kingfisher.agile.auth.client.model.MetaData;
import com.kingfisher.agile.auth.client.repository.AccessLogRepository;

@Service
public class AccessLogService {

	@Autowired
	AccessLogRepository accessLogRepository;

	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	public void logRequested(AccessLog accessLog) {
		if (accessLogRepository.findById(accessLog.getJSessionID()).isPresent())
			return;
		accessLogRepository.save(accessLog);
	}

	public void logSucceeded(MetaData metaData) {
		accessLogRepository.findById(metaData.getJSessionID()).ifPresent(x -> {
			x.setSuccess(ApplicationConstant.TRUE);
			x.setSucceededOn(new Date());
			accessLogRepository.save(x);
		});
	}

}
