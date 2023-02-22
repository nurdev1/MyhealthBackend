package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Prescription;
import Myhealth.myhealth.modeles.SyntheseMedical;
import Myhealth.myhealth.repository.SyntheseMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystheseMedicalServiceImplementation implements SystheseMedicalService {


    @Autowired
    SyntheseMedicalRepository syntheseMedicalRepository;
    @Override
    public ReponseMessage AjouterSystheseMedical(SyntheseMedical systheseMedical) {
        if (syntheseMedicalRepository.findById(systheseMedical.getId()) == null){
            syntheseMedicalRepository.save(systheseMedical);
            ReponseMessage message = new ReponseMessage("synthèse médical ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce synthèse Medical existe déjà ", false);

            return message;
        }
    }



    @Override
    public ReponseMessage modifierSystheseMedical(SyntheseMedical syntheseMedical) {

        if (syntheseMedicalRepository.findById(syntheseMedical.getId()) !=null) {
            return syntheseMedicalRepository.findById(syntheseMedical.getId())
                    .map(syntheseMedical1->{
                        syntheseMedical1.setNom(syntheseMedical.getNom());
                        syntheseMedical1.setDescription((syntheseMedical.getDescription()));
                        syntheseMedical1.setPieceJoint(syntheseMedical.getPieceJoint());
                        syntheseMedicalRepository.save(syntheseMedical1);
                        ReponseMessage message = new ReponseMessage("Synthèse médical modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Synthèse médical non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Synthèse médical non trouvée", false);

            return message;
        }
    }

    @Override
    public List<SyntheseMedical> afficherToutLesSystheseMedical() {
        return syntheseMedicalRepository.findAll();
    }

 /*   @Override
    public ReponseMessage SupprimerSystheseMedical(Long idsysthesemedical) {
        if(syntheseMedicalRepository.findByIdsynthesemedical(idsysthesemedical) != null){
            syntheseMedicalRepository.deleteById(idsysthesemedical);
            ReponseMessage message = new ReponseMessage("Systhese Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Systhese Medical non trouvé", false);
            return message;
        }
    }*/
@Override
    public ReponseMessage SupprimerSystheseMedical(Long id) {
        final SyntheseMedical syntheseMedical = null;
        if (syntheseMedicalRepository.findById(id) != null) {
            syntheseMedical.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Systhèse Medical supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Systhèse Medical non trouvée", false);
            return message;
        }
    }

    }

