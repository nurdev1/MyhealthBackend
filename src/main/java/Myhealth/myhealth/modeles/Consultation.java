package Myhealth.myhealth.modeles;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
@Table
@NoArgsConstructor
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String fichier;
    private boolean etat = true;
    private Date date;
    private  String observations;
    private String medicaments;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Patient patient;
}

/*@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "MedecinPatient",
        joinColumns = @JoinColumn(name = "idmedecin"),
        inverseJoinColumns = @JoinColumn(name = "idpatient"))
private Set<Patient> patients = new HashSet<>();
*/
