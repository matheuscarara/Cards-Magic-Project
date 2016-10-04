package modelo;

public class Usuario {

	private String login, senha;
	private Baralho baralho;
	private Integer id;

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.baralho = new Baralho();
	}

	// metodo de adicionar carta no baralho atraves do metodo de adicionar carta
	// do baralho
	public void adicionaCartaNoBaralho(Carta carta)
			throws ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		getBaralho().adicionaCarta(carta);
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public String getLogin() {
		return login;
	}


	// metodo de verificacao de senha
	public Boolean verificaSenha(String senha) {
		return this.senha.equals(senha);
	}
}