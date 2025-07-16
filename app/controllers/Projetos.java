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
}
