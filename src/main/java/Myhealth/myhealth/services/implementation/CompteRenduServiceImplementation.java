package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.repository.CompteRenduRepository;
import Myhealth.myhealth.services.CompteRenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
class CompteRenduServiceImplementation implements CompteRenduService {

    @Autowired
    CompteRenduRepository compteRenduRepository;
    @Override
    public ReponseMessage creerCompteRendu(CompteRendu compteRendu) {
        if (compteRenduRepository.findById(compteRendu.getId()) == null){
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

        if (compteRenduRepository.findById(compteRendu.getId()) !=null) {
            return compteRenduRepository.findById(compteRendu.getId())
                    .map(compteRendu1->{
                        compteRendu1.setNom(compteRendu.getNom());
                        compteRendu1.setDescription(compteRendu.getDescription());
                        compteRendu1.setFileName(compteRendu.getFileName());
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
        if (compteRenduRepository.findById(id) != null) {
            compteRendu.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Compte rendu supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Compte rendu non trouvée", false);
            return message;
        }
    }

    @Override
    public CompteRendu storeFile(CompteRendu compteRendu,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            compteRendu.setFileName(fileName);
            compteRendu.setFileType(file.getContentType());
            compteRendu.setData(file.getBytes());
            compteRendu.setDate(LocalDateTime.now());
            compteRendu.setEtat(true);

            // Save the Dossier object to the database
            return compteRenduRepository.save(compteRendu);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
}

