package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.projet.entity.ProjetDetail;

import java.util.List;

public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {

    List<ProjetDetail> findByTechnologieLike(String technologie);

    List<ProjetDetail> findByTechnologieContains(String technologie);

    List<ProjetDetail> findByTechnologieContaining(String technologie);
}
