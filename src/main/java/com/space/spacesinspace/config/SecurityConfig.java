package com.space.spacesinspace.config;

import com.space.spacesinspace.exception.AuthFailHandler;
import com.space.spacesinspace.exception.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AuthFailHandler authFailHandler;
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    public SecurityConfig(AuthFailHandler authFailHandler, AuthSuccessHandler authSuccessHandler) {
        this.authFailHandler = authFailHandler;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/auth/login", "/user/member/checkDuplicateId", "/user/member/findId",
                    "/auth/admin/login","/user/member/signup", "/auth/fail", "/fragments/*",
                    "/user/product/productList", "/user/product/productAll", "/user/product/productByCategory/*",
                    "/user/product/productDetail/*", "/img/**", "/static/**", "/uploadedFiles/**",
                    "/user/faq/list","/main", "/", "/error/**", "/error-500", "/error-404").permitAll();
            auth.requestMatchers("/admin/*").hasAnyAuthority("ADMIN");
            auth.requestMatchers("/member/*").hasAnyAuthority("USER", "ADMIN");
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/auth/login");
            login.usernameParameter("memberId");
            login.passwordParameter("memberPw");
//            login.defaultSuccessUrl("/", true);
            login.successHandler(authSuccessHandler);
            login.failureHandler(authFailHandler);
        })/*.formLogin(login -> {
            login.loginPage("/auth/admin/login");  // Separate login page for admin
            login.usernameParameter("memberId");
            login.passwordParameter("memberPw");
//            login.defaultSuccessUrl("/admin/member/selectAllMembers", true)  // Admin dashboard
            login.successHandler(authSuccessHandler);
            login.failureHandler(authFailHandler);
        })*/.logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
        }).sessionManagement(session -> {
            session.maximumSessions(1);
            session.invalidSessionUrl("/");
        }).csrf(csrf -> {
           csrf.disable();
        });

        return http.build();
    }


}
