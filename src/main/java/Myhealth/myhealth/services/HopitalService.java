package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Hopital;
import org.springframework.stereotype.Service;

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
}
