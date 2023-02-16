package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.services.DossierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dossier")
@CrossOrigin(origins = "http://localhost:8100")
public class DossierController {
    private DossierService dossierService;

    //ajout
    @PostMapping("/ajouter")
    public  ReponseMessage Ajouter(@RequestBody Dossier dossier){
        return dossierService.creerDossier(dossier);
    }
    //modifier
    @PutMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Dossier dossier){
        return dossierService.modifierDossier(dossier);
    }

    //supprimer
    @DeleteMapping("/supprimer/{id}")
    public ReponseMessage supprimer (@PathVariable Long id){
        return dossierService.SupprimerDossier(id);
    }

    //afficher
    @GetMapping("/afficher")
    public  List<Dossier> Afficher(){
        return dossierService.afficherToutLesDossier();
    }

    @PostMapping("/{medecinId}/patients/{patientId}/dossiers")
    public ResponseEntity<Dossier> addDossier(@PathVariable Long medecinId, @PathVariable Long patientId,
                                              @Valid @RequestBody Dossier dossierDto) {
        Dossier dossier = dossierService.addDossier(patientId, medecinId, dossierDto);
        return new ResponseEntity<>(dossier, HttpStatus.CREATED);
    }

    @PutMapping("/{medecinId}/dossiers/{dossierId}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long medecinId, @PathVariable Long dossierId,
                                                 @Valid @RequestBody Dossier dossierDto) {
        Dossier dossier = dossierService.updateDossier(dossierId, medecinId, dossierDto);
        return new ResponseEntity<>(dossier, HttpStatus.OK);
    }

    @DeleteMapping("/{medecinId}/dossiers/{dossierId}")
    public ResponseEntity<String> deleteDossier(@PathVariable Long medecinId, @PathVariable Long dossierId) {
        dossierService.deleteDossier(dossierId, medecinId);
        return new ResponseEntity<>("Le dossier a été supprimé avec succès", HttpStatus.OK);
    }

}

