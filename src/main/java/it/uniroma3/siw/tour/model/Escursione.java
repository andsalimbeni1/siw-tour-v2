package it.uniroma3.siw.tour.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class Escursione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message="Inserire un nome")
	private String nome;
	
	@NotBlank(message="Inserire una descrizione")
	private String descrizione;
	
	@ManyToOne
	@JoinColumn(name="guida_id")
	private Guida guida;
	
	@ManyToMany
	@JoinTable(name="escursione_città", joinColumns= @JoinColumn(name="escursione_id", referencedColumnName= "id"))
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Guida getGuida() {
		return guida;
	}

	public void setGuida(Guida guida) {
		this.guida = guida;
	}

	public List<Citta> getCitta() {
		return citta;
	}

	public void setCitta(List<Citta> citta) {
		this.citta = citta;
	}
	
}
