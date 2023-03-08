package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.services.AnalyseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/analyse")
@CrossOrigin(origins = "http://localhost:8100")
public class AnalyseController {
    private AnalyseService analyseService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Analyse analyse) {
        return analyseService.creerAnalyse(analyse);
    }

    @PostMapping("/save")
    public ResponseEntity<Analyse> uploadFile(@RequestBody Analyse analyse,@RequestParam("file") MultipartFile file) {
         analyseService.storeFile(analyse, file);
        return new ResponseEntity<>(analyse, HttpStatus.CREATED);
    }

    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Analyse analyse) {
        return analyseService.modifierAnalyse(analyse);
    }

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id) {
        return analyseService.SupprimerAnalyse(id);
    }

    @GetMapping("/afficher")
    public List<Analyse> Afficher() {
        return analyseService.afficherToutLesAnalyse();
    }
}
