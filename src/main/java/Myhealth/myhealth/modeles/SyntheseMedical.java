package Myhealth.myhealth.modeles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table
@NoArgsConstructor
public class SyntheseMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthesemedical;
    private String nom;
    private String description;
    private String pieceJoint;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private boolean etat = true;


    @ManyToOne
    private CompteRendu compteRendu;
}
