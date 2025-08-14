package com.project.testApp.config;

import com.project.testApp.security.JwtAuthenticationEntryPoint;
import com.project.testApp.security.JwtAuthenticationFilter;
import com.project.testApp.security.JwtTokenProvider;
import com.project.testApp.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static com.project.testApp.config.SecurityConfigText.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint handler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        try {
            log.info(CREATING_JWT_AUTHENTICATION_FILTER_BEAN);
            return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
        } catch (Exception e) {
            log.error(ERROR_CREATING_JWT_AUTHENTICATION_FILTER_BEAN, e.getMessage());
            throw e;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        try {
            log.info(CREATING_BCRYPT_PASSWORD_ENCODER_BEAN);
            return new BCryptPasswordEncoder();
        } catch (Exception e) {
            log.error(ERROR_CREATING_BCRYPT_PASSWORD_ENCODER_BEAN, e.getMessage());
            throw e;
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        try {
            log.info(CREATING_AUTHENTICATION_MANAGER_BEAN);
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            log.error(ERROR_CREATING_AUTHENTICATION_MANAGER_BEAN, e.getMessage());
            throw e;
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        try {
            log.info(CREATING_CORS_FILTER_BEAN);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("http://localhost:3000");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        } catch (Exception e) {
            log.error(ERROR_CREATING_CORS_FILTER_BEAN, e.getMessage());
            throw e;
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) throws Exception {
        try {
            log.info(CONFIGURING_HTTP_SECURITY);
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(withDefaults())
                    .exceptionHandling(ex -> ex.authenticationEntryPoint(handler))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/api/v1/**").authenticated()
                            .anyRequest().permitAll()
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtAuthenticationFilter(jwtTokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        } catch (Exception e) {
            log.error(ERROR_CONFIGURING_HTTP_SECURITY, e.getMessage());
            throw e;
        }
    }
}