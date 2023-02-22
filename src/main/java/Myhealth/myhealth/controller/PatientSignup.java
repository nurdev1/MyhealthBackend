package Myhealth.myhealth.controller;

import Myhealth.myhealth.jwt.JwtUtils;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.SignupPatientRequest;
import Myhealth.myhealth.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class PatientSignup {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /*@PostMapping("/signup/patient")
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
        User patient = new User(signupPatientRequest.getUsername(),
                signupPatientRequest.getEmail(),
                encoder.encode(signupPatientRequest.getPassword()));

        patient.setNom(signupPatientRequest.getNom());
        patient.setPrenom(signupPatientRequest.getPrenom());
        patient.setPhoto(signupPatientRequest.getPhoto());
        patient.setTelephone(signupPatientRequest.getTelephone());
        patient.setVille(signupPatientRequest.getVille());
        patient.setAdresse(signupPatientRequest.getCodePatient());

        Role patientRole = roleRepository.findByName(RoleName.ROLE_PATIENT)
                .orElseThrow(() -> new RuntimeException("Erreur: le rôle n'est pas trouvé."));
        Set<Role> roles = new HashSet<>();
        roles.add(patientRole);
        patient.setRoles(roles);

        patientRepository.save(patient);

        return ResponseEntity.ok(new MessageResponse("Patient enregistré avec succès!"));
    }

*/
/*
    @PostMapping("/signup")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            Patient patient = patientService.registerPatient(signUpRequest);
            return ResponseEntity.ok(new MessageResponse("Patient enregistré avec succès!"));
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: " + e.getMessage()));
        }
    }
*/


}
