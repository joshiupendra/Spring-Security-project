package com.seemay.springSec.config;

import com.seemay.springSec.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF for PUT, POST, DELETE
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // Check Authentication for every request
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // Display Login form on Browser
        httpSecurity.formLogin(Customizer.withDefaults());
        // Enable basic authentication for PostMan
        httpSecurity.httpBasic(Customizer.withDefaults());
        // Making Http Stateless - to make this work on browser comment out formLogin()
        // httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // OAuth 2
        httpSecurity.oauth2Login(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Creating User Here
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("seemay")
                .password("root")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }*/
}
