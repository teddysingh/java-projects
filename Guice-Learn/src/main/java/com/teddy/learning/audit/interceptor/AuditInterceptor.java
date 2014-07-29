package com.teddy.learning.audit.interceptor;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AuditInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(new Date() + " - Interceped method: "
				+ invocation.getMethod().getName() + "for parameters: "
				+ invocation.getArguments()[0]);
		Response response = (Response) invocation.proceed();
		System.out.println(new Date() + " - Interception complete with responseStatus: " + response.getStatus());
		
		return response;
	}

}
