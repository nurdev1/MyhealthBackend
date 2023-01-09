package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Imagerie;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.services.ImagerieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/imagerie")
@CrossOrigin(origins = "http://localhost:8100")
public class ImagerieController {

    private ImagerieService imagerieService;


    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Imagerie imagerie){
        return  imagerieService.creerImagerie(imagerie);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Imagerie imagerie){
        return imagerieService.modifierImagerie(imagerie);
    }

    @GetMapping("/afficher")
    public List<Imagerie> Afficher(){
      return   imagerieService.afficherToutLesImagerie();
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return imagerieService.SupprimerImagerie(id);
    }
}
