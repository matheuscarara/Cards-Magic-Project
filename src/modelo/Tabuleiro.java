package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private static final Integer TAMANHO = 5;
	private static List<Carta> campo;

	public Tabuleiro() {
		Tabuleiro.campo = new ArrayList<Carta>();
	}

	public static void colocaEmCampo(Carta carta) throws ExcecaoCampoCheio {
		if (campo.size() - 1 == TAMANHO)
			throw new ExcecaoCampoCheio();
		campo.add(carta);
	}

	public static List<Carta> getCampo() throws ExcecaoCampoVazio {
		if (campo.size() == 0 || campo.equals(null))
			throw new ExcecaoCampoVazio();
		return campo;
	}

	public static void retiraDoCampo(Integer posicao) throws ExcecaoCampoVazio {
		if (campo.size() == 0 || campo.equals(null))
			throw new ExcecaoCampoVazio();
		campo.remove(posicao);
	}

	// public static Carta mostraCartaDoCampo(Integer posicao) throws
	// ExcecaoCampoVazio {
	// if (campo.size() == 0)
	// throw new ExcecaoCampoVazio();
	// return campo.get(posicao);
	// }
}