package org.collections.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Autowired
  AuthenticationProviderService authenticationProvider;

  @Bean
  SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(c-> {
      c.disable();
    })
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(r -> {r
          .requestMatchers("/collections/**", "/comments/**").permitAll()
          .requestMatchers(HttpMethod.POST, "/user/", "/user/login").permitAll()
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
          .anyRequest().authenticated();
        })
        .authenticationProvider(authenticationProvider)
        .formLogin(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));  // Correctly specify the allowed origin without a trailing slash
    configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS"));  // Specify allowed methods
    configuration.setAllowedHeaders(List.of("*"));  // Allow all headers
    configuration.setAllowCredentials(true);  // Allow credentials if needed

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  UserDetailsService UserDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(authenticationProvider)
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
