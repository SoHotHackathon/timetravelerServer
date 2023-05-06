package com.example.Back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/v1/**", "/", "/v1/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(
                        "http://localhost:8080/*",
                        "http://localhost:8080",
                        "/api/v1/auth/**",
                        "/oauth/**",
                        "/script/**",
                        "/forum/**",
                        "/interview/**",
                        "/**",
                        "/members/**",
                        "/api/v1/version",
                        "/auth/**",
                        "/token/**").permitAll()
                .anyRequest().authenticated();

        http.cors() // CORS 구성 추가
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(
                        "http://localhost:8080/*",
                        "http://localhost:8080",
                        "/oauth/**",
                        "/script/**",
                        "/forum/**",
                        "/interview/**",
                        "/**",
                        "/members/**",
                        "/api/v1/version",
                        "/auth/**",
                        "/token/**").permitAll()
                .anyRequest().authenticated();
    }


}