package com.tangshengbo.config;

import com.tangshengbo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Tangshengbo on 2018/10/8
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/*").excludePathPatterns("/static/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(0, new FastJsonHttpMessageConverter());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("home/login");
        registry.addViewController("/register").setViewName("home/register");
        registry.addViewController("/goods/list").setViewName("site/goods");
        registry.addViewController("/").setViewName("index.html");
    }
}
