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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.tour.model.Attrazione;
import it.uniroma3.siw.tour.model.Citta;
import it.uniroma3.siw.tour.model.Regione;
import it.uniroma3.siw.tour.service.AttrazioneService;
import it.uniroma3.siw.tour.service.CittaService;
import it.uniroma3.siw.tour.service.RegioneService;

@Controller
public class CittaController {
	
	@Autowired
	private AttrazioneService attrazioneService; 
	
	@Autowired
	private CittaService cittaService;
	
	@Autowired
	private RegioneService regioneService;

	@GetMapping("/allCitta")
	private String allCitta(Model model) {
		model.addAttribute("cittas", this.cittaService.getAllCitta());
		return "allCitta";
	}
	
	@GetMapping("/citta/{id}")
	private String citta(@PathVariable("id") Long id, Model model) {
		Citta citta = cittaService.cittaById(id);
		List<Attrazione> attrazioni = citta.getAttrazioni();
				
		model.addAttribute("citta", citta);
		model.addAttribute("attrazioni", attrazioni);
		
		return "citta";
	}
	
	@GetMapping("/admin/cittaForm")
	private String getCittaForm(Model model) {
		model.addAttribute("citta", new Citta());
		model.addAttribute("listaRegioni", regioneService.getAllRegioni());
        model.addAttribute("attrazioni", attrazioneService.getAllAttrazioni());
        
        return "/admin/cittaForm";
	}
	
	@GetMapping("/admin/deleteCitta/{id}")
	public String deleteCitta(@PathVariable("id") Long id) {
		cittaService.deleteCittaById(id);
		return "redirect:/allCitta";
	}
	
	@PostMapping("/admin/cittaForm")
	private String postCittaForm(@Valid @ModelAttribute("citta") Citta citta, @RequestParam("attrazione1") Attrazione attrazione1,
			@RequestParam("attrazione2") Attrazione attrazione2, @RequestParam("attrazione3") Attrazione attrazione3,
			@RequestParam("attrazione4") Attrazione attrazione4, @RequestParam("regioneSelezionata") Regione regione, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			citta.setRegione(regione);
			citta.getAttrazioni().add(attrazione1);
			citta.getAttrazioni().add(attrazione2);
			citta.getAttrazioni().add(attrazione3);
			citta.getAttrazioni().add(attrazione4);
			
			cittaService.createCitta(citta);
			return "redirect:/allCitta";
		} else {
			return "admin/cittaForm";
		}
	}
	
}
