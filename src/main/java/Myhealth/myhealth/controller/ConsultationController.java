package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.modeles.DatabaseFile;
import Myhealth.myhealth.payloadFile.Response;
import Myhealth.myhealth.services.ConsultationService;
import Myhealth.myhealth.services.MedecinService;
import Myhealth.myhealth.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/consultation")
@CrossOrigin(origins = "http://localhost:8100")
public class ConsultationController {

private ConsultationService consultationService;
private MedecinService medecinService;
private PatientService patientService;
    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Consultation consultation, MultipartFile file){
        System.out.println(consultation);
        Consultation fileName = (Consultation) file;

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return   consultationService.creerConsultation(consultation,file);
    }
    //@PathVariable int medecin, @PathVariable int patient  ,medecin,patient
    @GetMapping("/afficher")
    List<Consultation> Afficher(){
        return consultationService.afficherToutLesConsultation();
    }
    @PutMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Consultation consultation){
        return consultationService.modifierConsultation(consultation);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return
                consultationService.SupprimerConsultation(id);
    }
    //nombre consultation
    int NombreConsultationMedecin(){
      return   consultationService.NombreConsultationMedecin();
    }

/*    @GetMapping("/afficher/idmedecin")
    public List<Object> MedecinConsultation (Long idmedecin){
        return consultationService.MedecinConsultation(idmedecin);
    }*/

    @GetMapping("")
    public List<Consultation> getAllConsultations() {
        return consultationService.getAllConsultations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Optional<Consultation> consultation = consultationService.getConsultationById(id);
        if (consultation.isPresent()) {
            return new ResponseEntity<>(consultation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Consultation> saveConsultation(@RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationService.saveOrUpdateConsultation(consultation);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")/**/
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation consultation) {
        Optional<Consultation> existingConsultation = consultationService.getConsultationById(id);
        if (existingConsultation.isPresent()) {
            consultation.setId(id);
            Consultation savedConsultation = consultationService.saveOrUpdateConsultation(consultation);
            return new ResponseEntity<>(savedConsultation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        Optional<Consultation> existingConsultation = consultationService.getConsultationById(id);
        if (existingConsultation.isPresent()) {
            consultationService.deleteConsultationById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/medecin/{id}")
    public List<Consultation> getConsultationsByMedecinId(@PathVariable Long id) {
        return consultationService.getConsultationsByMedecinId(id);
    }

    @GetMapping("/patient/{id}")
    public List<Consultation> getConsultationsByPatientId(@PathVariable Long id) {
        return consultationService.getConsultationsByPatientId(id);
    }

    /*@GetMapping("/creer-consultation")
    public String creerConsultation(Model model) {
        // Récupérer les informations du médecin et du patient
        Medecin medecin =null;
        // récupérer le médecin à partir de la base de données
        Patient patient =null ;
        // récupérer le patient à partir de la base de données

        // Créer une nouvelle consultation
        Consultation consultation = consultationService.creerConsultation(medecin, patient, "Consultation de suivi", "Consultation de suivi suite à un traitement", "resultat.pdf");

        // Ajouter la consultation au modèle pour l'afficher dans la vue
        model.addAttribute("consultation", consultation);

        // Afficher la vue de la consultation créée
        return "consultation";
    }*/
   /* @PostMapping("/creer-consultation/{patientId}")
    public String creerConsultation(@PathVariable Long patientId, @RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam("fichier") MultipartFile fichier, Principal principal, Model model) throws IOException {
        // Récupérer le patient à partir de l'identifiant donné
        Patient patient = patientService.getPatientById(patientId);

        // Récupérer le médecin à partir de l'identifiant de l'utilisateur connecté
        Medecin medecin = medecinService.getMedecinByUserId(principal.getName());

        // Vérifier que le médecin et le patient existent
        if (medecin == null || patient == null) {
            throw new RuntimeException("Médecin ou patient introuvable.");
        }

        // Enregistrer le fichier dans le système de fichiers et récupérer son chemin d'accès
        String cheminFichier = "";
        if (fichier != null) {
            String nomFichier = StringUtils.cleanPath(fichier.getOriginalFilename());
            cheminFichier = "chemin/vers/le/dossier/" + nomFichier;
            Path cheminAbsolu = Paths.get(cheminFichier).toAbsolutePath().normalize();
            Files.copy(fichier.getInputStream(), cheminAbsolu, StandardCopyOption.REPLACE_EXISTING);
        }

        // Créer une nouvelle consultation
        Consultation consultation = consultationService.creerConsultation(medecin, patient, titre, description, cheminFichier);

        // Ajouter la consultation au modèle pour l'afficher dans la vue
        model.addAttribute("consultation", consultation);

        // Afficher la vue de la consultation créée
        return "consultation";
    }
*/
}


