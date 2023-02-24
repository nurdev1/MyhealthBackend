package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.DossierRepository;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DossierServiceImplementation  implements DossierService {

    @Autowired
    DossierRepository dossierRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public ReponseMessage creerDossier(Dossier dossier) {
        if (dossierRepository.findByNom(dossier.getNom()) == null) {
            dossierRepository.save(dossier);
            ReponseMessage message = new ReponseMessage("dossier ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Ce dossier existe déjà ", false);

            return message;
        }
    }
    /*public ReponseMessage creerDossier(Dossier dossier) {
        if (dossierRepository.findById(dossier.getId()) == null) {
            dossierRepository.save(dossier);
            ReponseMessage message = new ReponseMessage("patient ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Cet patient existe déjà ", false);

            return message;
        }
    }*/

    @Override
    public ReponseMessage modifierDossier(Dossier dossier) {

        if (dossierRepository.findById(dossier.getId()) !=null) {
            return dossierRepository.findById(dossier.getId())
                    .map(dossier1->{
                        dossier1.setNom(dossier.getNom());
                        dossierRepository.save(dossier1);
                        ReponseMessage message = new ReponseMessage("dossier modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, dossier non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, dossier non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Dossier> afficherToutLesDossier() {
        return dossierRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerDossier(Long id) {
        final  Dossier dossier = null;
        if (dossierRepository.findById(id) != null) {
            dossier.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Dossier supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Dossier non trouvée", false);
            return message;
        }
    }

    public List<Dossier> getDossiersForPatient(String codePatient) {
        Patient patient = patientRepository.findByCodePatient(codePatient);


        if (patient == null) {
            return Collections.emptyList();
        }
        return dossierRepository.findByPatient(patient);
    }

    @Override
    public Dossier addDossier(Long patientId, Long medecinId, Dossier dossierDto) {
        return null;
    }

    @Override
    public Dossier updateDossier(Long dossierId, Long medecinId, Dossier dossierDto) {
        return null;
    }

    @Override
    public void deleteDossier(Long dossierId, Long medecinId) {

    }

/*    public Consultation updateConsultation(Long id, Consultation consultation) {
        Consultation existingConsultation = consultationRepository.findById(id).orElse(null);
        if (existingConsultation == null) {
            return null;
        }
        existingConsultation.setDate(consultation.getDate());
        existingConsultation.setPatient(consultation.getPatient());
        existingConsultation.setMedecin(consultation.getMedecin());
        // Autres champs à mettre à jour...
        return consultationRepository.save(existingConsultation);
    }*/


}
