package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	@Required
	@MinSize(3)
	public String nome;
	
	@Required
	@Email
	public String email;
	
	@OneToOne
	public Usuario usuario;

	@ManyToOne
	public Departamento departamento;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date dataNascimento;
	
	@Transient
	public Integer idade;
	
	public Pessoa() {
		this.status = Status.ATIVO;
	}
	
	public int getIdade() {
		if (idade == null) {			
			LocalDate localDataNascimento = dataNascimento.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			
			LocalDate dataCorrente = LocalDate.now();
			Period period = Period.between(localDataNascimento, dataCorrente);
			idade = period.getYears();			
		}
		
        return idade;
	}

}
