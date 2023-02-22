package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dispensation;
import Myhealth.myhealth.repository.DispensationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispensationServiceImplementation implements DispensationService{
    @Autowired
    DispensationRepository dispensationRepository;

    @Override
    public ReponseMessage creerDispensation(Dispensation dispensation) {
        if (dispensationRepository.findById(dispensation.getId()) == null){
            dispensationRepository.save(dispensation);
            ReponseMessage message = new ReponseMessage("Dispensation ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Dispensation existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierDispensation(Dispensation dispensation) {

        if (dispensationRepository.findById(dispensation.getId()) !=null) {
            return dispensationRepository.findById(dispensation.getId())
                    .map(dispensation1->{
                        dispensation1.setNom(dispensation.getNom());
                        dispensation1.setDescription(dispensation.getDescription());
                        dispensation1.setPieceJoint(dispensation.getPieceJoint());
                        dispensationRepository.save(dispensation1);
                        ReponseMessage message = new ReponseMessage("dispensation modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, dispensation non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, dispensation non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Dispensation> afficherToutLesImagerie() {
        return dispensationRepository.findAll();
    }

  /*  @Override
    public ReponseMessage SupprimerDispensation(Long iddispensation) {

        if(dispensationRepository.findByIddispensation(iddispensation) != null){
            dispensationRepository.deleteById(iddispensation);
            ReponseMessage message = new ReponseMessage("Dispensation Médical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Dispensation Médical non trouvé", false);
            return message;
        }
    }*/
    @Override
    public ReponseMessage SupprimerDispensation(Long id) {
        final  Dispensation dispensation = null;
        if (dispensationRepository.findById(id) != null) {
            dispensation.setEtat(false);
            ReponseMessage message = new ReponseMessage("Dispensation supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Dispensation non trouvée", false);
            return message;
        }
    }
}
