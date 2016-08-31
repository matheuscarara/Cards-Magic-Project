package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCartas {
	
	private static List<Carta> cartas = new ArrayList<Carta>();
	
	public static Carta getCarta(Integer id) throws ExcecaoCartaNaoExiste {
		if (id > cartas.size() || id < 0) {
			throw new ExcecaoCartaNaoExiste();
		}
		return cartas.get(id);
	}

	public void geraCartas() {
		// Cartas Vento
		criaCarta("Saci", 500, 400, Elemento.Vento);
		// Cartas Fogo
		criaCarta("Dragao", 800, 600, Elemento.Fogo);
		// Cartas Agua
		criaCarta("Boto Rosa", 500, 400, Elemento.Agua);
	}

	private void criaCarta(String nome, Integer ataque, Integer defesa, Elemento elemento) {
		Carta carta = new Carta(nome, ataque, defesa, elemento, cartas.size());
		cartas.add(carta);
	}
}
