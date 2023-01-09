package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.modeles.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyseRepository extends JpaRepository<Analyse, Long> {

    Patient findByIdanalyse(Long idanalyse);
}
