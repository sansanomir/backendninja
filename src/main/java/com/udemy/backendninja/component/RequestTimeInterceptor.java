package com.udemy.backendninja.component;



import com.udemy.backendninja.repository.LogRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String url = request.getRequestURL().toString();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if(auth != null && auth.isAuthenticated()){
			username = auth.getName();
		}

		logRepository.save(new com.udemy.backendninja.entity.Log(new Date(),auth.getDetails().toString(),username,url));
		long startTime = (long) request.getAttribute("startTime");
		LOG.info("URL: " + url + " TOTAL TIME: " + (System.currentTimeMillis() - startTime) + "ms.");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime",System.currentTimeMillis());
		return true;
	}

}
