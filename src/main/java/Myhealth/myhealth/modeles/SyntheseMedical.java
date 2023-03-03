package Myhealth.myhealth.modeles;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
public class SyntheseMedical extends CompteRendu {

     private String taille;
    private String poids;
    private String groupeSanguin;
    private String antecedentsMedicaux;
    private String traitements;

    /*@ManyToOne
    private List<Dossier> dossier;*/


}
/*{
    "dateCreation": "2022-02-23",
    "taille": 165,
    "poids": 65,
    "groupeSanguin": "A+",
    "antecedentsMedicaux": "Hypertension artérielle, diabète",
    "medicaments": "Metformine, Ramipril",
    "patient": {
        "id": 1
    }
}
*/