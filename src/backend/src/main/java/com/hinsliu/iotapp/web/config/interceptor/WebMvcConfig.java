package com.hinsliu.iotapp.web.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description: Config for interceptor.
 * @author: liuxuanming
 * @date: 2021/03/24 6:27 下午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    public IoTInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/app/login")
                .excludePathPatterns("/app/register")
                .excludePathPatterns("/app/callback")
                .excludePathPatterns("/default/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}
