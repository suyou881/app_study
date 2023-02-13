package com.study.config;

import com.study.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AccountService accountService;
    private final DataSource dataSource;

    /**
     * Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter 는 Deprecated.
     * -> SecurityFilterChain, WebSecurityCustomizer 를 상황에 따라 빈으로 등록해 사용한다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/","/ex", "/login", "/sign-up", "/check-email", "/check-email-token",
                                "/email-login", "/check-email-login", "login-link", "/profile/*").permitAll()
                   //get이든 post든 상관없이
                   .requestMatchers(HttpMethod.GET, "/profile/*").permitAll()
                   //get 요청만 허용
                   .anyRequest().authenticated()
                   //나머지 요청은 로그인해야만 사용가능
                   .and()
                   .formLogin().loginPage("/login").permitAll()
                   .and()
                   .logout().logoutSuccessUrl("/")
//                   .and()
//                   .rememberMe().userDetailsService(accountService).tokenRepository(tokenRepository())
                   .and().build();
    }

    //Don't know how to access to static resources
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                           .requestMatchers("/node_modules/**")
                           .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/images/**", "/static");
    }


}
