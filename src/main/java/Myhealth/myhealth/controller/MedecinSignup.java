package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.jwt.JwtUtils;
import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.reponse.JwtResponse;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.MedecinRepository;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.LoginRequest;
import Myhealth.myhealth.request.SignupMedecinRequest;
import Myhealth.myhealth.request.SignupPatientRequest;
import Myhealth.myhealth.services.MedecinDetailsImpl;
import Myhealth.myhealth.services.MedecinService;
import Myhealth.myhealth.services.PatientDetailsImpl;
import Myhealth.myhealth.services.UtilisateusDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static Myhealth.myhealth.modeles.ERole.PATIENT;

@RestController
@RequestMapping("/medecin")
public class MedecinSignup {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MedecinRepository medecinRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MedecinService medecinService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /*@PostMapping("/signup")
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
        medecin.setFileName(signupMedecinRequest.getFileName());
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
*/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    /*
     AuthenticationManager est comme un coordinateur où vous pouvez enregistrer plusieurs fournisseurs et,
     en fonction du type de demande, il enverra une demande d'authentification au bon fournisseur.
     */

        //authenticate effectue l'authentification avec la requête.

     /*
       AuthenticationManager utilise DaoAuthenticationProvider(avec l'aide de
       UserDetailsService& PasswordEncoder) pour valider l'instance de UsernamePasswordAuthenticationToken,
       puis renvoie une instance entièrement remplie Authenticationen cas d'authentification réussie.
     */

        Authentication authentication = authenticationManager.authenticate(
                //on lui fournit un objet avec username et password fournit par l'admin
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    /*
      SecurityContext et SecurityContextHolder sont deux classes fondamentales de Spring Security .
      Le SecurityContext est utilisé pour stocker les détails de l'utilisateur
      actuellement authentifié, également appelé principe. Donc, si vous devez obtenir
      le nom d'utilisateur ou tout autre détail d'utilisateur, vous devez d'abord obtenir
      ce SecurityContext . Le SecurityContextHolder est une classe d'assistance qui permet
      d'accéder au contexte de sécurité.
     */

        //on stocke les informations de connexion de l'utilisateur actuelle souhaiter se connecter dans SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //on envoie encore les infos au generateur du token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //on recupere les infos de l'user
        UtilisateusDetailsImpl medecinDetails = (UtilisateusDetailsImpl) authentication.getPrincipal();

        //on recupere les roles de l'users
        List<String> roles = medecinDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("conexion controlleur");

        //on retourne une reponse, contenant l'id username, e-mail et le role du collaborateur
        return ResponseEntity.ok(new JwtResponse(jwt,
                medecinDetails.getId(),
                medecinDetails.getUsername(),
                medecinDetails.getEmail(),
                medecinDetails.getNom(),
                medecinDetails.getPrenom(),
                roles));
    }

}
