package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mao {
	private List<Carta> mao;
	private Baralho baralho;
	private static final Integer TAMANHOMAO = 6;

	public Mao() {
		this.mao = new ArrayList<Carta>();
		this.baralho = Jogador.getBaralho();
	}

	public void sacarCarta(Carta carta) throws ExcecaoMaoCheia {
		if (mao.size() == TAMANHOMAO)
			throw new ExcecaoMaoCheia();
		mao.add(carta);
	}

	// metodo que inicia a mao do jogador
	// pega as 6 cartas do topo do baralho e as move para uma lista mao do
	// jogador
	public Integer iniciaMao() throws ExcecaoBaralhoVazio {
		for (int i = 0; i < TAMANHOMAO; i++) {
			mao.add(baralho.comprarCarta());
		}
		return mao.size();
	}
	
	public List<Carta> getMao() throws ExcecaoMaoVazia {
		if (mao.size() == 0)
			throw new ExcecaoMaoVazia();
		return mao;
	}
	
	public Carta getCartaDaMao(Integer indice) {
		Carta carta = mao.get(indice);
		mao.remove(indice);
		return carta;
	}
	
	public String mostrarMao(Jogador jogador) throws ExcecaoMaoVazia {

		if (getMao().size() == 0)
			throw new ExcecaoMaoVazia();
		String mao = "";
		for (int i = 0; i < this.mao.size(); i++) {
			mao += " " + i;
			mao += " " + getMao().get(i).getNome();
			mao += " " + getMao().get(i).getAtaque();
			mao += " " + getMao().get(i).getDefesa();
			mao += " " + getMao().get(i).getElemento();
			mao += "\n";
		}
		return mao;
	}

}
