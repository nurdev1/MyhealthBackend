package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {



    Patient findByIdpatient(Long idpatient);

   // Patient findByNumero(String numero);
}
