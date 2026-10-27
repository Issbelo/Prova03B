package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Operador extends Model {
	
	public String nome;
	public String username;
	public String senha;
	public boolean veterinario;
	
	public static boolean operadorExiste(String username, String senha) {
		Operador operador = Operador.find("username = ?1 and senha = ?2", username, senha).first();
		
		if (operador == null) {
			return false;
		}
			return true;
	}
	
}
