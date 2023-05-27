package com.example.practice.configs;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Config implements WebMvcConfigurer{
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/img/**").addResourceLocations("file:resources");
        registry.addResourceHandler("/static.css/**").addResourceLocations("classpath:/static/");
    }
}