package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.repository.CompteRenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteRenduServiceImplementation implements CompteRenduService{

    @Autowired
    CompteRenduRepository compteRenduRepository;
    @Override
    public ReponseMessage creerCompteRendu(CompteRendu compteRendu) {
        if (compteRenduRepository.findByIdcompterendu(compteRendu.getIdcompterendu()) == null){
            compteRenduRepository.save(compteRendu);
            ReponseMessage message = new ReponseMessage("compte Rendu ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce compt Rendu  existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierCompteRendu(CompteRendu compteRendu) {

        if (compteRenduRepository.findByIdcompterendu(compteRendu.getIdcompterendu()) !=null) {
            return compteRenduRepository.findById(compteRendu.getIdcompterendu())
                    .map(compteRendu1->{
                        compteRendu1.setNom(compteRendu.getNom());
                        compteRendu1.setDescription(compteRendu.getDescription());
                        compteRendu1.setPieceJoint(compteRendu.getPieceJoint());
                        compteRenduRepository.save(compteRendu1);
                        ReponseMessage message = new ReponseMessage("Compte rendu modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, compte rendu non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, compte rendu non trouvée", false);

            return message;
        }
    }

    @Override
    public List<CompteRendu> afficherToutLesCompteRendu() {
        return compteRenduRepository.findAll();
    }

  /*  @Override
    public ReponseMessage SupprimerCompteRendu(Long idcompterendu) {

        if(compteRenduRepository.findByIdcompterendu(idcompterendu) != null){
            compteRenduRepository.deleteById(idcompterendu);
            ReponseMessage message = new ReponseMessage("Compte rendu Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Compte rendu Medical non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerCompteRendu(Long id) {
        final  CompteRendu compteRendu = null;
        if (compteRenduRepository.findByIdcompterendu(id) != null) {
            compteRendu.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Compte rendu supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Compte rendu non trouvée", false);
            return message;
        }
    }
}
