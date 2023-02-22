package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Imagerie;
import Myhealth.myhealth.modeles.Prescription;
import Myhealth.myhealth.repository.ImagerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagerieServiceImplementation implements ImagerieService {
    @Autowired
    ImagerieRepository imagerieRepository;
    @Override
    public ReponseMessage creerImagerie(Imagerie imagerie) {
        if (imagerieRepository.findById(imagerie.getId()) == null){
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
        if (imagerieRepository.findById(imagerie.getId()) !=null) {
            return imagerieRepository.findById(imagerie.getId())
                    .map(imagerie1->{
                        imagerie1.setNom(imagerie.getNom());
                        imagerie1.setDescription(imagerie.getDescription());
                        imagerie1.setFichier(imagerie.getFichier());
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

/*    @Override
    public ReponseMessage SupprimerImagerie(Long idimagerie) {
        if(imagerieRepository.findByIdimagerie(idimagerie) != null){
            imagerieRepository.deleteById(idimagerie);
            ReponseMessage message = new ReponseMessage("imagerie Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("imagerie Medical non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerImagerie(Long id) {
        final Imagerie imagerie = null;
        if (imagerieRepository.findById(id) != null) {
            imagerie.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Imagerie supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Imagerie non trouvée", false);
            return message;
        }
    }
}
