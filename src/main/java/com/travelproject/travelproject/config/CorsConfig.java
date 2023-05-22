package com.travelproject.travelproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class CorsConfig  implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) { //CORS정책 설정 바꾸줘는 메서드
        registry
            .addMapping("/**") //어떠한 path에 대해서 허용을 할 것인지에 대해서 작성해주는 것
            .allowedMethods("*") //어떠한 Http Method에 대해서 허용을 할 것인지에 대해서 작성해주는 것
            .allowedOrigins("*"); //어떠한 출처에 대해서 허용을 할 것인지에 대해서 작성해주는 
            //.allowedOrigins설정은 보안상 프론트엔드 Origin 프로토콜, 호스트, 포트 번호만 허용해주는 것이 맞음
    }

}
