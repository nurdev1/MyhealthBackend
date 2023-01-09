package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;

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


}
