package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

	private static final Integer TAMANHO = 5;
	private List<Carta> espaco;
	private Baralho baralho;

	public Mesa() {
		espaco = new ArrayList<Carta>();
	}

	public void sacaCarta() {

	}

	public void baixaCarta(Integer posicao, Carta carta) throws ExecaoMesaCheia {
		if (espaco.size() == TAMANHO)
			throw new ExecaoMesaCheia();

		this.espaco.add(carta);
	}

	public Carta comprarCarta() throws ExcecaoBaralhoVazio {
		return baralho.comprarCarta();
	}
	
}
