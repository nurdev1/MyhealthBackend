package Myhealth.myhealth.modeles;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Synthese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsynthese;
    private String nom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    private Dossier dossier;
}
