package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.fst.projet.entity.Projet;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, String> {

    // Page 5 - projets by technologie
    @Query("SELECT p FROM Projet p WHERE p.projetDetail.technologie LIKE %:tech%")
    List<Projet> findByTechnologie(@Param("tech") String tech);

    // Page 6 - projets by equipe id
    @Query("SELECT p FROM Projet p JOIN p.equipes e WHERE e.id = :equipeId")
    List<Projet> findByEquipeId(@Param("equipeId") Integer equipeId);

    // Page 7 - projets by equipe id with non-null description
    @Query("SELECT p FROM Projet p JOIN p.equipes e WHERE e.id = :equipeId AND p.projetDetail.description IS NOT NULL")
    List<Projet> findByEquipeIdWithDescription(@Param("equipeId") Integer equipeId);
}