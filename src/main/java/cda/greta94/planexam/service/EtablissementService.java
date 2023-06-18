package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtablissementRepository;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;


@Component()
public class EtablissementService {
  private EtablissementRepository etablissementRepository;

  public EtablissementService(EtablissementRepository etablissementRepository) {
    this.etablissementRepository = etablissementRepository;
  }

}
