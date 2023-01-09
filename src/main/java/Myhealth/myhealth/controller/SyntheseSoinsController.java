package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.SyntheseDeSoins;
import Myhealth.myhealth.services.SystheseSoinsService;
import org.springframework.web.bind.annotation.*;

public class SyntheseSoinsController {
    private SystheseSoinsService systheseSoinsService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody SyntheseDeSoins syntheseDeSoins){
        return   systheseSoinsService.creerSyntheseSoins(syntheseDeSoins);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody SyntheseDeSoins syntheseDeSoins){
        return systheseSoinsService.modifierSyntheseSoins(syntheseDeSoins);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return systheseSoinsService.SupprimerSyntheseDeSoins(id);
    }
}
