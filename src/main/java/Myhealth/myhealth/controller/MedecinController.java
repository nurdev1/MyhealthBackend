package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;

import Myhealth.myhealth.mailNotification.EmailMedecinConstructor;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.MedecinRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.SignupMedecinRequest;
import Myhealth.myhealth.services.MedecinService;
import Myhealth.myhealth.services.PatientService;
import Myhealth.myhealth.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static Myhealth.myhealth.modeles.ERole.*;

@RestController
@AllArgsConstructor
@RequestMapping("/medecin")
@CrossOrigin
public class MedecinController {

    private MedecinService medecinService;
    private JavaMailSender mailSender;
    EmailMedecinConstructor emailMedecinConstructor;
    PatientService patientService;
    private RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    MedecinRepository medecinRepository;


 /*  @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Medecin medecin){
       medecin.setRole(roleRepository.findByName(MEDECIN));
       ReponseMessage message =  medecinService.creerMedecin(medecin);
       System.out.println(medecin);
       // mailSender.send(emailMedecinConstructor.constructNewMedecinEmail(medecin,medecin.getPassword()));
        return message;
    }*/

/*   @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Medecin medecin) {
        return medecinService.creerMedecin(medecin);
    }*/
@PostMapping("/save")
public ResponseEntity<?> registerMedecin(@Valid @RequestBody SignupMedecinRequest signupMedecinRequest) {
    if (medecinRepository.existsByEmail(signupMedecinRequest.getUsername())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Erreur: Ce nom d'utilisateur existe déjà!"));
    }

    if (medecinRepository.existsByEmail(signupMedecinRequest.getEmail())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Erreur: Cet email est déjà utilisé!"));
    }

    // Create new patient's account
    Medecin medecin = new Medecin();


    medecin.setUsername(signupMedecinRequest.getUsername());
    medecin.setEmail(signupMedecinRequest.getEmail());
    encoder.encode(signupMedecinRequest.getPassword());
    medecin.setNom(signupMedecinRequest.getNom());
    medecin.setPrenom(signupMedecinRequest.getPrenom());
    medecin.setPhoto(signupMedecinRequest.getPhoto());
    medecin.setTelephone(signupMedecinRequest.getTelephone());
    medecin.setSpecialite(signupMedecinRequest.getSpecialitet());
    medecin.setPassword(encoder.encode(signupMedecinRequest.getPassword()));
    // patient.setRole(roleRepository.findByName(PATIENT));
    Role patientRole = roleRepository.findByName(ERole.MEDECIN);
    // .orElseThrow(() -> new RuntimeException("Erreur: le rôle n'est pas trouvé."));
    medecin.setRole(patientRole);



    System.out.println(medecin);
    // mailSender.send(emailMedecinConstructor.constructNewMedecinEmail(medecin,medecin.getPassword()));

    medecinRepository.save(medecin);
    System.out.println(medecin);

    return ResponseEntity.ok(new MessageResponse("Medecin enregistré avec succès!"));
}
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Medecin medecin) {
        return medecinService.modifierMedecin(medecin);
    }

    @GetMapping("/afficher")
    public List<Medecin> Afficher() {

        return medecinService.afficherToutLesMedecin();
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id) {
        return medecinService.SupprimerMedecin(id);
    }

    //nombre de médecin
    @GetMapping("/compte")
    public int nombreMedecin() {
        return medecinService.NombreMedecin();
    } //nombre de médecin

    @GetMapping("/nbrspecialiste")
    public List<Object> nombreSpecialiste() {
        return medecinService.NombreMedecinSpecialite();
    } //nombre de médecin

    @GetMapping("/nbreparhopital")
    public List<Object> nombreMedecin(@PathVariable String hopital) {
        return medecinService.nombreMedecinparHopital(hopital);
    } //nombre de médecin

    @GetMapping("/nbremedecinhopital")
    public List<Object> nombreMedecinHopital() {
        return medecinService.nombreMedecinHopital();
    }

    @GetMapping("listeMedecin")
    public List<Object> HopitalListeMedecin() {
        return medecinService.HopitalListeMedecin();
    }

    /*@PostMapping
    public ResponseEntity<Medecin> createUser(@RequestBody Medecin user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activatedUser);
    }*/

    @GetMapping("/patient/{codePatient}/dossiers")
    public ResponseEntity<List<Dossier>> getDossiersForPatient(@PathVariable String codePatient) {
        List<Dossier> dossiers = patientService.getDossiersForPatient(codePatient);
        if (dossiers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dossiers, HttpStatus.OK);
        }
    }

    //CRUD
    @PostMapping("/patient/{codePatient}/dossiers")
    public ResponseEntity<Dossier> createDossierForPatient(@PathVariable String codePatient, @RequestBody Dossier dossier) {
        Dossier createdDossier = patientService.createDossierForPatient(codePatient, dossier);
        if (createdDossier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
        }
    }

 /*   @PutMapping("/patient/{codePatient}/dossiers/{dossierId}")
    public ResponseEntity<Dossier> updateDossierForPatient(@PathVariable String codePatient, @PathVariable Long dossierId, @RequestBody Dossier dossier) {
        Dossier updatedDossier = patientService.updateDossierForPatient(codePatient, dossierId, dossier);
        if (updatedDossier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/



    @PostMapping("/activatedEmail")
    public ResponseEntity<Medecin> addMedecin() {
        medecinService.acivateEmailMedecin();
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<String> activerMedecin(@PathVariable("id") Long id) {
        medecinService.activerMedecin(id);
        return ResponseEntity.ok("Le médecin a été activé avec succès.");
    }
}

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerPatient(@RequestBody SignupPatientRequest signupPatientRequest) {
        try {
            patientService.registerPatient(signupPatientRequest);
            return ResponseEntity.ok(new MessageResponse("Patient enregistré avec succès!"));
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: " + e.getMessage()));
        }
    }
}
*/


