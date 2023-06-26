package cda.greta94.planexam.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Ville {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  private String nom;

   @OneToMany(mappedBy = "ville")
  private List<Etablissement> etablissements;

    @OneToMany(mappedBy = "ville", orphanRemoval = true)
    private List<Professeur> professeurs = new ArrayList<>();

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }


    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public List<Etablissement> getEtablissements() {
    return etablissements;
  }

  public void setEtablissements(List<Etablissement> etablissements) {
    this.etablissements = etablissements;
  }

  @Override
  public String toString() {
    return "Ville{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Ville ville = (Ville) o;
    return getId() != null && Objects.equals(getId(), ville.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
