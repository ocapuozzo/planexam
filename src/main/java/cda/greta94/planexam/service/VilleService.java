package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.VilleRepository;
import cda.greta94.planexam.model.Ville;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

  private VilleRepository villeRepository;

  public VilleService(VilleRepository villeRepository) {
    this.villeRepository = villeRepository;
  }

  public Long getOrCreate(String nomVille) {
    nomVille = nomVille.isEmpty() ? "N/A": nomVille;
    Ville ville = villeRepository.findByNom(nomVille).orElse(null);
    if (ville == null) {
      ville = new Ville();
      ville.setNom(nomVille);
      villeRepository.save(ville);
    }
    return ville.getId();
  }

  public Ville getById(Long idVille) {
    return villeRepository.getReferenceById(idVille);
  }
}
