package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.RoleEntity;
import TerapiaBackend.TerapiaBackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;@Service
public class AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Autentica al usuario comparando nombre y password.
     * @param nombre nombre de usuario
     * @param password password en texto plano recibido
     * @return RoleEntity si autenticación exitosa, null si falla
     */
    public RoleEntity authenticate(String nombre, String password) {
        RoleEntity user = roleRepository.findByNombre(nombre);
        if (user != null) {
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            System.out.println("Comparando contraseñas para usuario '" + nombre + "': " + isPasswordMatch);
            if (isPasswordMatch) {
                return user;
            }
        }
        return null;
    }

    /**
     * Actualiza la contraseña de un usuario dado el nombre.
     * @param nombre nombre usuario
     * @param newPassword contraseña nueva en texto plano
     * @return RoleEntity actualizado o null si no existe usuario
     */
    public RoleEntity updatePassword(String nombre, String newPassword) {
        RoleEntity user = roleRepository.findByNombre(nombre);
        if (user != null) {
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);
            roleRepository.save(user);
            System.out.println("Contraseña actualizada para usuario '" + nombre + "'");
            return user;
        }
        return null;
    }
}