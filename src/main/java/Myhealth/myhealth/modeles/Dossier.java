package Myhealth.myhealth.modeles;

import jakarta.persistence.*;
import lombok.Data;

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
