package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.ERole;
import Myhealth.myhealth.modeles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(ERole name);
 // Role findByIdrole(Long id);


/*  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles(name) VALUES('ROLE_ADMIN');",nativeQuery = true)
  void createAdmin();
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles(name) VALUES('ROLE_Medecin');",nativeQuery = true)
  void creatteMedecin();
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles(name) VALUES('ROLE_Patient');",nativeQuery = true)
  void createPatient();*/

}
