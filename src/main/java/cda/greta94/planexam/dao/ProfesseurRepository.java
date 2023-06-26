package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}