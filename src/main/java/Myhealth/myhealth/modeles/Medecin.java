package Myhealth.myhealth.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmedecin;
    private String nom;
    private String prenom;
    private String photo;
    private String telehone;
    private String email;
    private String pieceidentite;
    private String ville;
    private String adresse;
    private String diplome;
    private String specialite;


    @ManyToOne
    private Hopital hopital;


    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="Medecinpatient",
            joinColumns = @JoinColumn(name = "idmedecin"),
            inverseJoinColumns = @JoinColumn(name = "idpatient")
    )
    private List<Patient> patients = new ArrayList<>();




}
