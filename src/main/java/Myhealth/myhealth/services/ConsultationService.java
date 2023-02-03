package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;

import java.util.List;

public interface ConsultationService {

    // Création d'un Consultation
    ReponseMessage creerConsultation (Consultation consultation, String medecin, String patient);

    // Mise à jour d'un Consultation
    ReponseMessage modifierConsultation (Consultation consultation);

    //affichage d'un Consultation

    List<Consultation > afficherToutLesConsultation ();

    //Suppression d'un Consultation
    ReponseMessage SupprimerConsultation (Long idconsultation );

    int NombreConsultationMedecin();
}
