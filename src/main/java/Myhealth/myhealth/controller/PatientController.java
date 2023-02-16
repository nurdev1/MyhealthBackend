package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.mailNotification.EmailConstructor;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.services.PatientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static Myhealth.myhealth.modeles.ERole.ROLE_Patient;

@Data
@RestController
@AllArgsConstructor
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:8100")
public class PatientController {
    private PatientService patientService;
    private PatientRepository patientRepository;
    private RoleRepository roleRepository;
    private JavaMailSender mailSender;

    @Autowired
    EmailConstructor emailConstructor;

    @PostMapping("/ajouter")
    ResponseEntity<String> Ajouter(@RequestBody Patient patient1){
        String codePatient;
        int  i = 0 ;
        if(patientRepository.existsByEmail(patient1.getEmail())==false){
            System.err.println("hello");
            System.err.println(patient1.getNom());
            patient1.setRole(roleRepository.findByName(ROLE_Patient));
            if (patient1.getPrenom() != null){
                codePatient =patient1.getPrenom().substring(0,2) +patient1.getNom().substring(0,2)
                        + patient1.getTelephone().substring(0,2)+ i;
                patient1.setCodePatient(codePatient);
            }
        }
        String passe ="masante@2023";
        patient1.setMotdepasse(passe);
        System.err.println(patient1.getNom());
        if (patient1.getNom() != null){
            System.err.println("Creer hello");
           // mailSender.send(emailConstructor.constructNewUserEmail(patient1,patient1.getMotdepasse()));
            patientService.creerPatient(patient1);

        }
        i = i+1;
      return new ResponseEntity<>("patient enregister avec succès", HttpStatus.OK);
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

    @GetMapping("/compte")
    public int nombre(){
        return patientService.NombrePatient();
    }


}
