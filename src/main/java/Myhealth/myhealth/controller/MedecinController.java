package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;

import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.services.MedecinService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medecin")
@CrossOrigin(origins = "http://localhost:8100")

public class MedecinController {

    private MedecinService medecinService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Medecin medecin){
        return   medecinService.creerMedecin(medecin);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Medecin medecin){
        return medecinService.modifierMedecin(medecin);
    }

    @GetMapping("/afficher")
    public List<Medecin> Afficher(){
        return medecinService.afficherToutLesMedecin();
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return medecinService.SupprimerMedecin(id);
    }

    //nombre de médecin
    @GetMapping("/compte")
    public int nombreMedecin(){
        return medecinService.NombreMedecin();
    } //nombre de médecin
    @GetMapping("/nbrspecialiste")
    public List<Object> nombreSpecialiste(){
        return medecinService.NombreMedecinSpecialite();
    } //nombre de médecin
    @GetMapping("/nbreparhopital")
    public List<Object> nombreMedecin(@PathVariable String hopital){
        return medecinService.nombreMedecinparHopital(hopital);
    } //nombre de médecin
    @GetMapping("/nbremedecinhopital")
    public List<Object> nombreMedecinHopital(){
        return medecinService.nombreMedecinHopital();
    }

    @GetMapping("listeMedecin")
    public  List<Object> HopitalListeMedecin(){
        return  medecinService.HopitalListeMedecin();
    }
}
