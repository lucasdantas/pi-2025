package controllers;

import models.Pessoa;
import models.Responsavel;
import models.Usuario;
import play.mvc.Controller;

public class Logins extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void logar(String login, String senha) {
		Usuario usuario = Usuario.find("login = ?1 and senha = ?2",
              	login, senha).first();
     	if (usuario == null) {
          	flash.error("Login ou senha inválidos");
          	form(); //Redireciona para form de login
     	} else {
          	session.put("usuarioLogado", usuario.login);
          	session.put("usuarioPerfil", usuario.perfil.name());
          	flash.success("Logado com sucesso!");
          	Projetos.listar(); //Página inicial
     	}
 	}
	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}

}
