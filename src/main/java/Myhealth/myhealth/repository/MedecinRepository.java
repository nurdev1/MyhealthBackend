package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, String> {
}
