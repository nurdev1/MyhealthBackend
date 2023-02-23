package Myhealth.myhealth.modeles;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Utilisateus {
    private String codePatient;
    private boolean etat = true;

    @ManyToOne
    private Role role;

}
