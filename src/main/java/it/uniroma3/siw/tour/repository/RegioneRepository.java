package it.uniroma3.siw.tour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.tour.model.Regione;

public interface RegioneRepository extends CrudRepository<Regione, Long>{
	
	public List<Regione> findByNome(String nome);
	
}
