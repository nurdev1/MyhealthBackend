package Myhealth.myhealth.services;


import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Role;

import java.util.List;

public interface RoleService {


    //================METHODE PERMETTANT D'AJOUTER UN ROLE=========================
    ReponseMessage ajouterRole(Role role);


    //================METHODE PERMETTANT DE MODIFIER UN ROLE=========================
    ReponseMessage modifierRole(Role role);

    //================METHODE PERMETTANT D'AFFICHER LES ROLES=========================
    List<Role> afficherRole();

    //================METHODE PERMETTANT DE SUPPRIMER UN ROLE=========================
   /* ReponseMessage supprimerRole(Long idrole);


    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN ROLE=========================
    Role trouverRoleParId(Long idrole);*/
}
