package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfiguration{
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	protected void configure(HttpSecurity http) throws Exception {
		// http 시큐리티 빌더
		http.cors() //WebNvcConfig 에서 이미 설정했으므로 기본 cors 설정 
		.and()
		.csrf() //csrf눈 현재 사용하지 않으므로 disable
		.disable()
		.httpBasic() // 토큰을 사용하므로 basic 인증 disable
		.disable()
		.sessionManagement() // session 기반이 아님을 선언
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests() // 와 /auth/** 경로는 인증 안해도 됨
		.requestMatchers("/", "/auth/**").permitAll()
		.anyRequest() // /와 /auth/** 이외의 모든 경로는 인증해야 됨
		.authenticated();
		
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
	}

}
