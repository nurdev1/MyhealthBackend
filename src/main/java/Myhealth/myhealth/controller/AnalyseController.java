package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.services.AnalyseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/analyse")
@CrossOrigin(origins = "http://localhost:8100")
public class AnalyseController {
    private AnalyseService analyseService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Analyse analyse){
        return   analyseService.creerAnalyse(analyse);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Analyse analyse){
        return analyseService.modifierAnalyse(analyse) ;
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return analyseService.SupprimerAnalyse(id);
    }

    @GetMapping("/afficher")
    public List<Analyse> Afficher(){
        return analyseService.afficherToutLesAnalyse();
    }
}
