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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    /**
     * Configuración del filtro de seguridad HTTP.
     * - Deshabilita CSRF (porque trabajamos con APIs y autenticación por sesión).
     * - Usa sesiones solo si es necesario (por login manual).
     * - Permite acceso público a rutas de autenticación.
     * - Protege todas las demás rutas con autenticación.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Usar sesiones cuando sea necesario
            )
            .formLogin()
                .loginPage("/login") // Configura la página de login
                .loginProcessingUrl("/api/auth/login") // URL para procesar el login
                .permitAll() // Permitir acceso al login sin autenticación
            .and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/api/auth/session") // Permitir login sin autenticación
                .permitAll()
                .anyRequest().authenticated() // Protege todas las demás rutas
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Configura CORS para aceptar cookies

        return http.build();
    }

    /**
     * Bean que expone el AuthenticationManager, necesario para autenticar usuarios manualmente.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Implementación personalizada de UserDetailsService para cargar usuarios y contraseñas desde el repositorio.
     */
    @Bean
    public UserDetailsService userDetailsService(RoleRepository roleRepository) {
        return username -> {
            RoleEntity role = roleRepository.findByNombre(username);
            if (role == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
            return new User(
                role.getNombre(),
                role.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        };
    }

    /**
     * Configura los permisos de CORS para permitir credenciales (cookies).
     * Se debe habilitar para que el frontend pueda acceder a las cookies desde dominios diferentes.
     */
    @Bean
public WebMvcConfigurer corsConfigurerForSecurity() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173") // O el dominio de tu frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true); // Asegúrate de permitir el uso de credenciales
        }
    };
}


    /**
     * Define el bean corsConfigurationSource para configurar CORS.
     */
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // Ajustar con tu dominio
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true); // Permitir credenciales (cookies)
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
