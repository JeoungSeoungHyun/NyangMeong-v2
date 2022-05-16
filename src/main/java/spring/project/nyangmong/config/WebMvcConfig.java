package spring.project.nyangmong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import spring.project.nyangmong.config.intercepter.SessionIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // @Value("${file.path}")
    // private String uploadFolder;

    //   @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) { // web.xml이 가지고있는 기본설정이므로 날리면 안됨!
    //     WebMvcConfigurer.super.addResourceHandlers(registry);

    //     registry
    //         .addResourceHandler("/upload/**")
    //         .addResourceLocations("file:///" + uploadFolder) // file 프로토콜은 :/// 사용
    //         .setCachePeriod(60*60) // 초 단위 => 한시간
    //         .resourceChain(true)
    //         .addResolver(new PathResourceResolver());
    // }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionIntercepter())
                .addPathPatterns("/s/**"); // 주소 /s 인것만 실행된다. // *, **
    }

}