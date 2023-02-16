package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.DossierRepository;
import Myhealth.myhealth.repository.PatientRepository;
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
        if (dossierRepository.findByIddossier(dossier.getIddossier()) == null) {
            dossierRepository.save(dossier);
            ReponseMessage message = new ReponseMessage("patient ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Cet patient existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierDossier(Dossier dossier) {

        if (dossierRepository.findByIddossier(dossier.getIddossier()) !=null) {
            return dossierRepository.findById(dossier.getIddossier())
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

 /*   @Override
    public ReponseMessage SupprimerDossier(Long iddossier) {

        if (dossierRepository.findByIddossier(iddossier) != null) {
            dossierRepository.deleteById(iddossier);
            ReponseMessage message = new ReponseMessage("dossier Medical supprimé avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("dossier Medical non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerDossier(Long id) {
        final  Dossier dossier = null;
        if (dossierRepository.findByIddossier(id) != null) {
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
}
