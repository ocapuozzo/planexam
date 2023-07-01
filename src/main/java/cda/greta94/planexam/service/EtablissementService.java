package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.EtablissementRepository;
import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Etablissement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;


@Component()
public class EtablissementService {
  private Logger logger = LoggerFactory.getLogger(EtablissementService.class);

  private EtablissementRepository etablissementRepository;
  private VilleService villeService;

  public EtablissementService(EtablissementRepository etablissementRepository, VilleService villeService) {
    this.etablissementRepository = etablissementRepository;
    this.villeService = villeService;
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
    etablissement.setVille(villeService.getById(etablissementDto.getIdVille()));

    etablissementRepository.save(etablissement);
  }

  public List<Etablissement> getAll() {
    return etablissementRepository.findAll();
  }

  public EtablissementDto findEtablissementDtoById(long id) {
    Etablissement etab = etablissementRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    return new EtablissementDto(etab.getId(), etab.getNom(), etab.getRne(), etab.getCode(),etab.getCcf(), (etab.getVille() != null) ? etab.getVille().getId() : null);
  }

  public Optional<Etablissement> findById(Long id) {
    return etablissementRepository.findById(id);
  }

  public Optional<Etablissement>  findByNom(String nomVille) {
    return etablissementRepository.findByNom(nomVille);
  }

  public void importEtablissementFromCSVFile(MultipartFile file) throws IOException {
    Reader in = new InputStreamReader(file.getInputStream());
    // l'entête : "id","Nom","Ville","RNE","Codification","CCF","CCF 2016"
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("id", "Nom", "Ville", "RNE", "Codification", "CCF", "CCF 2016").parse(in);
//      Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
//      Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().withSkipHeaderRecord().parse();
    int nbLigne = 0;
    for (CSVRecord record : records) {
      nbLigne++;
      // saute la première ligne d'entête si elle existe
      if (nbLigne == 1 && record.get("Ville").equals("Ville") && record.get("Nom").equals("Nom")) continue;

      Long idVille = villeService.getOrCreate(record.get("Ville").toUpperCase());

      EtablissementDto etabDto = new EtablissementDto(null, record.get("Nom"), record.get("RNE"), record.get("Codification"), record.get("CCF"), idVille);

      // TODO appliquer la validation par injection du Validator
      logger.info("Établissement à importer : " + etabDto);

      this.saveEtablissementFromEtablissementDto(etabDto);
    }
  }
  
}
