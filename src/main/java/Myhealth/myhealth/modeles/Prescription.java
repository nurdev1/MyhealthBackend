package Myhealth.myhealth.modeles;


import Myhealth.myhealth.modeles.Consultation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table
@NoArgsConstructor
public class Prescription extends Consultation {
    private String indication;


}
