package ru.feryafox.hacktemplate.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feryafox.hacktemplate.utils.auth.JwtAuthorizationFilter;
import ru.feryafox.hacktemplate.utils.auth.jwt.JwtUtilsBase;

@Configuration
public class AuthBeans {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtUtilsBase jwtUtilsBase() {
        return new JwtUtilsBase(jwtSecret);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtilsBase());
    }
}
