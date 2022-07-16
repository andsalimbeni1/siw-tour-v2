package it.uniroma3.siw.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.tour.model.Regione;
import it.uniroma3.siw.tour.repository.RegioneRepository;

@Service
@Transactional
public class RegioneService {
	
	@Autowired
	private RegioneRepository regioneRepository;
	
	public Regione saveRegione(Regione regione) {
		return regioneRepository.save(regione);
	}
	
	public void deleteRegioneById(Long id) {
		regioneRepository.deleteById(id);
	}
	
	public Regione regioneById(Long id) {
		Optional<Regione> optional = regioneRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public boolean alreadyExists(Regione regione) {
		List<Regione> list = this.regioneRepository.findByNome(regione.getNome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	} 
	
	public List<Regione> getAllRegioni(){
		return(List<Regione>) regioneRepository.findAll();
	}
}
