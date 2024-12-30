package cn.lanqiao.library_final_project.config;

import cn.lanqiao.library_final_project.interceptor.JwtTokenAdminInterceptor;
import cn.lanqiao.library_final_project.interceptor.JwtTokenUserInterceptor;
import cn.lanqiao.library_final_project.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    /**
     * 扩展SpringMVC 框架的消息转换器
     * @param converters
     */
    @Override
     public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//       创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//       需要为消息转换器设置一个对象转换器，对象转换器可以将java对象序列号为JSON数据
        converter.setObjectMapper(new JacksonObjectMapper());
//        将自己的消息转换器加入容器
        converters.add(0,converter);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/html/admin/**")
                .addPathPatterns("/admin/**") // 拦截 /html/admin/ 下的所有请求
                .addPathPatterns("/book/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/html/admin/login.html");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")// 拦截 /html/user/ 下的所有请求
                .addPathPatterns("/html/common/**")
                .excludePathPatterns("/user/page/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/checkUsername")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/html/common/login.html")
                .excludePathPatterns("/html/common/register.html");
    }
}
