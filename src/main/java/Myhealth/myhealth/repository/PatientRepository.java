package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Utilisateus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {




   Patient findByEmail(String email);
   Boolean existsByEmail(String email);

    Patient findByCodePatient(String codePatient);
    Optional<Patient> findByUsername(String username);

    Boolean existsByUsername(String username);

    //nombre par spécialité
    @Query(value = "SELECT COUNT(*) FROM patient;",nativeQuery = true)
    int NombrePatient();

}
