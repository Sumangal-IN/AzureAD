package com.SpringBootAD.SpringBootADConnection.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.gson.JsonElement;

public class CustomAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	JsonElement claimsData = null;
	boolean authenticated;

	public CustomAuthentication(JsonElement claimsData, boolean authenticated) {
		this.claimsData = claimsData;
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		return this.claimsData.getAsJsonObject().get("name").getAsString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		this.claimsData.getAsJsonObject().get("roles").getAsJsonArray()
				.forEach(x -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + x.getAsString())));
		return grantedAuthorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.claimsData.getAsJsonObject().get("preferred_username").getAsString();
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;

	}

}
