package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Utilisateus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UtilisateusRepository extends JpaRepository<Utilisateus, Long> {
  Optional<Utilisateus> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
