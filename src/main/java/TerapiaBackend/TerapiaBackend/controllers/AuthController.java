package TerapiaBackend.TerapiaBackend.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import TerapiaBackend.TerapiaBackend.entities.RoleEntity;
import TerapiaBackend.TerapiaBackend.services.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticación de usuarios.
 * Maneja login, cambio de contraseña y verificación de sesión.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Inicia sesión autenticando al usuario con Spring Security.
     * Guarda el contexto de seguridad automáticamente.
     *
     * @param loginRequest credenciales del usuario
     * @return 200 OK si el login es exitoso, 401 si las credenciales son inválidas
     */
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    try {
        // Autenticación usando el AuthenticationManager
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getNombre(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful");
    } catch (AuthenticationException e) {
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}


    /**
     * Cambia la contraseña del usuario indicado.
     *
     * @param updatePasswordRequest contiene el nombre y la nueva contraseña
     * @return mensaje de éxito o error
     */
    @PostMapping("/update-password")
    public String updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        // Log para ver qué datos estamos recibiendo
        System.out.println("Recibiendo solicitud de cambio de contraseña para el usuario: " + updatePasswordRequest.getNombre());

        RoleEntity role = authService.updatePassword(updatePasswordRequest.getNombre(), updatePasswordRequest.getPassword());
        if (role != null) {
            // Log para indicar que el cambio de contraseña fue exitoso
            System.out.println("Contraseña actualizada exitosamente para: " + updatePasswordRequest.getNombre());
            return "Password updated successfully";
        } else {
            // Log para indicar que hubo un error al actualizar la contraseña
            System.out.println("Error al intentar actualizar la contraseña para: " + updatePasswordRequest.getNombre());
            throw new RuntimeException("Error updating password");
        }
    }

    /**
     * Devuelve el nombre del usuario actualmente autenticado.
     * Si no hay sesión activa, devuelve null.
     *
     * @return JSON con el nombre del usuario autenticado
     */
    @GetMapping("/session")
    public SessionResponse session() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            // Log para indicar que la sesión está activa
            System.out.println("Usuario autenticado: " + auth.getName());
            return new SessionResponse(auth.getName());
        }
        // Log para indicar que no hay sesión activa
        System.out.println("No hay sesión activa.");
        return new SessionResponse(null);
    }

    // ==========================
    // Clases auxiliares internas
    // ==========================

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SessionResponse {
        private String role;

        public SessionResponse(String role) {
            this.role = role;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LoginRequest {
        private String nombre;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdatePasswordRequest {
        private String nombre;
        private String password;
    }
}
