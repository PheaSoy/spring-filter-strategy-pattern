package org.sp.spring.policy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisableUrlFilterPolicy extends AbstractFilterPolicy {

	@Override
	public boolean applyDoFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String keyMessage ="";
		
		String pathInfo = request.getRequestURI();
		if (pathInfo != "/hello") { //sample check condition fake
			keyMessage = UrlFilterValue.errorPageNotFound;
			replyFilterFail(response,keyMessage,404);
			return false;
		}
		return true;
	}
}
