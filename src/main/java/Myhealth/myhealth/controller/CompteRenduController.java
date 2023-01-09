package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.CompteRendu;
import Myhealth.myhealth.services.CompteRenduService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody CompteRendu compteRendu){
        return compteRenduService.creerCompteRendu(compteRendu) ;
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return compteRenduService.SupprimerCompteRendu(id);
    }
}
