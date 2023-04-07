package com.hln.znbf.controller;

import com.hln.commonuntils.R;
import com.hln.znbf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("znbf/user")
public class LoginController {


    @Autowired
    private LoginService loginService;


    @PostMapping("dsrnh/login")
    public R dsrnhLogin(String username,String password){
        String token =  loginService.dsrnhLogin(username,password);
        return R.ok().data("Authorization",token);
    }

    /**
     * 后台退出
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public R loginOut(HttpServletRequest request){
        loginService.dsrnhLoginOut(request);
        return R.ok();
    }



}
