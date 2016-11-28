package modelo.turnos;

import java.util.ArrayList;
import java.util.List;

import modelo.Carta;
import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoBaralhoVazio;
import modelo.excecoes.ExcecaoCampoVazio;
import modelo.excecoes.ExcecaoMaoCheia;
import modelo.excecoes.ExcecaoMaoVazia;

public class TurnoCompra extends Turno {

	@Override
	public void acao(Tabuleiro tabuleiro) {
		try {
			if (tabuleiro.getVez() == VEZDUELISTA)
				tabuleiro.getDuelista().comprarCarta();
			else
				tabuleiro.getBot().comprarCarta();
			atualizaTela(tabuleiro);
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

	private void atualizaTela(Tabuleiro tabuleiro) {
		List<Carta> campoBot;
		try {
			campoBot = tabuleiro.getCampoBot();
		} catch (ExcecaoCampoVazio e) {
			campoBot = new ArrayList<Carta>();
		}
		List<Carta> campoDuelista;
		try {
			campoDuelista = tabuleiro.getCampoDuelista();
		} catch (ExcecaoCampoVazio e) {
			campoDuelista = new ArrayList<Carta>();
		}
		List<Carta> maoDuelista;
		try {
			maoDuelista = tabuleiro.getDuelista().getMao();
		} catch (ExcecaoMaoVazia e) {
			maoDuelista = new ArrayList<Carta>();
		}
		tabuleiro.getComunicador().configuraTelaCompra(campoBot, campoDuelista, maoDuelista, tabuleiro.getDuelista().getPontosDeVida(), tabuleiro.getBot().getPontosDeVida());
	}

	@Override
	public void trocaTurno(Tabuleiro tabuleiro) {
		tabuleiro.trocaTurno(new TurnoPrincipal());
		tabuleiro.acaoTurno();
	}

}
