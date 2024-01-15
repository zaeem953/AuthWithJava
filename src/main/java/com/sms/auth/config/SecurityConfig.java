package com.sms.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public CustomAuthSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests().requestMatchers("/","/register","/login","/saveUser").permitAll()
//                .requestMatchers("/user/**").authenticated()
//                .and().formLogin().loginPage("/login").loginProcessingUrl("/userLogin")
////                .usernameParameter("email")
//                .defaultSuccessUrl("/user/profile").permitAll();

        httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers("user/**").hasRole("USER")
                .requestMatchers("admin/**").hasRole("ADMIN")
                .requestMatchers("/**").permitAll().and()
                .formLogin().loginPage("/login").loginProcessingUrl("/userLogin")
                .successHandler(successHandler)
                .and().logout().permitAll();
        return httpSecurity.build();
    }
}
