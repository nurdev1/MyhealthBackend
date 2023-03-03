package Myhealth.myhealth.modeles;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class Analyse extends Consultation {


    @Entity
    @Data
    @Table
    @NoArgsConstructor
    public static class Dispensation  extends CompteRendu {

        private String resultat;

    }
}
