package modelo;

import java.sql.SQLException;

import modelo.excecoes.ExcecaoBaralhoCheio;
import modelo.excecoes.ExcecaoCampoCheio;
import modelo.excecoes.ExcecaoCampoVazio;
import modelo.mapeadores.Mapeador;
import modelo.turnos.TurnoBatalha;

public class Fachada {

	private Tabuleiro tabuleiro;
	private Usuario usuario;
	private Usuario bot;
	private Mapeador mapeador;
	private String cartas;
	private Comunicador comunicador;

	public Fachada(Comunicador comunicador) {
		tabuleiro = null;
		usuario = null;
		cartas = null;
		bot = new Usuario("Bot", "Bot");
		mapeador = new Mapeador();
		this.comunicador = comunicador;
	}

	public void carregaUsuario(String login, String senha) throws SQLException, ExcecaoBaralhoCheio {
		usuario = mapeador.carregarUsuario(login, senha);
		carregaBaralho();
	}

	public void cadastraUsuario(String login, String senha) throws SQLException {
		usuario = new Usuario(login, senha);
		mapeador.salvarUsuario(usuario);
	}

	public void iniciarTabuleiro() throws SQLException, ExcecaoBaralhoCheio {
		mapeador.carregaBaralho(bot);
		tabuleiro = new Tabuleiro(usuario, bot, comunicador);
	}

	public void IniciaDuelo() {
		tabuleiro.acaoTurno();
		try {
			carregaBaralho();
		} catch (SQLException | ExcecaoBaralhoCheio e) {
		}
	}

	public Boolean decideLado(Boolean ladoMoeda) {
		if (ladoMoeda)
			return tabuleiro.decideLado(Moeda.Cara);
		else
			return tabuleiro.decideLado(Moeda.Coroa);
	}

	public void carregaBaralho() throws SQLException, ExcecaoBaralhoCheio {
		mapeador.carregaBaralho(usuario);
	}

	public String retornaCartas() throws SQLException {
		if (cartas == null)
			cartas = mapeador.carregaCartas();
		return cartas;
	}

	public void adicionaCarta(Integer idCarta) throws SQLException, ExcecaoBaralhoCheio {
		mapeador.adicionarCartaBaralho(usuario, idCarta);
	}

	public void colocaCartaNoCampo(Integer idCarta) {
		try {
			tabuleiro.colocaEmCampo(idCarta);
			tabuleiro.getTurno().trocaTurno(tabuleiro);
		} catch (ExcecaoCampoCheio e) {
			tabuleiro.getComunicador().campoCheio();
			tabuleiro.getTurno().trocaTurno(tabuleiro);
		}
	}

	public String mostraMao() {
		return tabuleiro.getDuelista().mostraMao();
	}

	public String getCampo() {
		return tabuleiro.getCampo();
	}

	public Integer getTamanhoDaMao() {
		return tabuleiro.getDuelista().getTamanhoDaMao();
	}

	public String mostraBaralho() {
		return usuario.mostraBaralho();
	}

	public void setComunicador(Comunicador comunicador) {
		tabuleiro.setComunicador(comunicador);
	}

	public void trocaTurno() {
		tabuleiro.getTurno().trocaTurno(tabuleiro);
	}

	public void ataca(Integer idCartaAtacando, Integer idCartaDefendendo) {
		if (idCartaDefendendo != null) {
			try {
				tabuleiro.ataca(tabuleiro.getCampoDuelista().get(idCartaAtacando),
						tabuleiro.getCampoBot().get(idCartaDefendendo));
			} catch (ExcecaoCampoVazio e) {
			}
		} else {
			try {
				tabuleiro.ataca(tabuleiro.getCampoDuelista().get(idCartaAtacando), null);
			} catch (ExcecaoCampoVazio e) {
			}
		}
	}
}