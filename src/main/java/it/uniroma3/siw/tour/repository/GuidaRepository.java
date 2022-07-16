package it.uniroma3.siw.tour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.tour.model.Guida;

public interface GuidaRepository extends CrudRepository<Guida, Long>{
	
	public List<Guida> findByNomeAndCognome(String nome, String Cognome);
	
	public List<Guida> findByNazionalita(String nazionalita);

}
