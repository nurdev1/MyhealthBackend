package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Consultation;
import Myhealth.myhealth.services.ConsultationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/consultation")
@CrossOrigin(origins = "http://localhost:8100")
public class ConsultationController {

private ConsultationService consultationService;
    @PostMapping("/ajouter/{medecin}/{patient}")
    ReponseMessage Ajouter(@RequestBody Consultation consultation,  @PathVariable String medecin, @PathVariable String patient){
        return   consultationService.creerConsultation(consultation,medecin,patient);
    }
    @GetMapping("/afficher")
    List<Consultation> Afficher(){
        return consultationService.afficherToutLesConsultation();
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Consultation consultation){
        return consultationService.modifierConsultation(consultation);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return
                consultationService.SupprimerConsultation(id);
    }
    //nombre consultation
    int NombreConsultationMedecin(){
      return   consultationService.NombreConsultationMedecin();
    }
}
