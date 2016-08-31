package iu;

import javax.swing.JOptionPane;
import modelo.ExcecaoBaralhoCheio;
import modelo.ExcecaoCartaNaoExiste;
import modelo.ExcecaoJogadorJaExiste;
import modelo.ExcecaoJogadorNaoExiste;
import modelo.ExcecaoSenhaErrada;
import modelo.Jogador;
import modelo.RepositorioCartas;
import modelo.RepositorioJogadores;

public class Menu {
	static final Integer QUANTIDADETOTALCARTAS = 42;
	public static void main(String[] args) {
		boolean logando = true;
		boolean dentro = true;
		Jogador jogador = null;
		String login;
		String senha;
		Integer opcao;
		RepositorioCartas.geraCartas();

		while (logando) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para realizar um login ou 2 para criar um jogador"));
			switch (opcao) {
				case 1:
					login = JOptionPane.showInputDialog("Digite o Login do jogador");
					senha = JOptionPane.showInputDialog("Digite a senha do jogador");
					try {
						jogador = RepositorioJogadores.entrar(login, senha);
						logando = false;
					} catch (ExcecaoJogadorNaoExiste | ExcecaoSenhaErrada e) {
						JOptionPane.showMessageDialog(null, "Login ou Senha Errados!");
					}
					break;
				case 2:
					login = JOptionPane.showInputDialog("Digite o Login do jogador");
					senha = JOptionPane.showInputDialog("Digite a Senha do jogador");
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
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 ver seu baralho; 2 para adicionar cartas no seu baralho; 3 para sair"));
			String texto = "";
			switch (opcao) {
				case 1:
					if (jogador.getBaralho().getCartas().isEmpty()) {
						texto = "Seu Baralho não possui cartas";
					} else {
						for (int i = 0; i < jogador.getBaralho().getCartas().size(); i++) {
							texto += jogador.getBaralho().getCartas().get(i).getId();
							texto += " " + jogador.getBaralho().getCartas().get(i).getNome();
							texto += " " + jogador.getBaralho().getCartas().get(i).getAtaque();
							texto += " " + jogador.getBaralho().getCartas().get(i).getDefesa();
							texto += " " + jogador.getBaralho().getCartas().get(i).getElemento().name();
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
								texto += " " + RepositorioCartas.getCarta(i).getNome();
								texto += " " + RepositorioCartas.getCarta(i).getAtaque();
								texto += " " + RepositorioCartas.getCarta(i).getDefesa();
								texto += " " + RepositorioCartas.getCarta(i).getElemento().name();
								texto += "\n";
							} catch (ExcecaoCartaNaoExiste e) {
							}
						}
						Integer opcao2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o Id da carta a ser inserida no baralho ou 50 para sair: \n" + texto));
						switch (opcao2){
							case 50:
								alterando = false;
							break;
							default:
							try {
								jogador.adicionaCartaNoBaralho(opcao2);
								JOptionPane.showMessageDialog(null, "Carta adicionada!");
							} catch (ExcecaoCartaNaoExiste e) {
								JOptionPane.showMessageDialog(null, "Carta não existe!");
							} catch (ExcecaoBaralhoCheio e) {
								JOptionPane.showMessageDialog(null, "Baralho atingiu número limite de cartas.");
							}
							break;
						}
					}
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Sessão Finalizada.");
					dentro = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Comando Invalido!");
					break;
			}
		}
	}

}
