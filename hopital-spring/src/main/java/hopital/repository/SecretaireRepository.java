package hopital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Formateur;

public interface SecretaireRepository extends JpaRepository<Formateur, Integer> {

}
