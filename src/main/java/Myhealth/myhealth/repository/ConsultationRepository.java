package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    Consultation findByIdconsultation(Long idconsultation);
}
