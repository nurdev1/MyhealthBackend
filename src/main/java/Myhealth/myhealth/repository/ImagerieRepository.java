package Myhealth.myhealth.repository;


import Myhealth.myhealth.modeles.Imagerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagerieRepository extends JpaRepository<Imagerie,Long> {

    Imagerie findByIdimagerie(Long idimagerie);
}
