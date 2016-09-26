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

	//metodo de adicionar carta no baralho atraves do metodo de adicionar carta do baralho
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
//metodo de verificacao de senha
	public Boolean verifica(String senha) {
		return this.senha.equals(senha);
	}
//metodo de inicializacao de pontos de vida do jogador
	public void inicializaPontosDeVida() {
		pontosDeVida = VIDAMAX;
	}
//metodo de causar dano (retirar pontos de vida)
	public void danoRecebido(Integer dano) {
		pontosDeVida -= dano;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}
//metodo que inicia a mao do jogador
	//pega as 6 cartas do topo do baralho e as move para uma lista mao do jogador
	public Integer iniciaMaoJogador() throws ExcecaoBaralhoVazio {
		for (int i = 0; i < TAMANHOMAO; i++) {
			mao.add(baralho.comprarCarta());
		}
		return mao.size();
	}

	public static List<Carta> getMao() throws ExcecaoMaoVazia {
		if (mao.size() == 0)
			throw new ExcecaoMaoVazia();
		return mao;
	}
//metodo de atacar
	public void atacar(Jogador alvo, Integer dano) {
		alvo.danoRecebido(dano);
	}

}
