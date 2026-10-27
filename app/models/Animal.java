package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Animal extends Model {
	
	public String nome;
	public Integer peso;
	public Date dataNascimento;
	public Boolean extincao;
	
	public Animal() {
		this.extincao = false;
	}
	
	@ManyToOne
	public TipoAnimal tipoAnimal;
	
	@Override
	public String toString() {
		return nome;
	}

}
