package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Hopital;
import Myhealth.myhealth.repository.HopitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class HopitalServiceImplementation implements HopitalService{

   @Autowired
    public HopitalRepository hopitalRepository;

    @Override
    public ReponseMessage creerHopital(Hopital hopital) {
        if (hopitalRepository.findByIdhopital(hopital.getIdhopital()) == null){
            hopitalRepository.save(hopital);
            ReponseMessage message = new ReponseMessage("Hopitalajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet Hopital existe déjà ", false);

            return message;
        }

    }

    @Override
    public ReponseMessage modifierHopital(Hopital hopital) {
        if (hopitalRepository.findByIdhopital(hopital.getIdhopital()) !=null) {
            return hopitalRepository.findById(hopital.getIdhopital())
                    .map(hopital1->{
                        hopital1.setAdresse(hopital.getAdresse());
                        hopital1.setNom(hopital.getNom());
                        hopital1.setPhoto(hopital.getPhoto());
                        hopital1.setVille(hopital.getVille());
                        hopitalRepository.save(hopital);
                        ReponseMessage message = new ReponseMessage("Hopital modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Hopital non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Hopital non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Hopital> afficherToutLesHopital() {
        return hopitalRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerHopital(Long idhopital) {
        if(hopitalRepository.findByIdhopital(idhopital) != null){
            hopitalRepository.deleteById(idhopital);
            ReponseMessage message = new ReponseMessage("hopital supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("hopital non trouvé", false);
            return message;
        }
    }
    //image

}
