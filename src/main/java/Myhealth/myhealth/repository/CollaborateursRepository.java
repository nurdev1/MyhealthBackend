package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Collaborateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaborateursRepository extends JpaRepository<Collaborateurs, Long> {
  Optional<Collaborateurs> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
