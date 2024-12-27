package com.server.pin.global.config;

import com.server.pin.global.security.jwt.filter.JwtAuthenticationFilter;
import com.server.pin.global.security.jwt.filter.JwtExceptionFilter;
import com.server.pin.global.security.jwt.handle.JwtAccessDeniedHandler;
import com.server.pin.global.security.jwt.handle.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter, JwtExceptionFilter jwtExceptionFilter) throws Exception {
        return http
                .cors(configurer -> configurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)

                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/admin/**").permitAll()
                        .requestMatchers("/admin/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/getMyInfo", "/board/job/{postId}", "/board/job/candidates").authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/auth/teacher/**", "/board/job/application/agree").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/auth/teacher/**", "/board/job/application/deny").authenticated()

                        .requestMatchers(HttpMethod.POST, "/mailCheck", "/mailSend", "/auth/signup/**", "/auth/login", "/auth/reissue", "file/upload").anonymous()
                        .requestMatchers(HttpMethod.GET, "/board/club/list", "/board/club/{boardId}", "/board/job").permitAll()

                        .requestMatchers(HttpMethod.POST, "/board/club/post", "/board/job", "/board/job/{postId}/application").authenticated()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
