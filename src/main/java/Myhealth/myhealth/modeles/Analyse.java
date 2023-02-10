package Myhealth.myhealth.modeles;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idanalyse;
    private String nom;
    private String description;
    //private Long size;
    @Lob
    private byte[] pieceJoint;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private boolean etat = true;

    @ManyToOne
    private Consultation consultation;
   /* @OneToMany
    private List<DatabaseFile> files = new ArrayList<>();*/
}
