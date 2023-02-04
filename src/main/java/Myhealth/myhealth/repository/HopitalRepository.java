package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Hopital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {

    Hopital findByIdhopital(Long idhopital);
    //Optional<Hopital> finbBynom(String nom);

    @Query(value = "SELECT COUNT(*) FROM hopital;",nativeQuery = true)
    int NombreHopital();

    @Query(value = "SELECT COUNT(*),ville FROM hopital GROUP BY ville;",nativeQuery = true)
    List<Object> NombreHopitalParVille();

    //Page<Hopital> findByNameContainingIgnoreCase(String productName, Pageable pageable);
}
