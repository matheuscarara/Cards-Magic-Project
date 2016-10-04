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
		criaCarta("Saci", 500, 400, "Vento");
		criaCarta("Falcao", 500, 400, "Vento");
		criaCarta("Filhote Dragao Branco", 800, 600, "Vento");
		criaCarta("Abelha Gigante", 300, 400, "Vento");
		criaCarta("Grifo", 700, 500, "Vento");
		criaCarta("General de Vento", 1500, 1000, "Vento");
		criaCarta("Guerreiro de Vento", 800, 900, "Vento");
		criaCarta("Lobo Branco", 600, 400, "Vento");
		criaCarta("Pegasus", 500, 400, "Vento");
		criaCarta("Dragao Branco", 1200, 800, "Vento");
		// Cartas Fogo
		criaCarta("Filhote Dragao Vermelho", 800, 600, "Fogo");
		criaCarta("General de Fogo", 1500, 1000, "Fogo");
		criaCarta("Guerreiro de Fogo", 1200, 600, "Fogo");
		criaCarta("Lobo Vermelho", 700, 300, "Fogo");
		criaCarta("Dragao Vermelho", 1200, 800, "Fogo");
		criaCarta("Mestre do Fogo", 2000, 2000, "Fogo");
		criaCarta("Salamandra", 400, 400, "Fogo");
		criaCarta("BoiTaTa", 800, 800, "Fogo");
		criaCarta("Mula sem Cabeca", 900, 800, "Fogo");
		criaCarta("Golem de Lava", 1000, 700, "Fogo");
		// Cartas Agua
		criaCarta("Boto Rosa", 500, 400, "Agua");
		criaCarta("Filhote Dragao Azul", 800, 600, "Agua");
		criaCarta("General de Agua", 1500, 1000, "Agua");
		criaCarta("Guerreiro de Agua", 1000, 900, "Agua");
		criaCarta("Lobo Azul", 700, 400, "Agua");
		criaCarta("Dragao Azul", 1200, 800, "Agua");
		criaCarta("Mestre da Agua", 2000, 2000, "Agua");
		criaCarta("Kraken", 1000, 1000, "Agua");
		criaCarta("Sereia", 700, 600, "Agua");
		criaCarta("Afogador", 500, 400, "Agua");
		criaCarta("Megalodon", 800, 900, "Agua");
	}

	// metodo que cria uma carta nova
	private static void criaCarta(String nome, Integer ataque, Integer defesa,
			 String elemento) {
		Carta carta = new Carta(nome, ataque, defesa, cartas.size(), elemento);
		cartas.add(carta);
	}
}
