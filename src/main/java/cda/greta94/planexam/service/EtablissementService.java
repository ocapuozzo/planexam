package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtablissementRepository;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Etablissement;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.List;


@Component()
public class EtablissementService {
  private EtablissementRepository etablissementRepository;

  public EtablissementService(EtablissementRepository etablissementRepository) {
    this.etablissementRepository = etablissementRepository;
  }

  public void saveEtablissementFromEtablissementDto(EtablissementDto etablissementDto) {
    Etablissement etablissement = null;
    if ((etablissementDto.getId() != null)) {
      etablissement = etablissementRepository.findById(etablissementDto.getId()).orElseThrow(NotFoundEntityException::new);
    } else {
      if ((etablissementDto.getRne() != null)) {
        etablissement = etablissementRepository.findByRne(etablissementDto.getRne()).orElse(null);
      }
      if (etablissement == null) etablissement = new Etablissement();
    }
    etablissement.setNom(etablissementDto.getNom());
    etablissement.setCcf(etablissementDto.getCcf());
    etablissement.setRne(etablissementDto.getRne());
    etablissement.setCode(etablissementDto.getCode());

    etablissementRepository.save(etablissement);
  }

  public List<Etablissement> getAll() {
    return etablissementRepository.findAll();
  }

  public EtablissementDto findEtablissementDtoById(long id) {
    Etablissement etab = etablissementRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new EtablissementDto(etab.getId(), etab.getNom(), etab.getRne(), etab.getCode(),etab.getCcf(), (etab.getVille() != null) ? etab.getVille().getId() : null);
  }
}
