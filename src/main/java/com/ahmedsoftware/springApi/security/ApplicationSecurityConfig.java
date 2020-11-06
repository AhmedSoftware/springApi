package com.ahmedsoftware.springApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


import static com.ahmedsoftware.springApi.security.ApplicationUserRole.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses",true);
    }
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails azadUser=User.builder()
                .username("azad")
                .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.grantedAuthorities())
                .build();
        
        UserDetails aliUser = User.builder()
                .username("ali")
                .password(passwordEncoder.encode("password12345."))
                .authorities(ADMIN.grantedAuthorities())
                .build();
    
        UserDetails galissUser = User.builder()
                .username("galiss")
                .password(passwordEncoder.encode("password12345."))
                .authorities(ADMINTRAINEE.grantedAuthorities())
                .build();
    
        return new InMemoryUserDetailsManager(
                azadUser,
                aliUser,
                galissUser);
    }
}
