package it.uniroma3.siw.tour.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Regione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Inserire un nome")
	private String nome;

	private Boolean mare;
	
	@OneToMany(mappedBy="regione", cascade=CascadeType.REMOVE)
	private List<Citta> citta = new LinkedList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getMare() {
		return mare;
	}

	public void setMare(Boolean mare) {
		this.mare = mare;
	}

	public List<Citta> getCitta() {
		return citta;
	}

	public void setCitta(List<Citta> citta) {
		this.citta = citta;
	}

}
