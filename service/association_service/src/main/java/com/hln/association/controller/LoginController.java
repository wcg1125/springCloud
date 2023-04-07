package com.hln.association.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.association.service.LoginService;
import com.hln.commonuntils.R;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("association/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 后台登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("admin/login")
    public R adminLogin(String username,String password){
       String token =  loginService.adminLogin(username,password);
        return R.ok().data("Authorization",token);
    }

    /**
     * 前台登录
     */
    @PostMapping("front/login")
    public R frontLogin(String username,String password){
        String token =  loginService.frontLogin(username,password);
        return R.ok().data("Authorization",token);
    }

    /**
     * 后台退出
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public R loginOut(HttpServletRequest request){
        loginService.adminLoginOut(request);
        return R.ok();
    }

}
