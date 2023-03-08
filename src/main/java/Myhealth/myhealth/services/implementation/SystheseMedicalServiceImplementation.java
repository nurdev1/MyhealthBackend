package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.SyntheseMedical;
import Myhealth.myhealth.repository.SyntheseMedicalRepository;
import Myhealth.myhealth.services.SystheseMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SystheseMedicalServiceImplementation implements SystheseMedicalService {


    @Autowired
    SyntheseMedicalRepository syntheseMedicalRepository;
    @Override
    public ReponseMessage AjouterSystheseMedical(SyntheseMedical systheseMedical) {
        if (syntheseMedicalRepository.findByNom(systheseMedical.getNom()) == null){
            syntheseMedicalRepository.save(systheseMedical);
            ReponseMessage message = new ReponseMessage("synthèse médical ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce synthèse Medical existe déjà ", false);

            return message;
        }
    }



    @Override
    public ReponseMessage modifierSystheseMedical(SyntheseMedical syntheseMedical) {

        if (syntheseMedicalRepository.findById(syntheseMedical.getId()) !=null) {
            return syntheseMedicalRepository.findById(syntheseMedical.getId())
                    .map(syntheseMedical1->{
                        syntheseMedical1.setNom(syntheseMedical.getNom());
                        syntheseMedical1.setDescription((syntheseMedical.getDescription()));
                        syntheseMedical1.setFileName(syntheseMedical.getFileName());
                        syntheseMedical1.setGroupeSanguin(syntheseMedical.getGroupeSanguin());
                        syntheseMedical1.setAntecedentsMedicaux(syntheseMedical.getAntecedentsMedicaux());
                        syntheseMedical1.setPoids(syntheseMedical.getPoids());
                        syntheseMedical1.setTaille(syntheseMedical.getTaille());
                        syntheseMedicalRepository.save(syntheseMedical1);
                        ReponseMessage message = new ReponseMessage("Synthèse médical modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Synthèse médical non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Synthèse médical non trouvée", false);

            return message;
        }
    }

    @Override
    public List<SyntheseMedical> afficherToutLesSystheseMedical() {
        return syntheseMedicalRepository.findAll();
    }

 /*   @Override
    public ReponseMessage SupprimerSystheseMedical(Long idsysthesemedical) {
        if(syntheseMedicalRepository.findByIdsynthesemedical(idsysthesemedical) != null){
            syntheseMedicalRepository.deleteById(idsysthesemedical);
            ReponseMessage message = new ReponseMessage("Systhese Medical supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Systhese Medical non trouvé", false);
            return message;
        }
    }*/
@Override
    public ReponseMessage SupprimerSystheseMedical(Long id) {
        final SyntheseMedical syntheseMedical = null;
        if (syntheseMedicalRepository.findById(id) != null) {
            syntheseMedical.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Systhèse Medical supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Systhèse Medical non trouvée", false);
            return message;
        }
    }

    @Override
    public SyntheseMedical storeFile(SyntheseMedical systheseMedical,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            SyntheseMedical syntheseMedical = new SyntheseMedical();
            syntheseMedical.setFileName(fileName);
            syntheseMedical.setFileType(file.getContentType());
            syntheseMedical.setData(file.getBytes());
            syntheseMedical.setDate(LocalDateTime.now());
            syntheseMedical.setEtat(true);

            // Save the Dossier object to the database
            return syntheseMedicalRepository.save(syntheseMedical);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }

}

