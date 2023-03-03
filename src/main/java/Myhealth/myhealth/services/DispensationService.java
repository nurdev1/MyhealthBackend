package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dispensation;

import java.util.List;

public interface DispensationService {


    // Création d'un Dispensation
    ReponseMessage creerDispensation(Dispensation dispensation);

    // Mise à jour d'un Dispensation
    ReponseMessage modifierDispensation(Dispensation dispensation);

    //affichage d'un Dispensation

    List<Dispensation> afficherToutLesImagerie();

    //Suppression d'un Imagerie
    ReponseMessage SupprimerDispensation(Long iddispensation);
}
