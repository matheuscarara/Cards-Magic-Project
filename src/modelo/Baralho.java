package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.excecoes.ExcecaoBaralhoCheio;
import modelo.excecoes.ExcecaoBaralhoVazio;

public class Baralho {
	private static final Integer TAMANHOMAX = 30;
	private static final Integer TAMANHOMIN = 21;
	private List<Carta> baralho;

	public Baralho() {
		this.baralho = new ArrayList<Carta>();
	}

	public List<Carta> getBaralho() {
		return baralho;
	}

	public Integer tamanhoDoBaralho() {
		return baralho.size();
	}

	public boolean baralhoEstaCompleto() {
		if (baralho.size() >= TAMANHOMIN && baralho.size() <= TAMANHOMAX)
			return true;
		return false;
	}

	public void adicionaCarta(Carta carta) throws ExcecaoBaralhoCheio {
		if (baralho.size() == TAMANHOMAX)
			throw new ExcecaoBaralhoCheio();
		baralho.add(carta);
	}

	public void removeCarta(Carta carta) throws ExcecaoBaralhoVazio {
		if (baralho.size() == 0)
			throw new ExcecaoBaralhoVazio();
		baralho.remove(carta);
	}

	public Carta comprarCarta() throws ExcecaoBaralhoVazio {
		if (baralho.size() == 0)
			throw new ExcecaoBaralhoVazio();
		return baralho.remove(baralho.size() - 1);
	}

	public List<Carta> embaralha() {
		Collections.shuffle(baralho);
		return baralho;
	}

	public void setBaralho(List<Carta> novoBaralho) {
		baralho = novoBaralho;
	}
}
