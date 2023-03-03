package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;

import java.util.List;

public interface AnalyseService {

    // Création d'un Medecin
    ReponseMessage creerAnalyse(Analyse analyse);

    // Mise à jour d'un Analyse
    ReponseMessage modifierAnalyse(Analyse analyse);

    //affichage d'un Analyse

    List<Analyse> afficherToutLesAnalyse();

    //Suppression d'un analyse
    ReponseMessage SupprimerAnalyse(Long idanalyse);
}
