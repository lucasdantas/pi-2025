package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

	public String login;
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	public Usuario() {
		this.perfil = Perfil.PESSOA;
	}
	
}
