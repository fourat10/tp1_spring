package tn.fst.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.projet.entity.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
}
