package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controle.Controle;

@SuppressWarnings("serial")
public class TelaEditarBaralho extends TelaJogo {
	
	public static final Integer XTELA = 400;
	
	private Controle controle;
	private JLabel baralho;
	private JLabel cartas;
	private JTextField campoIdCarta;

	public TelaEditarBaralho(Controle controle) {
		this.controle = controle;
		criaContentPane();
		configuraTela();
	}

	private void criaContentPane() {
		add(etiquetaTitulo());
		add(criaCampoIdCarta());
		add(botaoAdicionar());
		add(botaoVoltar());
		add(etiquetaCartas());
		add(etiquetaBaralho());
		setLayout(null);
	}

	private JLabel etiquetaBaralho() {
		baralho = new JLabel(controle.verBaralho());
		baralho.setBounds(10, 2, 250, 570);
		return baralho;
	}

	private JLabel etiquetaCartas() {
		cartas = new JLabel("<html> Cartas Disponíveis: <br>" + controle.verCartas() + "</html>", SwingConstants.CENTER);
		cartas.setBounds(XTELA - 100, 2, 250, 570);
		return cartas;
	}

	private JButton botaoVoltar() {
		JButton botaoVoltar = new JButton("Voltar ao Menu Principal");
		botaoVoltar.setBounds(XTELA + 190, 300 , 180, 30);
		botaoVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controle.voltaAoMenuPrincipal();
			}
			
		});
		return botaoVoltar;
	}

	private JButton botaoAdicionar() {
		JButton botaoAdicionar = new JButton("Adicionar Carta");
		botaoAdicionar.setBounds(XTELA + 190, 200 , 180, 30);
		botaoAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controle.adicionaCartaNoBaralho(Integer.parseInt(campoIdCarta.getText()));
			}
			
		});
		return botaoAdicionar;
	}
	
	public void atualizaBaralho() {
		remove(baralho);
		add(etiquetaBaralho());
		repaint();
	}

	private JTextField criaCampoIdCarta() {
		campoIdCarta = new JTextField();
		campoIdCarta.setBounds(XTELA + 235, 150, 80, 20);
		return campoIdCarta;
	}

	private JLabel etiquetaTitulo() {
		JLabel etiquetaTitulo = new JLabel("Adicione Cartas no Baralho", SwingConstants.CENTER);
		etiquetaTitulo.setBounds(XTELA + 190, 110, 180, 15);
		return etiquetaTitulo;
	}
	
	

}
