package Myhealth.myhealth.modeles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
public class Soins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthesesoins;
    private String nom;
    private String description;
    private String pieceJoint;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    private CompteRendu compteRendu;
}
