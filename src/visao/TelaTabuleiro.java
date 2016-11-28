package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controle.Controle;
import modelo.Carta;

@SuppressWarnings("serial")
public class TelaTabuleiro extends TelaJogo {

	public static final Integer XTELA = 400;

	private Controle controle;
	private JButton passaVez;
	private JButton maoDuelista1;
	private JButton maoDuelista2;
	private JButton maoDuelista3;
	private JButton maoDuelista4;
	private JButton maoDuelista5;
	private JButton maoDuelista6;
	private JButton campoDuelista1;
	private JButton campoDuelista2;
	private JButton campoDuelista3;
	private JButton campoDuelista4;
	private JButton campoDuelista5;
	private JButton campoBot1;
	private JButton campoBot2;
	private JButton campoBot3;
	private JButton campoBot4;
	private JButton campoBot5;
	private JLabel vidaJogador;
	private JLabel vidaBot;
	private Integer cartaAtacando;
	private List<Carta> jaAtacaram;

	public TelaTabuleiro(Controle controle) {
		this.controle = controle;
		jaAtacaram = new ArrayList<Carta>();
		configuraLocalBotoes();
		configuraJLabels();
		criaContentPane();
		configuraTela();
	}

	private void criaContentPane() {
		add(maoDuelista1);
		add(maoDuelista2);
		add(maoDuelista3);
		add(maoDuelista4);
		add(maoDuelista5);
		add(maoDuelista6);
		add(campoDuelista1);
		add(campoDuelista2);
		add(campoDuelista3);
		add(campoDuelista4);
		add(campoDuelista5);
		add(campoBot1);
		add(campoBot2);
		add(campoBot3);
		add(campoBot4);
		add(campoBot5);
		add(passaVez);
		add(vidaJogador);
		add(vidaBot);
		setLayout(null);
	}

	private void desabilitaBotoes() {
		remove(maoDuelista1);
		remove(maoDuelista2);
		remove(maoDuelista3);
		remove(maoDuelista4);
		remove(maoDuelista5);
		remove(maoDuelista6);
		remove(campoDuelista1);
		remove(campoDuelista2);
		remove(campoDuelista3);
		remove(campoDuelista4);
		remove(campoDuelista5);
		remove(campoBot1);
		remove(campoBot2);
		remove(campoBot3);
		remove(campoBot4);
		remove(campoBot5);
		maoDuelista1.setEnabled(Boolean.FALSE);
		maoDuelista2.setEnabled(Boolean.FALSE);
		maoDuelista3.setEnabled(Boolean.FALSE);
		maoDuelista4.setEnabled(Boolean.FALSE);
		maoDuelista5.setEnabled(Boolean.FALSE);
		maoDuelista6.setEnabled(Boolean.FALSE);
		campoDuelista1.setEnabled(Boolean.FALSE);
		campoDuelista2.setEnabled(Boolean.FALSE);
		campoDuelista3.setEnabled(Boolean.FALSE);
		campoDuelista4.setEnabled(Boolean.FALSE);
		campoDuelista5.setEnabled(Boolean.FALSE);
		campoBot1.setEnabled(Boolean.FALSE);
		campoBot2.setEnabled(Boolean.FALSE);
		campoBot3.setEnabled(Boolean.FALSE);
		campoBot4.setEnabled(Boolean.FALSE);
		campoBot5.setEnabled(Boolean.FALSE);
		passaVez.setEnabled(Boolean.FALSE);
		add(maoDuelista1);
		add(maoDuelista2);
		add(maoDuelista3);
		add(maoDuelista4);
		add(maoDuelista5);
		add(maoDuelista6);
		add(campoDuelista1);
		add(campoDuelista2);
		add(campoDuelista3);
		add(campoDuelista4);
		add(campoDuelista5);
		add(campoBot1);
		add(campoBot2);
		add(campoBot3);
		add(campoBot4);
		add(campoBot5);
	}

