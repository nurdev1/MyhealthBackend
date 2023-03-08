package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.DatabaseFile;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.DossierRepository;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.services.DatabaseFileService;
import Myhealth.myhealth.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class DossierServiceImplementation  implements DossierService {

    @Autowired
    DossierRepository dossierRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DatabaseFileService fileStorageService;

   /* @Override
    public ReponseMessage creerDossier(Dossier dossier) {
        if (dossierRepository.findByNom(dossier.getNom()) == null) {
            dossierRepository.save(dossier);
            ReponseMessage message = new ReponseMessage("dossier ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Ce dossier existe déjà ", false);

            return message;
        }
    }*/
    public ReponseMessage creerDossier(Dossier dossier) {
        if (dossierRepository.findById(dossier.getId()) == null) {
            dossierRepository.save(dossier);
            ReponseMessage message = new ReponseMessage("patient ajouté avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Cet patient existe déjà ", false);

            return message;
        }
    }

    @Override
    public Dossier storeFile(Dossier dossier,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            dossier.setFileName(fileName);
            dossier.setFileType(file.getContentType());
            dossier.setData(file.getBytes());
            dossier.setDate(LocalDateTime.now());
            dossier.setEtat(true);

            // Save the Dossier object to the database
            return dossierRepository.save(dossier);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }

   /*@Override
   public ReponseMessage creerDossier(Dossier dossier, MultipartFile file) {
       if (dossierRepository.findByNom(dossier.getNom()) == null) {
           // Enregistrer la pièce jointe
           if (file != null && !file.isEmpty()) {
               DatabaseFile dbFile = fileStorageService.storeFile(file);
               dossier.setPieceJoint(dbFile.getFileName());
           }
           // Enregistrer le dossier
           dossierRepository.save(dossier);
           ReponseMessage message = new ReponseMessage("dossier ajouté avec succès", true);
           return message;
       } else {
           ReponseMessage message = new ReponseMessage("Ce dossier existe déjà ", false);
           return message;
       }
   }*/


    @Override
    public ReponseMessage modifierDossier(Dossier dossier) {

        if (dossierRepository.findById(dossier.getId()) !=null) {
            return dossierRepository.findById(dossier.getId())
                    .map(dossier1->{
                        dossier1.setNom(dossier.getNom());
                        dossierRepository.save(dossier1);
                        ReponseMessage message = new ReponseMessage("dossier modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, dossier non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, dossier non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Dossier> afficherToutLesDossier() {
        return dossierRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerDossier(Long id) {
        final  Dossier dossier = null;
        if (dossierRepository.findById(id) != null) {
            dossier.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Dossier supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Dossier non trouvée", false);
            return message;
        }
    }

    public List<Dossier> getDossiersForPatient(String codePatient) {
        Patient patient = patientRepository.findByCodePatient(codePatient);


        if (patient == null) {
            return Collections.emptyList();
        }
        return dossierRepository.findByPatient(patient);
    }

    @Override
    public Dossier addDossier(Long patientId, Long medecinId, Dossier dossierDto) {
        return null;
    }

    @Override
    public Dossier updateDossier(Long dossierId, Long medecinId, Dossier dossierDto) {
        return null;
    }

    @Override
    public void deleteDossier(Long dossierId, Long medecinId) {

    }
    public Dossier trouverDossier(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

/*    public Consultation updateConsultation(Long id, Consultation consultation) {
        Consultation existingConsultation = consultationRepository.findById(id).orElse(null);
        if (existingConsultation == null) {
            return null;
        }
        existingConsultation.setDate(consultation.getDate());
        existingConsultation.setPatient(consultation.getPatient());
        existingConsultation.setMedecin(consultation.getMedecin());
        // Autres champs à mettre à jour...
        return consultationRepository.save(existingConsultation);
    }*/


}

