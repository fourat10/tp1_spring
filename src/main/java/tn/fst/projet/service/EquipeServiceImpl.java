package tn.fst.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.fst.projet.entity.Equipe;
import tn.fst.projet.repository.EquipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public List<Equipe> getAll() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<Equipe> getById(Integer id) {
        return equipeRepository.findById(id);
    }

    @Override
    public Equipe create(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe update(Integer id, Equipe equipe) {
        if (!equipeRepository.existsById(id)) {
            throw new RuntimeException("Equipe not found with id: " + id);
        }
        equipe.setId(id);
        return equipeRepository.save(equipe);
    }

    @Override
    public void delete(Integer id) {
        if (!equipeRepository.existsById(id)) {
            throw new RuntimeException("Equipe not found with id: " + id);
        }
        equipeRepository.deleteById(id);
    }
}