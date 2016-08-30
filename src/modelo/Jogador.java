package modelo;

public class Jogador {

	private String login, senha;

	public Jogador(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
}
