package it.uniroma3.siw.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.tour.model.Attrazione;
import it.uniroma3.siw.tour.repository.AttrazioneRepository;

@Service
@Transactional
public class AttrazioneService {
	
	@Autowired
	private AttrazioneRepository attrazioneRepository;
	
	public Attrazione saveAttrazione(Attrazione attrazione) {
		return attrazioneRepository.save(attrazione);
	}
	
	public void deleteAttrazioneById(Long id) {
		attrazioneRepository.deleteById(id);
	}
	
	public Attrazione attrazioneById(Long id) {
		Optional<Attrazione> optional = attrazioneRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public boolean alreadyExists(Attrazione attrazione) {
		List<Attrazione> list = this.attrazioneRepository.findByNome(attrazione.getNome());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	} 
	
	public List<Attrazione> getAllAttrazioni(){
		return(List<Attrazione>) attrazioneRepository.findAll();
	}
}
