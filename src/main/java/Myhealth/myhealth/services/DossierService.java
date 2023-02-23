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
    ReponseMessage SupprimerDossier(Long id);
    public List<Dossier> getDossiersForPatient(String codePatient);

    Dossier addDossier(Long patientId, Long medecinId, Dossier dossierDto);
    Dossier updateDossier(Long dossierId, Long medecinId, Dossier dossierDto);
    void deleteDossier(Long dossierId, Long medecinId);
}

