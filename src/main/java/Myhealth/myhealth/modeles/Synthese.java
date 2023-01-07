package Myhealth.myhealth.modeles;



import lombok.Data;

import javax.persistence.*;

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
