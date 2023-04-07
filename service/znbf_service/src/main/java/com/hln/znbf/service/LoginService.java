package com.hln.znbf.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String dsrnhLogin(String username, String password);

    void dsrnhLoginOut(HttpServletRequest request);
}
