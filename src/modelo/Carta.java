package modelo;

public class Carta {

	private String nome;
	private Integer ataque, defesa, id;
	private Elemento elemento;

	public Carta(String nome, Integer ataque, Integer defesa,
			Elemento elemento, Integer id) {
		this.nome = nome;
		this.id = id;
		this.ataque = ataque;
		this.defesa = defesa;
		this.elemento = elemento;
	}

	public String getNome() {
		return nome;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public Integer getId() {
		return id;
	}

	public Elemento getElemento() {
		return elemento;
	}

}