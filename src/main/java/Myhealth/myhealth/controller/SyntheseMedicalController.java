package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.SyntheseMedical;
import Myhealth.myhealth.services.SystheseMedicalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/synthesemedical")
@CrossOrigin(origins = "http://localhost:8100")
public class SyntheseMedicalController {

    private SystheseMedicalService systheseMedicalService;

    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody SyntheseMedical syntheseMedical){
        System.out.println(syntheseMedical);
        System.out.println("vgggggggggggggggg  hhhhhhhhhh");
        System.out.println("jhfvbhvbfjknjgknbnhghbvgvgggggggggggggggggggggggggggggggggggggg");
        System.out.println(syntheseMedical.getId());
        return   systheseMedicalService.AjouterSystheseMedical(syntheseMedical);
    }

    @PostMapping("/save")
    public ResponseEntity<SyntheseMedical> uploadFile(@RequestBody SyntheseMedical syntheseMedical,@RequestParam("file") MultipartFile file) {
        systheseMedicalService.storeFile(syntheseMedical, file);
        return new ResponseEntity<>(syntheseMedical, HttpStatus.CREATED);
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
