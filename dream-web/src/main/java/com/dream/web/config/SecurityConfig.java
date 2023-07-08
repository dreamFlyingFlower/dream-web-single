package com.dream.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.dream.web.config.oauth.RestOAuth2AuthExceptionEntryPoint;
import com.dream.web.entity.IntegrationUserDetailsAuthenticationHandler;
import com.dream.web.entity.IntegrationUserDetailsAuthenticationProvider;
import com.dream.web.filter.AuthenticationTokenFilter;
import com.dream.web.mobile.MobileAuthenticationProvider;
import com.dream.web.mobile.MobileUserDetailsService;
import com.dream.web.mobile.MobileVerifyCodeService;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private AuthenticationTokenFilter authenticationTokenFilter;

	@Autowired
	private PermitResource permitResource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private MobileUserDetailsService mobileUserDetailsService;

	@Autowired
	private MobileVerifyCodeService mobileVerifyCodeService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 普通用户数据库用户名密码登录
	 * 
	 * @return DaoAuthenticationProvider
	 */
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	/**
	 * 手机验证码登录
	 * 
	 * @return MobileAuthenticationProvider
	 */
	@Bean
	MobileAuthenticationProvider mobileAuthenticationProvider() {
		return new MobileAuthenticationProvider(mobileUserDetailsService, mobileVerifyCodeService);
	}

	/**
	 * 集成各种网页登录模式
	 * 
	 * @return IntegrationUserDetailsAuthenticationProvider
	 */
	@Bean
	public IntegrationUserDetailsAuthenticationProvider integrationUserDetailsAuthenticationProvider() {
		return new IntegrationUserDetailsAuthenticationProvider(integrationUserDetailsAuthenticationHandler());
	}

	/**
	 * 生成各种登录解析器,可自定义添加
	 * 
	 * @return AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
		authenticationProviders.add(daoAuthenticationProvider());
		authenticationProviders.add(mobileAuthenticationProvider());
		authenticationProviders.add(integrationUserDetailsAuthenticationProvider());
		ProviderManager providerManager = new ProviderManager(authenticationProviders);
		providerManager
				.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
		return providerManager;
	}

	/**
	 * SpringSecurity安全拦截,若报错,可去掉WebSecurity试试
	 * 
	 * @param http
	 * @param webSecurity
	 * @return SecurityFilterChain
	 */
	@Bean
	@SneakyThrows
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, WebSecurity webSecurity) {
		// 忽略授权的地址列表
		List<String> permitList = permitResource.getPermitList();
		String[] permits = permitList.toArray(new String[permitList.size()]);
		httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(permits).permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
				// .antMatchers("/login*").permitAll().antMatchers("/logout*").permitAll()
				// .antMatchers("/druid/**").permitAll()
				.anyRequest().authenticated().and()
				// 登录页面
				// .formLogin().loginPage("/login")
				// .authenticationDetailsSource(integrationWebAuthenticationDetailsSource)
				// 登录处理url
				// .loginProcessingUrl("/login.do")
				// .failureUrl("/login?authentication_error=1").defaultSuccessUrl("/oauth/authorize")
				// .and().logout().logoutUrl("/logout.do")
				// .deleteCookies("JSESSIONID").logoutSuccessUrl("/").and().exceptionHandling()
				// .accessDeniedPage("/login?authorization_error=2").and().requestCache()
				// .requestCache(getRequestCache(http))
				.exceptionHandling().authenticationEntryPoint(new RestOAuth2AuthExceptionEntryPoint()).and().headers()
				.frameOptions().disable().and().csrf().disable();

		webSecurity.ignoring().antMatchers("/public/**", "/webjars/**", "/v2/**", "/swagger**", "/static/**",
				"/resources/**");
		// web.httpFirewall(new DefaultHttpFirewall());//StrictHttpFirewall
		// 去除验url非法验证防火墙

		return httpSecurity.build();
	}

	///////////////////////////////////////
	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest,
			WebAuthenticationDetails> integrationWebAuthenticationDetailsSource;

	@Bean
	public IntegrationUserDetailsAuthenticationHandler integrationUserDetailsAuthenticationHandler() {
		IntegrationUserDetailsAuthenticationHandler authenticationHandler =
				new IntegrationUserDetailsAuthenticationHandler();
		return authenticationHandler;
	}

	private RequestCache getRequestCache(HttpSecurity http) {
		RequestCache result = http.getSharedObject(RequestCache.class);
		if (result != null) {
			return result;
		}
		HttpSessionRequestCache defaultCache = new HttpSessionRequestCache();
		defaultCache.setRequestMatcher(createDefaultSavedRequestMatcher(http));
		return defaultCache;
	}

	private RequestMatcher createDefaultSavedRequestMatcher(HttpSecurity http) {
		ContentNegotiationStrategy contentNegotiationStrategy = http.getSharedObject(ContentNegotiationStrategy.class);
		if (contentNegotiationStrategy == null) {
			contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
		}

		RequestMatcher notFavIcon = new NegatedRequestMatcher(new AntPathRequestMatcher("/**/favicon.ico"));

		MediaTypeRequestMatcher jsonRequest =
				new MediaTypeRequestMatcher(contentNegotiationStrategy, MediaType.APPLICATION_JSON);
		jsonRequest.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
		RequestMatcher notJson = new NegatedRequestMatcher(jsonRequest);

		// RequestMatcher notXRequestedWith =
		// new NegatedRequestMatcher(new RequestHeaderRequestMatcher("X-Requested-With",
		// "XMLHttpRequest"));

		@SuppressWarnings("unchecked")
		boolean isCsrfEnabled = http.getConfigurer(CsrfConfigurer.class) != null;

		List<RequestMatcher> matchers = new ArrayList<>();
		if (isCsrfEnabled) {
			RequestMatcher getRequests = new AntPathRequestMatcher("/**", "GET");
			matchers.add(0, getRequests);
		}
		matchers.add(notFavIcon);
		matchers.add(notJson);
		// matchers.add(notXRequestedWith);

		return new AndRequestMatcher(matchers);
	}
}