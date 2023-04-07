package com.hln.servicebase.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {


    /**
     * 注入自定义拦截类到spring容器
     * @return
     */
    @Bean
    public Interceptor getInterceptor() {
        return new Interceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(getInterceptor());
        registration.addPathPatterns("/**") ; //拦截
        registration.excludePathPatterns("/**/login","/association/front/**");//不拦截


    }

}
