package controllers;

import java.util.List;

import models.Animal;
import models.Dados;
import models.TipoAnimal;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Animais extends Controller {
	
	public static void form() {
		List<TipoAnimal> tipos = TipoAnimal.findAll();
		render(tipos);
	}
	
	public static void editar(Long id) {
		Animal a = Animal.findById(id);
		List<TipoAnimal> tipos = TipoAnimal.findAll();
		renderTemplate("Animais/form.html", a, tipos);
	}
	
	public static void listar(String nomeBusca, String mensagem) {
		List<Animal> animais = null;
		if (nomeBusca == null || nomeBusca.isEmpty()) {
			animais = Animal.findAll();
		} else {
			animais = Animal.find("lower(nome) = ?1", 
					nomeBusca.toLowerCase()).fetch();
		}
		Dados d = Cache.get(session.getId(), Dados.class);
		List<String> animaisCadastrados =d.animaisCadastrados;
		render(animais, nomeBusca, mensagem, animaisCadastrados);
	}

	public static void salvar(Animal animal) {
		if (animal.nome == null || animal.peso == null || animal.peso < 0) {
			String mensagem = "Verifique se nome e peso estão preenchidos ou se o peso é igual ou maior a zero";
			listar(null, mensagem);
		}
		
		Animal animalBanco = Animal.find("nome = ?1 and tipoAnimal.id = ?2", 
				animal.nome, animal.tipoAnimal.id).first();
		if (animalBanco != null && animal.id != animalBanco.id) {
			String mensagem = "Já existe animal cadastrado com esse nome e tipo";
			listar(null, mensagem);
		}

		animal.save();
		Dados d = Cache.get(session.getId(), Dados.class);
		d.animaisCadastrados.add(animal.nome);
		listar(null, "");
	}
	
	public static void deletar(Long id) {
		if ("false".equals(session.get("veterinario"))) {
			String mensagem = "Apenas veterinários podem excluir!";
			listar(null, mensagem);
		}
		Animal animal = Animal.findById(id);
		
		String mensagem = "";
		if (!animal.extincao) {
			animal.delete();			
		} else {
			mensagem = "Não é possível deletar animal em extinção";
		}
		listar(null, mensagem);
	}
}
