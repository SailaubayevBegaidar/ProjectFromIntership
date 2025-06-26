package com.example.Coordinater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username = ?"
        );
        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_' || role as authority FROM users WHERE username = ?"
        );
        return manager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                        // Разграничение доступа по ролям
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers(
                                "/api/tasks/create",
                                "/api/tasks/save",
                                "/api/tasks/edit/**",
                                "/api/tasks/update",
                                "/api/tasks/delete/**"
                        ).hasAnyRole("ADMIN", "MASTER")
                        .requestMatchers(HttpMethod.POST, "/api/tasks/assign/**").hasRole("ENGINEER")
                        .requestMatchers(HttpMethod.POST, "/api/tasks/assign/**").denyAll()
                        .requestMatchers("/api/tasks/**").hasAnyRole("ADMIN", "MASTER", "ENGINEER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/api/tasks", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}
