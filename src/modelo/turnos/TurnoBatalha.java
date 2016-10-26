package modelo.turnos;

import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoCampoVazio;

public class TurnoBatalha extends Turno {

	@Override
	public void acao(Tabuleiro tabuleiro) {
		if (RODADA == 1) {

			try {
				if (tabuleiro.getCampoDuelista().get(0).getAtaque() >= tabuleiro
						.getCampoBot().get(0).getAtaque()) {
					RODADA = 0;
					tabuleiro.getComunicador().ganhou();
				} else {
					RODADA = 0;
					tabuleiro.getComunicador().perdeu();
				}
			} catch (ExcecaoCampoVazio e) {
			}
		} else {
			trocaTurno(tabuleiro);
		}
	}

	@Override
	public void trocaTurno(Tabuleiro tabuleiro) {
		tabuleiro.trocaVez();
		RODADA++;
		tabuleiro.trocaTurno(new TurnoCompra());
		tabuleiro.acaoTurno();
	}

}
