package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.repository.AnalyseRepository;
import Myhealth.myhealth.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalyseServiceImplementation implements AnalyseService {

    @Autowired
    AnalyseRepository analyseRepository;

    @Override
    public ReponseMessage creerAnalyse(Analyse analyse) {
        if (analyseRepository.findById(analyse.getId()) == null){
            analyseRepository.save(analyse);
            ReponseMessage message = new ReponseMessage("analyse ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet analyse existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierAnalyse(Analyse analyse) {

        if (analyseRepository.findById(analyse.getId()) !=null) {
            return analyseRepository.findById(analyse.getId())
                    .map(analyse1->{
                        analyse1.setNom(analyse.getNom());
                        analyse1.setDescription(analyse.getDescription());
                        analyse1.setFileName(analyse.getFileName());
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

 /*   @Override
    public ReponseMessage SupprimerAnalyse(Long idanalyse) {

        if(analyseRepository.findByIdanalyse(idanalyse) != null){
            analyseRepository.deleteById(idanalyse);
            ReponseMessage message = new ReponseMessage("Analyse Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Analyse Medical non trouvé", false);
            return message;
        }
    }*/

    @Override
    public ReponseMessage SupprimerAnalyse(Long id) {
        final  Analyse analyse = null;
        if (analyseRepository.findById(id) != null) {
            analyse.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Analyse supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Analyse non trouvée", false);
            return message;
        }
    }

    @Override
    public Analyse storeFile(Analyse analyse,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            analyse.setFileName(fileName);
            analyse.setFileType(file.getContentType());
            analyse.setData(file.getBytes());
           // analyse.setDate(LocalDateTime.now());
            analyse.setEtat(true);

            // Save the Dossier object to the database
            return analyseRepository.save(analyse);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
}
