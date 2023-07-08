package com.dream.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 *
 * @author 飞花梦影
 * @date 2022-06-17 16:41:00
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecuritySuccessHandler securitySuccessHandler;

	@Autowired
	private SecurityFailureHandler securityFailureHandler;

	@Autowired
	private SecurityLogoutHandler securityLogoutHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().loginProcessingUrl("/login").permitAll()
		        .successHandler(securitySuccessHandler).permitAll().failureHandler(securityFailureHandler).permitAll()
		        .and().logout().logoutSuccessHandler(securityLogoutHandler).and().authorizeRequests().antMatchers("/**")
		        .permitAll();
	}
}