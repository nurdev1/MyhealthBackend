package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.Hopital;
import Myhealth.myhealth.repository.HopitalRepository;
import Myhealth.myhealth.services.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class HopitalServiceImplementation implements HopitalService {

   @Autowired
    public HopitalRepository hopitalRepository;

    @Override
    public ReponseMessage creerHopital(Hopital hopital) {
        if (hopitalRepository.findByIdhopital(hopital.getIdhopital()) == null){
            hopitalRepository.save(hopital);
            ReponseMessage message = new ReponseMessage("Hopitalajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet Hopital existe déjà ", false);

            return message;
        }

    }

    @Override
    public ReponseMessage modifierHopital(Hopital hopital) {
        if (hopitalRepository.findByIdhopital(hopital.getIdhopital()) !=null) {
            return hopitalRepository.findById(hopital.getIdhopital())
                    .map(hopital1->{
                        hopital1.setAdresse(hopital.getAdresse());
                        hopital1.setNom(hopital.getNom());
                        hopital1.setFileName(hopital.getFileName());
                        hopital1.setVille(hopital.getVille());
                        hopitalRepository.save(hopital);
                        ReponseMessage message = new ReponseMessage("Hopital modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, Hopital non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, Hopital non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Hopital> afficherToutLesHopital() {
        return hopitalRepository.findAll();
    }


    @Override
    public ReponseMessage SupprimerHopital(Long id) {
        final  Hopital hopital = null;
        if (hopitalRepository.findByIdhopital(id) != null) {
            hopital.setEtat(false);
            ReponseMessage message = new ReponseMessage(" Hôpital supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage(" Hôpital non trouvée", false);
            return message;
        }
    }

    @Override
    public int NombreHopital() {
        return 0;
    }

    @Override
    public List<Object> NombreHopitalParVille() {
        return null;
    }

    @Override
    public List<Hopital> NouveauHopital() {
        return hopitalRepository.NouveauHopital();
    }

    @Override
    public Hopital storeFile(Hopital hopital, MultipartFile file) {
        // Normalize the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            // Create a new Dossier object with the file name, content type, and data
            hopital.setFileName(fileName);
            hopital.setFileType(file.getContentType());
            hopital.setData(file.getBytes());
            //  imagerie.setDate(LocalDateTime.now());
            hopital.setEtat(true);

            // Save the Dossier object to the database
            return hopitalRepository.save(hopital);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }

    private Page<Hopital> getHopitalsList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return hopitalRepository.findAll(pageable);
    }

    private Page<Hopital> getHopitalsOrderByMedecin(int page, int limit,Sort.Direction sortType) {
        Sort sort = Sort.by(sortType, "price");
        Pageable pageable = PageRequest.of(page, limit,sort);
        return hopitalRepository.findAll(pageable);
    }


}
