package hopital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hopital.exception.IdException;
import hopital.exception.VisiteException;
import hopital.model.Visite;
import hopital.repository.VisiteRepository;

@Service
public class VisiteService {

	@Autowired
	private VisiteRepository visiteRepo;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private MedecinRepository medecinRepo;

	public Visite create(Visite visite) {
		checkNotNull(visite);
		if (visite.getId() != null) {
			throw new IdException();
		}
		checkConstraint(visite);
		return visiteRepo.save(visite);
	}

	private void checkConstraint(Visite visite) {
		if (visite.getMedecin() ==null) {
			throw new VisiteException("Medecin obligatoire");
		}
		if (visite.getPatient() == null ) {
			throw new VisiteException("Patient obligatoire");
		}
	}

	private void checkNotNull(Visite visite) {
		if (visite == null) {
			throw new VisiteException("visite obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Visite visite) {
		checkId(visite.getId());
		findById(visite.getId());
	}

	public Visite findById(Integer id) {
		checkId(id);
		return visiteRepo.findById(id).orElseThrow(VisiteException::new);
	}

	public Visite update(Visite visite) {
		checkNotNull(visite);
		checkExist(visite);
		checkConstraint(visite);
		Visite visiteEnBase = findById(visite.getId());
		visiteEnBase.setDateVisite(visite.getDateVisite());
		visiteEnBase.setMedecin(visite.getMedecin());
		visiteEnBase.setPatient(visite.getPatient());
		visiteEnBase.setSalle(visite.getSalle());
		visiteEnBase.setPrix(visite.getPrix());

		return visiteRepo.save(visiteEnBase);
	}

	public List<Visite> findAll() {
		return visiteRepo.findAll();
	}

	public void delete(Visite visite) {
		checkExist(visite);
		visiteRepo.delete(visite);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
}
