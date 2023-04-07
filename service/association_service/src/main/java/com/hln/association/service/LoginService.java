package com.hln.association.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String adminLogin(String userName, String password);

    void adminLoginOut(HttpServletRequest request);

    String frontLogin(String username, String password);
}
