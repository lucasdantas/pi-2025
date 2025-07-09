package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Pessoa;
import models.Projeto;
import play.mvc.Controller;

public class Projetos extends Controller {
	
	public static void listar() {
		List<Projeto> projetos = Projeto.findAll();
		render(projetos);
	}
	
	public static void formMembro(Long id) {
		Projeto projeto = Projeto.findById(id);
		List<Pessoa> pessoas = Pessoa.findAll();
		render(projeto, pessoas);
	}
	
	public static void salvarMembro(Long pessoaId, Long projetoId) {
		Pessoa pessoa = Pessoa.findById(pessoaId);
		Projeto projeto = Projeto.findById(projetoId);
		
		if (projeto.membros == null) {
			projeto.membros = new ArrayList<Pessoa>();
		}
		
		projeto.membros.add(pessoa);
		projeto.save();
		formMembro(projetoId);
	}

}
