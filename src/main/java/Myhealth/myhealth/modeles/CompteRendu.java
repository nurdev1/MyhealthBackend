package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Data
@Table
public class CompteRendu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idcompterendu;
    private String libelle;
    private String description;
    private String pieceJoint;
    private Date dateajout;

}
