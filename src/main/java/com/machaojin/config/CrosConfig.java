package com.machaojin.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/14 11:43
 * 跨域请求
 */
@Slf4j
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    /**
     * 实现跨域请求
     * @param registry 请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS","PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
     log.info("跨域请求");
    }

    /**
     * mp分页插件拦截器
     * @return 返回分页插件
     */
    @Bean
    public MybatisPlusInterceptor myBatisPlusInterceptors(){
        log.info("MyBatis分页插件拦截器");
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}



