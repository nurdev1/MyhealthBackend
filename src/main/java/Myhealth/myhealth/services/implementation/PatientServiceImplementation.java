package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.DossierRepository;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DossierRepository dossierRepository;
    @Override
    public ReponseMessage creerPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()) == null){
            patientRepository.save(patient);
            ReponseMessage message = new ReponseMessage("patient ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet patient existe déjà ", false);

            return message;
        }

    }
    @Override
    public Patient Save(MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            Patient patient = new Patient();
            patient.setFileName(fileName);
            patient.setFileType(file.getContentType());
            patient.setData(file.getBytes());
            patient.setDate(LocalDateTime.now());
            patient.setEtat(true);

            // Save the Dossier object to the database
            return patientRepository.save(patient);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }

    @Override
    public ReponseMessage modifierPatient(Patient patient) {
        if (patientRepository.findById(patient.getId()) !=null) {
            return patientRepository.findById(patient.getId())
                    .map(patient1->{
                        patient1.setNom(patient.getNom());
                        patient1.setPrenom(patient.getPrenom());
                        patient1.setAdresse(patient.getAdresse());
                        patientRepository.save(patient1);
                        ReponseMessage message = new ReponseMessage("Patient modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Patient non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Patient non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Patient> afficherToutLesPatient() {
        return patientRepository.findAll();
    }

    @Override
    public ReponseMessage SupprimerPatient(Long id) {
        final Patient patient = null;
        if (patientRepository.findById(id) != null) {
            patient.setEtat(false);
            ReponseMessage message = new ReponseMessage("Patient supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Patient non trouvée", false);
            return message;
        }
    }

    @Override
    public int NombrePatient() {
        return patientRepository.NombrePatient();
    }

    public Patient getPatientByCodePatient(String codePatient) {


        return patientRepository.findByCodePatient(codePatient);
    }

    @Override
    public List<Dossier> getDossiersForPatient(String codePatient) {
        Patient patient = patientRepository.findByCodePatient(codePatient);
        if (patient == null) {
            return Collections.emptyList();
        }
        return dossierRepository.findByPatient(patient);
    }
//medecin CRUD dossier pour patient

    @Override
    public Dossier createDossierForPatient(String codePatient, Dossier dossier) {
        Patient patient = patientRepository.findByCodePatient(codePatient);
        if (patient == null) {
            return null;
        }
        dossier.setPatient(patient);
        return dossierRepository.save(dossier);
    }

    @Override
    public Dossier updateDossierForPatient(String codePatient, Long dossierId, Dossier dossier) {
        Patient patient = patientRepository.findByCodePatient(codePatient);
        if (patient == null) {
            return null;
        }
        Dossier existingDossier = dossierRepository.findById(dossierId).orElse(null);
        if (existingDossier == null || !existingDossier.getPatient().equals(patient)) {
            return null;
        }
        dossier.setPatient(patient);
        dossier.setId(existingDossier.getId());
        return dossierRepository.save(dossier);
    }
    @Override
    public boolean deleteDossierForPatient(String codePatient, Long dossierId) {
        Patient patient = patientRepository.findByCodePatient(codePatient);
        if (patient == null) {
            return false;
        }
        Dossier existingDossier = dossierRepository.findById(dossierId).orElse(null);
        if (existingDossier == null || !existingDossier.getPatient().equals(patient)) {
            return false;
        }
        dossierRepository.delete(existingDossier);
        return true;
    }

}







