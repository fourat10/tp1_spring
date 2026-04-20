package tn.fst.projet.service;

import tn.fst.projet.entity.Entreprise;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {

    List<Entreprise> getAll();

    Optional<Entreprise> getById(Long id);

    Entreprise create(Entreprise entreprise);

    Entreprise update(Long id, Entreprise entreprise);

    void delete(Long id);

    List<Entreprise> findByNomContaining(String nom);

    List<Entreprise> findByAdresse(String adresse);

    List<Entreprise> findEntreprisesWithEquipes();
}