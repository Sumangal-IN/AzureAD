package com.SpringBootAD.SpringBootADConnection.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.gson.JsonElement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAuthentication implements Authentication {

	private static final long serialVersionUID = 7952604359296099311L;

	private JsonElement claimsData = null;
	private boolean authenticated = ApplicationConstant.TRUE;

	public CustomAuthentication(JsonElement claimsData) {
		this(claimsData, ApplicationConstant.TRUE);
	}

	@Override
	public String getName() {
		return this.claimsData.getAsJsonObject().get("aud").getAsString();
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
		return this.claimsData.getAsJsonObject().get("aud").getAsString();
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
