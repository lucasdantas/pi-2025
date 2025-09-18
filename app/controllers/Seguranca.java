package controllers;

import models.Perfil;
import play.mvc.Before;
import play.mvc.Controller;
import security.Administrador;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Você deve logar no sistema.");
			Logins.form();
		}
	}
	
	@Before
 	static void verificarAdministrador() {
      	   String perfil = session.get("usuarioPerfil");
      	   Administrador adminAnnotation = getActionAnnotation(Administrador.class);
      	   if (adminAnnotation != null && 
      			   !Perfil.ADMINISTRADOR.name().equals(perfil)) {
              forbidden("Acesso restrito aos administradores do sistema");
      	    }
 	}

}
