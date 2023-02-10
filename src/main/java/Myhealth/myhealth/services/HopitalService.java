package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Hopital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HopitalService {

    // Création d'un Hopital
    ReponseMessage creerHopital(Hopital hopital);

    // Mise à jour d'un Hopital
    ReponseMessage modifierHopital(Hopital hopital);

    //affichage d'un Hopital

    List<Hopital> afficherToutLesHopital();

    //Suppression d'un Hopital
    ReponseMessage SupprimerHopital(Long idhopital);

    int NombreHopital();
    List<Object> NombreHopitalParVille();
    List<Hopital> NouveauHopital();

    Page<Hopital> getRequestFilters(int page, int limit, String productName, Sort.Direction sortType);


}
