package Myhealth.myhealth.modeles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@NoArgsConstructor
public class Dispensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddispensation;
    private String nom;
    private String description;
    private String pieceJoint;
    boolean etat = true;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private CompteRendu compteRendu;
}
