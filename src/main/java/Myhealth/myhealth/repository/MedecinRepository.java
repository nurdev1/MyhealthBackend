package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {


    //Medecin findByNumero(String numero);
    Medecin findByEmail(String email);
    Medecin findByIdmedecin(Long id);
}
