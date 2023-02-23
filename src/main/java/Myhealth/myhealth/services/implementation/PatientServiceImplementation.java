package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.DossierRepository;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

/*    @Override
    public ReponseMessage SupprimerPatient(Long idpatient) {
        if(patientRepository.findByIdpatient(idpatient) != null){
            patientRepository.deleteById(idpatient);
            ReponseMessage message = new ReponseMessage("Patient supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Patient non trouvé", false);
            return message;
        }
    }*/
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

    /*public Patient registerPatient(SignupRequest signUpRequest) {
        if (patientRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Ce nom d'utilisateur existe déjà!");
        }

        if (patientRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Cet email est déjà utilisé!");
        }

        // Create new patient's account
        Patient patient = new Patient(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getNom(), signUpRequest.getPrenom(),
                signUpRequest.getPhoto(), signUpRequest.getTelephone(), signUpRequest.getVille(),
                signUpRequest.getAdresse());

        // Set patient role
        Role patientRole = new Role();
        patientRole.setName(ERole.PATIENT);
        patient.setRoles(Collections.singleton(patientRole));

        return patientRepository.save(patient);
    }*/
}







