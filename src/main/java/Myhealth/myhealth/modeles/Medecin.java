package Myhealth.myhealth.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medecin extends Utilisateus{

    private String diplome;
    private String specialite;
    private boolean etat = false;

  @JsonIgnore
  @ManyToMany(
          fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
          })
  @JoinTable(
          name="hopitalMedecin",
          joinColumns = @JoinColumn(name = "idmedecin"),
          inverseJoinColumns = @JoinColumn(name = "idhopital")
  )
  private List<Hopital> hopitals = new ArrayList<>();
    @ManyToOne
    private Role role;

}
