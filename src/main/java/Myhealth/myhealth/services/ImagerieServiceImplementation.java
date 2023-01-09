package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Imagerie;
import Myhealth.myhealth.repository.ImagerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagerieServiceImplementation implements ImagerieService {
    ImagerieRepository imagerieRepository;
    @Override
    public ReponseMessage creerImagerie(Imagerie imagerie) {
        if (imagerieRepository.findByIdimagerie(imagerie.getIdimagerie()) == null){
            imagerieRepository.save(imagerie);
            ReponseMessage message = new ReponseMessage("Imagerie ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Imagerie existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierImagerie(Imagerie imagerie) {
        if (imagerieRepository.findByIdimagerie(imagerie.getIdimagerie()) !=null) {
            return imagerieRepository.findById(imagerie.getIdimagerie())
                    .map(imagerie1->{
                        imagerie1.setLibelle(imagerie.getLibelle());
                        imagerie1.setDescription(imagerie.getDescription());
                        imagerie1.setPieceJoint(imagerie.getPieceJoint());
                        imagerieRepository.save(imagerie1);
                        ReponseMessage message = new ReponseMessage("imagerie modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, imagerie non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, imagerie non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Imagerie> afficherToutLesImagerie() {
        return imagerieRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerImagerie(Long idimagerie) {
        if(imagerieRepository.findByIdimagerie(idimagerie) != null){
            imagerieRepository.deleteById(idimagerie);
            ReponseMessage message = new ReponseMessage("imagerie Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("imagerie Medical non trouvé", false);
            return message;
        }
    }
}
