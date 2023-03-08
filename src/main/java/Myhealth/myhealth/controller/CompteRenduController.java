package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.services.CompteRenduService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/compteRendu")
@CrossOrigin(origins = "http://localhost:8100")
public class CompteRenduController {

    private CompteRenduService compteRenduService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody CompteRendu compteRendu){
      return   compteRenduService.creerCompteRendu(compteRendu);
    }
    @PostMapping("/save")
    public ResponseEntity<CompteRendu> uploadFile(@RequestBody CompteRendu compteRendu,@RequestParam("file") MultipartFile file) {
        compteRenduService.storeFile(compteRendu,file);
        return new ResponseEntity<>(compteRendu, HttpStatus.CREATED);
    }
    @GetMapping("afficher")
    public List<CompteRendu> Afficher(){
        return compteRenduService.afficherToutLesCompteRendu();
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody CompteRendu compteRendu){
        return compteRenduService.creerCompteRendu(compteRendu) ;
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return compteRenduService.SupprimerCompteRendu(id);
    }
}