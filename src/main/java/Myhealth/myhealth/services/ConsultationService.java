package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    // Création d'un Consultation
    ReponseMessage creerConsultation (Consultation consultation, MultipartFile file) ;
    //, int medecin, int patient

    // Mise à jour d'un Consultation
    ReponseMessage modifierConsultation (Consultation consultation);

    //affichage d'un Consultation

    List<Consultation > afficherToutLesConsultation ();

    //Suppression d'un Consultation
    ReponseMessage SupprimerConsultation (Long idconsultation );

    int NombreConsultationMedecin();

    /* @Override
     public List<Object> MedecinConsultation(Long idmedecin) {
         return consultationRepository.MedecinConsultation(idmedecin);
     }*/
    Consultation saveOrUpdateConsultation(Consultation consultation);

    Optional<Consultation> getConsultationById(Long id);

    List<Consultation> getAllConsultations();

    List<Consultation> getConsultationsByMedecinId(Long medecinId);

    List<Consultation> getConsultationsByPatientId(Long patientId);

    void deleteConsultationById(Long id);
//    public List<Object> MedecinConsultation (Long idmedecin);
}
