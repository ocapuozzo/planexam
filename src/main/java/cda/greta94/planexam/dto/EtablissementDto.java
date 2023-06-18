package cda.greta94.planexam.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EtablissementDto {

  private Long id;

  @NotBlank(message = "Nom établissement requis")
  @Size(min = 3, max = 30, message = "Longueur incorrecte")
  private String nom;

//  @NotBlank(message = "RNE établissement requis")
//  @Size(min = 6, max = 8, message = "Longueur incorrecte")
  private String rne;

  private String code;

  private String ccf;
  // TODO Boolean ou Enum ???

  private Long idVille;

  public EtablissementDto(Long id, String nom, String rne, String code, String ccf, Long idVille) {
    this.id = id;
    this.nom = nom;
    this.rne = rne;
    this.code = code;
    this.ccf = ccf;
    this.idVille = idVille;
  }

  public EtablissementDto() {
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

  public Long getIdVille() {
    return idVille;
  }

  public void setIdVille(Long idVille) {
    this.idVille = idVille;
  }

  @Override
  public String toString() {
    return "EtablissementDto{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", rne='" + rne + '\'' +
            ", code='" + code + '\'' +
            ", ccf='" + ccf + '\'' +
            ", idVille=" + idVille +
            '}';
  }
}
