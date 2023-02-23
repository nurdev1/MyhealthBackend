package Myhealth.myhealth.controller;

import Myhealth.myhealth.jwt.JwtUtils;
import Myhealth.myhealth.modeles.Utilisateus;
import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.reponse.JwtResponse;
import Myhealth.myhealth.reponse.MessageResponse;
import Myhealth.myhealth.repository.PatientRepository;
import Myhealth.myhealth.repository.UtilisateusRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.request.LoginRequest;
import Myhealth.myhealth.request.SignupRequest;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
//@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UtilisateusRepository collaborateursRepository;
  @Autowired
  PatientRepository patientRepository;

  @Autowired
  RoleRepository roleRepository;


  //encoder du password
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  //@Valid assure la validation de l'ensemble de l'objet
  //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
    UtilisateusDetailsImpl collaborateursDetails = (UtilisateusDetailsImpl) authentication.getPrincipal();

    //on recupere les roles de l'users
    List<String> roles = collaborateursDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    log.info("conexion controlleur");

    //on retourne une reponse, contenant l'id username, e-mail et le role du collaborateur
    return ResponseEntity.ok(new JwtResponse(jwt,
                         collaborateursDetails.getId(),
                         collaborateursDetails.getUsername(),
                         collaborateursDetails.getEmail(),
                         roles));
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/signup")//@valid s'assure que les données soit validées
  //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (collaborateursRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Ce nom d'utilisateur existe déjà!"));
    }

    if (collaborateursRepository.existsByEmail(signUpRequest.getEmail())) {

      //confectionne l'objet de retour à partir de ResponseEntity(une classe de spring boot) et MessageResponse
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Cet email est déjà utilisé!"));
    }

    // Create new user's account
    Utilisateus collaborateurs = new Utilisateus(signUpRequest.getUsername(),

               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    //on recupere le role de l'user dans un tableau ordonné de type string
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      System.out.println("####################################" + signUpRequest.getRole() + "###########################################");

      //on recupere le role de l'utilisateur
      Role userRole = roleRepository.findByName(ERole.PATIENT);
      System.out.println("############################ attributionde role: " + userRole + "###########################################");

      //on recupere le role de l'utilisateur
      Role usermedecinRole = roleRepository.findByName(ERole.MEDECIN);
      System.out.println("############################ attributionde role: " + userRole + "###########################################");

      roles.add(userRole);//on ajoute le role de l'user à roles
      System.out.println("############################ affichage de role: " + roles + "###########################################");

    } else {
      strRoles.forEach(role -> {//on parcours le role
        switch (role) {
        case "admin"://si le role est à égale à admin
          Role adminRole = roleRepository.findByName(ERole.ADMIN);
          roles.add(adminRole);

          break;
          case "patient":
            //on recupere le role de l'utilisateur
            Role patientRole = roleRepository.findByName(ERole.PATIENT);
            roles.add(patientRole);
        default://dans le cas écheant

          //on recupere le role de l'utilisateur
          Role medecinRole = roleRepository.findByName(ERole.MEDECIN);
          roles.add(medecinRole);
        }
      });
    }

    //on ajoute le role au collaborateur
    collaborateurs.setRoles(roles);
    collaborateursRepository.save(collaborateurs);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
  }
}
