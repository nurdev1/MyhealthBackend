package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table
public class SyntheseMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthesemedical;
    private String libelle;
    private String description;
    private String pieceJoint;
    private Date dateajout;
    @ManyToOne
    private Synthese synthese;
}
