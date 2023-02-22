package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Soins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoinsRepository extends JpaRepository<Soins,Long> {

 //   Soins findByIdsynthesesoins(Long idsynthesesoins);
}
