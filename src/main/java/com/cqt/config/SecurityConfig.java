package com.cqt.config;

import com.cqt.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/register", "/index").permitAll()  // Public access
                        .requestMatchers("/customers/**").hasRole("CUSTOMER")  // Admin-only access for managing customers
                        .requestMatchers("/cqtc/**").hasRole("ADMIN")  // Admin-only access for managing customers queries
                        .requestMatchers("/employees/**").hasRole("ADMIN")  // Employee task hub
                        .requestMatchers("/employees/**").hasRole("ADMIN")  // Admin-only access for managing employees
                        .requestMatchers("/products/**").hasRole("ADMIN")  // Admin-only access for managing products
                        .requestMatchers("/customers/**").hasRole("ADMIN")  // Admin-only access for managing customers
                        .anyRequest().authenticated())  // All other requests require authentication

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            authentication.getAuthorities().forEach(grantedAuthority -> {
                                String role = grantedAuthority.getAuthority();
                                try {
                                    if (role.equals("ROLE_ADMIN")) {
                                        response.sendRedirect("/");  // Admin redirect
                                    } else if (role.equals("ROLE_EMPLOYEE")) {
                                        response.sendRedirect("/employees");  // Employee redirect
                                    }
                                    else if (role.equals("ROLE_CUSTOMER")) {
                                        response.sendRedirect("/customers");  // Employee redirect
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }))
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
