package Myhealth.myhealth.controller;

import Myhealth.myhealth.jwt.JwtUtils;
import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.modeles.Utilisateus;
import Myhealth.myhealth.reponse.JwtResponse;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.LoginRequest;
import Myhealth.myhealth.request.SignupPatientRequest;
import Myhealth.myhealth.request.SignupRequest;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Myhealth.myhealth.modeles.ERole.PATIENT;

@RestController
@RequestMapping("/patient")
public class PatientSignup {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    /*
     AuthenticationManager est comme un coordinateur o?? vous pouvez enregistrer plusieurs fournisseurs et,
     en fonction du type de demande, il enverra une demande d'authentification au bon fournisseur.
     */

        //authenticate effectue l'authentification avec la requ??te.

     /*
       AuthenticationManager utilise DaoAuthenticationProvider(avec l'aide de
       UserDetailsService& PasswordEncoder) pour valider l'instance de UsernamePasswordAuthenticationToken,
       puis renvoie une instance enti??rement remplie Authenticationen cas d'authentification r??ussie.
     */

        Authentication authentication = authenticationManager.authenticate(
                //on lui fournit un objet avec username et password fournit par l'admin
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    /*
      SecurityContext et SecurityContextHolder sont deux classes fondamentales de Spring Security .
      Le SecurityContext est utilis?? pour stocker les d??tails de l'utilisateur
      actuellement authentifi??, ??galement appel?? principe. Donc, si vous devez obtenir
      le nom d'utilisateur ou tout autre d??tail d'utilisateur, vous devez d'abord obtenir
      ce SecurityContext . Le SecurityContextHolder est une classe d'assistance qui permet
      d'acc??der au contexte de s??curit??.
     */

        //on stocke les informations de connexion de l'utilisateur actuelle souhaiter se connecter dans SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //on envoie encore les infos au generateur du token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //on recupere les infos de l'user
        UtilisateusDetailsImpl patientDetails = (UtilisateusDetailsImpl) authentication.getPrincipal();

        //on recupere les roles de l'users
        List<String> roles = patientDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("conexion controlleur");

        //on retourne une reponse, contenant l'id username, e-mail et le role du collaborateur
        return ResponseEntity.ok(new JwtResponse(jwt,
                patientDetails.getId(),
                patientDetails.getUsername(),
                patientDetails.getEmail(),
                patientDetails.getNom(),
                patientDetails.getPrenom(),
                roles));
    }



}
