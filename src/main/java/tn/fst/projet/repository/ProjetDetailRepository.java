package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.fst.projet.entity.ProjetDetail;

import java.util.List;

public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {

    // Page 4 - LIKE (manual wildcards)
    @Query("SELECT pd FROM ProjetDetail pd WHERE pd.technologie LIKE :tech")
    List<ProjetDetail> findByTechnologieLike(@Param("tech") String tech);

    // Contains (automatic wildcards)
    @Query("SELECT pd FROM ProjetDetail pd WHERE pd.technologie LIKE %:tech%")
    List<ProjetDetail> findByTechnologieContains(@Param("tech") String tech);

    // Containing (same as contains, alias)
    @Query("SELECT pd FROM ProjetDetail pd WHERE pd.technologie LIKE %:tech%")
    List<ProjetDetail> findByTechnologieContaining(@Param("tech") String tech);

    // Filter by cout greater than a value
    @Query("SELECT pd FROM ProjetDetail pd WHERE pd.cout > :cout")
    List<ProjetDetail> findByCoutGreaterThan(@Param("cout") Long cout);

    // Order by dateDebut
    @Query("SELECT pd FROM ProjetDetail pd ORDER BY pd.dateDebut ASC")
    List<ProjetDetail> findAllOrderByDateDebutAsc();
}