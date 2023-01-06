package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Hopital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhopital;
    private String nom;
    private String ville;
    private String adresse;
    private String photo;
}
