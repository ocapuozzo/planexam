package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
}
