package iu;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Carta;
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
import modelo.Mao;
import modelo.RepositorioCartas;
import modelo.Tabuleiro;

public class Menu {
	public static void main(String[] args) {
		Connection bd = null;
		Tabuleiro tabuleiro;
		boolean logando = true;
		boolean dentro = true;
		boolean noTabuleiro = true;
		Jogador jogador = null;
		Mao maoJogador = null;
		String login;
		String senha;
		Integer opcao;
		bd = inicializaBancoDeDados(bd);
	      
		while (true) {
			dentro = true;
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
					Statement consulta = bd.createStatement();
					ResultSet retornoBD = consulta.executeQuery( "SELECT * FROM JOGADOR WHERE LOGIN = " + "'" + login + "';");
					retornoBD.next();
					jogador = new Jogador(retornoBD.getString("login"), retornoBD.getString("senha"), 0);
					logando = false;
					consulta.close();
					retornoBD.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"Login ou Senha Errados!");
				} 
				break;
			case 2:
				login = JOptionPane
						.showInputDialog("Digite o Login do jogador");
				senha = JOptionPane
						.showInputDialog("Digite a senha do jogador");
				try {
					Statement criacao = bd.createStatement();
					criacao.executeUpdate("INSERT INTO JOGADOR (LOGIN,SENHA)" + " VALUES ('" + login +"','"+ senha + "');");
					criacao.close();	
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
									+ "Digite 4 para sair."));
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
					if(texto.isEmpty()) {
						try {
							Statement consulta = bd.createStatement();
							ResultSet retornoBD = consulta.executeQuery( "SELECT * FROM CARTAS ORDER BY id;");
							while (retornoBD.next()) {
								texto += retornoBD.getString("id") + " " + retornoBD.getString("nome")
								+ " " + retornoBD.getInt("ataque") + " " + retornoBD.getInt("defesa") 
								+ " " + retornoBD.getString("elemento") +"\n";
							}
							consulta.close();
							retornoBD.close();
						} catch (SQLException e) {
							e.printStackTrace();
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
							Statement consulta = bd.createStatement();
							ResultSet retornoBD = consulta.executeQuery( "SELECT * FROM CARTAS WHERE ID = '" + opcao2 + "';");
							retornoBD.next();
								jogador.adicionaCartaNoBaralho(new Carta(retornoBD.getString("nome"),
										retornoBD.getInt("ataque"), retornoBD.getInt("defesa"),
										retornoBD.getInt("id"), retornoBD.getString("elemento")));
							JOptionPane.showMessageDialog(null,
									"Carta adicionada!");
							consulta.close();
							retornoBD.close();
						} catch (ExcecaoCartaNaoExiste e) {
							JOptionPane.showMessageDialog(null,
									"Carta não existe!");
						} catch (ExcecaoBaralhoCheio e) {
							JOptionPane.showMessageDialog(null,
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
				try {
					maoJogador = new Mao();
					if (jogador.getBaralho().baralhoEstaCompleto()) {
						jogador.getBaralho().embaralha();
						maoJogador.iniciaMao();
						JOptionPane.showMessageDialog(null, "Mao iniciada!");
						noTabuleiro = true;
					} else {
						JOptionPane.showMessageDialog(null,
								"Seu baralho esta incompleto.");
						break;
					}
				} catch (ExcecaoBaralhoVazio e1) {
					JOptionPane.showMessageDialog(null, "Baralho vazio.");
				}
				dentro = false;
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Sessão Finalizada.");
				dentro = false;
				logando = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Comando Invalido!");
				break;
			}
		}
		while (noTabuleiro) {
			String campo = "";
			String mao = "";
			tabuleiro = new Tabuleiro();
			opcao = Integer.parseInt(JOptionPane
					.showInputDialog("Digite 1 para ver sua mao;\n"
							+ "Digite 2 para ver seu campo;\n"
							+ "Digite 3 para inserir carta no seu campo;\n"
							+ "Digite 4 para retirar carta de campo;\n"
							+ "Digite 5 para sair."));
			switch (opcao) {
			case 1:
				try {
					for (int i = 0; i < maoJogador.getMao().size(); i++) {
						mao += " " + maoJogador.getMao().get(i).getId();
						mao += " " + maoJogador.getMao().get(i).getNome();
						mao += " " + maoJogador.getMao().get(i).getAtaque();
						mao += " " + maoJogador.getMao().get(i).getDefesa();
						mao += " " + maoJogador.getMao().get(i).getElemento();
						mao += "\n";
					}
					JOptionPane.showMessageDialog(null, mao);
				} catch (ExcecaoMaoVazia e) {
					JOptionPane.showMessageDialog(null, "Mao vazia.");
				}
				break;
			case 2:
				try {
					for (int i = 0; i < tabuleiro.getCampo().size(); i++) {
						campo += " " + tabuleiro.getCampo().get(i).getId();
						campo += " " + tabuleiro.getCampo().get(i).getNome();
						campo += " " + tabuleiro.getCampo().get(i).getAtaque();
						campo += " " + tabuleiro.getCampo().get(i).getDefesa();
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
				boolean alterando = true;
				while (alterando) {
					Integer opcaoAddCartaCampo = null;
					tabuleiro = new Tabuleiro();
					try {
						JOptionPane.showMessageDialog(null,
								maoJogador.mostrarMao(jogador));
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
							tabuleiro.colocaEmCampo(maoJogador
									.getCartaDaMao(opcaoAddCartaCampo));
							// tabuleiro.colocaEmCampo(jogador.getMao().get(opcaoAddCartaCampo));
							// jogador.getMao().remove(opcaoAddCartaCampo);
						} catch (ExcecaoCampoCheio e) {
							JOptionPane.showMessageDialog(null, "Campo cheio.");
						}
						break;
					}
				}
			case 4:
				boolean alterando2 = true;
				while (alterando2) {
					Integer opcaoRemCartaCampo = null;
					try {
						for (int i = 0; i < tabuleiro.getCampo().size(); i++) {
							mao += " " + tabuleiro.getCampo().get(i).getId();
							mao += " " + tabuleiro.getCampo().get(i).getNome();
							mao += " "
									+ tabuleiro.getCampo().get(i).getAtaque();
							mao += " "
									+ tabuleiro.getCampo().get(i).getDefesa();
							mao += " "
									+ tabuleiro.getCampo().get(i).getElemento();
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
			case 5:
				noTabuleiro = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao invalida.");
				break;
			}
		}
	}
}

	private static Connection inicializaBancoDeDados(Connection bd) {
		try {
	          Class.forName("org.postgresql.Driver");
	          bd = DriverManager
	             .getConnection("jdbc:postgresql://localhost:5432/cards&magic",
	             "postgres", "10203040");
	       } catch (Exception e) {
	          e.printStackTrace();
	          System.err.println(e.getClass().getName()+": "+e.getMessage());
	          System.exit(0);
	       }
		return bd;
	}
}
