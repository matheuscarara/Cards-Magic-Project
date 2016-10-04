package iu;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import modelo.Duelista;
import modelo.ExcecaoBaralhoCheio;
import modelo.ExcecaoCampoCheio;
import modelo.ExcecaoCampoVazio;
import modelo.ExcecaoCartaNaoExiste;
import modelo.Mapeador;
import modelo.Moeda;
import modelo.Tabuleiro;
import modelo.Usuario;

public class Menu {
	public static void main(String[] args) {
		Mapeador bd = new Mapeador();
		Tabuleiro tabuleiro = null;
		boolean logando = true;
		boolean dentro = true;
		boolean noTabuleiro = false;
		Usuario jogador = null;
		Duelista duelista = null;
		String login;
		String senha;
		Integer opcao;

		while (true) {
			logando = true;
			dentro = true;
			noTabuleiro = false;

			while (logando) {
				opcao = Integer
						.parseInt(JOptionPane
								.showInputDialog("Digite 1 para realizar um login ou 2 para criar um jogador"));
				switch (opcao) {
				case 1:
					login = JOptionPane
							.showInputDialog("Digite o Login do jogador");
					senha = JOptionPane
							.showInputDialog("Digite a senha do jogador");
					try {
						jogador = bd.carregarJogador(login, senha);
						bd.carregaBaralho(jogador);
						logando = false;
					} catch (SQLException | ExcecaoCartaNaoExiste
							| ExcecaoBaralhoCheio e) {
						JOptionPane.showMessageDialog(null, "Login inválido!");
					}
					break;
				case 2:
					login = JOptionPane
							.showInputDialog("Digite o Login do jogador");
					senha = JOptionPane
							.showInputDialog("Digite a senha do jogador");
					try {
						bd.salvarJogador(login, senha);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Login ja existe!");
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, "Comando Invalido!");
					break;
				}
			}
			while (dentro) {
				opcao = Integer
						.parseInt(JOptionPane
								.showInputDialog("Digite 1 ver seu baralho;\n"
										+ "Digite 2 para adicionar cartas no seu baralho;\n"
										+ "Digite 3 para entrar no tabuleiro;\n"
										+ "Digite 4 para deslogar."));
				String texto = "";
				switch (opcao) {
				case 1:
					if (jogador.getBaralho().getBaralho().isEmpty()) {
						texto = "Seu Baralho não possui cartas";
					} else {
						for (int i = 0; i < jogador.getBaralho().getBaralho()
								.size(); i++) {
							texto += jogador.getBaralho().getBaralho().get(i)
									.getId();
							texto += " "
									+ jogador.getBaralho().getBaralho().get(i)
											.getNome();
							texto += " "
									+ jogador.getBaralho().getBaralho().get(i)
											.getAtaque();
							texto += " "
									+ jogador.getBaralho().getBaralho().get(i)
											.getDefesa();
							texto += " "
									+ jogador.getBaralho().getBaralho().get(i)
											.getElemento();
							texto += "\n";
						}
					}
					JOptionPane.showMessageDialog(null, jogador.getBaralho()
							.tamanhoDoBaralho()
							+ " Cartas em seu baralho:\n"
							+ texto);
					break;
				case 2:
					boolean alterando = true;

					while (alterando) {
						if (texto.isEmpty()) {
							try {
								texto += bd.carregaCartas();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						Integer opcao2 = Integer
								.parseInt(JOptionPane
										.showInputDialog("Digite o Id da carta a ser inserida no baralho.\n"
												+ "Digite 50 para sair. \n"
												+ texto));

						switch (opcao2) {
						case 50:
							alterando = false;
							break;
						default:
							try {
								bd.adicionarCartaBaralho(jogador, opcao2);
							} catch (ExcecaoCartaNaoExiste e) {
								JOptionPane.showMessageDialog(null,
										"Carta não existe!");
							} catch (ExcecaoBaralhoCheio e) {
								JOptionPane
										.showMessageDialog(null,
												"Baralho atingiu número limite de cartas.");
								break;
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						}
					}
					break;
				case 3:
					JOptionPane.showMessageDialog(null,
							"Entrando no tabuleiro...\n" + "Iniciando mao...");
					if (jogador.getBaralho().baralhoEstaCompleto()) {
						duelista = new Duelista(jogador);
						JOptionPane.showMessageDialog(null, "Mao iniciada!");
						noTabuleiro = true;
					} else {
						JOptionPane.showMessageDialog(null,
								"Seu baralho esta incompleto.");
						break;
					}
					dentro = false;
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Sessão Finalizada.");
					dentro = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Comando Invalido!");
					break;
				}

			}

			while (noTabuleiro) {

				if (tabuleiro == null) {
					tabuleiro = new Tabuleiro(duelista);
					Boolean mando;
					Integer ladoMoeda = Integer
							.parseInt(JOptionPane
									.showInputDialog("Digite 0 para selecionar cara ou 1 para coroa"));
					if (ladoMoeda == 0)
						mando = tabuleiro.decideLado(Moeda.Cara);
					else
						mando = tabuleiro.decideLado(Moeda.Coroa);
					if (mando)
						JOptionPane.showMessageDialog(null,
								"Acertou o lado. Você joga primeiro.");
					else
						JOptionPane.showMessageDialog(null,
								"Errou o lado. Você não joga primeiro.");
				}
				String campo = "";
				String mao = "";
				opcao = Integer.parseInt(JOptionPane
						.showInputDialog("Digite 1 para ver sua mao;\n"
								+ "Digite 2 para ver seu campo;\n"
								+ "Digite 3 para inserir carta no seu campo;\n"
								+ "Digite 4 para retirar carta de campo;\n"
								+ "Digite qualquer outro valor para sair."));
				switch (opcao) {
				case 1:
					mao = duelista.mostraMao();

					JOptionPane.showMessageDialog(null, mao);
					break;
				case 2:
					try {
						for (int i = 0; i < tabuleiro.getCampo().size(); i++) {
							campo += i;
							campo += " "
									+ tabuleiro.getCampo().get(i).getNome();
							campo += " "
									+ tabuleiro.getCampo().get(i).getAtaque();
							campo += " "
									+ tabuleiro.getCampo().get(i).getDefesa();
							campo += " "
									+ tabuleiro.getCampo().get(i).getElemento();
							campo += "\n";
						}
						JOptionPane.showMessageDialog(null, campo);
					} catch (ExcecaoCampoVazio e) {
						JOptionPane.showMessageDialog(null, "Campo vazio");
					}
					break;
				case 3:

					Integer opcaoAddCartaCampo;
					mao = duelista.mostraMao();
					opcaoAddCartaCampo = Integer
							.parseInt(JOptionPane
									.showInputDialog("Digite a carta a ser inserida: \n\n"
											+ mao
											+ "\n\n"
											+ "Digite qualquer outro valor para sair."));

					if (!(opcaoAddCartaCampo >= duelista.getTamanhoDaMao() || opcaoAddCartaCampo < 0)) {
						try {
							tabuleiro.colocaEmCampo(opcaoAddCartaCampo);
						} catch (ExcecaoCampoCheio e) {
							JOptionPane.showMessageDialog(null, "Campo cheio.");
							break;
						}
					}
					break;
				case 4:
					campo = "";
					Integer opcaoRemCartaCampo;
					try {
						for (int i = 0; i < tabuleiro.getCampo().size(); i++) {
							campo += i;
							campo += " "
									+ tabuleiro.getCampo().get(i).getNome();
							campo += " "
									+ tabuleiro.getCampo().get(i).getAtaque();
							campo += " "
									+ tabuleiro.getCampo().get(i).getDefesa();
							campo += " "
									+ tabuleiro.getCampo().get(i).getElemento();
							campo += "\n";
						}
					} catch (ExcecaoCampoVazio e) {
					}
					opcaoRemCartaCampo = Integer
							.parseInt(JOptionPane
									.showInputDialog("Digite a carta a ser removida: \n\n"
											+ campo
											+ "\n\n"
											+ "Digite qualquer outro valor para sair."));

					if (!(opcaoRemCartaCampo >= tabuleiro.getTamanhoDoCampo() || opcaoRemCartaCampo < 0)) {
						try {
							tabuleiro.retiraDoCampo(opcaoRemCartaCampo);
						} catch (ExcecaoCampoVazio e) {
							JOptionPane.showMessageDialog(null,
									"Campo vazio, tente outra vez.");
							break;
						}
					}
					break;
				default:
					tabuleiro = null;
					noTabuleiro = false;
					break;
				}
				
			}
		}
	}
}
