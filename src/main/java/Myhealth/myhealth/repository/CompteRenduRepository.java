package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.modeles.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRenduRepository extends JpaRepository<CompteRendu, Long> {

  //  CompteRendu findByIdcompterendu(Long id);
  CompteRendu findByNom(String nom);
}
