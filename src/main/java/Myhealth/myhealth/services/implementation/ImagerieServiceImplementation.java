package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Imagerie;
import Myhealth.myhealth.repository.ImagerieRepository;
import Myhealth.myhealth.services.ImagerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
                        imagerie1.setFileName(imagerie.getFileName());
                        imagerie1.setResultat(imagerie.getResultat());
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

    @Override
    public Imagerie storeFile(Imagerie imagerie,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            imagerie.setFileName(fileName);
            imagerie.setFileType(file.getContentType());
            imagerie.setData(file.getBytes());
          //  imagerie.setDate(LocalDateTime.now());
            imagerie.setEtat(true);

            // Save the Dossier object to the database
            return imagerieRepository.save(imagerie);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
}
