package com.example.Back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // CORS 설정 추가
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() // CORS preflight 요청에 대한 응답 허용
                .antMatchers("/").permitAll() // 루트 경로 접근 허용
                .anyRequest().authenticated() // 인증된 요청에 대해서만 접근 허용
                .and()
                .formLogin().disable() // 기본 로그인 페이지 사용하지 않음
                .httpBasic().disable() // HTTP 기본 인증 사용하지 않음
                .logout().disable(); // 로그아웃 사용하지 않음
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/**"); // 모든 요청에 대해 인증/인가 무시
    }
}