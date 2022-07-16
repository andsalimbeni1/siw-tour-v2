package it.uniroma3.siw.tour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.tour.model.Escursione;


public interface EscursioneRepository extends CrudRepository<Escursione, Long>{
	
	public List<Escursione> findByNome(String string);
	

}
