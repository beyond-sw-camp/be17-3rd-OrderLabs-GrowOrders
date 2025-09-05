package org.example.groworders.config.security;

import lombok.RequiredArgsConstructor;
import org.example.groworders.config.filter.JwtAuthFilter;
import org.example.groworders.config.filter.LoginFilter;
import org.example.groworders.config.oauth.OAuth2AuthenticationSuccessHandler;
import org.example.groworders.domain.users.service.OAuth2UserService;
import org.example.groworders.domain.users.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationConfiguration configuration;
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {

        // JWT 로그인 필터
        LoginFilter loginFilter = new LoginFilter(configuration.getAuthenticationManager(), userService);
        loginFilter.setFilterProcessesUrl("/login"); // JWT 로그인 전용 URL

        http
                // OAuth2 로그인 설정
                .oauth2Login(oauth -> oauth
                        .loginPage("/login/oauth2") // 일반 로그인 페이지와 분리
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                )

                // 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/user/signup").permitAll()
                        .requestMatchers("/test/**").hasRole("USER")
                        .requestMatchers("/order/**", "/payment/**", "/cart/**", "/ws/**").permitAll()
                        .requestMatchers("/crops/**", "/inventories/**", "/farms/**").permitAll()
                        .anyRequest().permitAll()
                )

                // CORS / CSRF / 기타 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        // 필터 순서
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // JWT 인증
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class); // JWT 로그인 처리

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:8081", "http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
