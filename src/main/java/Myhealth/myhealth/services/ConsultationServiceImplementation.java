package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImplementation implements ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;
    @Override
    public ReponseMessage creerConsultation(Consultation consultation,  String medecin, String patient) {
        if (consultationRepository.findByIdconsultation(consultation.getIdconsultation()) == null){
            consultationRepository.save(consultation);
            ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce consultation  existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierConsultation (Consultation consultation) {
        if (consultationRepository.findByIdconsultation(consultation.getIdconsultation()) !=null) {
            return consultationRepository.findById(consultation.getIdconsultation())
                    .map(consultation1->{
                        consultation1.setTitre(consultation.getTitre());
                        consultation1.setDesciption(consultation.getDesciption());
                        consultation1.setFichier(consultation.getFichier());
                        consultationRepository.save(consultation1);
                        ReponseMessage message = new ReponseMessage("consultation modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, consultation non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, consultation non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Consultation> afficherToutLesConsultation() {
        return null;
    }

 /*   @Override
    public ReponseMessage SupprimerConsultation(Long idconsultation) {
        if(consultationRepository.findByIdconsultation(idconsultation) != null){
            consultationRepository.deleteById(idconsultation);
            ReponseMessage message = new ReponseMessage("consultation Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("consultation Medical non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerConsultation(Long id) {
        final  Consultation consultation = null;
        if (consultationRepository.findByIdconsultation(id) != null) {
            consultation.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Consultation supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Consultation non trouvée", false);
            return message;
        }
    }

    @Override
    public int NombreConsultationMedecin() {
        return consultationRepository.NombreConsultationMedecin();
    }
}
