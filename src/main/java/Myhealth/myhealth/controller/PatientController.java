package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:8100")
public class PatientController {
    private PatientService patientService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Patient patient){
        return   patientService.creerPatient(patient);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Patient patient){
        return patientService.modifierPatient(patient);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return patientService.SupprimerPatient(id);
    }

    @GetMapping("/afficher")
    public List<Patient> Afficher(){
        return patientService.afficherToutLesPatient();
    }
}