	public void configuraTelaCompra(List<Carta> campoBot, List<Carta> campoDuelista, List<Carta> maoDuelista,
			Integer vidaJogador, Integer vidaBot) {
		//desabilitaBotoes();
		atualizaImagens(campoBot, campoDuelista, maoDuelista, vidaJogador, vidaBot);
		repaint();
	}

	public void cartaColocaNoCampo() {
		remove(maoDuelista1);
		remove(maoDuelista2);
		remove(maoDuelista3);
		remove(maoDuelista4);
		remove(maoDuelista5);
		remove(maoDuelista6);
		maoDuelista1 = habilitaBotaoMao(maoDuelista1, 0);
		maoDuelista2 = habilitaBotaoMao(maoDuelista2, 1);
		maoDuelista3 = habilitaBotaoMao(maoDuelista3, 2);
		maoDuelista4 = habilitaBotaoMao(maoDuelista4, 3);
		maoDuelista5 = habilitaBotaoMao(maoDuelista5, 4);
		maoDuelista6 = habilitaBotaoMao(maoDuelista6, 5);
		add(maoDuelista1);
		add(maoDuelista2);
		add(maoDuelista3);
		add(maoDuelista4);
		add(maoDuelista5);
		add(maoDuelista6);

	}

	public void configuraTelaBatalha(List<Carta> campoBot, List<Carta> campoDuelista, Integer vidaJogador,
			Integer vidaBot, Integer idCartaAtacando) {
		atualizaImagensDeBatalha(campoBot, campoDuelista, vidaJogador, vidaBot, idCartaAtacando);
		repaint();
	}

