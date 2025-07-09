package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Departamento extends Model {

	public String nome;
	public Integer ramal;
	
	public Departamento() {
		
	}

	public Departamento(String nome, Integer ramal) {
		this.nome = nome;
		this.ramal = ramal;
	}

	@Override
	public String toString() {
		return nome + " ("+ ramal + ")";
	}

}
