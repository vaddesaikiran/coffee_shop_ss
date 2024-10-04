package com.example.CoffeeShop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
            http
              .authorizeHttpRequests(requests -> requests
                      .requestMatchers("/home", "/contact-us","/csrf-token","/csrf").permitAll()
                      .anyRequest().authenticated())
              .formLogin(Customizer.withDefaults())
              
              .sessionManagement(session -> 
               session
               .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
               .invalidSessionUrl("/session-expired")
               .maximumSessions(1)
               .expiredUrl("/session-expired")
               );
            
      return http.build();
  }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("saikiran")
                .password("{noop}s@123")
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("kishore")
                .password("{noop}k@123")
                .roles("USER")
                .build();

        UserDetails user3 = User.withUsername("rahul")
                .password("{noop}r@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
