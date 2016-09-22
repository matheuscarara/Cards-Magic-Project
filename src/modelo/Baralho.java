package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//classe
public class Baralho {
	private static final Integer TAMANHOMAX = 40;
	private List<Carta> baralho;
//construtor
	//baralho eh uma lista de cartas
	public Baralho() {
		this.baralho = new ArrayList<Carta>();
	}
//metodo que retorna o baralho
	public List<Carta> getBaralho() {
		return baralho;
	}
//metodo de adicionar carta no baralho
	public void adicionaCarta(Carta carta) throws ExcecaoBaralhoCheio {
		if (baralho.size() == TAMANHOMAX)
			throw new ExcecaoBaralhoCheio();
		baralho.add(carta);
	}
//metodo de remover carta do baralho
	public void removeCarta(Carta carta) throws ExcecaoBaralhoVazio {
		if (baralho.size() == 0)
			throw new ExcecaoBaralhoVazio();
		baralho.remove(carta);
	}
//metodo de comprar carta do topo do baralho
	//remove do topo do baralho
	//retorna carta removida
	public Carta comprarCarta() throws ExcecaoBaralhoVazio {
		if (baralho.size() < 0)
			throw new ExcecaoBaralhoVazio();
		return baralho.remove(baralho.size()-1);
	}
//metodo de embaralhar o baralho
	public List<Carta> embaralha() {
		Collections.shuffle(baralho);
		return baralho;
	}
}