	private void atualizaImagensDeBatalha(List<Carta> campoBot, List<Carta> campoDuelista, Integer vidaJogador2,
			Integer vidaBot2, Integer idCartaAtacando) {
		if (idCartaAtacando != 99)
			jaAtacaram.add(campoDuelista.get(idCartaAtacando));
		remove(campoDuelista1);
		remove(campoDuelista2);
		remove(campoDuelista3);
		remove(campoDuelista4);
		remove(campoDuelista5);
		remove(campoBot1);
		remove(campoBot2);
		remove(campoBot3);
		remove(campoBot4);
		remove(campoBot5);
		remove(vidaJogador);
		remove(vidaBot);
		for (int i = 0; i < 5; i++) {
			if (i >= campoDuelista.size()) {
				switch (i) {
				case (0):
					campoDuelista1.setIcon(null);
					break;
				case (1):
					campoDuelista2.setIcon(null);
					break;
				case (2):
					campoDuelista3.setIcon(null);
					break;
				case (3):
					campoDuelista4.setIcon(null);
					break;
				case (4):
					campoDuelista5.setIcon(null);
					break;
				default:
					break;
				}
			} else {
				ImageIcon icon = new ImageIcon(campoDuelista.get(i).getCaminhoImagem());
				switch (i) {
				case (0):
					campoDuelista1.setIcon(icon);
					break;
				case (1):
					campoDuelista2.setIcon(icon);
					break;
				case (2):
					campoDuelista3.setIcon(icon);
					break;
				case (3):
					campoDuelista4.setIcon(icon);
					break;
				case (4):
					campoDuelista5.setIcon(icon);
					break;
				default:
					break;
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			if (i >= campoBot.size()) {
				switch (i) {
				case (0):
					campoBot1.setIcon(null);
					break;
				case (1):
					campoBot2.setIcon(null);
					break;
				case (2):
					campoBot3.setIcon(null);
					break;
				case (3):
					campoBot4.setIcon(null);
					break;
				case (4):
					campoBot5.setIcon(null);
					break;
				default:
					break;
				}
			} else {
				ImageIcon icon = new ImageIcon(campoBot.get(i).getCaminhoImagem());
				switch (i) {
				case (0):
					campoBot1.setIcon(icon);
					break;
				case (1):
					campoBot2.setIcon(icon);
					break;
				case (2):
					campoBot3.setIcon(icon);
					break;
				case (3):
					campoBot4.setIcon(icon);
					break;
				case (4):
					campoBot5.setIcon(icon);
					break;
				default:
					break;
				}
			}
		}

		for (int i = 0; i < jaAtacaram.size(); i++) {
			switch (campoDuelista.indexOf(jaAtacaram.get(i))) {
			case (0):
				campoDuelista1.setEnabled(Boolean.FALSE);
				break;
			case (1):
				campoDuelista2.setEnabled(Boolean.FALSE);
				break;
			case (2):
				campoDuelista3.setEnabled(Boolean.FALSE);
				break;
			case (3):
				campoDuelista4.setEnabled(Boolean.FALSE);
				break;
			case (4):
				campoDuelista5.setEnabled(Boolean.FALSE);
				break;
			default:
				break;
			}
		}

		switch (idCartaAtacando) {
		case (0):
			campoDuelista1.setEnabled(Boolean.FALSE);
			break;
		case (1):
			campoDuelista2.setEnabled(Boolean.FALSE);
			break;
		case (2):
			campoDuelista3.setEnabled(Boolean.FALSE);
			break;
		case (3):
			campoDuelista4.setEnabled(Boolean.FALSE);
			break;
		case (4):
			campoDuelista5.setEnabled(Boolean.FALSE);
			break;
		default:
			break;
		}
		add(campoDuelista1);
		add(campoDuelista2);
		add(campoDuelista3);
		add(campoDuelista4);
		add(campoDuelista5);
		add(campoBot1);
		add(campoBot2);
		add(campoBot3);
		add(campoBot4);
		add(campoBot5);
		vidaJogador.setText("Sua vida: " + vidaJogador2);
		vidaBot.setText("Vida do Bot: " + vidaBot2);
		add(vidaJogador);
		add(vidaBot);
	}

	public void iniciaTelaBatalha() {
		habilitaBotoesBatalha(campoDuelista1, 1);
		habilitaBotoesBatalha(campoDuelista2, 2);
		habilitaBotoesBatalha(campoDuelista3, 3);
		habilitaBotoesBatalha(campoDuelista4, 4);
		habilitaBotoesBatalha(campoDuelista5, 5);
		remove(passaVez);
		passaVez.setEnabled(Boolean.TRUE);
		passaVez.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jaAtacaram.clear();
				controle.ataca(null, null);
			}
		});
		add(passaVez);
		repaint();
	}

	private void habilitaBotoesBatalha(JButton botao, Integer idCartaAtacando) {
		if (botao.getIcon() != null) {
			remove(botao);
			botao.setEnabled(Boolean.TRUE);
			botao.addActionListener(new ActionListener() {
				private Integer _id = idCartaAtacando;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//desabilitaCampoDuelista();
					if (campoBot1.getIcon() == null && campoBot2.getIcon() == null && campoBot3.getIcon() == null
							&& campoBot4.getIcon() == null && campoBot5.getIcon() == null) {
						controle.ataca(_id, null);
					} else {
						habilitaBotoesBot();
						cartaAtacando = _id;
					}
				}
			});
			add(botao);
		}
	}

	private void habilitaBotoesBot() {
		botoesBotBatalha(campoBot1, 0);
		botoesBotBatalha(campoBot2, 1);
		botoesBotBatalha(campoBot3, 2);
		botoesBotBatalha(campoBot4, 3);
		botoesBotBatalha(campoBot5, 4);

	}

	private void botoesBotBatalha(JButton botao, Integer idCartaDefendendo) {
		if (botao.getIcon() != null) {
			remove(botao);
			botao.setEnabled(Boolean.TRUE);
			botao.addActionListener(new ActionListener() {
				private Integer _id = idCartaDefendendo;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//desabilitaCampoBot();
					controle.ataca(cartaAtacando, _id);

				}

			});
			add(botao);
		}
	}

	protected void desabilitaCampoBot() {
		remove(campoBot1);
		remove(campoBot2);
		remove(campoBot3);
		remove(campoBot4);
		remove(campoBot5);
		campoBot1.setEnabled(Boolean.FALSE);
		campoBot2.setEnabled(Boolean.FALSE);
		campoBot3.setEnabled(Boolean.FALSE);
		campoBot4.setEnabled(Boolean.FALSE);
		campoBot5.setEnabled(Boolean.FALSE);
		add(campoBot1);
		add(campoBot2);
		add(campoBot3);
		add(campoBot4);
		add(campoBot5);

	}

	private void desabilitaCampoDuelista() {
		remove(campoDuelista1);
		remove(campoDuelista2);
		remove(campoDuelista3);
		remove(campoDuelista4);
		remove(campoDuelista5);
		campoDuelista1.setEnabled(Boolean.FALSE);
		campoDuelista2.setEnabled(Boolean.FALSE);
		campoDuelista3.setEnabled(Boolean.FALSE);
		campoDuelista4.setEnabled(Boolean.FALSE);
		campoDuelista5.setEnabled(Boolean.FALSE);
		add(campoDuelista1);
		add(campoDuelista2);
		add(campoDuelista3);
		add(campoDuelista4);
		add(campoDuelista5);
	}

	private JButton habilitaBotaoMao(JButton botao, Integer id) {
		if (botao.getIcon() != null) {
			botao.setEnabled(Boolean.TRUE);
			botao.addActionListener(new ActionListener() {
				private Integer _id = id;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controle.adicionaCartaEmCampo(_id);
					//desabilitaBotoes();
				}
			});
		}
		return botao;
	}

	private void atualizaImagens(List<Carta> campoBot, List<Carta> campoDuelista, List<Carta> maoDuelista,
			Integer vidaJogador2, Integer vidaBot2) {
		remove(maoDuelista1);
		remove(maoDuelista2);
		remove(maoDuelista3);
		remove(maoDuelista4);
		remove(maoDuelista5);
		remove(maoDuelista6);
		remove(campoDuelista1);
		remove(campoDuelista2);
		remove(campoDuelista3);
		remove(campoDuelista4);
		remove(campoDuelista5);
		remove(campoBot1);
		remove(campoBot2);
		remove(campoBot3);
		remove(campoBot4);
		remove(campoBot5);
		remove(vidaJogador);
		remove(vidaBot);
		for (int i = 0; i < 6; i++) {
			if (i >= maoDuelista.size()) {
				switch (i) {
				case (0):
					maoDuelista1.setIcon(null);
					break;
				case (1):
					maoDuelista2.setIcon(null);
					break;
				case (2):
					maoDuelista3.setIcon(null);
					break;
				case (3):
					maoDuelista4.setIcon(null);
					break;
				case (4):
					maoDuelista5.setIcon(null);
					break;
				case (5):
					maoDuelista6.setIcon(null);
					break;
				default:
					break;
				}
			} else {
				ImageIcon icon = new ImageIcon(maoDuelista.get(i).getCaminhoImagem());
				switch (i) {
				case (0):
					maoDuelista1.setIcon(icon);
					break;
				case (1):
					maoDuelista2.setIcon(icon);
					break;
				case (2):
					maoDuelista3.setIcon(icon);
					break;
				case (3):
					maoDuelista4.setIcon(icon);
					break;
				case (4):
					maoDuelista5.setIcon(icon);
					break;
				case (5):
					maoDuelista6.setIcon(icon);
					break;
				default:
					break;
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			if (i >= campoDuelista.size()) {
				switch (i) {
				case (0):
					campoDuelista1.setIcon(null);
					break;
				case (1):
					campoDuelista2.setIcon(null);
					break;
				case (2):
					campoDuelista3.setIcon(null);
					break;
				case (3):
					campoDuelista4.setIcon(null);
					break;
				case (4):
					campoDuelista5.setIcon(null);
					break;
				default:
					break;
				}
			} else {
				ImageIcon icon = new ImageIcon(campoDuelista.get(i).getCaminhoImagem());
				switch (i) {
				case (0):
					campoDuelista1.setIcon(icon);
					break;
				case (1):
					campoDuelista2.setIcon(icon);
					break;
				case (2):
					campoDuelista3.setIcon(icon);
					break;
				case (3):
					campoDuelista4.setIcon(icon);
					break;
				case (4):
					campoDuelista5.setIcon(icon);
					break;
				default:
					break;
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			if (i >= campoBot.size()) {
				switch (i) {
				case (0):
					campoBot1.setIcon(null);
					break;
				case (1):
					campoBot2.setIcon(null);
					break;
				case (2):
					campoBot3.setIcon(null);
					break;
				case (3):
					campoBot4.setIcon(null);
					break;
				case (4):
					campoBot5.setIcon(null);
					break;
				default:
					break;
				}
			} else {
				ImageIcon icon = new ImageIcon(campoBot.get(i).getCaminhoImagem());
				switch (i) {
				case (0):
					campoBot1.setIcon(icon);
					break;
				case (1):
					campoBot2.setIcon(icon);
					break;
				case (2):
					campoBot3.setIcon(icon);
					break;
				case (3):
					campoBot4.setIcon(icon);
					break;
				case (4):
					campoBot5.setIcon(icon);
					break;
				default:
					break;
				}
			}
		}
		add(maoDuelista1);
		add(maoDuelista2);
		add(maoDuelista3);
		add(maoDuelista4);
		add(maoDuelista5);
		add(maoDuelista6);
		add(campoDuelista1);
		add(campoDuelista2);
		add(campoDuelista3);
		add(campoDuelista4);
		add(campoDuelista5);
		add(campoBot1);
		add(campoBot2);
		add(campoBot3);
		add(campoBot4);
		add(campoBot5);
		vidaJogador.setText("Sua vida: " + vidaJogador2);
		vidaBot.setText("Vida do Bot: " + vidaBot2);
		add(vidaJogador);
		add(vidaBot);
	}

	private void configuraLocalBotoes() {
		maoDuelista1 = configuraBotao(maoDuelista1, 30, 450);
		maoDuelista2 = configuraBotao(maoDuelista2, 140, 450);
		maoDuelista3 = configuraBotao(maoDuelista3, 250, 450);
		maoDuelista4 = configuraBotao(maoDuelista4, 360, 450);
		maoDuelista5 = configuraBotao(maoDuelista5, 470, 450);
		maoDuelista6 = configuraBotao(maoDuelista6, 580, 450);
		campoDuelista1 = configuraBotao(campoDuelista1, 30, 270);
		campoDuelista2 = configuraBotao(campoDuelista2, 140, 270);
		campoDuelista3 = configuraBotao(campoDuelista3, 250, 270);
		campoDuelista4 = configuraBotao(campoDuelista4, 360, 270);
		campoDuelista5 = configuraBotao(campoDuelista5, 470, 270);
		campoBot1 = configuraBotao(campoBot1, 30, 60);
		campoBot2 = configuraBotao(campoBot2, 140, 60);
		campoBot3 = configuraBotao(campoBot3, 250, 60);
		campoBot4 = configuraBotao(campoBot4, 360, 60);
		campoBot5 = configuraBotao(campoBot5, 470, 60);
		passaVez = new JButton("Passar Vez");
		passaVez.setBounds(690, 450, 100, 120);
	}

	private JButton configuraBotao(JButton botao, Integer x, Integer y) {
		botao = new JButton();
		botao.setBounds(x, y, 100, 120);
		return botao;
	}

	private void configuraJLabels() {
		vidaJogador = new JLabel();
		vidaJogador.setBounds(670, 180, 110, 15);
		vidaBot = new JLabel();
		vidaBot.setBounds(670, 230, 110, 15);
	}

	public void perdeu() {
		JOptionPane.showConfirmDialog(null, "Você perdeu.");
		controle.finalizaDuelo();
	}

	public void ganhou() {
		JOptionPane.showConfirmDialog(null, "Você ganhou!");
		controle.finalizaDuelo();
	}
}
