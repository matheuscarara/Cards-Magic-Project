package iu;

import javax.swing.JOptionPane;

import modelo.ExcecaoBaralhoCheio;
import modelo.ExcecaoBaralhoVazio;
import modelo.ExcecaoCampoCheio;
import modelo.ExcecaoCampoVazio;
import modelo.ExcecaoCartaNaoExiste;
import modelo.ExcecaoJogadorJaExiste;
import modelo.ExcecaoJogadorNaoExiste;
import modelo.ExcecaoMaoVazia;
import modelo.ExcecaoSenhaErrada;
import modelo.Jogador;
import modelo.RepositorioCartas;
import modelo.RepositorioJogadores;
import modelo.Tabuleiro;

public class Menu {
	static final Integer QUANTIDADETOTALCARTAS = 30;

	public static void main(String[] args) {
		boolean logando = true;
		boolean dentro = true;
		boolean noTabuleiro = true;
		Jogador jogador = null;
		String login;
		String senha;
		Integer opcao;
		RepositorioCartas.geraCartas();

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
					jogador = RepositorioJogadores.entrar(login, senha);
					logando = false;
				} catch (ExcecaoJogadorNaoExiste | ExcecaoSenhaErrada e) {
					JOptionPane.showMessageDialog(null,
							"Login ou Senha Errados!");
				}
				break;
			case 2:
				login = JOptionPane
						.showInputDialog("Digite o Login do jogador");
				senha = JOptionPane
						.showInputDialog("Digite a Senha do jogador");
				try {
					RepositorioJogadores.criaJogador(login, senha);
				} catch (ExcecaoJogadorJaExiste e) {
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
									+ "Digite 4 para sair."));
			String texto = "";
			switch (opcao) {
			case 1:
				if (jogador.getBaralho().getCartas().isEmpty()) {
					texto = "Seu Baralho não possui cartas";
				} else {
					for (int i = 0; i < jogador.getBaralho().getCartas().size(); i++) {
						texto += jogador.getBaralho().getCartas().get(i)
								.getId();
						texto += " "
								+ jogador.getBaralho().getCartas().get(i)
										.getNome();
						texto += " "
								+ jogador.getBaralho().getCartas().get(i)
										.getAtaque();
						texto += " "
								+ jogador.getBaralho().getCartas().get(i)
										.getDefesa();
						texto += " "
								+ jogador.getBaralho().getCartas().get(i)
										.getElemento().name();
						texto += "\n";
					}
				}
				JOptionPane.showMessageDialog(null, texto);
				break;
			case 2:
				boolean alterando = true;

				while (alterando) {
					for (int i = 0; i < QUANTIDADETOTALCARTAS; i++) {
						try {
							texto += RepositorioCartas.getCarta(i).getId();
							texto += " "
									+ RepositorioCartas.getCarta(i).getNome();
							texto += " "
									+ RepositorioCartas.getCarta(i).getAtaque();
							texto += " "
									+ RepositorioCartas.getCarta(i).getDefesa();
							texto += " "
									+ RepositorioCartas.getCarta(i)
											.getElemento().name();
							texto += "\n";
						} catch (ExcecaoCartaNaoExiste e) {
							JOptionPane.showMessageDialog(null,
									"Carta nao existe.");
						}
					}
					Integer opcao2 = Integer
							.parseInt(JOptionPane
									.showInputDialog("Digite o Id da carta a ser inserida no baralho.\n"
											+ "Digite 50 para sair. \n" + texto));
					switch (opcao2) {
					case 50:
						alterando = false;
						break;
					default:
						try {
							jogador.adicionaCartaNoBaralho(opcao2);
							JOptionPane.showMessageDialog(null,
									"Carta adicionada!");
						} catch (ExcecaoCartaNaoExiste e) {
							JOptionPane.showMessageDialog(null,
									"Carta não existe!");
						} catch (ExcecaoBaralhoCheio e) {
							JOptionPane.showMessageDialog(null,
									"Baralho atingiu número limite de cartas.");
						}
						break;
					}
				}
				break;
			case 3:
				dentro = false;
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Sessão Finalizada.");
				dentro = false;
				return;
			default:
				JOptionPane.showMessageDialog(null, "Comando Invalido!");
				break;
			}
		}
		while (noTabuleiro) {
			String campo = "";
			String mao = "";
			opcao = Integer.parseInt(JOptionPane
					.showInputDialog("Digite 1 para iniciar mao;\n"
							+ "Digite 2 para ver sua mao;\n"
							+ "Digite 3 para ver seu campo;\n"
							+ "Digite 4 para inserir carta no seu campo;\n"
							+ "Digite 5 para retirar carta de campo;\n"
							+ "Digite 6 para sair."));
			switch (opcao) {
			case 1:
				try {
					jogador.iniciaMaoJogador();
					JOptionPane.showMessageDialog(null, "Mao iniciada!");
				} catch (ExcecaoBaralhoVazio e1) {
					JOptionPane.showMessageDialog(null, "Baralho vazio.");
				}
				break;
			case 2:
				try {
					for (int i = 0; i < Jogador.getMao().size(); i++) {
						mao += " " + Jogador.getMao().get(i).getId();
						mao += " " + Jogador.getMao().get(i).getNome();
						mao += " " + Jogador.getMao().get(i).getAtaque();
						mao += " " + Jogador.getMao().get(i).getDefesa();
						mao += " " + Jogador.getMao().get(i).getElemento();
						mao += "\n";
					}
					JOptionPane.showMessageDialog(null, mao);
				} catch (ExcecaoMaoVazia e) {
					JOptionPane.showMessageDialog(null, "Mao vazia.");
				}
				break;
			case 3:
				try {
					for (int i = 0; i < Tabuleiro.getCampo().size(); i++) {
						campo += " " + Tabuleiro.getCampo().get(i).getId();
						campo += " " + Tabuleiro.getCampo().get(i).getNome();
						campo += " " + Tabuleiro.getCampo().get(i).getAtaque();
						campo += " " + Tabuleiro.getCampo().get(i).getDefesa();
						campo += " "
								+ Tabuleiro.getCampo().get(i).getElemento();
						campo += "\n";
					}
					JOptionPane.showMessageDialog(null, campo);
				} catch (ExcecaoCampoVazio e) {
					JOptionPane.showMessageDialog(null, "Campo vazio");
				}
				break;
			case 4:
				boolean alterando = true;
				while (alterando) {
					Integer opcaoAddCartaCampo = null;
					try {
						for (int i = 0; i < Jogador.getMao().size(); i++) {
							mao += " " + Jogador.getMao().get(i).getId();
							mao += " " + Jogador.getMao().get(i).getNome();
							mao += " " + Jogador.getMao().get(i).getAtaque();
							mao += " " + Jogador.getMao().get(i).getDefesa();
							mao += " " + Jogador.getMao().get(i).getElemento();
							mao += "\n";
						}
						opcaoAddCartaCampo = Integer
								.parseInt(JOptionPane
										.showInputDialog("Digite a carta a ser inserida: \n"
												+ "Digite 50 para sair." + mao));
					} catch (ExcecaoMaoVazia e) {
						JOptionPane.showMessageDialog(null, "Mao vazia.");
					}
					switch (opcaoAddCartaCampo) {
					case 50:
						alterando = false;
						break;
					default:
						try {
							try {
								Tabuleiro.colocaEmCampo(Tabuleiro.getCampo()
										.get(opcaoAddCartaCampo));
							} catch (ExcecaoCampoCheio e) {
								JOptionPane.showMessageDialog(null,
										"Campo cheio.");
							}
						} catch (ExcecaoCampoVazio e) {
							JOptionPane.showMessageDialog(null, "Campo vazio.");
						}
						break;
					}
				}
			case 5:
				boolean alterando2 = true;
				while (alterando2) {
					Integer opcaoRemCartaCampo = null;

					try {
						for (int i = 0; i < Tabuleiro.getCampo().size(); i++) {
							mao += " " + Tabuleiro.getCampo().get(i).getId();
							mao += " " + Tabuleiro.getCampo().get(i).getNome();
							mao += " "
									+ Tabuleiro.getCampo().get(i).getAtaque();
							mao += " "
									+ Tabuleiro.getCampo().get(i).getDefesa();
							mao += " "
									+ Tabuleiro.getCampo().get(i).getElemento();
							mao += "\n";
						}
					} catch (ExcecaoCampoVazio e) {
						JOptionPane.showMessageDialog(null, "Campo vazio.");
					}
					opcaoRemCartaCampo = Integer
							.parseInt(JOptionPane
									.showInputDialog("Digite a posicao da carta a ser removida: \n"
											+ "Digite 50 para sair." + mao));
					switch (opcaoRemCartaCampo) {
					case 50:
						alterando2 = false;
						break;
					default:
						try {
							Tabuleiro.retiraDoCampo(opcaoRemCartaCampo);
						} catch (ExcecaoCampoVazio e) {
							JOptionPane.showMessageDialog(null, "Campo vazio.");
						}
						break;
					}

				}
			case 6:
				noTabuleiro = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao invalida.");
				break;
			}
		}
	}
}
