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

import hopital.controller.validator.MedecinValidator;
import hopital.model.Medecin;
import hopital.service.MedecinService;

@Controller
@RequestMapping("/medecin")
public class MedecinController {

	@Autowired
	private MedecinService medecinSrv;
	
	@GetMapping("")
	public String list(Model model) {
		List<Medecin> medecins = medecinSrv.findAll();

		model.addAttribute("medecins", medecins);

		return "medecin/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("medecin", new Medecin());
//		model.addAttribute("formateurs", formateurService.findAll());

		return "medecin/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		model.addAttribute("medecin", medecinSrv.findById(id));
//		model.addAttribute("formateurs", formateurService.findAll());

		return "medecin/form";
	}

	@PostMapping("")
	public String save(@ModelAttribute("medecin") @Valid Medecin medecin, BindingResult result) {
		new MedecinValidator().validate(medecin, result);
		
		if(result.hasErrors()) {
			return "medecin/form";
		}
		
		if (medecin.getId() == null) {
			medecinSrv.create(medecin);
		} else {
			medecinSrv.update(medecin);
		}
		return "redirect:medecin";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/medecin";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		medecinSrv.delete(id);

		return "redirect:medecin";
	}
	
	
	
	
}
