package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {


    //Medecin findByNumero(String numero);
    Medecin findByEmail(String email);
    Medecin findByIdmedecin(Long id);

    @Query(value = "SELECT COUNT(*) FROM medecin;",nativeQuery = true)
    int NombreMedecin();
    //nombre par spécialité
    @Query(value = "SELECT COUNT(*) FROM medecin WHERE medecin.specialite=:specialite;",nativeQuery = true)
    List<Object> NombreMedecinParSpecialite(@Param("specialite") String specialite);

    //nombre par spécialité
    @Query(value = "SELECT COUNT(*),medecin.specialite FROM medecin GROUP BY medecin.specialite;",nativeQuery = true)
    List<Object> NombreMedecinSpecialite();

    @Query(value = "SELECT COUNT(*),hopital.nom FROM medecin,hopital WHERE " +
            "medecin.hopital_idhopital=hopital.idhopital GROUP BY medecin.hopital_idhopital;",nativeQuery = true)
    List<Object> nombreMedecinHopital();

    @Query(value = "SELECT COUNT(*) FROM medecin,hopital WHERE medecin.hopital_idhopital=hopital.idhopital AND" +
            " hopital.nom=:nom;",nativeQuery = true)
    List<Object> nombreMedecinparHopital(@Param("nom")String nom);
}
