package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.fst.projet.entity.Entreprise;

import java.util.List;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    // Find by nom containing
    @Query("SELECT e FROM Entreprise e WHERE e.nom LIKE %:nom%")
    List<Entreprise> findByNomContaining(@Param("nom") String nom);

    // Find by adresse
    @Query("SELECT e FROM Entreprise e WHERE e.adresse LIKE %:adresse%")
    List<Entreprise> findByAdresse(@Param("adresse") String adresse);

    // Find entreprises that have equipes
    @Query("SELECT DISTINCT e FROM Entreprise e JOIN e.equipes eq")
    List<Entreprise> findEntreprisesWithEquipes();
}