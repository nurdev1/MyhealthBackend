package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.SyntheseDeSoins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyntheseSoinsRepository extends JpaRepository<SyntheseDeSoins,Long> {

    SyntheseDeSoins findByIdsynthesesoins(Long idsynthesesoins);
}
