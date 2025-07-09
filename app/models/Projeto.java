package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Projeto extends Model {

	public String nome;
	@Temporal(TemporalType.DATE)
	public Date inicio;
	@Temporal(TemporalType.DATE)
	public Date fim;

	@ManyToMany
	public List<Pessoa> membros;

}