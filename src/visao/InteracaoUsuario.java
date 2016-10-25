package visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import controle.Mapeador;
import modelo.ExcecaoCampoVazio;
import modelo.Tabuleiro;
import modelo.Usuario;

public class InteracaoUsuario {
	
	String cartas;

	public InteracaoUsuario() {
		cartas = null;
	}

	public String retornaString(String mensagem) {
		return JOptionPane.showInputDialog(mensagem);
	}

	public Integer retornaInt(String mensagem) {
		return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
	}

	public Double retornaDouble(String mensagem) {
		return Double.parseDouble(JOptionPane.showInputDialog(mensagem));
	}

	public char retornaCaractere(String mensagem) {
		return (JOptionPane.showInputDialog(mensagem)).charAt(0);
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	public Integer mostraCartasParaAdicionar(Mapeador mapeador) throws SQLException {
		if (cartas == null)
			cartas = mapeador.carregaCartas();
		return retornaInt("Digite o id da carta que deseja inserir: \n"
				+ "Ou 50 para sair. \n\n"+cartas);
	}

	public void mostraBaralho(Usuario usuario) {
		String baralhoUsuario = "";
		int i;
		for (i = 0; i < usuario.getBaralho().getBaralho().size(); i++) {
			baralhoUsuario += usuario.getBaralho().getBaralho().get(i).getId();
			baralhoUsuario += " " + usuario.getBaralho().getBaralho().get(i).getNome();
			baralhoUsuario += " " + usuario.getBaralho().getBaralho().get(i).getAtaque();
			baralhoUsuario += " " + usuario.getBaralho().getBaralho().get(i).getDefesa();
			baralhoUsuario += " " + usuario.getBaralho().getBaralho().get(i).getElemento();
			baralhoUsuario += "\n";
		}
		mostraMensagem(i + " Carta(s) no seu baralho \n\n" + baralhoUsuario);
	}

	public String mostraCampo(Tabuleiro tabuleiro) {
		String campo = "";
		try {
			for (int i = 0; i < tabuleiro.getCampo().size(); i++) {
				campo += i;
				campo += " " + tabuleiro.getCampo().get(i).getNome();
				campo += " " + tabuleiro.getCampo().get(i).getAtaque();
				campo += " " + tabuleiro.getCampo().get(i).getDefesa();
				campo += " " + tabuleiro.getCampo().get(i).getElemento();
				campo += "\n";
			}
		} catch (ExcecaoCampoVazio e) {
			return "Campo Vazio";
		}
		return campo;
	}

}
