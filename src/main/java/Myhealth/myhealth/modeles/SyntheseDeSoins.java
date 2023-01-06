package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table
public class SyntheseDeSoins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthesesoins;
    private String libelle;
    private String description;
    private String pieceJoint;
    private Date dateajout;

    @ManyToOne
    private Synthese synthese;
}
