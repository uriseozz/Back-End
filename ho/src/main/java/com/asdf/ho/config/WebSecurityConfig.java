package com.asdf.ho.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.



        http.authorizeRequests()
                .antMatchers("/**").permitAll()
// image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//// css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//// 회원 관리 처리 API 전부를 login 없이 허용
//                .antMatchers("/api/user/**").permitAll()
//
//                .antMatchers("/api/posting/**").permitAll()
//
//                .antMatchers("/list").permitAll()
//
//                .antMatchers("/webjars/bootstrap/4.5.0/css/bootstrap.min.css").permitAll()
//                .antMatchers("/webjars/jquery/3.5.1/jquery.min.js").permitAll()
//                .antMatchers("/webjars/bootstrap/4.5.0/js/bootstrap.min.js").permitAll()
//                .antMatchers("https://fonts.gstatic.com").permitAll()
//                .antMatchers("https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap").permitAll()



// 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
//// [로그인 기능]
//                .formLogin()
//// 로그인 View 제공 (GET /user/login)
//                .loginPage("/user/login")
//// 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//// 로그인 처리 후 성공 시 URL
//                .defaultSuccessUrl("/list")
//// 로그인 처리 후 실패 시 URL
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//// [로그아웃 기능]
//                .logout()
//// 로그아웃 처리 URL
//                .logoutUrl("/user/logout")
//                .permitAll();
    }
}