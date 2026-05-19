package com.restaurantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth
                        // rutas publicas tanto GET como POST
                        .requestMatchers("/hola", "/adios", "/login",
                                "/register", "/css/**", "/images/**", "/webjars/**").permitAll()

                        // de golpe:
//                .requestMatchers(HttpMethod.GET, "/restaurants", "/restaurants/*", "/dishes", "/dishes/*").permitAll()

                        // separado de una en una
                        .requestMatchers(HttpMethod.GET, "/restaurantes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurantes/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurantes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurantes/deactivar/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurantes/nuevo").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurantes/editar/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/platos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/platos/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/platos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/platos/nuevo").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/platos/editar/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/resenas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/resenas/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/resenas").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/resenas/nuevo").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/resenas/editar/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/resenas/desactivar/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/resenas/borrar/*").hasRole("ADMIN")

                        // solo user normal, no admin
//                .requestMatchers(HttpMethod.GET, "/orders").hasRole("USER")
//                .requestMatchers(HttpMethod.GET, "/orders/new").hasRole("USER")
//                .requestMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")
                        // todos los roles
                        .requestMatchers("/pedidos", "/pedidos/**").authenticated()

                        // lo demás autenticado si o si
                        .anyRequest().authenticated()
        );

        http.formLogin(form ->
                form.loginPage("/login")
                        .defaultSuccessUrl("/restaurantes", true)
                        .permitAll()
        );

        return http.build();
    }
}
