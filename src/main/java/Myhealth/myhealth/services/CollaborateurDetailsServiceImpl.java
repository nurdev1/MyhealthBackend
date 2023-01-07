package Myhealth.myhealth.services;

import Myhealth.myhealth.modeles.Collaborateurs;
import Myhealth.myhealth.repository.CollaborateursRepository;
import Myhealth.myhealth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*

UserDetailsServiceest décrit comme une interface principale qui charge des données spécifiques à
l'utilisateur dans la documentation Spring.

 */

@Service
public class CollaborateurDetailsServiceImpl implements UserDetailsService, Services {

      @Autowired
      CollaborateursRepository collaborateursRepository;

      @Autowired
      RoleRepository roleRepository;

  //recupere les details du collaborateur
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Collaborateurs user = collaborateursRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("collaborateur non trouvé: " + username));

    return CollaborateurDetailsImpl.build(user);
  }

@Override
  public String modifierCollaborateur(Collaborateurs collaborateurs) {
      System.out.println("salut");
    if (collaborateursRepository.findById(collaborateurs.getId()) != null) {
        System.out.println("salut");
      return collaborateursRepository.findById(collaborateurs.getId())
              .map(c -> {
                c.setPassword(collaborateurs.getPassword());
                c.setEmail(collaborateurs.getEmail());
                collaborateursRepository.save(c);
                return  "Modifié Reçu avec succes";

              }).orElseThrow(() -> new RuntimeException("Collaborateurs non trouvée !"));
    }else {
      return "Modification échoué";
    }
  }


/*
    @Override
    public List<Role> afficherRoles() {
        return roleRepository.findAll();
    }

 */

    /*
    @Override
    public String modifierRole(Role role) {
        if (roleRepository.findById(role.getId()) !=null) {
            return roleRepository.findById(role.getId())
                    .map(r -> {
                        r.setName(role.getName());
                        roleRepository.save(r);
                        return  "roles modifié avec succes";

                    }).orElseThrow(() -> new RuntimeException("Activité non trouvée !"));
        }else {
            return "Modification échoué";
        }
    }


    @Override
    public String supprimerRole(Long id) {
        if(roleRepository.findById(id).get() != null){
            roleRepository.deleteById(id);

            return "Modification Reçu";
        }else {
            return "Modification échoué";
        }
    }


    @Override
    public String supprimerCollaborateur(Long id) {
        if(collaborateursRepository.findById(id).get() != null){
            collaborateursRepository.deleteById(id);

            return "Modification reçue";
        }else{
            return null;
        }

    }

     */



}
