package modelo.turnos;

import modelo.Duelista;
import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoCampoCheio;
import modelo.excecoes.ExcecaoMaoVazia;

public class TurnoPrincipal extends Turno {

	@Override
	public void acao(Tabuleiro tabuleiro) {
		if (tabuleiro.getVez() == VEZBOT) {
			try {
				tabuleiro.colocaEmCampo(procuraMaisAlta(tabuleiro.getBot()));
			} catch (ExcecaoCampoCheio | ExcecaoMaoVazia e) {
			}
			trocaTurno(tabuleiro);
		} else {
			tabuleiro.getComunicador().cartaColocaNoCampo();
		}
	}

	private Integer procuraMaisAlta(Duelista bot) throws ExcecaoMaoVazia {
		Integer indiceMaisAlta = 0;
		for (Integer i = 0; i < bot.getTamanhoDaMao(); i++) {
			if (bot.getMao().get(i).getAtaque() > bot.getMao().get(indiceMaisAlta).getAtaque())
				indiceMaisAlta = i;
		}
		return indiceMaisAlta;
	}

	@Override
	public void trocaTurno(Tabuleiro tabuleiro) {
		tabuleiro.trocaTurno(new TurnoBatalha());
		tabuleiro.acaoTurno();
	}

}
