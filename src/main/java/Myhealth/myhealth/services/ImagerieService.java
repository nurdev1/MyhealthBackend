package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Imagerie;

import java.util.List;

public interface ImagerieService {

    // Création d'un Imagerie
    ReponseMessage creerImagerie(Imagerie imagerie);

    // Mise à jour d'un Imagerie
    ReponseMessage modifierImagerie(Imagerie imagerie);

    //affichage d'un Imagerie

    List<Imagerie> afficherToutLesImagerie();

    //Suppression d'un Imagerie
    ReponseMessage SupprimerImagerie(Long idimagerie);
}
