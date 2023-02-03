package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Hopital;
import Myhealth.myhealth.services.HopitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hopital")
@CrossOrigin(origins = "http://localhost:8100")
public class HopitalController {

   private HopitalService hopitalService;

//ajouter hospital
    @PostMapping("/ajouter")
    public ReponseMessage ajouter(@RequestBody Hopital hopital){

        return   hopitalService.creerHopital(hopital);
    }

    //afficher
    @GetMapping("/afficher")
    public List<Hopital> Afficher(){

        return hopitalService.afficherToutLesHopital();
    }
    @GetMapping
    //modifier
    @PutMapping("/modifier")
    public ReponseMessage modifier(@RequestBody Hopital hopital){
        return hopitalService.modifierHopital(hopital);
    }
    //ici on Supprimer un Hopital
    @DeleteMapping("/supprimer/{id}")
    public ReponseMessage delete (@PathVariable Long id){

        return hopitalService.SupprimerHopital(id);
    }

    @GetMapping("/compte")
    public int nombre(){

        return hopitalService.NombreHopital();
    }

    @GetMapping("/compteparville")
    public List<Object> CompteParVille(){

        return hopitalService.NombreHopitalParVille();
    }

}
