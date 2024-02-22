package com.ecomerce.config;

import com.ecomerce.services.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PMSSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        //UserDetails user = User.withUsername("user").password(passwordEncoder.encode("password")).roles("USER").build();
        //UserDetails user2 = User.withUsername("user1").password(passwordEncoder.encode("password1")).roles("ADMIN").build();
        //UserDetails user3 = User.withUsername("user2").password(passwordEncoder.encode("password2")).roles("USER", "ADMIN").build();
        //return new InMemoryUserDetailsManager(user, user2, user3);
        return new UserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((AbstractHttpConfigurer::disable))
                .authorizeHttpRequests((authz) ->
                        authz.
                                requestMatchers("/users/create", "/users/invoice/download/**",
                                        "/swagger-ui/index.html/**", "/v3/api-docs/**")
                                .permitAll()
                                .requestMatchers("/users/**").authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public RoleHierarchy roleHierarchy() {
        System.out.println("arrive public RoleHierarchy roleHierarchy()");
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return r;
    }

}