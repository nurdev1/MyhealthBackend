package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Prescription;
import Myhealth.myhealth.repository.PrescriptionRepository;
import Myhealth.myhealth.services.PrescritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescritionServiceImplementation implements PrescritionService {


    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Override
    public ReponseMessage creerPrescription(Prescription prescription) {
        if (prescriptionRepository.findById(prescription.getId()) == null){
            prescriptionRepository.save(prescription);
            ReponseMessage message = new ReponseMessage("prescription ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Ce prescription existe déjà ", false);

            return message;
        }
    }

    @Override
    public ReponseMessage modifierPrescription(Prescription prescription) {
        if (prescriptionRepository.findById(prescription.getId()) !=null) {
            return prescriptionRepository.findById(prescription.getId())
                    .map(prescription1->{
                       prescription1.setNom(prescription.getNom());
                        prescription1.setDescription(prescription.getDescription());
                        prescription1.setFileName(prescription.getFileName());
                        prescriptionRepository.save(prescription1);
                        ReponseMessage message = new ReponseMessage("Patient modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Patient non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Patient non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Prescription> afficherToutLesPrescription() {
        return prescriptionRepository.findAll();
    }


    @Override
    public ReponseMessage SupprimerPrescription(Long id) {
        final  Prescription prescription = null;
        if (prescriptionRepository.findById(id) != null) {
            prescription.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Prescription supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Prescription non trouvée", false);
            return message;
        }
    }

    @Override
    public Prescription storeFile(Prescription prescription,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            prescription.setFileName(fileName);
            prescription.setFileType(file.getContentType());
            prescription.setData(file.getBytes());
           // prescription.setDate(LocalDateTime.now());
            prescription.setEtat(true);

            // Save the Dossier object to the database
            return prescriptionRepository.save(prescription);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
}
