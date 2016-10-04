package modelo;

import java.util.ArrayList;
import java.util.List;

public class Duelista {

	private Integer pontosDeVida;
	private static final Integer VIDAMAX = 1000;
	private List<Carta> mao;
	private Baralho baralhoDoJogo;
	private Usuario jogador;
	private static final Integer TAMANHOMAXMAO = 6;

	// classe duelista
	public Duelista(Usuario jogador) {
		this.mao = new ArrayList<Carta>();
		this.baralhoDoJogo = new Baralho();
		this.jogador = jogador;
		baralhoDoJogo.setBaralho(jogador.getBaralho().embaralha());
		pontosDeVida = VIDAMAX;
	}

	public void comprarCarta() throws ExcecaoMaoCheia, ExcecaoBaralhoVazio {
		if (mao.size() == TAMANHOMAXMAO)
			throw new ExcecaoMaoCheia();
		mao.add(baralhoDoJogo.comprarCarta());
	}

	public boolean verificaDuelista(Usuario jogador) {
		if (this.jogador == jogador)
			return true;
		return false;
	}

	public void receberDano(Integer dano) {
		pontosDeVida -= dano;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}

	public void causarDano(Duelista alvo, Integer dano) {
		alvo.receberDano(dano);
	}

	// metodo que inicia a mao do jogador
	// pega as 6 cartas do topo do baralho e as move para uma lista mao do
	// jogador
	public void iniciaMao() {
		for (int i = 0; i < TAMANHOMAXMAO; i++) {
			try {
				mao.add(baralhoDoJogo.comprarCarta());
			} catch (ExcecaoBaralhoVazio e) {
			}
		}
	}

	public Integer getTamanhoDaMao(){
		return mao.size();
	}
	
	public List<Carta> getMao() throws ExcecaoMaoVazia {
		if (mao.size() == 0)
			throw new ExcecaoMaoVazia();
		return mao;
	}

	public Carta getCartaDaMao(Integer indice) {
		Carta carta = mao.get(indice);
		mao.remove(mao.get(indice));
		return carta;
	}

	public String mostraMao() {
		String mao = "";
		for (int i = 0; i < this.mao.size(); i++) {
			try {
				mao += " " + i;
				mao += " " + getMao().get(i).getNome();
				mao += " " + getMao().get(i).getAtaque();
				mao += " " + getMao().get(i).getDefesa();
				mao += " " + getMao().get(i).getElemento();
				mao += "\n";
			} catch (ExcecaoMaoVazia e) {
				return mao;
			}
		}
		return mao;
	}
	
	
	
}
