package cda.greta94.planexam.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Etablissement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  private String nom;

  @Basic
  private String rne;

  @Basic
  private String code;

  @Basic
  private String ccf;
  // TODO Boolean ou Enum ???

  @ManyToOne
  private Ville ville;

    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.REMOVE, orphanRemoval = true)
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

  public String getRne() {
    return rne;
  }

  public void setRne(String rne) {
    this.rne = rne;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCcf() {
    return ccf;
  }

  public void setCcf(String ccf) {
    this.ccf = ccf;
  }

  public Ville getVille() {
    return ville;
  }

  public void setVille(Ville ville) {
    this.ville = ville;
  }


 // codes générés par JPA Utilities...
 // à voir, car la logique du equals n'est pas la même que celle du hash...

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Etablissement that = (Etablissement) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

 // fin codes générés par JPA Utilities...


  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
            "id = " + id + ", " +
            "nom = " + nom + ", " +
            "rne = " + rne + ", " +
            "code = " + code + ", " +
            "ccf = " + ccf + ", " +
            "ville = " + ((ville != null) ? ville.getNom() : "N/A") +
            ")";
  }
}
