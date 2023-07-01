package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SessionE5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String libelle;
    private Date dateDebut;
    private Date dateFin;

    @OneToMany(mappedBy = "sessionE5", orphanRemoval = true)
    private List<JourPassage> jourPassages = new ArrayList<>();

    public List<JourPassage> getJourPassages() {
        return jourPassages;
    }

    public void setJourPassages(List<JourPassage> jourPassages) {
        this.jourPassages = jourPassages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
