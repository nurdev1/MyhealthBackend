package Myhealth.myhealth.repository;

import Myhealth.myhealth.modeles.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
