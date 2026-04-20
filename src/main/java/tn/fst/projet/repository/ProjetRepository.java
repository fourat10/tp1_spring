package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.projet.entity.Projet;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, String> {

    List<Projet> findByProjetDetailTechnologieContains(String technologie);

    List<Projet> findByEquipesId(Integer equipeId);

    List<Projet> findByEquipesIdAndProjetDetailDescriptionNotNull(Integer equipeId);
}