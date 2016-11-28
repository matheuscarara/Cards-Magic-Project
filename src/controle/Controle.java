package controle;

import java.awt.event.WindowEvent;
import java.sql.SQLException;

import modelo.Fachada;
import modelo.excecoes.ExcecaoBaralhoCheio;
import visao.InteracaoUsuario;
import visao.MenuPrincipal;
import visao.TelaEditarBaralho;
import visao.TelaInicial;
import visao.TelaJogo;
import visao.TelaTabuleiro;

public class Controle {

	private Fachada fachada;
	private InteracaoUsuario iu;
	private TelaJogo tela;
	private String loginDoUsuario;

	public Controle() {
		iu = new InteracaoUsuario();
		tela = new TelaInicial(this);
		fachada = new Fachada(tela);
	}

	public void login(String login, String senha) {
		if (login.equalsIgnoreCase("Bot")) {
			iu.mostraMensagem("Login inválido!");
		} else {
			try {
				fachada.carregaUsuario(login, senha);
				tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
				loginDoUsuario = login;
				tela = new MenuPrincipal(this);
			} catch (SQLException | ExcecaoBaralhoCheio e) {
				iu.mostraMensagem("Login inválido!");
			}
		}
	}

	public void cadastrar(String login, String senha) {
		try {
			fachada.cadastraUsuario(login, senha);
			iu.mostraMensagem("Cadastrado com sucesso!");
		} catch (SQLException e) {
			iu.mostraMensagem("Login ja existe!");
		}
	}

	public void finalizaDuelo() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		tela = new MenuPrincipal(this);
	}
	
	
	public void iniciarTabuleiro() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		iu.mostraMensagem("Entrando no tabuleiro...\n" + "Iniciando mao...");
		try {
			fachada.iniciarTabuleiro();
			iu.mostraMensagem("Mao iniciada!");
			Boolean ladoMoeda = iu.retornaBool("Digite 0 para selecionar cara ou 1 para coroa");
			Boolean mando = fachada.decideLado(ladoMoeda);
			if (mando)
				iu.mostraMensagem("Acertou o lado. Você joga primeiro.");
			else
				iu.mostraMensagem("Errou o lado. Você não joga primeiro.");
			jogando();
		} catch (SQLException | ExcecaoBaralhoCheio e) {
			iu.mostraMensagem("Baralho incompleto! Impossivel acessar o tabuleiro!");
			tela = new MenuPrincipal(this);
		}
	}

	public void adicionaCartaEmCampo(Integer idCarta) {
		fachada.colocaCartaNoCampo(idCarta);
	}
	
	private void jogando() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		tela = new TelaTabuleiro(this);
		fachada.setComunicador(tela);
		fachada.IniciaDuelo();
		
	}

	public void editarBaralho() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		tela = new TelaEditarBaralho(this);
	}

	public void adicionaCartaNoBaralho(Integer idCarta) {
		try {
			fachada.adicionaCarta(idCarta);
			((TelaEditarBaralho) tela).atualizaBaralho();
		} catch (SQLException e) {
			iu.mostraMensagem("Carta nao existe!");
		} catch (ExcecaoBaralhoCheio e) {
			iu.mostraMensagem("Baralho ja está cheio!");
			voltaAoMenuPrincipal();
		}
	}
	
	public String verCartas() {
		try {
			return fachada.retornaCartas();
		} catch (SQLException e) {
			return null;
		}
	}

	public String verBaralho() {
		return fachada.mostraBaralho();
	}
	
	public void voltaAoMenuPrincipal() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		tela = new MenuPrincipal(this);
	}
	
	public void deslogar() {
		tela.dispatchEvent(new WindowEvent(tela, WindowEvent.WINDOW_CLOSING));
		tela = new TelaInicial(this);
	}
	
	public String getNomeUsuario() {
		return loginDoUsuario;
	}

	public void ataca(Integer idCartaAtacando, Integer idCartaDefendendo) {
		if (idCartaDefendendo == null && idCartaAtacando == null) {
			fachada.trocaTurno();
		} else {
			fachada.ataca(idCartaAtacando, idCartaDefendendo);
		}
	}
	
}