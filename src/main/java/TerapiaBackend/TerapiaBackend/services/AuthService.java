package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.RoleEntity;
import TerapiaBackend.TerapiaBackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

public RoleEntity authenticate(String nombre, String password) {
    RoleEntity role = roleRepository.findByNombre(nombre);
    if (role != null) {
        boolean isPasswordMatch = passwordEncoder.matches(password, role.getPassword());
        System.out.println("Comparando contraseñas: " + password + " con " + role.getPassword() + " => " + isPasswordMatch);
        if (isPasswordMatch) {
            return role;
        }
    }
    return null;
}


    // Método para actualizar la contraseña
    public RoleEntity updatePassword(String nombre, String newPassword) {
        RoleEntity role = roleRepository.findByNombre(nombre);
        if (role != null) {
            // Hashear la nueva contraseña
            String hashedPassword = passwordEncoder.encode(newPassword);
            role.setPassword(hashedPassword);
            roleRepository.save(role); // Guardar el rol con la nueva contraseña
            return role;
        }
        return null;
    }
}
