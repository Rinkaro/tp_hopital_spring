package hopital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hopital.model.Secretaire;
import hopital.repository.SecretaireRepository;
import quest.exception.IdException;
import quest.exception.SecretaireException;
import quest.model.Adresse;
import quest.repository.FiliereRepository;

@Service
public class SecretaireService {

	@Autowired
	private SecretaireRepository secretaireRepo;

	public Secretaire create(Secretaire secretaire) {
		checkNotNull(secretaire);
		if (secretaire.getId() != null) {
			throw new IdException();
		}
		checkConstraint(secretaire);
		return secretaireRepo.save(secretaire);
	}

	private void checkConstraint(Secretaire secretaire) {
		if (secretaire.getLogin() == null || secretaire.getPassword().isEmpty()) {
			throw new SecretaireException("prenom obligatoire");
		}
		if (secretaire.getLogin() == null || secretaire.getLogin().isEmpty()) {
			throw new SecretaireException("nom obligatoire");
		}
	}

	private void checkNotNull(Secretaire secretaire) {
		if (secretaire == null) {
			throw new SecretaireException("secretaire obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Secretaire secretaire) {
		checkId(secretaire.getId());
		findById(secretaire.getId());
	}

	public Secretaire findById(Integer id) {
		checkId(id);
		return secretaireRepo.findById(id).orElseThrow(SecretaireException::new);
	}

	public Secretaire update(Secretaire secretaire) {
		checkNotNull(secretaire);
		checkExist(secretaire);
		checkConstraint(secretaire);
		Secretaire secretaireEnBase = findById(secretaire.getId());
		secretaireEnBase.setLogin(secretaire.getLogin());
		secretaireEnBase.setLogin(secretaire.getPassword());
		
		return secretaireRepo.save(secretaireEnBase);
	}

	public List<Secretaire> findAll() {
		return secretaireRepo.findAll();
	}

	public void delete(Secretaire secretaire) {
		checkExist(secretaire);
		secretaireRepo.delete(secretaire);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
}
