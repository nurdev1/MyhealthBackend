package Myhealth.myhealth.modeles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private boolean etat = true;
    @ManyToOne
    private Patient patient;
    // Add getters and setters for the fileName field
    public String getFileName() {


        return fileName;
    }
    public void setFileName(String fileName) {


        this.fileName = fileName;
    }
}
