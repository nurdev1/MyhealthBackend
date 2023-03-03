package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse, Long> {

    //Analyse findByIdanalyse(Long idanalyse);
}
