package it.uniroma3.siw.tour.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.tour.model.Escursione;
import it.uniroma3.siw.tour.model.Guida;
import it.uniroma3.siw.tour.service.GuidaService;

@Controller
public class GuidaController {
	
	@Autowired
	private GuidaService guidaService;

	@GetMapping("/allGuide")
	private String allGuide(Model model) {
		model.addAttribute("guide", this.guidaService.getAllGuide());
		return "allGuide";
	}
	
	@GetMapping("/guida/{id}")
	private String guida(@PathVariable("id") Long id, Model model) {
		Guida guida = guidaService.guidaById(id);
		List<Escursione> buffets = guida.getEscursioni();
				
		model.addAttribute("guida", guida);
		model.addAttribute("buffets", buffets);
		
		return "guida";
	}
	
	@GetMapping("/admin/guidaForm")
	private String getGuidaForm(Model model) {
		model.addAttribute("guida", new Guida());
        
        return "admin/guidaForm";
	}
	
	@GetMapping("/admin/deleteGuida/{id}")
	public String deleteGuida(@PathVariable("id") Long id) {
		guidaService.deleteGuidaById(id);
		return "redirect:/allGuide";
	}
	
	@PostMapping("/admin/guidaForm")
	private String postGuidaForm(@Valid @ModelAttribute("guida") Guida guida, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			guidaService.saveGuida(guida);
			return "redirect:/allGuide";
		} else {
			return "admin/guidaForm";
		}
	}
	
}
