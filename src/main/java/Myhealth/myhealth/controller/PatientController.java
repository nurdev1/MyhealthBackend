package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.jwt.JwtUtils;
import Myhealth.myhealth.mailNotification.EmailConstructor;
import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.SignupPatientRequest;
import Myhealth.myhealth.services.PatientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static Myhealth.myhealth.modeles.ERole.PATIENT;


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
    //encoder du password
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;




   /* @PostMapping("/ajouter")
    ResponseEntity<String> Ajouter(@RequestBody Patient patient1){

        String codePatient;
        int  i = 0 ;
        if(patientRepository.existsByEmail(patient1.getEmail())==false){
            System.err.println("hello");
            System.err.println(patient1.getNom());
            patient1.setRole(roleRepository.findByName(PATIENT));
            if (patient1.getPrenom() != null){
                System.out.println("dddddddddddddddddddddddddddddddddddddii");
                codePatient ="M"+patient1.getPrenom().substring(0,5) +patient1.getNom().substring(0,2)
                        + patient1.getTelephone().substring(0,3);
                patient1.setCodePatient(codePatient);
                System.out.println(codePatient);
                System.out.println("ddddddddddddddddddddddddddddddddddddd");
            }
        }
      *//*  String passe ="masante@2023";
        patient1.setMotdepasse(passe);*//*
        System.err.println(patient1.getNom());
        if (patient1.getNom() != null){
            System.err.println("Creer hello");
          //  mailSender.send(emailConstructor.constructNewUserEmail(patient1,patient1.getCodePatient()));
            patientService.creerPatient(patient1);

        }
        i = i+1;
      return new ResponseEntity<>("patient enregister avec succès", HttpStatus.OK);
    }*/
   @PostMapping("signup")
   public ResponseEntity<?> registerPatient(@Valid @RequestBody SignupPatientRequest signupPatientRequest) {
       if (patientRepository.existsByEmail(signupPatientRequest.getUsername())) {
           return ResponseEntity
                   .badRequest()
                   .body(new MessageResponse("Erreur: Ce nom d'utilisateur existe déjà!"));
       }

       if (patientRepository.existsByEmail(signupPatientRequest.getEmail())) {
           return ResponseEntity
                   .badRequest()
                   .body(new MessageResponse("Erreur: Cet email est déjà utilisé!"));
       }

       // Create new patient's account
       Patient patient = new Patient();


       patient.setUsername(signupPatientRequest.getUsername());
       patient.setEmail(signupPatientRequest.getEmail());
       encoder.encode(signupPatientRequest.getPassword());
       patient.setNom(signupPatientRequest.getNom());
       patient.setPrenom(signupPatientRequest.getPrenom());
       patient.setPhoto(signupPatientRequest.getPhoto());
       patient.setTelephone(signupPatientRequest.getTelephone());
       patient.setAdresse(signupPatientRequest.getCodePatient());
       patient.setPassword(encoder.encode(signupPatientRequest.getPassword()));
       // patient.setRole(roleRepository.findByName(PATIENT));
       Role patientRole = roleRepository.findByName(ERole.PATIENT);
       // .orElseThrow(() -> new RuntimeException("Erreur: le rôle n'est pas trouvé."));
       patient.setRole(patientRole);



      /*  Role patientRole = roleRepository.findByName(PATIENT)
                .orElseThrow(() -> new RuntimeException("Erreur: le rôle n'est pas trouvé."));
        Set<Role> roles = new HashSet<>();
        roles.add(patientRole);
        patient.setRoles(roles);*/
       String codePatient;
       int  i = 0 ;
       if(patientRepository.existsByEmail(patient.getEmail())==false){
           System.err.println("hello");
           System.err.println(patient.getNom());
           patient.setRole(roleRepository.findByName(PATIENT));
           if (patient.getPrenom() != null){
               System.out.println("dddddddddddddddddddddddddddddddddddddii");
               codePatient ="M"+patient.getTelephone().substring(0,2) + patient.getPrenom().substring(0,2) +patient.getNom().substring(0,2)
                       +  patient.getUsername().substring(0,2);
               patient.setCodePatient(codePatient);
               System.out.println(codePatient);
               System.out.println("ddddddddddddddddddddddddddddddddddddd");
           }
       }
      /*  String passe ="masante@2023";
        patient1.setMotdepasse(passe);*/
       System.err.println(patient.getNom());
       if (patient.getNom() != null){
           System.err.println("Creer hello");
           //  mailSender.send(emailConstructor.constructNewUserEmail(patient1,patient1.getCodePatient()));
       }
       i = i+1;

       patientRepository.save(patient);

       return ResponseEntity.ok(new MessageResponse("Patient enregistré avec succès!"));
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





    @GetMapping("/patient/{codePatient}")


    public ResponseEntity<Patient> getPatientByCodePatient(@PathVariable String codePatient) {

        Patient patient;



         patient = patientService.getPatientByCodePatient(codePatient);


        if (patient == null) {


            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    else {


        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}


}





