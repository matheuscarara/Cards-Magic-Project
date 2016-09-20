package iu;

import javax.swing.JOptionPane;

import modelo.Carta;
import modelo.ExcecaoBaralhoCheio;
import modelo.ExcecaoBaralhoVazio;
import modelo.ExcecaoCartaNaoExiste;
import modelo.ExcecaoJogadorJaExiste;
import modelo.ExcecaoJogadorNaoExiste;
import modelo.ExcecaoSenhaErrada;
import modelo.ExcecaoCampoCheio;
import modelo.ExcecaoCampoVazio;
import modelo.Jogador;
import modelo.RepositorioCartas;
import modelo.RepositorioJogadores;
import modelo.Tabuleiro;

public class Menu {
	static final Integer QUANTIDADETOTALCARTAS = 42;

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
				break;
			default:
				JOptionPane.showMessageDialog(null, "Comando Invalido!");
				break;
			}
		}
		while (noTabuleiro) {
			String carta = "";
			Integer cartaID;
			Carta cartaSel = null;
			opcao = Integer.parseInt(JOptionPane
					.showInputDialog("Digite 1 para ver seu campo;\n"
							+ "Digite 2 para inserir carta no seu campo;\n"
							+ "Digite 3 para retirar carta de campo;\n"
							+ "Digite 4 para sair."));
			switch (opcao) {
			case 1:
				try {
					JOptionPane.showMessageDialog(null, Tabuleiro.getCampo());
				} catch (ExcecaoCampoVazio e) {
					JOptionPane.showMessageDialog(null, "Campo vazio");
				}
				break;
			case 2:
				Integer tamanhoMao = null;
				try {
					tamanhoMao = Jogador.iniciaMaoJogador();

				} catch (ExcecaoBaralhoVazio e) {
					JOptionPane
							.showMessageDialog(null, "Sem cartas no baralho");
				}
				carta += "Digite qual carta inserir no campo: \n";
				for (int i = 0; i < tamanhoMao; i++) {
					carta += " " + Jogador.getMao().get(i).getNome();
					carta += " " + Jogador.getMao().get(i).getAtaque();
					carta += " " + Jogador.getMao().get(i).getDefesa();
					carta += " " + Jogador.getMao().get(i).getElemento().name();
					carta += "\n";
				}
				// fazer execao aqui
				cartaID = Integer.parseInt(JOptionPane.showInputDialog("Didite o id da carta que você deseja colocar em campo."));
				try {
					cartaSel = Carta.getByID(cartaID);
				} catch (ExcecaoCartaNaoExiste e) {
					JOptionPane.showMessageDialog(null, "Carta nao existe.");
				}
				try {
					Tabuleiro.colocaEmCampo(cartaSel);
				} catch (ExcecaoCampoCheio e) {
					JOptionPane.showMessageDialog(null, "Mesa cheia");
				}
				break;
			case 3:
				break;
			default:
				break;
			}
		}
	}
}
