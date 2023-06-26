package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.model.Ville;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfesseurDto {
    private Long id;
    @NotBlank(message = "Prenom est requis")
    private String prenom;
    @NotBlank(message = "Nom est requis")
    private String nom;
    @NotBlank(message = "Email est requis")
    @Email(message = "Email n'est pas valide")
    private String email;

    private Long idVille;
    private Long idEtablissement;
    private Long idSpecialite;
}
