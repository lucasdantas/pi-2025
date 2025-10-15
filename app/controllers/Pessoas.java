package controllers;

import java.util.List;

import models.Departamento;
import models.Pessoa;
import models.Status;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
import security.Administrador;

@With(Seguranca.class)
public class Pessoas extends Controller {
	
	@Administrador
	public static void form() {
		List<Departamento> departamentos = Departamento.findAll();
		render(departamentos);
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
		List<Departamento> departamentos = Departamento.findAll();
		
		renderTemplate("Pessoas/form.html", p, departamentos);
	}
	
	@Administrador
	public static void salvar(@Valid Pessoa pessoa) {

		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		
		pessoa.nome = pessoa.nome.toUpperCase();		
		pessoa.email = pessoa.email.toLowerCase();
			
		flash.success(pessoa.nome + " foi cadastrada com sucesso.");
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
