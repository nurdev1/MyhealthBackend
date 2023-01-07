package Myhealth.myhealth.modeles;


import lombok.Data;

import javax.persistence.*;
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
