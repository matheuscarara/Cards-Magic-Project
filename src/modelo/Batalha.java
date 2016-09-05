package modelo;

import java.util.Random;

public class Batalha {
	private Jogador jogador1, jogador2;

	public Batalha() throws ExcecaoBaralhoVazio {
		iniciaJogadores();
		definePrimeiroJogador();
		iniciaBatalha();
	}

	public void iniciaJogadores() throws ExcecaoBaralhoVazio {
		jogador1.inicializaPontosDeVida();
		jogador1.iniciaMaoJogador();
		jogador2.inicializaPontosDeVida();
		jogador2.iniciaMaoJogador();
	}

	public void causaDano(Jogador alvo, Integer dano) {
		alvo.danoRecebido(dano);
	}

	public Jogador definePrimeiroJogador() {
		Random r = new Random();
		if ((r.nextInt(2) + 1) == 1)
			return jogador1;
		else
			return jogador2;
	}
	
	public void iniciaBatalha(){
	}
}