package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioJogadores {
	
	private static List<Usuario> jogadores = new ArrayList<Usuario>();
//metodo de criar jogador
	//insere jogador numa lista de jogadores
	public static void criaJogador(String login, String senha) throws ExcecaoJogadorJaExiste{
		if (retornaIndiceJogador(login) != -1) {
			throw new ExcecaoJogadorJaExiste();
		} else {
			Usuario jogador = new Usuario(login, senha, jogadores.size());
			jogadores.add(jogador);
		}
	}

	private static Integer retornaIndiceJogador(String login) {
		int i;
		for (i = 0; i < jogadores.size(); i++) {
			if (jogadores.get(i).getLogin().equals(login)) {
				return i;
			}
		}
		return -1;
	}
	//metodo de fazer login
	public static Usuario entrar (String login, String senha) throws ExcecaoJogadorNaoExiste, ExcecaoSenhaErrada{
		int indice = retornaIndiceJogador(login);
		if (indice == -1)
			throw new ExcecaoJogadorNaoExiste();
		else 
			if (jogadores.get(indice).verificaSenha(senha))
				return jogadores.get(indice);
			else
				throw new ExcecaoSenhaErrada();
	}
	
	public static Usuario getJogador (Integer index) throws ExcecaoJogadorNaoExiste {
		if (index >= jogadores.size() || index < 0) {
			throw new ExcecaoJogadorNaoExiste();
		}
		return jogadores.get(index);
	}

}
