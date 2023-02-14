package hopital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Visite;

public interface VisiteRepository extends JpaRepository<Visite, Integer> {
	
	@Query("select v from Visite v left join fetch v.medecin m where m=:med")
	Optional<Visite> findByMedecin(@Param("med") Medecin medecin);

	@Query("select v from Visite v left join fetch v.patient p where p=:pati")
	Optional<Visite> findByPatient(@Param("pati") Patient patient);
}
