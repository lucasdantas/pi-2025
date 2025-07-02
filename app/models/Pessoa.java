package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	public String nome;
	public String email;

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
