package Myhealth.myhealth.modeles;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
public class Hopital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhopital;
    private String nom;
    private String ville;
    private String adresse;
    private String photo;
    private  String date;
    private boolean etat = true;
}
