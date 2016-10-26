package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.excecoes.ExcecaoCampoCheio;
import modelo.excecoes.ExcecaoCampoVazio;
import modelo.turnos.Turno;
import modelo.turnos.TurnoCompra;

public class Tabuleiro {

	private Duelista duelista;
	private Duelista bot;
	private static final Integer TAMANHO = 5;
	private static final Boolean VEZDUELISTA = Boolean.TRUE;
	private static final Boolean VEZBOT = Boolean.FALSE;
	private List<Carta> campoDuelista;
	private List<Carta> campoBot;
	private Boolean vez;
	private Turno turno;
	private Comunicador comunicador;

	public Tabuleiro(Usuario usuario, Usuario bot, Comunicador comunicador) {
		this.duelista = new Duelista(usuario);
		this.bot = new Duelista(bot);
		this.campoDuelista = new ArrayList<Carta>();
		this.campoBot = new ArrayList<Carta>();
		this.duelista.iniciaMao();
		this.bot.iniciaMao();
		this.turno = new TurnoCompra();
		this.comunicador = comunicador;
	}
	
	public Comunicador getComunicador() {
		return comunicador;
	}

	public void colocaEmCampo(Integer indice) throws ExcecaoCampoCheio {
		if (vez == VEZDUELISTA) {
			if (campoDuelista.size() - 1 == TAMANHO)
				throw new ExcecaoCampoCheio();
			campoDuelista.add(duelista.getCartaDaMao(indice));
		} else {
			if (campoBot.size() - 1 == TAMANHO)
				throw new ExcecaoCampoCheio();
			campoBot.add(bot.getCartaDaMao(indice));
		}
	}

	public void retiraDoCampo(Integer posicao) throws ExcecaoCampoVazio {
		if (vez == VEZDUELISTA) {
			if (campoDuelista.size() == 0)
				throw new ExcecaoCampoVazio();
			campoDuelista.remove(campoDuelista.get(posicao));
		} else {
			if (campoBot.size() == 0)
				throw new ExcecaoCampoVazio();
			campoBot.remove(campoBot.get(posicao));
		}
	}

	private Moeda jogaMoeda() {
		Double random = Math.random();
		if (random > 0.5)
			return Moeda.Cara;
		return Moeda.Coroa;
	}

	public Integer getTamanhoDoCampo() {
		if (vez == VEZDUELISTA)
			return campoDuelista.size();
		else
			return campoBot.size();
	}

	public Boolean decideLado(Moeda lado1) {
		Moeda lado2 = jogaMoeda();
		if (lado1 == lado2) {
			vez = VEZDUELISTA;
		} else
			vez = VEZBOT;
		return vez;
	}

	public List<Carta> getCampoDuelista() throws ExcecaoCampoVazio {
		if (campoDuelista.size() == 0)
			throw new ExcecaoCampoVazio();
		else 
			return campoDuelista;
	}
	
	public List<Carta> getCampoBot() throws ExcecaoCampoVazio {
		if (campoBot.size() == 0)
			throw new ExcecaoCampoVazio();
		else 
			return campoBot;
	}

	public String getCampo() {
		String campoDuelista = "Campo Duelista: \n";
		String campoBot = "\n\nCampo Bot: \n";
		try {
			for (int i = 0; i < getCampoDuelista().size(); i++) {
				campoDuelista += i;
				campoDuelista += " " + getCampoDuelista().get(i).getNome();
				campoDuelista += " " + getCampoDuelista().get(i).getAtaque();
				campoDuelista += " " + getCampoDuelista().get(i).getDefesa();
				campoDuelista += " " + getCampoDuelista().get(i).getElemento();
				campoDuelista += "\n";
			}
		} catch (ExcecaoCampoVazio e) {
			campoDuelista += "Campo Vazio!\n";
		}
		try {
			for (int i = 0; i < getCampoBot().size(); i++) {
				campoBot += i;
				campoBot += " " + getCampoBot().get(i).getNome();
				campoBot += " " + getCampoBot().get(i).getAtaque();
				campoBot += " " + getCampoBot().get(i).getDefesa();
				campoBot += " " + getCampoBot().get(i).getElemento();
				campoBot += "\n";
			}
		} catch (ExcecaoCampoVazio e) {
			campoBot += "Campo Vazio!\n";
		}
		return campoDuelista+campoBot;
	}

	public Duelista getDuelista() {
		return duelista;
	}

	public void trocaTurno(Turno turno) {
		this.turno = turno;
	}

	public Boolean getVez() {
		return vez;
	}

	public Duelista getBot() {
		return bot;
	}

	public void trocaVez() {
		vez = !vez;
	}

	public void acaoTurno() {
		turno.acao(this);
	}

}