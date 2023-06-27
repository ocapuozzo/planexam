package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VilleRepository extends JpaRepository<Ville, Long> {
  Optional<Ville> findByNom(String nomVille);
}
