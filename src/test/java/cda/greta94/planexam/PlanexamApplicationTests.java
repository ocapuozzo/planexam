package cda.greta94.planexam;

import cda.greta94.planexam.controller.AdminController;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.VilleService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;


@TestPropertySource(
				locations = "classpath:application-test.properties")
@SpringBootTest
class PlanexamApplicationTests {
	private Logger logger = LoggerFactory.getLogger(PlanexamApplicationTests.class);

	@Autowired
	EtablissementService etablissementService;
  @Autowired
	VilleService villeService;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	@Transactional
	void createNewEtablissement(){
		Long idVille = villeService.getOrCreate("PARIS");

		EtablissementDto etabDto = new EtablissementDto(null, "NomA", "RNEA", "CodificationA", "CCFA", idVille);

		logger.info("etabDto.id : " + etabDto.getId());
		assertNull(etabDto.getId());

		// l'établissement que l'on veut créer ne doit pas être déjà présent dans la BD
		Etablissement etab = etablissementService.findByNom("NomA").orElse(null);
		assertNull(etab);

		etablissementService.saveEtablissementFromEtablissementDto(etabDto);

		// Si ajouté à la base de données, alors on doit pouvoir le retrouver.
		etab = etablissementService.findByNom("NomA").orElse(null);
   	assertNotNull(etab);
	}
}
