package jobs;

import java.util.Date;

import models.Departamento;
import models.Pessoa;
import models.Projeto;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Departamento.count() == 0) {
			Departamento rh = new Departamento("RH", 100);
			rh.save();
			
			Departamento ti = new Departamento("TI", 101);
			ti.save();
			
			Departamento seac = new Departamento("SEAC", 102);
			seac.save();
			
			Pessoa joao = new Pessoa();
			joao.nome = "João da Silva";
			joao.email = "joaossilva@gmail.com";
			joao.dataNascimento = new Date();
			joao.departamento = ti;
			joao.save();
			
			Pessoa maria = new Pessoa();
			maria.nome = "Teixeirinha";
			maria.email = "teixeirinha@gmail.com";
			maria.dataNascimento = new Date();
			maria.departamento = seac;
			maria.save();
			
			Projeto p1 = new Projeto();
			p1.nome = "Suap";
			p1.inicio = new Date();
			p1.fim = new Date();
			p1.save();
		}
	}

}
