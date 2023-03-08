package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Prescription;
import Myhealth.myhealth.services.PrescritionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/prescription")
@CrossOrigin(origins = "http://localhost:8100")
public class PrescriptionController {

    private PrescritionService prescritionService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Prescription prescription){
        return  prescritionService.creerPrescription(prescription);
    }

    @PostMapping("/save")
    public ResponseEntity<Prescription> uploadFile(@RequestBody Prescription prescription,@RequestParam("file") MultipartFile file) {
        prescritionService.storeFile(prescription,file);
        return new ResponseEntity<>(prescription, HttpStatus.CREATED);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Prescription prescription){
        return prescritionService.modifierPrescription(prescription);
    }

    @GetMapping("/afficher")
    public  List<Prescription> Afficher(){
        return prescritionService.afficherToutLesPrescription();
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return prescritionService.SupprimerPrescription(id);
    }
}
