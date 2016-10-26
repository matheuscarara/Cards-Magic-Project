package controle;

import java.sql.SQLException;

import modelo.Fachada;
import modelo.excecoes.ExcecaoBaralhoCheio;
import visao.InteracaoUsuario;

public class Controle {
	
	private Fachada fachada;
	private InteracaoUsuario iu;
	
	public Controle() {
		iu = new InteracaoUsuario();
		fachada = new Fachada(iu);
		menuInicial();
	}

	private void menuInicial() {
		Integer opcao = iu.retornaInt("Digite: \n1 para realizar um login \n"
				+ "2 para criar um jogador \n"
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
		if (login.equalsIgnoreCase("Bot")) {
			iu.mostraMensagem("Login inválido!");
			menuInicial();
		} else {
			try {
				fachada.carregaUsuario(login, senha);
				menuLogado();
			} catch (SQLException | ExcecaoBaralhoCheio e) {
				iu.mostraMensagem("Login inválido!");
				menuInicial();
			}
		}
	}

	private void cadastrar() {
		String login = iu.retornaString("Digite o Login do jogador:");
		String senha = iu.retornaString("Digite a senha do jogador:");
		try {
			fachada.cadastraUsuario(login, senha);
			iu.mostraMensagem("Cadastrado com sucesso!");
		} catch (SQLException e) {
			iu.mostraMensagem("Login ja existe!");
		}
		menuInicial();
	}

	private void menuLogado() {
		Integer opcao = iu.retornaInt("Digite 1 ver seu baralho;\n"
				+ "Digite 2 para adicionar cartas no seu baralho;\n"
				+ "Digite 3 para entrar no tabuleiro;\n"
				+ "Digite 4 para deslogar.");
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
		try {
			fachada.iniciarTabuleiro();
			iu.mostraMensagem("Mao iniciada!");
			Boolean ladoMoeda = iu
					.retornaBool("Digite 0 para selecionar cara ou 1 para coroa");
			Boolean mando = fachada.decideLado(ladoMoeda);
			if (mando)
				iu.mostraMensagem("Acertou o lado. Você joga primeiro.");
			else
				iu.mostraMensagem("Errou o lado. Você não joga primeiro.");
			jogando();
		} catch (SQLException | ExcecaoBaralhoCheio e) {
			iu.mostraMensagem("Baralho incompleto! Impossivel acessar o tabuleiro!");
			menuLogado();
		}
	}

	private void jogando() {
		fachada.IniciaDuelo();
		menuLogado();
	}

	private void adicionaCartaNoBaralho() {
		try {
			Integer idCarta = iu.mostraCartasParaAdicionar(fachada
					.retornaCartas());
			if (idCarta == 50)
				menuLogado();
			else {
				fachada.adicionaCarta(idCarta);
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
		iu.mostraMensagem(fachada.mostraBaralho());
		menuLogado();
	}

}
