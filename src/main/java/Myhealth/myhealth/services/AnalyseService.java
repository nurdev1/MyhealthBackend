package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Analyse;

import java.util.List;

public interface AnalyseService {

    // Création d'un Medecin
    ReponseMessage creerAnalyse(Analyse analyse);

    // Mise à jour d'un Analyse
    ReponseMessage modifierImagerie(Analyse analyse);

    //affichage d'un Analyse

    List<Analyse> afficherToutLesAnalyse();

    //Suppression d'un Imagerie
    ReponseMessage SupprimerAnalyse(Long idanalyse);
}
