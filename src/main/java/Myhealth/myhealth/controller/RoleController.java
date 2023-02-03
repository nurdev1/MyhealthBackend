package Myhealth.myhealth.controller;


import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:8100")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN ROLE======================
    @PostMapping("/ajouter")
    public ReponseMessage create(@RequestBody Role role){
        return roleService.ajouterRole(role);
    }
    //================FIN DE LA METHODE PERMETTANT D'AJOUTER UN ROLE======================

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN ROLE======================
    @PutMapping("/modifier")
    public ReponseMessage update(@RequestBody Role role){
            return roleService.modifierRole(role);
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN ROLE======================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER LA LISTE DES ROLES======================
    @GetMapping("/afficher")
    public List<Role> read(){
        return roleService.afficherRole();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER LA LISTE DES ROLES========================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN ROLE======================
  /*  @DeleteMapping("/supprimer/{idrole}")
    public ReponseMessage delete(@PathVariable Long idrole){

        return roleService.supprimerRole(idrole);
    }*/
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN ROLE======================
}
