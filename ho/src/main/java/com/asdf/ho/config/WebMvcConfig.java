package com.asdf.ho.config;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final  long MAX_AGE_SECS = 3600;//추가

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://s3.url", "http://localhost:3000", "http://domain.shop/", "http://localhost:8080")
                //클라이언트 로컬 주소임. 클라이언트에서 내 서버의 api에 접근 시 허용에 관한 부분. CORS.
                //2개 이상의 origin에 대해서 허용할 수 있음!

                .allowedHeaders("*")  //추가
                .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS") // 클라이언트에서 요청하는 메소드 어디까지 허용할 것인가.
                .allowCredentials(true)
                .exposedHeaders("X-AUTH-TOKEN")  //추가
                .maxAge(MAX_AGE_SECS);  //추가
    }

//    @Bean
//    public TomcatContextCustomizer sameSiteCookiesConfig() {
//        return context -> {
//            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
//            cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
//            context.setCookieProcessor(cookieProcessor);
//        };
//    }
}