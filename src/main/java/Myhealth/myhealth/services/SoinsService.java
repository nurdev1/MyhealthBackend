package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Soins;

import java.util.List;

public interface SoinsService {

    // Création d'un Systhese de Soins
    ReponseMessage creerSyntheseSoins(Soins syntheseDeSoins);

    // Mise à jour d'un Systhese de Soins
    ReponseMessage modifierSyntheseSoins(Soins syntheseSoins);

    //affichage d'un Synthese de Soins

    List<Soins> afficherToutLesSystheseSoins();

    //Suppression d'un Systhese de Soins
    ReponseMessage SupprimerSyntheseDeSoins(Long idsynthesesoins);
}
