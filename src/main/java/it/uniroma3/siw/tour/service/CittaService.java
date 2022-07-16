package it.uniroma3.siw.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.tour.model.Citta;
import it.uniroma3.siw.tour.repository.CittaRepository;

@Service
@Transactional
public class CittaService {
	
	@Autowired
	private CittaRepository cittaRepository;
	
	public Citta createCitta(Citta citta) {
		return cittaRepository.save(citta);
	}
	
	public void deleteCittaById(Long id) {
		cittaRepository.deleteById(id);
	}
	
	public Citta cittaById(Long id) {
		Optional<Citta> optional = cittaRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Citta> getAllCitta(){
		return(List<Citta>) cittaRepository.findAll();
	}
}
