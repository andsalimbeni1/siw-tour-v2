package it.uniroma3.siw.tour.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.tour.model.Regione;
import it.uniroma3.siw.tour.service.RegioneService;

@Controller
public class RegioneController {
	
	@Autowired
	private RegioneService regioneService; 

	@GetMapping("/allRegioni")
	private String allRegioni(Model model) {
		model.addAttribute("regioni", this.regioneService.getAllRegioni());
		return "allRegioni";
	}
	
	@GetMapping("/regione/{id}")
	private String regione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("regione", this.regioneService.regioneById(id));
		return "regione";
	}
	
	@GetMapping("/admin/regioneForm")
	private String getattrazioneForm(Model model) {
		model.addAttribute("regione", new Regione());
        
        return "/admin/regioneForm";
	}
	
	@GetMapping("/admin/deleteRegione/{id}")
	public String deleteRegione(@PathVariable("id") Long id) {
		regioneService.deleteRegioneById(id);
		return "redirect:/allRegioni";
	}
	
	@PostMapping("/admin/regioneForm")
	private String postRegioneForm(@Valid @ModelAttribute("regione") Regione regione, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			regioneService.saveRegione(regione);
			return "redirect:/allRegioni";
		} else {
			return "admin/regioneForm";
		}
	}
	
}
