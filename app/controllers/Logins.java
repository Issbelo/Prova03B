package controllers;

import models.Operador;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void logar(String username, String senha) {
		Operador operadorLogado = Operador.obterUsuario(username, senha);
		if (operadorLogado == null) {
			flash.error("Usuário ou senha inválidos, tente novamente!");
			form();
		}
		
		session.put("logado", username);
		session.put("veterinario", operadorLogado.veterinario);
		flash.success("Usuário logado com sucesso!");
		Animais.listar(null, null);
	}
	
	public static void sair() {
		session.clear();
		form();
	}

}
