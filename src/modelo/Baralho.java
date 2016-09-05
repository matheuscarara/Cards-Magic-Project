package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
	private static final Integer TAMANHOMAX = 40;
	private List<Carta> cartas;

	public Baralho() {
		this.cartas = new ArrayList<Carta>();
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void adicionaCarta(Carta carta) throws ExcecaoBaralhoCheio {
		if (cartas.size() == TAMANHOMAX)
			throw new ExcecaoBaralhoCheio();
		cartas.add(carta);
	}

	public void removeCarta(Carta carta) throws ExcecaoBaralhoVazio {
		if (cartas.size() == 0)
			throw new ExcecaoBaralhoVazio();
		cartas.remove(carta);
	}

	public Carta comprarCarta() throws ExcecaoBaralhoVazio {
		if (cartas.size() == 0)
			throw new ExcecaoBaralhoVazio();
		return cartas.remove(cartas.size());
	}

	public List<Carta> embaralha() {
		Collections.shuffle(cartas);
		return cartas;
	}
}
