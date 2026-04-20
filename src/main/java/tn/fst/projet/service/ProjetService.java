package tn.fst.projet.service;

import tn.fst.projet.entity.Projet;
import tn.fst.projet.entity.ProjetDetail;

import java.util.List;
import java.util.Optional;

public interface ProjetService {

    // GET all
    List<Projet> getAll();

    // GET by ID
    Optional<Projet> getById(String id);

    // CREATE
    Projet create(Projet projet);

    // UPDATE
    Projet update(String id, Projet projet);

    // DELETE
    void delete(String id);

    // Page 4
    List<ProjetDetail> getByTechnologie(String tech);

    // Page 5
    List<Projet> getProjetsByTechnologie(String tech);

    // Page 6
    List<Projet> getProjetsByEquipe(Integer equipeId);

    // Page 7
    List<Projet> getProjetsByEquipeWithDescription(Integer equipeId);
}