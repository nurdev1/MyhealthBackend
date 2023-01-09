package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Patient;

import java.util.List;

public interface PatientService {

    // Création d'un Patient
    ReponseMessage creerPatient(Patient patient);

    // Mise à jour d'un Patient
    ReponseMessage modifierPatient(Patient patient);
    //affichage d'un Patient

    List<Patient> afficherToutLesPatient();
    //Suppression d'un Patient
    ReponseMessage SupprimerPatient(Long idpatient);
}
