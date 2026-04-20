package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.fst.projet.entity.Domain;
import tn.fst.projet.entity.Equipe;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    // Find by domaine
    @Query("SELECT e FROM Equipe e WHERE e.domaine = :domaine")
    List<Equipe> findByDomaine(@Param("domaine") Domain domaine);

    // Find by nom containing
    @Query("SELECT e FROM Equipe e WHERE e.nom LIKE %:nom%")
    List<Equipe> findByNomContaining(@Param("nom") String nom);

    // Find equipes that have at least one projet
    @Query("SELECT DISTINCT e FROM Equipe e JOIN e.projets p")
    List<Equipe> findEquipesWithProjets();

    // Count projets per equipe
    @Query("SELECT e, COUNT(p) FROM Equipe e LEFT JOIN e.projets p GROUP BY e")
    List<Object[]> countProjetsPerEquipe();
}