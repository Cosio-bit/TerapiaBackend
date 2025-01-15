package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.UsuarioEntity;
import TerapiaBackend.TerapiaBackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity update(Long id, UsuarioEntity updatedUsuario) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(updatedUsuario.getNombre());
            usuario.setRut(updatedUsuario.getRut());
            usuario.setDireccion(updatedUsuario.getDireccion());
            usuario.setEmail(updatedUsuario.getEmail());
            usuario.setTelefono(updatedUsuario.getTelefono());
            usuario.setSexo(updatedUsuario.getSexo());
            usuario.setFecha_nacimiento(updatedUsuario.getFecha_nacimiento());
            usuario.setSaldo(updatedUsuario.getSaldo());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
}
