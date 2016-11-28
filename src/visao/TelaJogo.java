package visao;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Carta;
import modelo.Comunicador;

@SuppressWarnings("serial")
public class TelaJogo extends JFrame implements Comunicador {

	@Override
	public void perdeu() {
	}

	@Override
	public void ganhou() {
	}

	@Override
	public void campoCheio() {
		JOptionPane.showMessageDialog(null, "Campo cheio");
	}

	@Override
	public void mostraCampo(String campo) {
	}

	@Override
	public List<Integer> selecionaCartas() {
		return null;
	}

	protected void configuraTela() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setResizable(Boolean.FALSE);
		setLocationRelativeTo(null);
		setVisible(Boolean.TRUE);
	}

	@Override
	public void configuraTelaCompra(List<Carta> campoBot, List<Carta> campoDuelista, List<Carta> maoDuelista,
			Integer vidaJogador, Integer vidaBot) {
	}

	@Override
	public void cartaColocaNoCampo() {
	}

	@Override
	public void configuraTelaBatalha(List<Carta> campoBot, List<Carta> campoDuelista, Integer pontosDeVida,
			Integer pontosDeVida2, Integer idCartaAtacando) {
	}

	@Override
	public void iniciaTelaBatalha() {
	}

}
