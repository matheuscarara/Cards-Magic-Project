package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
	private List<Carta> cartas;

	public Baralho(ArrayList<Carta> cartas) {
		this.cartas = cartas;
		cartas = new ArrayList<Carta>();
	}

	public Carta retiraDoTopo() {
		// cartas.size -1 ?
		return cartas.remove(cartas.size());
	}

	private void adicionaAoBaralho(Carta novaCarta) {
		cartas.add(novaCarta);
	}

	private void embaralha() {
		Collections.shuffle(cartas);
	}

}
