package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.services.AnalyseService;
import org.springframework.web.bind.annotation.*;

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
}
