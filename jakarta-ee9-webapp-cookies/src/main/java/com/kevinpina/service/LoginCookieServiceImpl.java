package com.kevinpina.service;

import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCookieServiceImpl implements LoginCookieService {

	@Override
	public Optional<String> getUsername(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
		return Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).map(Cookie::getValue).findAny();
	}

}
