package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Utilisateus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateusRepository extends JpaRepository<Utilisateus, Long> {
  Optional<Utilisateus> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
