package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

  // DSL Domain Specific Language
  Optional<Etablissement> findByRne(String rne);
}
