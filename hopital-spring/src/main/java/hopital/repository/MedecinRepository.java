package hopital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hopital.model.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Integer>{

}
