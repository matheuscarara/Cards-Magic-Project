package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCartas {
	
	private static List<Carta> repositorioCartas = new ArrayList<Carta>();

	private RepositorioCartas(String nome, int ataque, int defesa, Elemento elemento, int id){
		repositorioCartas.add(new Carta(nome, ataque, defesa, elemento, id));
	}
}
