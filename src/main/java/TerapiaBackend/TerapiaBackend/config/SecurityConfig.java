package TerapiaBackend.TerapiaBackend.config;

import TerapiaBackend.TerapiaBackend.repositories.RoleRepository;
import TerapiaBackend.TerapiaBackend.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crear sesión si es necesaria
            )
            // QUITAR formLogin para evitar redirección automática
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/api/auth/session", "/api/auth/logout")
                .permitAll()
                .anyRequest().authenticated()
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

@Bean
public UserDetailsService userDetailsService(RoleRepository roleRepository) {
    return username -> {
        RoleEntity user = roleRepository.findByNombre(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        String roleName = user.getNombre();
        if (roleName == null || roleName.isEmpty()) {
            roleName = "user"; // rol por defecto en minúsculas
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName.toLowerCase());

        return new User(
            user.getNombre(),
            user.getPassword(),
            Collections.singletonList(authority)
        );
    };
}



    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true); // Permitir cookies y credenciales
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
