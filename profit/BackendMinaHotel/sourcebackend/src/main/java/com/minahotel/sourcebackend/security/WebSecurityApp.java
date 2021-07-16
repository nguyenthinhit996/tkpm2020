package com.minahotel.sourcebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import com.minahotel.sourcebackend.security.entrypoints.Http403ForbiddenEntryPointCustom;
import com.minahotel.sourcebackend.security.filter.ErrorFilterCustome;
import com.minahotel.sourcebackend.security.filter.JWTAuthenticationFilter;
import com.minahotel.sourcebackend.security.filter.JWTAuthorizationFilter;
import com.minahotel.sourcebackend.security.filter.JWTLogoutFilterFilter;
import com.minahotel.sourcebackend.security.filter.LogoutHandlerCustomize;
import com.minahotel.sourcebackend.security.handle.CustomizeAccessDenieHandle;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;


/**
 * WebSecurity is class configuration security for application belong things below: 
 * <li> Authorization (phân quyền) </li>
 * <li> Handle Forbiden (not token in header or role check). </li>
 * <li> Handle Logout.  </li>
 * <li> Handle Authentication (Login process). </li>
 * <li> Handle Authorization on every request (token validation). </li>
 * <li> Cross Origin Requests (Cors) and Cross-site Request Forgery (crfs) </li>
 * <li> Catch All error from application </li>
 * @author Peter
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityApp extends WebSecurityConfigurerAdapter {

	@Autowired
	private StaffRepositoryServices userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/**").authorizeRequests()
				.antMatchers(SecurityConstants.LOG_IN_URL, SecurityConstants.LOG_OUT_URL,
						SecurityConstants.REFRESH_TOKEN_URL)
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(delegatingEntryPoint()).accessDeniedHandler(accessDeniedHandler())
				.and()
				.addFilter(new JWTLogoutFilterFilter(getLogoutSuccessHandler(), getLogoutHandlerArray())) // order 2
				.addFilter(new JWTAuthenticationFilter(authenticationManager())) //order 3
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))  //order 4
				.addFilterBefore(new ErrorFilterCustome(), CorsFilter.class) // handled error all filter above // order 1
				
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable();  
	}
	
	/**
	 * ExceptionHandling on ExceptionTranslationFilter. accessDeniedHandler For access permission, role . 
	 * @return AccessDeniedHandler
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomizeAccessDenieHandle();
	}
	
	/**
	 * Check EntryPoint for annonymous login into authenticated() forbiden catch
	 * @return AuthenticationEntryPoint
	 */
	@Bean
	public AuthenticationEntryPoint delegatingEntryPoint() {
		return new Http403ForbiddenEntryPointCustom();
	}

	/**
	 * Set useDetailService and PasswordEncoder for provider authentication 
	 * @param AuthenticationManagerBuilder auth
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Config for Cors  
	 * @return CorsConfigurationSource
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); // spectify ,"http://localhost:3000"
		corsConfiguration.applyPermitDefaultValues();
		// customize defaut
		corsConfiguration.addAllowedMethod(HttpMethod.PUT);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	/**
	 * Config bean for PasswordEncoder 
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // specify BCryptPasswordEncoder because upgrade from from project orginal
	}

	/**
	 * To handle logout process
	 * @return LogoutSuccessHandler
	 */
	private LogoutSuccessHandler getLogoutSuccessHandler() {
		SimpleUrlLogoutSuccessHandler simpleLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
		simpleLogoutSuccessHandler.setDefaultTargetUrl(SecurityConstants.LOG_IN_URL);
		return simpleLogoutSuccessHandler;
	}

	/**
	 * To handle if logout success
	 * @return LogoutHandler
	 */
	private LogoutHandler[] getLogoutHandlerArray() {
		LogoutHandlerCustomize logoutHandler = new LogoutHandlerCustomize();
		LogoutHandler[] handlers = new LogoutHandler[] { logoutHandler };
		return handlers;
	}


	// config auth ignore for swagger
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/v3/api-docs/**",
				"/configuration/ui",
				"/swagger-resources/**",
				"/swagger-ui/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}
}
