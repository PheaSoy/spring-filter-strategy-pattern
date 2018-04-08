package org.sp.spring.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

public class ExecuteFilterPolicyImpl {

	
	private List<AbstractFilterPolicy> listFilerPolicy;

	public boolean isOk() {
		return true;
	}

	public List<AbstractFilterPolicy> getListFilerPolicy() {
		return listFilerPolicy;
	}

	public void setListFilerPolicy(List<AbstractFilterPolicy> listFilerPolicy) {
		this.listFilerPolicy = listFilerPolicy;
	}

	public boolean applyRule(HttpServletRequest request, HttpServletResponse response)
			 {
		
		boolean isOk = true;
		try {
		if (UrlFilterValue.policyUrl == null || UrlFilterValue.policyUrl.isEmpty()) {
			System.out.println("No policy filter rule applied");
			return isOk;
		}
		List<String> listPolicyUrl = Arrays.asList(UrlFilterValue.policyUrl.split("\\s*,\\s*"));
		System.out.println("listPolicyUrl:"+listPolicyUrl);
		ArrayList<AbstractFilterPolicy> oauthPolicyList = new ArrayList<>();
		for (String url : listPolicyUrl) {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(url);
			AbstractFilterPolicy policy = (AbstractFilterPolicy) clazz.newInstance();
			oauthPolicyList.add(policy);
		}
		this.setListFilerPolicy(oauthPolicyList);
		
		for (AbstractFilterPolicy filterPolicy : listFilerPolicy) {
			boolean checkStatus = filterPolicy.applyDoFilter(request, response);
			System.out.println("Policy Name:"+filterPolicy+" Result checked:"+checkStatus);
			if (checkStatus == false) {
				isOk = checkStatus;
				break;
			}
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return isOk;
	}
}
