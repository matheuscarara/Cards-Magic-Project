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
		criaCarta("Abelha Gigante", 300, 400, Elemento.Vento);
		criaCarta("Grifo", 700, 500, Elemento.Vento);
		criaCarta("General de Vento", 1500, 1000, Elemento.Vento);
		criaCarta("Guerreiro de Vento", 800, 900, Elemento.Vento);
		criaCarta("Lobo Branco", 600, 400, Elemento.Vento);
		criaCarta("Pegasus", 500, 400, Elemento.Vento);
		criaCarta("Dragao Branco", 1200, 800, Elemento.Vento);
		// Cartas Fogo
		criaCarta("Filhote Dragao Vermelho", 800, 600, Elemento.Fogo);
		criaCarta("General de Fogo", 1500, 1000, Elemento.Fogo);
		criaCarta("Guerreiro de Fogo", 1200, 600, Elemento.Fogo);
		criaCarta("Lobo Vermelho", 700, 300, Elemento.Fogo);
		criaCarta("Dragao Vermelho", 1200, 800, Elemento.Fogo);
		criaCarta("Mestre do Fogo", 2000, 2000, Elemento.Fogo);
		criaCarta("Salamandra", 400, 400, Elemento.Fogo);
		criaCarta("BoiTaTa", 800, 800, Elemento.Fogo);
		criaCarta("Mula sem Cabeca", 900, 800, Elemento.Fogo);
		criaCarta("Golem de Lava", 1000, 700, Elemento.Fogo);
		// Cartas Agua
		criaCarta("Boto Rosa", 500, 400, Elemento.Agua);
		criaCarta("Filhote Dragao Azul", 800, 600, Elemento.Agua);
		criaCarta("General de Agua", 1500, 1000, Elemento.Agua);
		criaCarta("Guerreiro de Agua", 1000, 900, Elemento.Agua);
		criaCarta("Lobo Azul", 700, 400, Elemento.Agua);
		criaCarta("Dragao Azul", 1200, 800, Elemento.Agua);
		criaCarta("Mestre da Agua", 2000, 2000, Elemento.Agua);
		criaCarta("Kraken", 1000, 1000, Elemento.Agua);
		criaCarta("Sereia", 700, 600, Elemento.Agua);
		criaCarta("Afogador", 500, 400, Elemento.Agua);
		criaCarta("Megalodon", 800, 900, Elemento.Agua);
	}

	// metodo que cria uma carta nova
	private static void criaCarta(String nome, Integer ataque, Integer defesa,
			Elemento elemento) {
		Carta carta = new Carta(nome, ataque, defesa, elemento, cartas.size());
		cartas.add(carta);
	}
}
