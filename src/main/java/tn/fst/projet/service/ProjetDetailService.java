package tn.fst.projet.service;

import tn.fst.projet.entity.ProjetDetail;

import java.util.List;
import java.util.Optional;

public interface ProjetDetailService {

    List<ProjetDetail> getAll();

    Optional<ProjetDetail> getById(Long id);

    ProjetDetail create(ProjetDetail projetDetail);

    ProjetDetail update(Long id, ProjetDetail projetDetail);

    void delete(Long id);

    List<ProjetDetail> getByTechnologieLike(String technologie);

    List<ProjetDetail> getByTechnologieContains(String technologie);

    List<ProjetDetail> getByTechnologieContaining(String technologie);
}