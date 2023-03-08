package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.*;
import Myhealth.myhealth.repository.ConsultationRepository;
import Myhealth.myhealth.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImplementation implements ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

        @Override
        public ReponseMessage creerConsultation(Consultation consultation) {
            if (consultationRepository.findByNom(consultation.getNom()) == null) {
                consultationRepository.save(consultation);
                ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
                return message;
            } else {
                ReponseMessage message = new ReponseMessage("Ce consultation  existe déjà ", false);
                return message;
            }
        }
 /*   @Override
    public ReponseMessage creerConsultation(Consultation consultation, MultipartFile file) {
        if (consultationRepository.findByNom(consultation.getNom()) == null) {
            // Normaliser le nom du fichier
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                // Vérifiez si le nom du fichier contient des caractères invalides
                if(fileName.contains("..")) {
                    throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
                }

                Consultation dbFile = new Consultation(fileName, file.getContentType(), file.getBytes());
                consultationRepository.save(consultation);
                ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
                return message;
            } catch (IOException ex) {
                throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
            }
           *//* consultationRepository.save(consultation);
            ReponseMessage message = new ReponseMessage("consultation ajouté avec succes", true);
            return message;*//*
        } else {
            ReponseMessage message = new ReponseMessage("Ce consultation  existe déjà ", false);

            return message;
        }
    }*/
    //,  int medecin, int patient

    @Override
    public ReponseMessage modifierConsultation(Consultation consultation) {
        if (consultationRepository.findById(consultation.getId()) != null) {
            return consultationRepository.findById(consultation.getId())
                    .map(consultation1 -> {
                        consultation1.setNom(consultation.getNom());
                        consultation1.setDescription(consultation.getDescription());
                        consultation1.setFileName(consultation.getFileName());
                        consultationRepository.save(consultation1);
                        ReponseMessage message = new ReponseMessage("consultation modifié avec succes", true);
                        return message;
                    }).orElseThrow(() -> new RuntimeException("Désole, consultation non trouvée"));
        } else {
            ReponseMessage message = new ReponseMessage("Désole, consultation non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Consultation> afficherToutLesConsultation() {
        return null;
    }


    @Override
    public ReponseMessage SupprimerConsultation(Long id) {
        final Consultation consultation = null;
        if (consultationRepository.findById(id) != null) {
            consultation.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Consultation supprimée avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage(" Consultation non trouvée", false);
            return message;
        }
    }

    @Override
    public int NombreConsultationMedecin() {
        return consultationRepository.NombreConsultationMedecin();
    }

    @Override
    public Consultation saveOrUpdateConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Optional<Consultation> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public List<Consultation> getConsultationsByMedecinId(Long medecinId) {
        return consultationRepository.findByMedecinId(medecinId);
    }

    @Override
    public List<Consultation> getConsultationsByPatientId(Long patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    @Override
    public void deleteConsultationById(Long id) {
        consultationRepository.deleteById(id);
    }

/*    @Override
    public Consultation storeFile(Consultation consultation,MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            consultation.setFileName(fileName);
            consultation.setFileType(file.getContentType());
            consultation.setData(file.getBytes());
            //consultation.setDate(LocalDateTime.now());
            consultation.setEtat(true);

            // Save the Dossier object to the database
            return consultationRepository.save(consultation);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }*/

    public Consultation creerConsultation(Medecin medecin, Patient patient, String titre, String desciption, String fileName) {
        Consultation consultation = new Consultation();
        consultation.setMedecin(medecin);
        consultation.setPatient(patient);
        consultation.setNom(titre);
        consultation.setDescription(desciption);
        consultation.setFileName(fileName);
        consultationRepository.save(consultation);
        return consultation;
    }

}


