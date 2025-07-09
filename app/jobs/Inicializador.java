package jobs;

import java.util.Date;

import models.Departamento;
import models.Pessoa;
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
		}
	}

}
