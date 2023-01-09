package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.SyntheseDeSoins;
import Myhealth.myhealth.repository.SyntheseSoinsRepository;

import java.util.List;

public interface SystheseSoinsService {

    // Création d'un Systhese de Soins
    ReponseMessage creerSyntheseSoins(SyntheseDeSoins syntheseDeSoins);

    // Mise à jour d'un Systhese de Soins
    ReponseMessage modifierSyntheseSoins(SyntheseDeSoins syntheseSoins);

    //affichage d'un Synthese de Soins

    List<SyntheseDeSoins> afficherToutLesSystheseSoins();

    //Suppression d'un Systhese de Soins
    ReponseMessage SupprimerSyntheseDeSoins(Long idsynthesesoins);
}
