package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService{

    @Autowired
    PatientRepository patientRepository;
    @Override
    public ReponseMessage creerPatient(Patient patient) {
        if (patientRepository.findByIdpatient(patient.getIdpatient()) == null){
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
        if (patientRepository.findByIdpatient(patient.getIdpatient()) !=null) {
            return patientRepository.findById(patient.getIdpatient())
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
    public ReponseMessage SupprimerPatient(Long idpatient) {
        if(patientRepository.findByIdpatient(idpatient) != null){
            patientRepository.deleteById(idpatient);
            ReponseMessage message = new ReponseMessage("Patient supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("Patient non trouvé", false);
            return message;
        }
    }
}
