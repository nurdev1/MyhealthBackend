package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {

    Hopital findByIdhopital(Long idhopital);
    //Optional<Hopital> finbBynom(String nom);

    @Query(value = "SELECT COUNT(*) FROM hopital;",nativeQuery = true)
    int NombreHopital();

    @Query(value = "SELECT COUNT(*),ville FROM hopital GROUP BY ville;",nativeQuery = true)
    List<Object> NombreHopitalParVille();
        @Query(value = "SELECT * FROM hopital ORDER BY date DESC LIMIT 3;\n",nativeQuery = true)
    List<Hopital> NouveauHopital();


    //Page<Hopital> findByNameContainingIgnoreCase(String productName, Pageable pageable);
}
