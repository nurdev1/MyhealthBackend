package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Dispensation;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.repository.DispensationRepository;
import Myhealth.myhealth.services.DispensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DispensationServiceImplementation implements DispensationService {
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
                        dispensation1.setFileName(dispensation.getFileName());
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

    @Override
    public Dispensation storeFile(Dispensation dispensation,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            dispensation.setFileName(fileName);
            dispensation.setFileType(file.getContentType());
            dispensation.setData(file.getBytes());
            dispensation.setDate(LocalDateTime.now());
            dispensation.setEtat(true);

            // Save the Dossier object to the database
            return dispensationRepository.save(dispensation);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
}
