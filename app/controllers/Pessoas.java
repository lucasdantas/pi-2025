package controllers;

import java.util.List;

import models.Pessoa;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void listar(String termo) {
		List<Pessoa> pessoas = null;
		if (termo == null) {
			pessoas = Pessoa.findAll();	
		} else {
			pessoas = Pessoa.find("lower(nome) like ?1 "
					+ "or lower(email) like ?1",
					"%" + termo.toLowerCase() + "%").fetch();
		}
		render(pessoas, termo);
	}

	public static void detalhar(Pessoa pessoa) {
		render(pessoa);
	}
	
	public static void editar(Long id) {
		Pessoa p = Pessoa.findById(id);
		renderTemplate("Pessoas/form.html", p);
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
		listar(null);
	}	
	
}
