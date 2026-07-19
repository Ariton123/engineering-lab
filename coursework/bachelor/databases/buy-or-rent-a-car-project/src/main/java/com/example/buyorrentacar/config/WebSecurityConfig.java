package com.example.buyorrentacar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;
    private final CustomAutheticationSuccessHandler autheticationSuccessHandler;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider, CustomAutheticationSuccessHandler autheticationSuccessHandler) {
        this.passwordEncoder = passwordEncoder;

        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
        this.autheticationSuccessHandler = autheticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/","/h2/**","/home","/register").permitAll()
                .antMatchers("/naracki/**").hasRole("SOPSTVENIK")
                .antMatchers("/avtomobili/**").hasRole("KUPUVAC")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .successHandler(autheticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("buyCar")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("kupuvac")
//                .password(passwordEncoder.encode("kupuvac"))
//                .authorities("ROLE_KUPUVAC")
//                .and()
//                .withUser("sopstvenik")
//                .password(passwordEncoder.encode("sopstvenik"))
//                .authorities("ROLE_SOPSTVENIK");
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);
    }
}