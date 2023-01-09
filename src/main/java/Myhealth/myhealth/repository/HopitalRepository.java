package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {

    Hopital findByIdhopital(Long idhopital);
    //Optional<Hopital> finbBynom(String nom);
}
