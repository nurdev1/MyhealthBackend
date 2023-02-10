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
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idconsultation;
    private String titre;
    private String desciption;
    private String fichier;
    private boolean etat = true;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Patient patient;
}
