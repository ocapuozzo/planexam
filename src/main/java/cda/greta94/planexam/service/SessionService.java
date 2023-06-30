package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JourPassageRepository;
import cda.greta94.planexam.dao.SessionE5Repository;
import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.model.SessionE5;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private SessionE5Repository sessionRepository;
    private JourPassageRepository jourPassageRepository;

    public SessionService(SessionE5Repository sessionRepository, JourPassageRepository jourPassageRepository) {
        this.sessionRepository = sessionRepository;
        this.jourPassageRepository = jourPassageRepository;
    }

    public List<SessionE5> getAll(){
        return sessionRepository.findAll();
    }

    public SessionE5 toSession(SessionE5Dto sessionDto){
        SessionE5 sessionE5 ;
        if(sessionDto.getId()!=null){
            sessionE5 = sessionRepository.findById(sessionDto.getId()).orElse(new SessionE5());
        }
        else{
            sessionE5 = new SessionE5();
        }
        sessionE5.setLibelle(sessionDto.getLibelle());
        sessionE5.setDateDebut(sessionDto.getDateDebut());
        sessionE5.setDateFin(sessionDto.getDateFin());
        return sessionE5;

    }

    public SessionE5 save(SessionE5Dto sessionDto){
        SessionE5 sessionE5 = this.toSession(sessionDto);

        //TODO Instancier les joursPassages entre dateDebut et dateFin

       return  sessionRepository.save(sessionE5);
    }
}
