package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.modeles.SyntheseMedical;
import Myhealth.myhealth.services.SystheseMedicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SyntheseMedicalController {

    private SystheseMedicalService systheseMedicalService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody SyntheseMedical syntheseMedical){
        return   systheseMedicalService.AjouterSystheseMedical(syntheseMedical);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody SyntheseMedical syntheseMedical){
        return systheseMedicalService.modifierSystheseMedical(syntheseMedical);
    }
    @GetMapping("/afficher")
    public List<SyntheseMedical> Afficher(){
        return systheseMedicalService.afficherToutLesSystheseMedical();
    }
    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return systheseMedicalService.SupprimerSystheseMedical(id);
    }
}
