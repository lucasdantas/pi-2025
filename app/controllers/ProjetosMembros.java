package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Pessoa;
import models.Projeto;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class ProjetosMembros extends Controller {
	
	public static void form(Long id) {
		Projeto projeto = Projeto.findById(id);
		List<Pessoa> pessoas = Pessoa.findAll();
		render(projeto, pessoas);
	}
	
	public static void salvar(Long pessoaId, Long projetoId) {
		Pessoa pessoa = Pessoa.findById(pessoaId);
		Projeto projeto = Projeto.findById(projetoId);
		
		if (projeto.membros == null) {
			projeto.membros = new ArrayList<Pessoa>();
		}
		
		if (projeto.membros.contains(pessoa)) {
			flash.error("Essa pessoa já é membro do projeto!");
			form(projetoId);
		}
		
		projeto.membros.add(pessoa);
		projeto.save();
		form(projetoId);
	}
	
	public static void remover(Long projetoId, Long pessoaId) {
		Pessoa pessoa = Pessoa.findById(pessoaId);
		Projeto projeto = Projeto.findById(projetoId);
		
		if (!projeto.membros.contains(pessoa)) {
			flash.error("Essa pessoa não é membro do projeto!");
			form(projetoId);
		}
		
		projeto.membros.remove(pessoa);
		projeto.save();
		flash.success("Pessoa removida com sucesso!");
		form(projetoId);
	}

}
