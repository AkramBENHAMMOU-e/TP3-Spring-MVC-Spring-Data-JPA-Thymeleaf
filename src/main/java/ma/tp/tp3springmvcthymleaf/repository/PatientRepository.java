package ma.tp.tp3springmvcthymleaf.repository;

import ma.tp.tp3springmvcthymleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
