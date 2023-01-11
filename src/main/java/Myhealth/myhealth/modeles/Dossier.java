package Myhealth.myhealth.modeles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@Table
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long iddossier;
    private String nom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
}
