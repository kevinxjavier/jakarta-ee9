package com.kevinpina.service;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginCookieService {

	Optional<String> getUsername(HttpServletRequest req);

}
