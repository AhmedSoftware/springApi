package com.ahmedsoftware.springApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import static com.ahmedsoftware.springApi.security.ApplicationUserRole.*;
import static com.ahmedsoftware.springApi.security.ApplicatonUserPermission.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //disable csrf to secure our application
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails azadUser=User.builder()
                .username("azad")
                .password(passwordEncoder.encode("password"))
                //.roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.grantedAuthorities())
                .build();
        
        UserDetails aliUser = User.builder()
                .username("ali")
                .password(passwordEncoder.encode("password12345."))
                //.roles(ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.grantedAuthorities())
                .build();
    
        UserDetails galissUser = User.builder()
                .username("galiss")
                .password(passwordEncoder.encode("password12345."))
                //.roles(ADMINTRAINEE.name()) //ROLE_ADMINTRAINNE
                .authorities(ADMINTRAINEE.grantedAuthorities())
                .build();
    
        return new InMemoryUserDetailsManager(
                azadUser,
                aliUser,
                galissUser);
    }
}
