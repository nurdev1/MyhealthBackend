package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    Consultation findByIdconsultation(Long idconsultation);

    @Query(value = "SELECT COUNT(idconsultation) FROM medecin,consultation,patient WHERE" +
            " medecin.idmedecin=consultation.medecin_idmedecin AND " +
            "patient.idpatient=consultation.patient_idpatient;",nativeQuery = true)
    int NombreConsultationMedecin();
   /* @Query(value = "SELECT * FROM consultation WHERE consultation.medecin_idmedecin =:consultation;",nativeQuery = true)
    List<Object> MedecinConsultation(@Param("specialite") Long idmedecin);*/
}
