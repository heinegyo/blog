package com.lrm.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())//先加入攔截器
                .addPathPatterns("/admin/**")//增加要攔截的路徑
                .excludePathPatterns("/admin")//排除路徑 總不能連登入都攔截掉吧
                .excludePathPatterns("/admin/login");


    }
}
