package Myhealth.myhealth.modeles;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table
@NoArgsConstructor
public class Dispensation extends CompteRendu{
    private Date dateRentre;
    private String motif;

}
