package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		render();
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
	
}
