package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Soins;
import Myhealth.myhealth.repository.SoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoinsServiceImplementation implements SoinsService {

    @Autowired
    SoinsRepository syntheseSoinsRepository;
    @Override
    public ReponseMessage creerSyntheseSoins(Soins syntheseDeSoins) {
        if (syntheseSoinsRepository.findById(syntheseDeSoins.getId()) == null){
            syntheseSoinsRepository.save(syntheseDeSoins);
            ReponseMessage message = new ReponseMessage("synthèse de soins ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce synthèse de soins existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierSyntheseSoins(Soins syntheseSoins) {
        if (syntheseSoinsRepository.findById(syntheseSoins.getId()) !=null) {
            return syntheseSoinsRepository.findById(syntheseSoins.getId())
                    .map(synthse1->{
                        synthse1.setNom(syntheseSoins.getNom());
                        synthse1.setDescription(syntheseSoins.getDescription());
                        syntheseSoins.setPieceJoint(syntheseSoins.getPieceJoint());
                        syntheseSoinsRepository.save(synthse1);
                        ReponseMessage message = new ReponseMessage("Synthèse de soins médical modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Synthèse de soins médical non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Synthèse de soins médical non trouvée", false);

            return message;
        }
    }


    @Override
    public List<Soins> afficherToutLesSystheseSoins() {
        return syntheseSoinsRepository.findAll();
    }

 /*   @Override
    public ReponseMessage SupprimerSyntheseDeSoins(Long idsynthesesoins) {
        if(syntheseSoinsRepository.findByIdsynthesesoins(idsynthesesoins) != null){
            syntheseSoinsRepository.deleteById(idsynthesesoins);
            ReponseMessage message = new ReponseMessage("Synthese de soins supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Synthese de soins non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerSyntheseDeSoins(Long id) {
        final  Soins  soins = null;
        if (syntheseSoinsRepository.findById(id) != null) {
            soins.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Synthèse de soins supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Synthèse de soins non trouvée", false);
            return message;
        }
    }


}
