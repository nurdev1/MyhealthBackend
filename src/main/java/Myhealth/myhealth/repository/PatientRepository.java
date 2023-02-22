package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {




   Patient findByEmail(String email);
   Boolean existsByEmail(String email);

    Patient findByCodePatient(String codePatient);

    //nombre par spécialité
    @Query(value = "SELECT COUNT(*) FROM patient;",nativeQuery = true)
    int NombrePatient();

}
