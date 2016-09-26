package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCartas {

	private static List<Carta> cartas = new ArrayList<Carta>();

	// metodo getter que retorna o id da carta.
	public static Carta getCarta(Integer id) throws ExcecaoCartaNaoExiste {
		if (id >= cartas.size() || id < 0) {
			throw new ExcecaoCartaNaoExiste();
		}
		return cartas.get(id);
	}

	// metodo gerador de cartas
	public static void geraCartas() {
		// Cartas Vento
		criaCarta("Saci", 500, 400, Elemento.Vento);
		criaCarta("Falcao", 500, 400, Elemento.Vento);
		criaCarta("Filhote Dragao Branco", 800, 600, Elemento.Vento);

		// Cartas Fogo
		criaCarta("Filhote Dragao Vermelho", 800, 600, Elemento.Fogo);
		criaCarta("General de Fogo", 1500, 1000, Elemento.Fogo);
		criaCarta("Guerreiro de Fogo", 1200, 600, Elemento.Fogo);

		// Cartas Agua
		criaCarta("Boto Rosa", 500, 400, Elemento.Agua);
		criaCarta("Filhote Dragao Azul", 800, 600, Elemento.Agua);
		criaCarta("General de Agua", 1500, 1000, Elemento.Agua);

	}

	// metodo que cria uma carta nova
	private static void criaCarta(String nome, Integer ataque, Integer defesa, Elemento elemento) {
		Carta carta = new Carta(nome, ataque, defesa, elemento, cartas.size());
		cartas.add(carta);
	}

	public static Integer getTamanhoRepositorio() {
		return cartas.size();
	}
}
