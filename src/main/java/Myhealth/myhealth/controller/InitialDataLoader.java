package Myhealth.myhealth.controller;

import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Role;
import Myhealth.myhealth.repository.RoleRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;


@Component


public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        createRoleIfNotFound(ERole.PATIENT);
        createRoleIfNotFound(ERole.ADMIN);
        createRoleIfNotFound(ERole.MEDECIN);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(ERole name) {

        Optional<Role> optionalRole = Optional.ofNullable(roleRepository.findByName(name));
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        } else {
            Role role = new Role(name);
            roleRepository.save(role);
            return role;
        }
    }
}
