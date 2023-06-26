package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}