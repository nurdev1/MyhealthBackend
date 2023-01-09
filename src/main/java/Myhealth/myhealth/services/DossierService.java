package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;


import java.util.List;

public interface DossierService {

    // Création d'un Dossier
    ReponseMessage creerDossier(Dossier dossier);

    // Mise à jour d'un Dossier
    ReponseMessage modifierDossier(Dossier dossier);

    //affichage d'un Dossier

    List<Dossier> afficherToutLesDossier();

    //Suppression d'un Dossier
    ReponseMessage SupprimerDossier(Long iddossier);
}
