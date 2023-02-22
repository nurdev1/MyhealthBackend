package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Hopital;
import Myhealth.myhealth.repository.HopitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Service
public class HopitalServiceImplementation implements HopitalService{

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
                        hopital1.setPhoto(hopital.getPhoto());
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
