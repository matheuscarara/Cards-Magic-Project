package modelo;

public class Usuario {

	private String login, senha;
	private Baralho baralho;
	private Integer id;

	public Usuario(String login, String senha, Integer id) {
		this.login = login;
		this.senha = senha;
		this.id = id;
		this.baralho = new Baralho();
	}

	// metodo de adicionar carta no baralho atraves do metodo de adicionar carta
	// do baralho
	public void adicionaCartaNoBaralho(Integer id)
			throws ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		getBaralho().adicionaCarta(RepositorioCartas.getCarta(id));
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public String getLogin() {
		return login;
	}

	public Integer getId() {
		return id;
	}

	// metodo de verificacao de senha
	public Boolean verificaSenha(String senha) {
		return this.senha.equals(senha);
	}
}