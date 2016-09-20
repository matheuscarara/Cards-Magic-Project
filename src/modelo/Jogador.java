package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	private static final Integer VIDAMAX = 1000;
	private static final Integer TAMANHOMAO = 7;
	private String login, senha;
	private static Baralho baralho;
	private Integer pontosDeVida, id;
	private static List<Carta> mao;

	public Jogador(String login, String senha, Integer id) {
		this.login = login;
		this.senha = senha;
		this.id = id;
		Jogador.baralho = new Baralho();
		Jogador.mao = new ArrayList<Carta>();
	}

	public void adicionaCartaNoBaralho(Integer id)
			throws ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		baralho.adicionaCarta(RepositorioCartas.getCarta(id));
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

	public Boolean verifica(String senha) {
		return this.senha.equals(senha);
	}

	public void inicializaPontosDeVida() {
		pontosDeVida = VIDAMAX;
	}

	public void danoRecebido(Integer dano) {
		pontosDeVida -= dano;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}

	public static Integer iniciaMaoJogador() throws ExcecaoBaralhoVazio {
		for (int i = 0; i < TAMANHOMAO; i++) {
			mao.add(baralho.comprarCarta());
		}
		return mao.size();
	}

	public static List<Carta> getMao() {
		return mao;
	}

	public void atacar(Jogador alvo, Integer dano) {
		alvo.danoRecebido(dano);
	}

}
