package modelo;

public class Jogador {
	private static final Integer VIDAMAX = 1000;
	private String login, senha;
	private static Baralho baralho;
	private Integer pontosDeVida, id;

	public Jogador(String login, String senha, Integer id) {
		this.login = login;
		this.senha = senha;
		this.id = id;
		this.baralho = new Baralho();
	}

	// metodo de adicionar carta no baralho atraves do metodo de adicionar carta
	// do baralho
	public void adicionaCartaNoBaralho(Carta carta)
			throws ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		getBaralho().adicionaCarta(carta);
	}

	public static Baralho getBaralho() {
		return baralho;
	}

	public String getLogin() {
		return login;
	}

	public Integer getId() {
		return id;
	}

	// metodo de verificacao de senha
	public Boolean verifica(String senha) {
		return this.senha.equals(senha);
	}

	// metodo de inicializacao de pontos de vida do jogador
	public void inicializaPontosDeVida() {
		pontosDeVida = VIDAMAX;
	}

	// metodo de causar dano (retirar pontos de vida)
	public void danoRecebido(Integer dano) {
		pontosDeVida -= dano;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}

	// metodo de atacar
	public void atacar(Jogador alvo, Integer dano) {
		alvo.danoRecebido(dano);
	}

}
