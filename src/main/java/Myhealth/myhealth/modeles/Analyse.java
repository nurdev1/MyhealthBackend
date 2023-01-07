package Myhealth.myhealth.modeles;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne
    private Dossier dossier;
}
