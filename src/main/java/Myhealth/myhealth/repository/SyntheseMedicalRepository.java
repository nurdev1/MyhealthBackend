package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.modeles.SyntheseMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyntheseMedicalRepository extends JpaRepository<SyntheseMedical,Long> {

  //  SyntheseMedical findByIdsynthesemedical(Long idsynthesemedical);
  SyntheseMedical findByNom(String nom);
}
