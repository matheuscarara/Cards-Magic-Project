package modelo.turnos;

import modelo.Duelista;
import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoCampoCheio;
import modelo.excecoes.ExcecaoMaoVazia;

public class TurnoPrincipal extends Turno {

	@Override
	public void acao(Tabuleiro tabuleiro) {
		if (tabuleiro.getVez() == VEZDUELISTA) {
			Boolean valido = Boolean.FALSE;
			Integer indice = 0;
			while (!valido) {
				indice = tabuleiro.getComunicador().indiceCartaDaMaoParaColocaNoCampo(tabuleiro.getCampo(),
						tabuleiro.getDuelista().mostraMao());
				if (!(indice >= tabuleiro.getDuelista().getTamanhoDaMao() || indice < 0)){
					valido = Boolean.TRUE;
				} else {
					tabuleiro.getComunicador().indiceInvalido();
				}
			}
			try {
				tabuleiro.colocaEmCampo(indice);
			} catch (ExcecaoCampoCheio e) {
				tabuleiro.getComunicador().campoCheio();
			}
		} else {
			try {
				tabuleiro.colocaEmCampo(procuraMaisAlta(tabuleiro.getBot()));
				tabuleiro.getComunicador().mostraCampo(tabuleiro.getCampo());
			} catch (ExcecaoCampoCheio | ExcecaoMaoVazia e) {
			}
		}
		trocaTurno(tabuleiro);
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
