package org.collections.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
  @Bean
  SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(c-> {
      c.disable();
    })
        .authorizeHttpRequests(r -> {r
          .requestMatchers("/collections/latest", "/collections/largest").permitAll()
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
          .anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
