package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private Duelista duelista;
	private static final Integer TAMANHO = 5;
	private List<Carta> campoPrimeiro;
	private List<Carta> campoSegundo;
	private Boolean mandoDeCampo;

	public Tabuleiro(Duelista duelista) {
		this.duelista = duelista;
		this.campoPrimeiro = new ArrayList<Carta>();
		this.campoSegundo = new ArrayList<Carta>();
		duelista.iniciaMao();
	}

	public void colocaEmCampo(Integer indice) throws ExcecaoCampoCheio {
		if (mandoDeCampo == true) {
			if (campoPrimeiro.size() - 1 == TAMANHO)
				throw new ExcecaoCampoCheio();
			campoPrimeiro.add(duelista.getCartaDaMao(indice));
		} else {

			if (campoSegundo.size() - 1 == TAMANHO)
				throw new ExcecaoCampoCheio();
			campoSegundo.add(duelista.getCartaDaMao(indice));
		}
	}

	public List<Carta> getCampo() throws ExcecaoCampoVazio {
		if (mandoDeCampo == true) {
			if (campoPrimeiro.size() == 0) {
				throw new ExcecaoCampoVazio();
			}
			return campoPrimeiro;
		} else {
			if (campoSegundo.size() == 0) {
				throw new ExcecaoCampoVazio();
			}
			return campoSegundo;

		}
	}

	public void retiraDoCampo(Integer posicao) throws ExcecaoCampoVazio {
		if (mandoDeCampo == true) {
			if (campoPrimeiro.size() == 0)
				throw new ExcecaoCampoVazio();
			campoPrimeiro.remove(campoPrimeiro.get(posicao));
		} else {
			if (campoSegundo.size() == 0)
				throw new ExcecaoCampoVazio();
			campoSegundo.remove(campoSegundo.get(posicao));
		}
	}

	private Moeda jogaMoeda() {
		Double random = Math.random();
		if (random > 0.5)
			return Moeda.Cara;
		return Moeda.Coroa;
	}

	public Integer getTamanhoDoCampo() {
		if (mandoDeCampo == true)
			return campoPrimeiro.size();
		else
			return campoSegundo.size();
	}

	public Boolean decideLado(Moeda lado1) {
		Moeda lado2 = jogaMoeda();
		if (lado1 == lado2) {
			mandoDeCampo = true;
		} else
			mandoDeCampo = false;
		return mandoDeCampo;
	}
}