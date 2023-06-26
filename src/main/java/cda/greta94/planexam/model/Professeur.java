package cda.greta94.planexam.model;

import jakarta.persistence.*;

@Entity
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    private String prenom;
    @Basic(optional = false)
    private String nom;
    @Basic(optional = false)
    @Column(nullable = false,unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;
    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
    @ManyToOne
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;


    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public Etablissement getEtablissement() {
        return etablissement;
    }
    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professeur that = (Professeur) o;

        if (!id.equals(that.id)) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
