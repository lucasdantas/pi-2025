package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Departamento extends Model {

	public String nome;
	public Integer ramal;
	
	@Override
	public String toString() {
		return nome + " ("+ ramal + ")";
	}

}
