package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table
public class Imagerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idimagerie;
    private String libelle;
    private String description;
    private String pieceJoint;
    private Date dateajout;
    @ManyToOne
    private Dossier dossier;
}
