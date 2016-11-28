package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controle.Controle;

@SuppressWarnings("serial")
public class MenuPrincipal extends TelaJogo {

	public static final Integer XTELA = 400;
	public static final Integer YTITULO = 100;
	public static final Integer YBOTAO = 250;
	private Controle controle;

	public MenuPrincipal(Controle controle) {
		this.controle = controle;
		criaContentPane();
		configuraTela();
	}

	private void criaContentPane() {
		setContentPane(new JLabel(new ImageIcon("resources/telamenuprincipal.jpg")));
		add(botaoEditarBaralho());
		add(botaoEntrarTabuleiro());
		add(botaoDeslogar());
		add(etiquetaTitulo());
		add(etiquetaEditar());
		add(etiquetaEntrarTabuleiro());
		setLayout(null);
	}

	private JLabel etiquetaTitulo() {
		JLabel titulo = new JLabel("Bem Vindo " + controle.getNomeUsuario() + "!", SwingConstants.CENTER);
		titulo.setBounds(XTELA - 100, 80, 200, 15);
		titulo.setOpaque(Boolean.TRUE);
		return titulo;
	}

	private JLabel etiquetaEditar() {
		JLabel etiquetaEditar = new JLabel("Editar Baralho");
		etiquetaEditar.setBounds(XTELA - 250, YBOTAO - 30, 80, 15);
		etiquetaEditar.setOpaque(Boolean.TRUE);
		return etiquetaEditar;
	}

	private JLabel etiquetaEntrarTabuleiro() {
		JLabel etiquetaEntrarTabuleiro = new JLabel(" Teste suas habilidades");
		etiquetaEntrarTabuleiro.setBounds(XTELA + 120, YBOTAO - 30, 135, 15);
		etiquetaEntrarTabuleiro.setOpaque(Boolean.TRUE);
		return etiquetaEntrarTabuleiro;
	}

	private JButton botaoEntrarTabuleiro() {
		JButton botaoEntrarTabuleiro = new JButton("");
		botaoEntrarTabuleiro.setIcon(new ImageIcon("resources/duelar.jpg"));
		botaoEntrarTabuleiro.setBounds(XTELA + 20, YBOTAO, 310, 255);
		botaoEntrarTabuleiro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controle.iniciarTabuleiro();
			}
		});
		return botaoEntrarTabuleiro;
	}

	private JButton botaoDeslogar() {
		JButton botaoDeslogar = new JButton("Deslogar");
		botaoDeslogar.setBounds(XTELA + 280, 10, 100, 40);
		botaoDeslogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controle.deslogar();
			}
		});
		return botaoDeslogar;
	}
	
	private JButton botaoEditarBaralho() {
		JButton botaoEditarBaralho = new JButton("");
		botaoEditarBaralho.setIcon(new ImageIcon("resources/editar.jpg"));
		botaoEditarBaralho.setBounds(XTELA - 300, YBOTAO, 210, 290);
		botaoEditarBaralho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controle.editarBaralho();
				;
			}
		});
		return botaoEditarBaralho;
	}
}