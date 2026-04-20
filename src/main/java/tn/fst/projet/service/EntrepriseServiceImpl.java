package tn.fst.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.fst.projet.entity.Entreprise;
import tn.fst.projet.repository.EntrepriseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public List<Entreprise> getAll() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Optional<Entreprise> getById(Long id) {
        return entrepriseRepository.findById(id);
    }

    @Override
    public Entreprise create(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise update(Long id, Entreprise entreprise) {
        if (!entrepriseRepository.existsById(id)) {
            throw new RuntimeException("Entreprise not found with id: " + id);
        }
        entreprise.setIdEntreprise(id);
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void delete(Long id) {
        if (!entrepriseRepository.existsById(id)) {
            throw new RuntimeException("Entreprise not found with id: " + id);
        }
        entrepriseRepository.deleteById(id);
    }

    @Override
    public List<Entreprise> findByNomContaining(String nom) {
        return entrepriseRepository.findByNomContaining(nom);
    }

    @Override
    public List<Entreprise> findByAdresse(String adresse) {
        return entrepriseRepository.findByAdresse(adresse);
    }

    @Override
    public List<Entreprise> findEntreprisesWithEquipes() {
        return entrepriseRepository.findEntreprisesWithEquipes();
    }
}