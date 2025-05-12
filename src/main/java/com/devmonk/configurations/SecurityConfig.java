package com.devmonk.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                .requestMatchers("/login", "/registration", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()  // Ensure /login is allowed
                .anyRequest().authenticated()  // All other requests require authentication
            )
            .formLogin(form -> form
            	    .loginPage("/login")             // GET request - serves your custom page via controller
            	    .loginProcessingUrl("/perform_login") // POST request - handled by Spring Security, not a controller
            	    .defaultSuccessUrl("/home", true)
            	    .permitAll()
            	)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }



}
