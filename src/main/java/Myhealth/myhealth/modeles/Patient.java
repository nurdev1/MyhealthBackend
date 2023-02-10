package Myhealth.myhealth.modeles;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpatient;
    //private String identifiant;
    private String nom;
    private String prenom;
    private String photo;
    private String telephone;
    private String email;
   // private String pieceidentite;
    private String ville;
    private String adresse;
    private boolean etat;

     @ManyToOne
    private Utilisateus utilisateus;


}
