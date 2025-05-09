package controllers;

import java.util.List;

import models.Pessoa;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void listar() {
		List<Pessoa> pessoas = Pessoa.findAll();
		render(pessoas);
	}

	public static void detalhar(Pessoa pessoa) {
		render(pessoa);
	}
	
	public static void salvar(Pessoa pessoa) {
		if (pessoa.nome != null) {
			pessoa.nome = pessoa.nome.toUpperCase();		
		}
		if (pessoa.email != null) {
			pessoa.email = pessoa.email.toLowerCase();
		}
		pessoa.save();
		detalhar(pessoa);
	}
	
	public static void remover(Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		pessoa.delete();
		listar();
	}	
	
}
