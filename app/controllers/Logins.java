package controllers;

import models.Operador;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void logar(String username, String senha) {
		if (!Operador.operadorExiste(username, senha)) {
			System.out.println("pq diabos cai aq");
			flash.error("Usuário ou senha inválidos, tente novamente!");
			form();
		}
		
		session.put("logado", username);
		flash.success("Usuário logado com sucesso!");
		Animais.listar(null, null);
	}
	
	public static void sair() {
		session.clear();
		form();
	}

}
