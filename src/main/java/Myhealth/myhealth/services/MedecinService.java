package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinService {

    // Création d'un Medecin
    ReponseMessage creerMedecin(Medecin medecin);

    // Mise à jour d'un Medecin
    ReponseMessage modifierMedecin(Medecin medecin);
    //affichage d'un Medecin

    List<Medecin> afficherToutLesMedecin();
    //Suppression d'un Medecin
    ReponseMessage SupprimerMedecin(Long idmedecin);

    List<Object> nombreMedecinparHopital(@Param("nom")String nom);
    List<Object> nombreMedecinHopital();
    List<Object> NombreMedecinSpecialite();
    List<Object> NombreMedecinParSpecialite(@Param("specialite") String specialite);
    int NombreMedecin();
    List<Object> HopitalListeMedecin();

    void acivateEmailMedecin();

    void activerMedecin(Long id);
}
