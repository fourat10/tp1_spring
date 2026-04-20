package tn.fst.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.fst.projet.entity.Projet;
import tn.fst.projet.entity.ProjetDetail;
import tn.fst.projet.repository.ProjetDetailRepository;
import tn.fst.projet.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private ProjetDetailRepository projetDetailRepository;

    @Override
    public List<Projet> getAll() {
        return projetRepository.findAll();
    }

    @Override
    public Optional<Projet> getById(String id) {
        return projetRepository.findById(id);
    }

    @Override
    public Projet create(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Projet update(String id, Projet projet) {
        if (!projetRepository.existsById(id)) {
            throw new RuntimeException("Projet not found with id: " + id);
        }
        projet.setId(id);
        return projetRepository.save(projet);
    }

    @Override
    public void delete(String id) {
        if (!projetRepository.existsById(id)) {
            throw new RuntimeException("Projet not found with id: " + id);
        }
        projetRepository.deleteById(id);
    }

    @Override
    public List<ProjetDetail> getByTechnologie(String tech) {
        return projetDetailRepository.findByTechnologieContains(tech);
    }

    @Override
    public List<Projet> getProjetsByTechnologie(String tech) {
        return projetRepository.findByProjetDetailTechnologieContains(tech);
    }

    @Override
    public List<Projet> getProjetsByEquipe(Integer equipeId) {
        return projetRepository.findByEquipesId(equipeId);
    }

    @Override
    public List<Projet> getProjetsByEquipeWithDescription(Integer equipeId) {
        return projetRepository.findByEquipesIdAndProjetDetailDescriptionNotNull(equipeId);
    }
}