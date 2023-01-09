package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Prescription;
import Myhealth.myhealth.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescritionServiceImplementation implements PrescritionService {


    PrescriptionRepository prescriptionRepository;
    @Override
    public ReponseMessage creerPrescription(Prescription prescription) {
        if (prescriptionRepository.findByIdprescription(prescription.getIdprescription()) == null){
            prescriptionRepository.save(prescription);
            ReponseMessage message = new ReponseMessage("prescription ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce prescription existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierPrescription(Prescription prescription) {
        if (prescriptionRepository.findByIdprescription(prescription.getIdprescription()) !=null) {
            return prescriptionRepository.findById(prescription.getIdprescription())
                    .map(prescription1->{
                        prescription1.setLibelle(prescription.getLibelle());
                        prescription1.setDescription(prescription.getDescription());
                        prescription1.setPieceJoint(prescription.getPieceJoint());
                        prescriptionRepository.save(prescription1);
                        ReponseMessage message = new ReponseMessage("Patient modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Patient non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Patient non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Prescription> afficherToutLesPrescription() {
        return prescriptionRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerPrescription(Long idprescription) {
        if(prescriptionRepository.findByIdprescription(idprescription) != null){
            prescriptionRepository.deleteById(idprescription);
            ReponseMessage message = new ReponseMessage("Systhese Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Systhese Medical non trouvé", false);
            return message;
        }
    }
}
