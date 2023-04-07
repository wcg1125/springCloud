package com.hln.znbf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.service.DsrnhService;
import com.hln.znbf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private DsrnhService dsrnhService;

    @Override
    public String dsrnhLogin(String username, String password) {
        QueryWrapper<Dsrnh> wrapper= new QueryWrapper<>();
        wrapper.eq("sfzh",username);
        Dsrnh dsrnh = dsrnhService.getOne(wrapper);
        if(dsrnh==null){
            throw new PracticeException(ResultCode.ERROR,"用户名不存在!");
        }
        if("0".equals(dsrnh.getStatus())){
            throw new PracticeException(ResultCode.ERROR,"账号被禁用！请联系管理员。");

        }
        if(!password.equals(dsrnh.getPassword())){
            throw new PracticeException(ResultCode.ERROR,"密码错误！");
        }
        //返回token
        String token = JwtUtils.getJwtToken(dsrnh.getId(), username);
        redisTemplate.opsForValue().set(token,username, 120, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public void dsrnhLoginOut(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        boolean flag = redisTemplate.hasKey(authorization);
        if(flag){
            //删除
            redisTemplate.delete(authorization);
        }
    }
}
