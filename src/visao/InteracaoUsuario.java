package visao;

import javax.swing.JOptionPane;

public class InteracaoUsuario {

		
	
	public InteracaoUsuario() {

	}

	public String retornaString(String mensagem) {
		return JOptionPane.showInputDialog(mensagem);
	}

	public Integer retornaInt(String mensagem) {
		return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
	}

	public Boolean retornaBool(String mensagem) {
		return Boolean.parseBoolean(JOptionPane.showInputDialog(mensagem));
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

}
