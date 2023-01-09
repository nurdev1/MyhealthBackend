package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Dossier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

    Dossier findByIddossier(Long iddossier);
}
