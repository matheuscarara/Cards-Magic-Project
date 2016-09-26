package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private static final Integer TAMANHO = 5;
	private static List<Carta> campo;

	public Tabuleiro() {
		Tabuleiro.campo = new ArrayList<Carta>();
	}
//metodo de colocar carta em campo
	//adicionando uma carta ao campo
	public void colocaEmCampo(Carta carta) throws ExcecaoCampoCheio {
		if (campo.size() - 1 == TAMANHO)
			throw new ExcecaoCampoCheio();
		campo.add(carta);
	}

	public List<Carta> getCampo() throws ExcecaoCampoVazio {
		if (campo.size() == 0){
			throw new ExcecaoCampoVazio();
		}
		return campo;
	}
//metodo de retirar carta do campo
	public static void retiraDoCampo(Integer posicao) throws ExcecaoCampoVazio {
		if (campo.size() == 0)
			throw new ExcecaoCampoVazio();
		campo.remove(posicao);
	}

}