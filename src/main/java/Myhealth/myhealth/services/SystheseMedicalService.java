package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Dossier;
import Myhealth.myhealth.modeles.SyntheseMedical;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SystheseMedicalService {

    // Création d'un Systhese Medical
    ReponseMessage AjouterSystheseMedical(SyntheseMedical systheseMedical);

    // Mise à jour d'un Systhese Medical
    ReponseMessage modifierSystheseMedical(SyntheseMedical syntheseMedical);

    //affichage d'un Systhese Medical

    List<SyntheseMedical> afficherToutLesSystheseMedical();

    //Suppression d'un SystheseMedical
    ReponseMessage SupprimerSystheseMedical(Long idsysthesemedical);
    public SyntheseMedical storeFile(SyntheseMedical systheseMedical,MultipartFile file);
}


