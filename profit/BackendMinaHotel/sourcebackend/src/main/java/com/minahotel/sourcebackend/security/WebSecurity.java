package com.minahotel.sourcebackend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.minahotel.sourcebackend.security.entrypoints.Http403ForbiddenEntryPointCustom;
import com.minahotel.sourcebackend.security.filter.ErrorFilterCustome;
import com.minahotel.sourcebackend.security.filter.JWTAuthenticationFilter;
import com.minahotel.sourcebackend.security.filter.JWTAuthorizationFilter;
import com.minahotel.sourcebackend.security.filter.JWTLogoutFilterFilter;
import com.minahotel.sourcebackend.security.filter.LogoutHandlerCustomize;
import com.minahotel.sourcebackend.security.handle.CustomizeAccessDenieHandle;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private StaffRepositoryServices userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/**").authorizeRequests()
				.antMatchers(SecurityConstants.LOG_IN_URL, SecurityConstants.LOG_OUT_URL,
						SecurityConstants.REFRESH_TOKEN_URL)
				.permitAll()
//				.antMatchers("/staff").hasRole("admin")// test delegatingEntryPoint
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(delegatingEntryPoint()).accessDeniedHandler(accessDeniedHandler())
				.and()
				.addFilter(new JWTLogoutFilterFilter(getLogoutSuccessHandler(), getLogoutHandlerArray()))
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.addFilterBefore(new ErrorFilterCustome(), CorsFilter.class) // handled error all filter above

				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable();  
	}
	
	
	// exceptionHandling ExceptionTranslationFilter
	// accessDeniedHandler For access permission, role . 
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomizeAccessDenieHandle();
	}
	
	//EntryPoint for annonymous login into authenticated() 
	@Bean
	public AuthenticationEntryPoint delegatingEntryPoint() {
		return new Http403ForbiddenEntryPointCustom();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

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

	@Bean
	PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}

	LogoutSuccessHandler getLogoutSuccessHandler() {
		SimpleUrlLogoutSuccessHandler simpleLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
		simpleLogoutSuccessHandler.setDefaultTargetUrl(SecurityConstants.LOG_IN_URL);
		return simpleLogoutSuccessHandler;
	}

	LogoutHandler[] getLogoutHandlerArray() {
		LogoutHandlerCustomize logoutHandler = new LogoutHandlerCustomize();
		LogoutHandler[] handlers = new LogoutHandler[] { logoutHandler };
		return handlers;
	}

}
