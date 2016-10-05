package modelo;

public class Usuario {

	private String login, senha;
	private Baralho baralho;

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.baralho = new Baralho();
	}

	public void adicionaCartaNoBaralho(Carta carta) throws ExcecaoBaralhoCheio {
		getBaralho().adicionaCarta(carta);
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public String getLogin() {
		return login;
	}

	public Boolean verificaSenha(String senha) {
		return this.senha.equals(senha);
	}
}