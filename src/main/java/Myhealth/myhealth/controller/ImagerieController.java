package Myhealth.myhealth.controller;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.Imagerie;
import Myhealth.myhealth.services.ImagerieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/imagerie")
@CrossOrigin(origins = "http://localhost:8100")
public class ImagerieController {

    private ImagerieService imagerieService;


    @PostMapping("/ajouter")
    ReponseMessage Ajouter(@RequestBody Imagerie imagerie){
        return  imagerieService.creerImagerie(imagerie);
    }

    @PostMapping("/FileUpload")
    public ResponseEntity<Imagerie> uploadFile(@RequestBody Imagerie imagerie,@RequestParam("file") MultipartFile file) {
         imagerieService.storeFile(imagerie,file);
        return new ResponseEntity<>(imagerie, HttpStatus.CREATED);
    }
    @GetMapping("/modifier")
    public ReponseMessage Modifier(@RequestBody Imagerie imagerie){
        return imagerieService.modifierImagerie(imagerie);
    }

    @GetMapping("/afficher")
    public List<Imagerie> Afficher(){
      return   imagerieService.afficherToutLesImagerie();
    }
    /*@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    @PostMapping("/uploadFile")
    public ResponseEntity<Dossier> uploadFile(@RequestParam("file") MultipartFile file) {
        Dossier dossier = dossierService.storeFile(file);
        return new ResponseEntity<>(dossier, HttpStatus.CREATED);
    }

}

@Override
public Dossier storeFile(MultipartFile file) {
    // Normalize the file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
        // Check if the file name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
        }

        // Create a new Dossier object with the file name, content type, and data
        Dossier dossier = new Dossier();
        dossier.setFileName(fileName);
        dossier.setFileType(file.getContentType());
        dossier.setData(file.getBytes());
        dossier.setDate(LocalDateTime.now());
        dossier.setEtat(true);

        // Save the Dossier object to the database
        return dossierRepository.save(dossier);
    } catch (IOException ex) {
        throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
    }
}

*/

    @DeleteMapping("/supprimer")
    public ReponseMessage Supprimer(@PathVariable Long id){
        return imagerieService.SupprimerImagerie(id);
    }
}
