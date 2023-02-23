package Myhealth.myhealth.services;

import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.modeles.Patient;
import Myhealth.myhealth.modeles.Utilisateus;
import Myhealth.myhealth.repository.MedecinRepository;
import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.repository.UtilisateusRepository;
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
public class MedecinDetailsServiceImpl implements UserDetailsService, Services {

    @Autowired
    MedecinRepository medecinRepository;

    @Autowired
    RoleRepository roleRepository;

    //recupere les details du collaborateur
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medecin user = medecinRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Médecin non trouvé: " + username));

        return MedecinDetailsImpl.build(user);
    }

    @Override
    public String modifierCollaborateur(Utilisateus collaborateurs) {
        System.out.println("salut");
        if (medecinRepository.findById(collaborateurs.getId()) != null) {
            System.out.println("salut");
            return medecinRepository.findById(collaborateurs.getId())
                    .map(c -> {
                        c.setPassword(collaborateurs.getPassword());
                        c.setEmail(collaborateurs.getEmail());
                        medecinRepository.save(c);
                        return  "Modifié Reçu avec succes";

                    }).orElseThrow(() -> new RuntimeException("Medecin non trouvée !"));
        }else {
            return "Modification échoué";
        }
    }

}


