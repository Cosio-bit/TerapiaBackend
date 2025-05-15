package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.RoleEntity;
import TerapiaBackend.TerapiaBackend.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getNombre(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Crear sesión HTTP y guardar contexto seguridad
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        RoleEntity role = authService.updatePassword(updatePasswordRequest.getNombre(), updatePasswordRequest.getPassword());
        if (role != null) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(400).body("Error updating password");
        }
    }

    @GetMapping("/session") 
public SessionResponse session() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
        // Obtener el primer rol (authority) asignado al usuario
        String role = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("user"); // rol por defecto si no tiene ninguno

        // Limpiar el prefijo "ROLE_" si existe y pasar a minúsculas
        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }
        role = role.toLowerCase();

        System.out.println("Usuario autenticado: " + auth.getName() + ", rol: " + role);

        return new SessionResponse(role);
    }

    System.out.println("No hay sesión activa.");
    return new SessionResponse(null);
}


    // Clases auxiliares internas

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
