package com.ch.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**")
                    .permitAll()
                .antMatchers("/", "/users/register", "/users/login", "/users/login-error")
                    .permitAll()
                .and()
                    .formLogin()
                    .loginPage("/users/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                    .failureForwardUrl("/users/login-error")//todo - implement
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

//                and().
//                oauth2Login().
//                loginPage("/login").
//                successHandler(oAuth2UserAuthSuccessHandler)

    }
}
