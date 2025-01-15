package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    public List<ArriendoEntity> findAll() {
        return arriendoRepository.findAll();
    }

    public ArriendoEntity save(ArriendoEntity arriendo) {
        return arriendoRepository.save(arriendo);
    }

    public List<ArriendoEntity> findByEstado(String estado) {
        return arriendoRepository.findByEstado(estado);
    }

    public void deleteById(Long id) {
        arriendoRepository.deleteById(id);
    }
}
