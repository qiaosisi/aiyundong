package com.ranba;

import com.ranba.web.MySessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //媒体适配器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MySessionInterceptor()).addPathPatterns("/my/**").addPathPatterns("/adm/**");
    }

}
