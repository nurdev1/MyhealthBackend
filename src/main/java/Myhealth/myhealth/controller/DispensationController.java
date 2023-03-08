package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dispensation;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.services.DispensationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/dispensation")
@CrossOrigin(origins = "http://localhost:8100")
public class DispensationController {

    private DispensationService dispensationService;

    @PostMapping("/ajouter")
    public ReponseMessage Ajouter(@RequestBody Dispensation dispensation){
        return dispensationService.creerDispensation(dispensation);
    }

    @PostMapping("/save")
    public ResponseEntity<Dispensation> uploadFile(@RequestBody Dispensation dispensation,@RequestParam("file") MultipartFile file) {
        dispensationService.storeFile(dispensation,file);
        return new ResponseEntity<>(dispensation, HttpStatus.CREATED);
    }

    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Dispensation dispensation){
        return dispensationService.modifierDispensation(dispensation);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return dispensationService.SupprimerDispensation(id);
    }
}
