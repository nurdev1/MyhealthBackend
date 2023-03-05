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
    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    private boolean etat = true;
    private Date date;
    private  String observations;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Hopital hopital;

    public Consultation(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

/*@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "MedecinPatient",
        joinColumns = @JoinColumn(name = "idmedecin"),
        inverseJoinColumns = @JoinColumn(name = "idpatient"))
private Set<Patient> patients = new HashSet<>();
*/
