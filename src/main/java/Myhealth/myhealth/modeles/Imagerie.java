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
public class Imagerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idimagerie;
    private String nom;
    private String description;
    private String pieceJoint;
    private boolean etat = true;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private Consultation consultation;
}
