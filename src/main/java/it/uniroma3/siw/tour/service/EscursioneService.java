package it.uniroma3.siw.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.tour.model.Escursione;
import it.uniroma3.siw.tour.repository.EscursioneRepository;

@Service
@Transactional
public class EscursioneService {
	
	@Autowired
	private EscursioneRepository escursioneRepository;
	
	public Escursione saveEscursione(Escursione escursione) {
		return escursioneRepository.save(escursione);
	}
	
	public Escursione editEscursione(Escursione escursione) {
		return escursioneRepository.save(escursione);
	}
	
	public void deleteEscursioneById(Long id) {
		escursioneRepository.deleteById(id);
	}
	
	public List<Escursione> getAllEscursioni(){
		return(List<Escursione>) escursioneRepository.findAll();
	}
	
	public Escursione escursioneById(Long id) {
		Optional<Escursione> optional = escursioneRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Boolean alreadyExists(Escursione escursione) {
		List<Escursione> list = this.escursioneRepository.findByNome(escursione.getNome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
