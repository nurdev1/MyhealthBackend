package Myhealth.myhealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
* @RequestMapping: pour mapper les requêtes HTTP aux méthodes de gestion des contrôleurs MVC et REST
*
* L'annotation @RequestMapping peut être appliquée au
* niveau de la classe et/ou au niveau de la méthode dans un contrôleur.
*
* L'annotation @RequestMapping au niveau de la classe mappe un chemin ou
* un modèle de demande spécifique sur un contrôleur.
*
* L'annotation @RequestMapping au
* niveau de la méthode permet de rendre les mappages plus spécifiques aux méthodes de gestionnaire
 * */
@Controller
@RequestMapping("/")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public String swaggerUi() {

        return "redirect:/swagger-ui.html";

    }
}
