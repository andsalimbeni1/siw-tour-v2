package it.uniroma3.siw.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.tour.model.Guida;
import it.uniroma3.siw.tour.repository.GuidaRepository;

@Service
@Transactional
public class GuidaService {
	
	@Autowired
	private GuidaRepository guidaRepository;
	
	public Guida saveGuida(Guida guida) {
		return guidaRepository.save(guida);
	}

	public void deleteGuidaById(Long id) {
		guidaRepository.deleteById(id);
	}
	
	public List<Guida> getAllGuide(){
		return(List<Guida>) guidaRepository.findAll();
	}
	
	public Guida guidaById(Long id) {
		Optional<Guida> optional = guidaRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public boolean alreadyExists(Guida guida) {
		List<Guida> list = this.guidaRepository.findByNomeAndCognome(guida.getNome(), guida.getCognome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
