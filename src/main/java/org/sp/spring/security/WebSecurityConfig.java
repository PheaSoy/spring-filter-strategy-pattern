package org.sp.spring.security;

import org.sp.spring.policy.UrlFilterValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${filterurlpolicy}")
	private String policyUrl;
	
	@Value("${error.page.not.found}")
	private String msgPageNotFound;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		UrlFilterValue.policyUrl = policyUrl;
		UrlFilterValue.errorPageNotFound = msgPageNotFound;
		
		http.authorizeRequests().antMatchers("/welcome").permitAll();
		http.authorizeRequests().antMatchers("/**").authenticated();
	}


}
