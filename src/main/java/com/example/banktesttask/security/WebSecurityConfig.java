package com.example.banktesttask.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable().cors().configurationSource(corsConfigurationSource());

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/users/signin").permitAll()//
                .antMatchers("/users/signup").permitAll()//
                .antMatchers("/h2-console/**/**").permitAll()
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/public")

                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "OPTIONS", "DELETE"));
        configuration.setAllowedHeaders(
          List.of("Authorization", "Cache-Control", "Content-Type", "ngrok-skip-browser-warning"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
