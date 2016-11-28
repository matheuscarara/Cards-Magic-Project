package modelo.turnos;

import java.util.ArrayList;
import java.util.List;

import modelo.Carta;
import modelo.Tabuleiro;
import modelo.excecoes.ExcecaoCampoVazio;

public class TurnoBatalha extends Turno {

	@Override
	public void acao(Tabuleiro tabuleiro) {
		if (RODADA > 1) {
			if (tabuleiro.getVez() == VEZBOT) {
				ataqueBot(tabuleiro);
				trocaTurno(tabuleiro);
			} else {
				tabuleiro.getComunicador().iniciaTelaBatalha();
			}
		}
		else {
			trocaTurno(tabuleiro);
		}
	}

	private void ataqueBot(Tabuleiro tabuleiro) {
		List<Carta> jaAtacaram = new ArrayList<Carta>();
		try {
			while (jaAtacaram.size() < tabuleiro.getTamanhoDoCampo() && tabuleiro.getDuelista().getPontosDeVida() > 0) {
				Carta atacando = getMenorAtaque(tabuleiro.getCampoBot(), jaAtacaram);
				try {
					Carta defendendo = getMenorDefesa(tabuleiro.getCampoDuelista());
					if (defendendo.getDefesa() < atacando.getAtaque()) {
						tabuleiro.ataca(atacando, defendendo);
						jaAtacaram.add(atacando);
					}
				} catch (ExcecaoCampoVazio e) {
					tabuleiro.ataca(atacando, null);
					jaAtacaram.add(atacando);
				}
			}
		} catch (ExcecaoCampoVazio e) {
		}
	}

	private Carta getMenorAtaque(List<Carta> campo, List<Carta> jaAtacaram) {
		Integer menorAtaque = Integer.MAX_VALUE;
		Integer cartaMenor = 0;
		for (int i = 0; i < campo.size(); i++) {
			if (campo.get(i).getAtaque() < menorAtaque && !(jaAtacaram.contains(campo.get(i)))) {
				menorAtaque = campo.get(i).getAtaque();
				cartaMenor = i;
			}
		}
		return campo.get(cartaMenor);
	}

	private Carta getMenorDefesa(List<Carta> campo) {
		Integer menorDefesa = Integer.MAX_VALUE;
		Integer cartaMenor = 0;
		for (int i = 0; i < campo.size(); i++) {
			if (campo.get(i).getDefesa() < menorDefesa) {
				menorDefesa = i;
				cartaMenor = i;
			}
		}
		return campo.get(cartaMenor);
	}

	@Override
	public void trocaTurno(Tabuleiro tabuleiro) {
		tabuleiro.trocaVez();
		RODADA++;
		tabuleiro.trocaTurno(new TurnoCompra());
		tabuleiro.acaoTurno();
	}
}
