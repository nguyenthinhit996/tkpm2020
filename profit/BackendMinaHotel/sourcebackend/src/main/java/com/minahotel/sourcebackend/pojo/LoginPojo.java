package com.minahotel.sourcebackend.pojo;

import java.io.Serializable;

/**
 * LoginPojo is object json communication client and server
 * @author Peter
 *
 */
public class LoginPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1333L;
	private String iduser;
	private String password;
	private String accessToken;
	private String refreshToken;
	private Boolean authenticated;
	private String role;
	private String fullName;

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String username) {
		this.iduser = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	 

	public LoginPojo(String iduser, String password, String accessToken, String refreshToken, Boolean authenticated,
			String role, String fullName) {
		super();
		this.iduser = iduser;
		this.password = password;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.authenticated = authenticated;
		this.role = role;
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LoginPojo() {
		super();
	}
}
