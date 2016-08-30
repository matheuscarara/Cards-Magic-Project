package modelo;

public class Carta {

	private String nome;
	private int ataque, defesa, id;
	private Elemento elemento;

	public Carta(String nome, int ataque, int defesa, Elemento elemento, int id) {
		this.nome = nome;
		this.ataque = ataque;
		this.defesa = defesa;
		this.elemento = elemento;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefesa() {
		return defesa;
	}

	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
