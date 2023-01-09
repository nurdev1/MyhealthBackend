package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Prescription;

import java.util.List;

public interface PrescritionService {

    // Création d'un prescription
    ReponseMessage creerPrescription(Prescription prescription);

    // Mise à jour d'un Medecin
    ReponseMessage modifierPrescription(Prescription prescription);

    //affichage d'un prescription
    List<Prescription> afficherToutLesPrescription();

    //Suppression d'un prescription
    ReponseMessage SupprimerPrescription(Long idprescription);
}
