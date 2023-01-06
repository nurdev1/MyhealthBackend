package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpatient;
    private String identifiant;
    private String nom;
    private String prenom;
    private String photo;
    private String telehone;
    private String email;
    private String pieceidentite;
    private String ville;
    private String adresse;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="Medecinpatient",
            joinColumns = @JoinColumn(name = "idpatient"),
            inverseJoinColumns = @JoinColumn(name = "idmedecin")
    )
    private List<Medecin> medecins = new ArrayList<>();

}
