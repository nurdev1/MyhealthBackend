package Myhealth.myhealth.modeles;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Synthese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthese;

    @ManyToOne
    private Dossier dossier;
}
