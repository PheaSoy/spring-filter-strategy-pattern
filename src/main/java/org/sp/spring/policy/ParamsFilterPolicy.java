package org.sp.spring.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamsFilterPolicy extends AbstractFilterPolicy{

	@Override
	public boolean applyDoFilter(HttpServletRequest request,HttpServletResponse response) {
		
		
		if(request.getQueryString()!=null) {
			return false;
		}
		return true;
	}
}
