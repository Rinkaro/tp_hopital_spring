package hopital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hopital.model.Secretaire;
import hopital.service.SecretaireService;

@Controller
@RequestMapping("/secretaire")
public class SecretaireController {

	@Autowired
	private SecretaireService secretaireService;
	
	
	@GetMapping("") // ETAPE 1 : RECEPTION DE LA REQUETE
	public String list(Model model) {
		// ETAPE 2 : RECUPERATION DES DONNEES
		List<Secretaire> secretaires = secretaireService.findAll();

		// ETAPE 3 : RENSEIGNER LE MODEL
		model.addAttribute("secretaires", secretaires);

		// ETAPE 4 : APPEL DE LA VIEW
		return "secretaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("secretaire", new Secretaire());

		return "secretaire/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		model.addAttribute("secretaire", secretaireService.findById(id));

		return "secretaire/form";
	}

	@PostMapping("")
	public String save(@ModelAttribute("secretaire") @Valid Secretaire secretaire, BindingResult result, @RequestParam(required = false) Integer idReferent) {
		//new SecretaireValidator().validate(secretaire, result);
		
		if(result.hasErrors()) {
			return "secretaire/form";
		}
		
		if (secretaire.getId() == null) {
			secretaireService.create(secretaire);
		} else {
			secretaireService.update(secretaire);
		}
		return "redirect:secretaire";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/secretaire";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		secretaireService.delete(id);

		return "redirect:secretaire";
	}
}
