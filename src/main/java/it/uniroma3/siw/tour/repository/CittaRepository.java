package it.uniroma3.siw.tour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.tour.model.Citta;

public interface CittaRepository extends CrudRepository<Citta, Long>{
	
	public List<Citta> findByNome(String nome);

}
