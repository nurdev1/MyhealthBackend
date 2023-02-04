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
    public ReponseMessage SupprimerHopital(Long idhopital) {
        if(hopitalRepository.findByIdhopital(idhopital) != null){
            hopitalRepository.deleteById(idhopital);
            ReponseMessage message = new ReponseMessage("hopital supprimé avec succes", true);
            return message;
        }else {
            ReponseMessage message = new ReponseMessage("hopital non trouvé", false);
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
    //image



    //filtre
    @Override
    public Page<Hopital> getRequestFilters(int page, int limit, String hopitalVille, Sort.Direction sortType) {
        Page<Hopital> hopitalPage = null;
        /*if(hopitalVille==null && sortType==null){
            hopitalPage = getHopitalsList(page,limit);
        }
        if(hopitalVille!=null && sortType==null ){
            hopitalPage = findHopitalByName(page,limit,hopitalVille);
        }
        if(hopitalVille==null && sortType != null ){
            hopitalPage = getHopitalsOrderByMedecin(page,limit,sortType);
        }
        if(hopitalVille!=null && sortType!=null){
            hopitalPage = findProductsByNameAndOrderByPrice(page,limit,hopitalVille,sortType);
        }*/
        return  hopitalPage;
    }

    private Page<Hopital> getHopitalsList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return hopitalRepository.findAll(pageable);
    }

    /*private Page<Hopital> findHopitalByName(int page,int limit,String HopitalName) {
        Pageable pageable = PageRequest.of(page, limit);
        //return hopitalRepository.findByNameContainingIgnoreCase(HopitalName, pageable);
        return new Page<Hopital>() {
        };
    }*/


    private Page<Hopital> getHopitalsOrderByMedecin(int page, int limit,Sort.Direction sortType) {
        Sort sort = Sort.by(sortType, "price");
        Pageable pageable = PageRequest.of(page, limit,sort);
        return hopitalRepository.findAll(pageable);
    }

    /*private Page<Hopital> findProductsByNameAndOrderByPrice(int page, int limit,
                                                            String productName,
                                                            Sort.Direction sortType) {
        Sort sort = Sort.by(sortType, "price");
        Pageable pageable = PageRequest.of(page, limit,sort);
        return hopitalRepository.findByNameContainingIgnoreCase(productName,pageable);
    }*/

}
