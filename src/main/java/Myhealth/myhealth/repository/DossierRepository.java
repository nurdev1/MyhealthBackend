package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Dossier;

import Myhealth.myhealth.modeles.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {

    //Dossier findByIddossier(Long id);
    List<Dossier> findByPatient(Patient patient);
}
