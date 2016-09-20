package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private static final Integer TAMANHO = 5;
	private static List<Carta> campo = null;

	public Tabuleiro() {
		Tabuleiro.campo = new ArrayList<Carta>();
	}

	public static void colocaEmCampo(Carta carta)
			throws ExcecaoCampoCheio {
		if (campo.size()-1 == TAMANHO)
			throw new ExcecaoCampoCheio();
		campo.add(carta);
	}

	public static String getCampo() throws ExcecaoCampoVazio {
		String campoTexto ="";
		for(int i=0; i< TAMANHO; i++)
		campoTexto += " "+ campo.toString();
		return campoTexto;
	}
}