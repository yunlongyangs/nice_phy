package cn.nice.nice_phy.config;

import cn.nice.nice_phy.handler.JwtHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
    @Autowired
    private JwtHandler jwtHandler;

    //静态资源映射
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtHandler).addPathPatterns("/**")
                .excludePathPatterns("/**/login*")
                .excludePathPatterns("/user/**")
        .excludePathPatterns("/static/**");
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login.html").setViewName("login.html");
        registry.addViewController("").setViewName("index.html");
        registry.addViewController("index.html").setViewName("index.html");
    }
}
