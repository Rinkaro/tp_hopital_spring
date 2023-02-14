package hopital.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hopital.model.Medecin;

public class MedecinValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Medecin.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Medecin medecin = (Medecin) target;

//		if (formateur.getAdresse() != null
//				&& (!formateur.getAdresse().getCodePostal().isBlank() ^ !formateur.getAdresse().getVille().isBlank())) {
//			errors.rejectValue("adresse.codePostal", "codePostal.ville.required",
//					"Le code postal et la ville doivent être renseignés conjointement");
//		}

	}
}
