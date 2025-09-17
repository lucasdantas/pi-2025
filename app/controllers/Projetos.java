package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Pessoa;
import models.Projeto;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Projetos extends Controller {
	
	public static void listar() {
		List<Projeto> projetos = Projeto.findAll();
		render(projetos);
	}
}
