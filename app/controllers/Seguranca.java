package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void autenticar() {
		if(!session.contains("logado")) {
			flash.error("Erro! Logue para acessar.");
			Logins.form();
		}
	}

}
