package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(ERole name);
}
