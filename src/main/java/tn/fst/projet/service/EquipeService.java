package tn.fst.projet.service;

import tn.fst.projet.entity.Equipe;

import java.util.List;
import java.util.Optional;

public interface EquipeService {

    List<Equipe> getAll();

    Optional<Equipe> getById(Integer id);

    Equipe create(Equipe equipe);

    Equipe update(Integer id, Equipe equipe);

    void delete(Integer id);
}