package Myhealth.myhealth.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmedecin;
    private String nom;
    private String prenom;
    private String photo;
    private String telehone;
    private String email;
  //  private String pieceidentite;
    private String ville;
    private String adresse;
    private String diplome;
    private String specialite;
    private boolean etat = false;


    @ManyToOne
    private Hopital hopital;

    private String motdepasse;

    @ManyToOne
    private Role role;

   /*  @ManyToOne
    private Utilisateus utilisateus;*/


   /* @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="MedecinPatient",
            joinColumns = @JoinColumn(name = "idmedecin"),
            inverseJoinColumns = @JoinColumn(name = "idpatient")
    )
    private List<Patient> patients = new ArrayList<>();*/




}
