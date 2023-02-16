package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Dossier;

import Myhealth.myhealth.modeles.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

    Dossier findByIddossier(Long iddossier);
    List<Dossier> findByPatient(Patient patient);
}
