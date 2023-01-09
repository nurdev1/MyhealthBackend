package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.SyntheseMedical;
import Myhealth.myhealth.repository.SyntheseMedicalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystheseMedicalServiceImplementation implements SystheseMedicalService {

    SyntheseMedicalRepository syntheseMedicalRepository;
    @Override
    public ReponseMessage AjouterSystheseMedical(SyntheseMedical systheseMedical) {
        if (syntheseMedicalRepository.findByIdsynthesemedical(systheseMedical.getIdsynthesemedical()) == null){
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

        if (syntheseMedicalRepository.findByIdsynthesemedical(syntheseMedical.getIdsynthesemedical()) !=null) {
            return syntheseMedicalRepository.findById(syntheseMedical.getIdsynthesemedical())
                    .map(syntheseMedical1->{
                        syntheseMedical1.setLibelle(syntheseMedical.getLibelle());
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

    @Override
    public ReponseMessage SupprimerSystheseMedical(Long idsysthesemedical) {
        if(syntheseMedicalRepository.findByIdsynthesemedical(idsysthesemedical) != null){
            syntheseMedicalRepository.deleteById(idsysthesemedical);
            ReponseMessage message = new ReponseMessage("Systhese Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Systhese Medical non trouvé", false);
            return message;
        }
    }
    }

