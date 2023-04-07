package com.hln.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.association.entity.ActMember;
import com.hln.association.service.ActMemberService;
import com.hln.association.service.LoginService;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.MD5;
import com.hln.commonuntils.R;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private ActMemberService actMemberService;

    /**
     * 默认后台的用户名密码
     */
    private final  String name="admin";

    private final  String psd="123456";

    @Override
    public String adminLogin(String userName, String password) {

        if(!name.equals(userName)){
            throw new PracticeException(ResultCode.ERROR,"用户名不存在!");
        }
        if(!MD5.encrypt(name+psd).equals(MD5.encrypt(userName+password))){
            throw new PracticeException(ResultCode.ERROR,"密码错误!");
        }
        //返回token
        String token = JwtUtils.getJwtToken("5157", userName);
        redisTemplate.opsForValue().set(token,userName, 120, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public void adminLoginOut(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        boolean flag = redisTemplate.hasKey(authorization);
        if(flag){
            //删除
            redisTemplate.delete(authorization);
        }
    }

    @Override
    public String frontLogin(String username, String password) {
        QueryWrapper<ActMember> wrapper = new QueryWrapper<>();
        wrapper.eq("account",username);
        ActMember thisMember = actMemberService.getOne(wrapper);
        if(thisMember==null){
            throw new PracticeException(ResultCode.ERROR,"账号不存在！");
        }
        if(!thisMember.getPassword().equals(password)){
            throw new PracticeException(ResultCode.ERROR,"密码错误！");
        }

        //返回token
        String token = JwtUtils.getJwtToken(String.valueOf(thisMember.getId()), thisMember.getAccount());
        redisTemplate.opsForValue().set(token,username, 120, TimeUnit.MINUTES);
        return token;
    }
}
