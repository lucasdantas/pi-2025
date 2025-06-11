package controllers;

import java.util.List;

import models.Pessoa;
import models.Status;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void listar(String termo) {
		List<Pessoa> pessoas = null;
		if (termo == null) {
			pessoas = Pessoa.find("status <> ?1", Status.INATIVO).fetch();	
		} else {
			pessoas = Pessoa.find("(lower(nome) like ?1 "
					+ "or lower(email) like ?1) and status <> ?2",
					"%" + termo.toLowerCase() + "%",
					Status.INATIVO).fetch();
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
		pessoa.status = Status.INATIVO;
		pessoa.save();
		listar(null);
	}	
	
}
