package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.modeles.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

   // Prescription findByIdprescription(Long idprescription);
   Prescription findByNom(String nom);
}
