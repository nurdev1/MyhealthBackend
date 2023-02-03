package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Soins;
import Myhealth.myhealth.services.SoinsService;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/soins")
@CrossOrigin(origins = "http://localhost:8100")
public class SoinsController {
    private SoinsService systheseSoinsService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Soins syntheseDeSoins){
        return   systheseSoinsService.creerSyntheseSoins(syntheseDeSoins);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Soins syntheseDeSoins){
        return systheseSoinsService.modifierSyntheseSoins(syntheseDeSoins);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return systheseSoinsService.SupprimerSyntheseDeSoins(id);
    }
}
