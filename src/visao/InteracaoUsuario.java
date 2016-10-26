package visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Comunicador;

public class InteracaoUsuario implements Comunicador {

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

	public Integer mostraCartasParaAdicionar(String cartas) throws SQLException {
		return retornaInt("Digite o id da carta que deseja inserir: \n"
				+ "Ou 50 para sair. \n\n" + cartas);
	}

	@Override
	public Integer indiceCartaDaMaoParaColocaNoCampo(String campo, String mao) {
		return retornaInt("Digite a carta a ser inserida: \n\n" + mao + "\n\n"
				+ campo + "\n\n" + "Digite qualquer outro valor para sair.");
	}

	@Override
	public void perdeu() {
		mostraMensagem("Voc� Perdeu!");
	}

	@Override
	public void ganhou() {
		mostraMensagem("Parab�ns!! Voc� Ganhou!!");
	}

	@Override
	public void campoCheio() {
		mostraMensagem("Campo cheio, imposs�vel adicionar mais alguma carta.");
	}

	@Override
	public void indiceInvalido() {
		mostraMensagem("Indice Inv�lido");
	}

	@Override
	public void mostraCampo(String campo) {
		mostraMensagem(campo);
	}

}
