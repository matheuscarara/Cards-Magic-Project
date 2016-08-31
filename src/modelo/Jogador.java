package modelo;


public class Jogador {
	private static final Integer VIDAMAX = 1000;
	private String login, senha;
	private Baralho baralho;
	private Integer pontosDeVida, id;
	
	public Jogador(String login, String senha, Integer id) {
		this.login = login;
		this.senha = senha;
		this.id = id;
		baralho = new Baralho();
	}
	
	public void adicionaCartaNoBaralho(Integer id) throws ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		baralho.adicionaCarta(RepositorioCartas.getCarta(id));
	}

	public String getLogin() {
		return login;
	}
	
	public Integer getId() {
		return id;
	}

	public Boolean verifica(String senha) {
		return this.senha.equals(senha);
	}
	
	public void inicializaPontosDeVida () {
		pontosDeVida = VIDAMAX;
	}
	
	public void danoRecebido(Integer dano) {
		pontosDeVida -= dano;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}
}
