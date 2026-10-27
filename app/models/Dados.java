package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dados implements Serializable {
	
	public List<String> animaisCadastrados;
	
	public Dados() {
		animaisCadastrados = new ArrayList<String>();
	}

}
