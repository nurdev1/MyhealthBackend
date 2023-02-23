package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Utilisateus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {


    //Medecin findByNumero(String numero);
    Medecin findByEmail(String email);

        boolean existsByUsername(String username);
        boolean existsByEmail(String email);
    Optional<Medecin> findByUsername(String username);



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
    @Query(value = "SELECT medecin.nom,medecin.prenom,medecin.photo,medecin.email,medecin.telehone," +
            "hopital.nom,hopital.ville FROM medecin,hopital WHERE medecin.hopital_idhopital=" +
            "hopital.idhopital;",nativeQuery = true)
    List<Object> HopitalListeMedecin();
}
