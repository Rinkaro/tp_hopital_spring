package hopital.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hopital.model.Secretaire;

public class SecretaireValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Secretaire.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Secretaire secretaire = (Secretaire) target;

//		if (formateur.getAdresse() != null
//				&& (!formateur.getAdresse().getCodePostal().isBlank() ^ !formateur.getAdresse().getVille().isBlank())) {
//			errors.rejectValue("adresse.codePostal", "codePostal.ville.required",
//					"Le code postal et la ville doivent être renseignés conjointement");
//		}

	}
}
