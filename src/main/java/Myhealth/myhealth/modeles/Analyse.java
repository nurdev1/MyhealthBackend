package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idanalyse;
    private String libelle;
    private String description;
    private String pieceJoint;
    private Date dateajout;

    @ManyToOne private Dossier dossier;
}