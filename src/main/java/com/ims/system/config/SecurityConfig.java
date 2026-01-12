//package com.ims.system.config;
//
//import com.ims.system.service.UserDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserDetailsServiceImpl userDetailsService;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.userDetailsService = userDetailsService;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST API
//                .cors(Customizer.withDefaults()) // Enable CORS
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT
//                .authorizeHttpRequests(auth -> auth
//                        // ⭐ PUBLIC ENDPOINTS - NO AUTHENTICATION REQUIRED
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/hello").permitAll()
//                        .requestMatchers("/api/public/**").permitAll()
//                        .requestMatchers("/", "/index.html", "/error").permitAll()
//
//                        // 🔒 PROTECTED ENDPOINTS - AUTHENTICATION REQUIRED
//                        .requestMatchers("/api/users/**").authenticated() // Any authenticated user can access
//                        .requestMatchers("/api/laptops/**").authenticated() // Any authenticated user can access
//                        .requestMatchers("/api/assignments/**").authenticated()
//                        .requestMatchers("/api/return-leasing/**").authenticated() // Changed from hasRole("ADMIN") to authenticated()
//                        .requestMatchers("/api/repairs/**").authenticated() // Changed from hasRole("ADMIN") to authenticated()
//
//                        // Everything else requires authentication
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

package com.ims.system.config;

import com.ims.system.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST API
                .cors(Customizer.withDefaults()) // Enable CORS (if you have CORS config)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT
                .authorizeHttpRequests(auth -> auth
                        // ⭐ PUBLIC ENDPOINTS
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/hello").permitAll()
                        .requestMatchers("/api/public/**").permitAll()

                        // Allow static frontend files and root
                        .requestMatchers("/", "/index.html", "/static/**", "/public/**", "/css/**", "/js/**", "/images/**", "/error").permitAll()

                        // 🔒 PROTECTED API ENDPOINTS
                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers("/api/laptops/**").authenticated()
                        .requestMatchers("/api/assignments/**").authenticated()
                        .requestMatchers("/api/return-leasing/**").authenticated()
                        .requestMatchers("/api/repairs/**").authenticated()

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}