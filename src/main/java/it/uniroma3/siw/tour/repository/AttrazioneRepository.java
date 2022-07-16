package it.uniroma3.siw.tour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.tour.model.Attrazione;

public interface AttrazioneRepository extends CrudRepository<Attrazione, Long>{
	
	public List<Attrazione> findByNome(String nome);
	
}
