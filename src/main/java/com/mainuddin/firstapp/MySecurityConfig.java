package com.mainuddin.firstapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter { // 3 important method to override

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyAuthenticationProvider authenticationProvider;


//    @Override // its to modify manager ->provider->UDS & PasswordEncoder
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("mainuddin").password(passwordEncoder.encode("passwayne")).authorities("read").build();
//        userDetailsService.createUser(user);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().permitAll();
        http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


//---------------------- DEPRECATED REPLACED----------------
//-----Below are the replace one of two override method of WebSecurityConfigurerAdapter -> use mean instead of method

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder,
//                                             UserDetailsService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authenticationProvider)
//                .build();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//        return http.build();
//
//    }