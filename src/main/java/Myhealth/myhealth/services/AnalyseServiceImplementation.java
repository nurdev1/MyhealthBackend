package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.repository.AnalyseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseServiceImplementation implements AnalyseService {


    AnalyseRepository analyseRepository;
    @Override
    public ReponseMessage creerAnalyse(Analyse analyse) {
        if (analyseRepository.findByIdanalyse(analyse.getIdanalyse()) == null){
            analyseRepository.save(analyse);
            ReponseMessage message = new ReponseMessage("analyse ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet analyse existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierImagerie(Analyse analyse) {

        if (analyseRepository.findByIdanalyse(analyse.getIdanalyse()) !=null) {
            return analyseRepository.findById(analyse.getIdanalyse())
                    .map(analyse1->{
                        analyse1.setLibelle(analyse.getLibelle());
                        analyse1.setDescription(analyse.getDescription());
                        analyse1.setPieceJoint(analyse.getPieceJoint());
                        analyseRepository.save(analyse1);
                        ReponseMessage message = new ReponseMessage("analyse modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, analyse non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, analyse non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Analyse> afficherToutLesAnalyse() {
        return analyseRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerAnalyse(Long idanalyse) {

        if(analyseRepository.findByIdanalyse(idanalyse) != null){
            analyseRepository.deleteById(idanalyse);
            ReponseMessage message = new ReponseMessage("Analyse Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Analyse Medical non trouvé", false);
            return message;
        }
    }
}
