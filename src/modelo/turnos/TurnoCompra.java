package modelo.turnos;

import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoBaralhoVazio;
import modelo.excecoes.ExcecaoMaoCheia;

public class TurnoCompra extends Turno{

	@Override
	public void acao(Tabuleiro tabuleiro){
		try {
			if (tabuleiro.getVez() == VEZDUELISTA)
				tabuleiro.getDuelista().comprarCarta();
			else
				tabuleiro.getBot().comprarCarta();
			trocaTurno(tabuleiro);
		} catch (ExcecaoMaoCheia e) {
			trocaTurno(tabuleiro);
		} catch (ExcecaoBaralhoVazio e) {
			if (tabuleiro.getVez() == VEZDUELISTA)
				tabuleiro.getComunicador().perdeu();
			else
				tabuleiro.getComunicador().ganhou();
		}
	}

	@Override
	public void trocaTurno(Tabuleiro tabuleiro){
		tabuleiro.trocaTurno(new TurnoPrincipal());
		tabuleiro.acaoTurno();
	}

}
