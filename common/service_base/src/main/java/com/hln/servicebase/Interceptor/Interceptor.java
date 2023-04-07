package com.hln.servicebase.Interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 第三方接口注册拦截器
 */
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /***
     * 在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了拦截器的preHandle方法");
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            throw new PracticeException(ResultCode.tokenCode,"参数为空！");
        }
        //解析token;判断token是否有效
        boolean flag = JwtUtils.checkToken(token);
        if(flag){
            boolean redisTokenStatus = redisTemplate.hasKey(token);
            if(!redisTokenStatus){
                throw new PracticeException(ResultCode.tokenCode,"token过期！");
            }
            //重置时间
            redisTemplate.expire(token,120L, TimeUnit.MINUTES);
        }else {
            throw new PracticeException(ResultCode.tokenCode,"token过期或token格式错误！");
        }
        return  true;

        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /***
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行了拦截器的postHandle方法");
        //重置时间
    }

    /***
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行了拦截器的afterCompletion方法");
    }
}
