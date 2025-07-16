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
		
		if (projeto.membros.contains(pessoa)) {
			flash.error("Essa pessoa já é membro do projeto!");
			formMembro(projetoId);
		}
		
		projeto.membros.add(pessoa);
		projeto.save();
		formMembro(projetoId);
	}
	
	public static void removerMembro(Long projetoId, Long pessoaId) {
		Pessoa pessoa = Pessoa.findById(pessoaId);
		Projeto projeto = Projeto.findById(projetoId);
		
		if (!projeto.membros.contains(pessoa)) {
			flash.error("Essa pessoa não é membro do projeto!");
			formMembro(projetoId);
		}
		
		projeto.membros.remove(pessoa);
		projeto.save();
		flash.success("Pessoa removida com sucesso!");
		formMembro(projetoId);
	}

}
