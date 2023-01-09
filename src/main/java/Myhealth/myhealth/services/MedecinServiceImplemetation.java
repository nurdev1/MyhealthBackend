package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedecinServiceImplemetation implements MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;
    @Override
    public ReponseMessage creerMedecin(Medecin medecin) {
        if (medecinRepository.findByEmail(medecin.getEmail()) == null){
            medecinRepository.save(medecin);
            ReponseMessage message = new ReponseMessage("medecin ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet medecin existe déjà ", false);

            return message;
        }
    }


    @Override
    public ReponseMessage modifierMedecin(Medecin medecin) {
        if (medecinRepository.findByIdmedecin(medecin.getIdmedecin()) !=null) {
            return medecinRepository.findById(medecin.getIdmedecin())
                    .map(medecin1->{
                        medecin1.setNom(medecin.getNom());
                        medecin1.setPrenom(medecin.getPrenom());
                        medecin1.setAdresse(medecin.getAdresse());
                        medecin1.setVille(medecin.getVille());
                        medecin1.setPhoto(medecin.getPhoto());
                        medecinRepository.save(medecin1);
                        ReponseMessage message = new ReponseMessage("medecin modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, medecin non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, medecin non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Medecin> afficherToutLesMedecin() {
        return medecinRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerMedecin(Long idmedecin) {
        if (medecinRepository.findByIdmedecin(idmedecin) != null) {
            medecinRepository.deleteById(idmedecin);
            ReponseMessage message = new ReponseMessage("Medecin supprimée avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Medecin non trouvée", false);
            return message;
        }
    }
}
