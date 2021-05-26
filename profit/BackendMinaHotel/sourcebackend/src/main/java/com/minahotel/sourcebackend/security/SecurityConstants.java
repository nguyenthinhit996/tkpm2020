package com.minahotel.sourcebackend.security;

public class SecurityConstants {

	public static final String SECRET = "SECRET_KEY";
	public static final long EXPIRATION_TIME_ACCESSTOKEN = 300_000; // 5 minutes
	
	public static final String SECRET_REFRESH = "SECRET_KEY_REFRESH";
	public static final long EXPIRATION_TIME_REFERSHTOKEN = 600_000; // 10 minutes
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	
	public static final String LOG_IN_URL = "/login";
	public static final String LOG_OUT_URL = "/logout";
	public static final String REFRESH_TOKEN_URL = "/token";
}