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

import it.uniroma3.siw.tour.model.Escursione;
import it.uniroma3.siw.tour.model.Guida;
import it.uniroma3.siw.tour.model.Citta;
import it.uniroma3.siw.tour.service.EscursioneService;
import it.uniroma3.siw.tour.service.GuidaService;
import it.uniroma3.siw.tour.service.CittaService;

@Controller
public class EscursioneController {
	
	@Autowired
	private EscursioneService escursioneService;
	
	@Autowired
	private GuidaService guidaService;
	
	@Autowired
	private CittaService cittaService;

	@GetMapping("/allEscursioni")
	private String allEscursioni(Model model) {
		model.addAttribute("escursioni", this.escursioneService.getAllEscursioni());
		return "allEscursioni";
	}
	
	@GetMapping("/escursione/{id}")
	private String escursione(@PathVariable("id") Long id, Model model) {
		Escursione escursione = escursioneService.escursioneById(id);
		List<Citta> listaCitta = escursione.getCitta();
		
		model.addAttribute("escursione", escursione);
		model.addAttribute("listaCitta", listaCitta);
		
		return "escursione";
	}
	
	@GetMapping("/admin/escursioneForm")
	private String getEscursioneForm(Model model) {
		model.addAttribute("escursione", new Escursione());
		model.addAttribute("listaGuide", guidaService.getAllGuide());
        model.addAttribute("listaCitta", cittaService.getAllCitta());
        
        return "admin/escursioneForm";
	}
	
	@GetMapping("/admin/editEscursioneForm/{id}")
	private String editEscursione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("escursione", escursioneService.escursioneById(id));
		model.addAttribute("listaGuide", guidaService.getAllGuide());
		
		List<Citta> cittaAttuali = escursioneService.escursioneById(id).getCitta();
		
		model.addAttribute("citta1Attuale", cittaAttuali.get(0));
		model.addAttribute("citta2Attuale", cittaAttuali.get(1));
		model.addAttribute("citta3Attuale", cittaAttuali.get(2));
		model.addAttribute("citta4Attuale", cittaAttuali.get(3));
		
		model.addAttribute("listaCitta", cittaService.getAllCitta());
		
		return "admin/editEscursioneForm";
	}
	
	@GetMapping("/admin/deleteEscursione/{id}")
	public String deleteEscursione(@PathVariable("id") Long id) {
		escursioneService.deleteEscursioneById(id);
		return "redirect:/allEscursioni";
	}
	
	@PostMapping("/admin/escursioneForm")
	private String postEscursioneForm(@Valid @ModelAttribute("escursione") Escursione escursione, @RequestParam("citta1") Citta citta1,
			@RequestParam("citta2") Citta citta2, @RequestParam("citta3") Citta citta3, @RequestParam("citta4") Citta citta4,
			@RequestParam("guidaSelezionata") Guida guida, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			escursione.setGuida(guida);
			escursione.getCitta().add(citta1);
			escursione.getCitta().add(citta2);
			escursione.getCitta().add(citta3);
			escursione.getCitta().add(citta4);
			
			escursioneService.saveEscursione(escursione);
			return "redirect:/allEscursioni";
		} else {
			return "redirect:/admin/escursioneForm";
		}
	}
	
	@PostMapping("/admin/editEscursioneForm/{id}")
	private String editEscursionePost(@Valid @ModelAttribute("escursione") Escursione escursione, @PathVariable("id") Long id, @RequestParam("citta1") Citta citta1,
			@RequestParam("citta2") Citta citta2, @RequestParam("citta3") Citta citta3, @RequestParam("citta4") Citta citta4,
			@RequestParam("guidaSelezionata") Guida guida, BindingResult bindingResult) {
		
		
		escursione.setGuida(guida);
		escursione.getCitta().clear();
		escursione.getCitta().add(citta1);
		escursione.getCitta().add(citta2);
		escursione.getCitta().add(citta3);
		escursione.getCitta().add(citta4);
			
		escursioneService.editEscursione(escursione);
		
		return "redirect:/allEscursioni";
		
	}
	
}
