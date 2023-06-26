package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.ProfesseurRepository;
import cda.greta94.planexam.dto.ProfesseurDto;
import cda.greta94.planexam.exception.NotFoundEntityException;
import cda.greta94.planexam.model.Professeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {
    private ProfesseurRepository professeurRepository;
    @Autowired
    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public List<Professeur> index(){
        return professeurRepository.findAll();
    }

    public Professeur show(Long id){
        return professeurRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public Professeur save(ProfesseurDto professeurDto){
        ///TODO
        return professeurRepository.save(new Professeur());
    }

    public Professeur toProf(ProfesseurDto professeurDto){
        //TODO
        return new Professeur();
    }

    public ProfesseurDto toDto(Professeur professeur){
        //TODO
        return new ProfesseurDto();
    }

}