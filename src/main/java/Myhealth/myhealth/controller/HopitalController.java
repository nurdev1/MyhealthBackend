package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Hopital;
import Myhealth.myhealth.services.HopitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println(hopital.getNom());
        return   hopitalService.creerHopital(hopital);
    }

    @PostMapping("/save")
    public ResponseEntity<Hopital> uploadFile(@RequestBody Hopital hopital,@RequestParam("file") MultipartFile file) {
        hopitalService.storeFile(hopital,file);
        return new ResponseEntity<>(hopital, HttpStatus.CREATED);
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
    @GetMapping("/nouveau")
    public  List<Hopital> NouveauHopital(){

        return hopitalService.NouveauHopital();
    }



}
