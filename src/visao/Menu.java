package visao;

import java.sql.SQLException;

import modelo.Duelista;
import modelo.ExcecaoBaralhoCheio;
import modelo.ExcecaoCampoCheio;
import modelo.ExcecaoCampoVazio;
import modelo.Mapeador;
import modelo.Moeda;
import modelo.Tabuleiro;
import modelo.Usuario;

public class Menu {

	private Tabuleiro tabuleiro;
	private Usuario usuario;
	private Duelista duelista;
	private Mapeador mapeador;
	private InteracaoUsuario iu;

	public Menu() {
		tabuleiro = null;
		usuario = null;
		duelista = null;
		mapeador = new Mapeador();
		iu = new InteracaoUsuario();
		menuInicial();
	}

	private void menuInicial() {
		Integer opcao = iu.retornaInt("Digite: 1 para realizar um login \n" + "2 para criar um jogador \n"
				+ "Qualquer outro valor para sair.");
		if (opcao == 1)
			login();
		else if (opcao == 2)
			cadastrar();
		else
			finalizar();
	}

	private void finalizar() {
		iu.mostraMensagem("Finalizando...");
	}

	private void login() {
		String login = iu.retornaString("Digite o Login do jogador:");
		String senha = iu.retornaString("Digite a senha do jogador:");
		try {
			usuario = mapeador.carregarUsuario(login, senha);
			mapeador.carregaBaralho(usuario);
			menuLogado();
		} catch (SQLException | ExcecaoBaralhoCheio e) {
			iu.mostraMensagem("Login inválido!");
			menuInicial();
		}
	}

	private void cadastrar() {
		String login = iu.retornaString("Digite o Login do jogador:");
		String senha = iu.retornaString("Digite a senha do jogador:");
		try {
			mapeador.salvarUsuario(login, senha);
			iu.mostraMensagem("Cadastrado com sucesso!");
		} catch (SQLException e) {
			iu.mostraMensagem("Login ja existe!");
		}
		menuInicial();
	}

	private void menuLogado() {
		Integer opcao = iu.retornaInt("Digite 1 ver seu baralho;\n" + "Digite 2 para adicionar cartas no seu baralho;\n"
				+ "Digite 3 para entrar no tabuleiro;\n" + "Digite 4 para deslogar.");
		switch (opcao) {
		case (1):
			verBaralho();
			break;
		case (2):
			adicionaCartaNoBaralho();
			break;
		case (3):
			iniciarTabuleiro();
			break;
		case (4):
			iu.mostraMensagem("Deslogando...");
			iu.mostraMensagem("Deslogado com sucesso!");
			menuInicial();
			break;
		default:
			iu.mostraMensagem("Comando Invalido");
			menuLogado();
			break;
		}
	}

	private void iniciarTabuleiro() {
		iu.mostraMensagem("Entrando no tabuleiro...\n" + "Iniciando mao...");
		if (usuario.getBaralho().baralhoEstaCompleto()) {
			duelista = new Duelista(usuario);
			iu.mostraMensagem("Mao iniciada!");
			noTabuleiro();
		} else {
			iu.mostraMensagem("Baralho incompleto! Impossivel acessar o tabuleiro!");
			menuLogado();
		}
	}

	private void noTabuleiro() {
		if (tabuleiro == null) {
			tabuleiro = new Tabuleiro(duelista);
			Boolean mando;
			Integer ladoMoeda = iu.retornaInt("Digite 0 para selecionar cara ou 1 para coroa");
			if (ladoMoeda == 0)
				mando = tabuleiro.decideLado(Moeda.Cara);
			else
				mando = tabuleiro.decideLado(Moeda.Coroa);
			if (mando)
				iu.mostraMensagem("Acertou o lado. Você joga primeiro.");
			else
				iu.mostraMensagem("Errou o lado. Você não joga primeiro.");
		}
		Integer opcao = iu.retornaInt("Digite 1 para ver sua mao;\n" + "Digite 2 para ver seu campo;\n"
				+ "Digite 3 para inserir carta no seu campo;\n" + "Digite 4 para retirar carta de campo;\n"
				+ "Digite qualquer outro valor para sair do tabuleiro.");
		switch (opcao) {
		case (1):
			verMao();
			break;
		case (2):
			verCampo();
			break;
		case (3):
			inserirCartaNoCampo();
			break;
		case (4):
			retirarCartaDoCampo();
			break;
		default:
			tabuleiro = null;
			try {
				mapeador.carregaBaralho(usuario);
			} catch (SQLException | ExcecaoBaralhoCheio e) {
				e.printStackTrace();
			}
			menuLogado();
			break;
		}
	}

	private void retirarCartaDoCampo() {
		String campo = iu.mostraCampo(tabuleiro);
		Integer opcaoRemCartaCampo = iu.retornaInt(
				"Digite a carta a ser removida: \n\n" + campo + "\n\n" + "Digite qualquer outro valor para sair.");

		if (!(opcaoRemCartaCampo >= tabuleiro.getTamanhoDoCampo() || opcaoRemCartaCampo < 0)) {
			try {
				tabuleiro.retiraDoCampo(opcaoRemCartaCampo);
			} catch (ExcecaoCampoVazio e) {
				iu.mostraMensagem("Campo vazio, tente outra vez.");
			}
		}
		noTabuleiro();
	}

	private void inserirCartaNoCampo() {
		String mao = duelista.mostraMao();
		Integer opcaoAddCartaCampo = iu.retornaInt(
				"Digite a carta a ser inserida: \n\n" + mao + "\n\n" + "Digite qualquer outro valor para sair.");
		if (!(opcaoAddCartaCampo >= duelista.getTamanhoDaMao() || opcaoAddCartaCampo < 0)) {
			try {
				tabuleiro.colocaEmCampo(opcaoAddCartaCampo);
			} catch (ExcecaoCampoCheio e) {
				iu.mostraMensagem("Campo cheio.");
			}
		}
		noTabuleiro();
	}

	private void verCampo() {
		iu.mostraMensagem(iu.mostraCampo(tabuleiro));
		noTabuleiro();
	}

	private void verMao() {
		iu.mostraMensagem(duelista.mostraMao());
		noTabuleiro();
	}

	private void adicionaCartaNoBaralho() {
		try {
			Integer idCarta = iu.mostraCartasParaAdicionar(mapeador);
			if (idCarta == 50)
				menuLogado();
			else {
				mapeador.adicionarCartaBaralho(usuario, idCarta);
				adicionaCartaNoBaralho();
			}
		} catch (SQLException e) {
			iu.mostraMensagem("Carta nao existe!");
			adicionaCartaNoBaralho();
		} catch (ExcecaoBaralhoCheio e) {
			iu.mostraMensagem("Baralho ja está cheio!");
			menuLogado();
		}
	}

	private void verBaralho() {
		iu.mostraBaralho(usuario);
		menuLogado();
	}

}
