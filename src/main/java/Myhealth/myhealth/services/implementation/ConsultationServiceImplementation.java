package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.ConsultationRepository;
import Myhealth.myhealth.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImplementation implements ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

/*    @Override
    public ReponseMessage creerConsultation(Consultation consultation) {
        if (consultationRepository.findById(consultation.getId()) == null) {
            consultationRepository.save(consultation);
            ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Ce consultation  existe déjà ", false);

            return message;
        }
    }*/
@Override
public ReponseMessage creerConsultation(Consultation consultation) {
    if (consultationRepository.findByNom(consultation.getNom()) == null) {
        consultationRepository.save(consultation);
        ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
        return message;
    } else {
        ReponseMessage message = new ReponseMessage("Ce consultation  existe déjà ", false);

        return message;
    }
}
    //,  int medecin, int patient

    @Override
    public ReponseMessage modifierConsultation(Consultation consultation) {
        if (consultationRepository.findById(consultation.getId()) != null) {
            return consultationRepository.findById(consultation.getId())
                    .map(consultation1 -> {
                        consultation1.setNom(consultation.getNom());
                        consultation1.setDescription(consultation.getDescription());
                        consultation1.setFichier(consultation.getFichier());
                        consultationRepository.save(consultation1);
                        ReponseMessage message = new ReponseMessage("consultation modifié avec succes", true);
                        return message;
                    }).orElseThrow(() -> new RuntimeException("Désole, consultation non trouvée"));
        } else {
            ReponseMessage message = new ReponseMessage("Désole, consultation non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Consultation> afficherToutLesConsultation() {
        return null;
    }


    @Override
    public ReponseMessage SupprimerConsultation(Long id) {
        final Consultation consultation = null;
        if (consultationRepository.findById(id) != null) {
            consultation.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Consultation supprimée avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage(" Consultation non trouvée", false);
            return message;
        }
    }

    @Override
    public int NombreConsultationMedecin() {
        return consultationRepository.NombreConsultationMedecin();
    }

    @Override
    public Consultation saveOrUpdateConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Optional<Consultation> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public List<Consultation> getConsultationsByMedecinId(Long medecinId) {
        return consultationRepository.findByMedecinId(medecinId);
    }

    @Override
    public List<Consultation> getConsultationsByPatientId(Long patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    @Override
    public void deleteConsultationById(Long id) {
        consultationRepository.deleteById(id);
    }

    public Consultation creerConsultation(Medecin medecin, Patient patient, String titre, String desciption, String fichier) {
        Consultation consultation = new Consultation();
        consultation.setMedecin(medecin);
        consultation.setPatient(patient);
        consultation.setNom(titre);
        consultation.setDescription(desciption);
        consultation.setFichier(fichier);
        consultationRepository.save(consultation);
        return consultation;
    }

}


