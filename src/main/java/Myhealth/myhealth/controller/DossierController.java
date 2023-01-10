package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.services.DossierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
