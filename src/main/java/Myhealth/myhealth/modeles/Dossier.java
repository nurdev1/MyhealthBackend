package Myhealth.myhealth.modeles;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long iddossier;
    private String nom;
    private Date datedossier;
}
