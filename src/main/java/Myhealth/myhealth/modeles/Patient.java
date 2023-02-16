package Myhealth.myhealth.modeles;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpatient;
    private String codePatient;
    private String nom;
    private String prenom;
    private String photo;
    private String telephone;
    private String email;
    private String ville;
    private String adresse;
    private boolean etat= true;
    private String motdepasse;

    @ManyToOne
    private Role role;

/*    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "patient_roles",
            joinColumns = @JoinColumn(name = "id_patient"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();*/


}
/*public Patient trouverPatientParCode(String codePatient) {
    for (Patient patient : patients) {
        if (patient.getCodePatient().equals(codePatient)) {
            return patient;
        }
    }
    return null; // patient non trouvé
}
*/
