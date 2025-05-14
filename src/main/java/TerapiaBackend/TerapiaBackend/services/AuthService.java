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
        if (role != null && passwordEncoder.matches(password, role.getPassword())) {
            return role;
        }
        return null;
    }
}
