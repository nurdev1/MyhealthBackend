package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.SyntheseDeSoins;
import Myhealth.myhealth.repository.SyntheseSoinsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystheseSoinsServiceImplementation implements  SystheseSoinsService {

    SyntheseSoinsRepository syntheseSoinsRepository;
    @Override
    public ReponseMessage creerSyntheseSoins(SyntheseDeSoins syntheseDeSoins) {
        if (syntheseSoinsRepository.findByIdsynthesesoins(syntheseDeSoins.getIdsynthesesoins()) == null){
            syntheseSoinsRepository.save(syntheseDeSoins);
            ReponseMessage message = new ReponseMessage("synthèse de soins ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce synthèse de soins existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierSyntheseSoins(SyntheseDeSoins syntheseSoins) {
        if (syntheseSoinsRepository.findByIdsynthesesoins(syntheseSoins.getIdsynthesesoins()) !=null) {
            return syntheseSoinsRepository.findById(syntheseSoins.getIdsynthesesoins())
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
    public List<SyntheseDeSoins> afficherToutLesSystheseSoins() {
        return syntheseSoinsRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerSyntheseDeSoins(Long idsynthesesoins) {
        if(syntheseSoinsRepository.findByIdsynthesesoins(idsynthesesoins) != null){
            syntheseSoinsRepository.deleteById(idsynthesesoins);
            ReponseMessage message = new ReponseMessage("Synthese de soins supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Synthese de soins non trouvé", false);
            return message;
        }
    }


}
