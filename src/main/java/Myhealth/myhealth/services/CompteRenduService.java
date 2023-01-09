package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.CompteRendu;

import java.util.List;

public interface CompteRenduService {

    // Création d'un Compte Rendu
    ReponseMessage creerCompteRendu(CompteRendu compteRendu);

    // Mise à jour d'un CompteRendu
    ReponseMessage modifierCompteRendu(CompteRendu compteRendu);

    //affichage d'un CompteRendu

    List<CompteRendu> afficherToutLesCompteRendu();

    //Suppression d'un CompteRendu
    ReponseMessage SupprimerCompteRendu(Long idcompterendu);
}
