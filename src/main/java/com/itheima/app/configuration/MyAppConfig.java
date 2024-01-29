package com.itheima.app.configuration;

import com.itheima.app.converter.GuiGuMessageConverter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

@Configuration
public class MyAppConfig implements WebMvcConfigurer {
    /*
     * @Author GhostGalaxy
     * @Description 开启springboot对矩阵变量的支持方式1
     * @Date 16:50:51 2022/12/22
     * @Param [configurer]
     * @return void
     **/
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
    /*
     * @Author GhostGalaxy
     * @Description 开启springboot对矩阵变量的支持方式2
     * @Date 16:51:25 2022/12/22
     * @Param []
     * @return org.springframework.web.servlet.config.annotation.WebMvcConfigurer
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }
     **/
    //注入自定义的内容协商管理器的方式1
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new GuiGuMessageConverter());
//    }

//    public WebMvcRegistrations webMvcRegistrations(){
//        return new WebMvcRegistrations() {
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
//            }
//
//            @Override
//            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
//                return WebMvcRegistrations.super.getRequestMappingHandlerAdapter();
//            }
//
//            @Override
//            public ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
//                return WebMvcRegistrations.super.getExceptionHandlerExceptionResolver();
//            }
//        };
//    }
}
