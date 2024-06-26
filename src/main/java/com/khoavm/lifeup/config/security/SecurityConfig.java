package com.khoavm.lifeup.config.security;

import com.khoavm.lifeup.filter.ExceptionFilter;
import com.khoavm.lifeup.filter.JWTValidatorFilter;
import com.khoavm.lifeup.filter.JwtGeneratorFilter;
import com.khoavm.lifeup.filter.TraceIdFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TraceIdFilter traceIdFilter;
    private final JwtGeneratorFilter jwtGeneratorFilter;
    private final JWTValidatorFilter jwtValidatorFilter;
    private final Oauth2SuccessHandler oAuth2AuthenticationSuccessHandler;
    private final ExceptionFilter exceptionFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/login", "/user/sign-up", "/login/oauth2/code/google", "/user/test").permitAll()
                        .anyRequest().authenticated()

                )
                //.exceptionHandling(e -> e.authenticationEntryPoint(authEntryPoint))
                .addFilterBefore(exceptionFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(traceIdFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtGeneratorFilter, BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oAuthLogin -> oAuthLogin.successHandler(oAuth2AuthenticationSuccessHandler))
                .anonymous(AbstractHttpConfigurer::disable);
        return http.build();

    }


}
