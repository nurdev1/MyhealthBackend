package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.CompteRendu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRenduRepository extends JpaRepository<CompteRendu, Long> {

    CompteRendu findByIdcompterendu(Long idcompterendu);
}
