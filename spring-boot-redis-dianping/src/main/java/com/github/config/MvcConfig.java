package com.github.config;

import com.github.inteceptor.LoginInteceptor;
import com.github.inteceptor.RefreshTokenInteceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登陆拦截器
        registry.addInterceptor(new LoginInteceptor())
                .excludePathPatterns("/user/code",
                        "/user/login",
                        "/blog/hot",
                        "/shop-type/**",
                        "/upload/**",
                        "/voucher/**",
                        "/shop/**",
                        "/hmdp/**")
                .order(1);// order值越大，执行的优先级越低
        // token刷新拦截器
        registry.addInterceptor(new RefreshTokenInteceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .order(0);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("开始静态资源映射");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/hmdp/**").addResourceLocations("classpath:/hmdp/");
    }
}
