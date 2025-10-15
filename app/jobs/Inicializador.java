package jobs;

import java.util.Date;

import models.Departamento;
import models.Perfil;
import models.Pessoa;
import models.Projeto;
import models.Responsavel;
import models.Usuario;
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
			
			Usuario usuarioJoao = new Usuario();
			usuarioJoao.login = "joao";
			usuarioJoao.senha = "1111";
			usuarioJoao.perfil = Perfil.RESPONSAVEL;
			usuarioJoao.save();
			
			Responsavel joao = new Responsavel();
			joao.usuario = usuarioJoao;
			joao.save();
			
			Usuario usuarioMaria = new Usuario();
			usuarioMaria.login = "teixeira";
			usuarioMaria.senha = "1111";
			usuarioMaria.save();
			
			Pessoa maria = new Pessoa();
			maria.nome = "Teixeirinha";
			maria.email = "teixeirinha@gmail.com";
			maria.dataNascimento = new Date();
			maria.departamento = seac;
			maria.usuario = usuarioMaria;
			maria.save();
			
			Projeto p1 = new Projeto();
			p1.nome = "Suap";
			p1.inicio = new Date();
			p1.fim = new Date();
			p1.save();
		}
	}

}
