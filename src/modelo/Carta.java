package modelo;

public class Carta {

	private String nome;
	private Integer ataque, defesa, id;
	private String elemento;

	public Carta(String nome, Integer ataque, Integer defesa, Integer id,
			String elemento) {
		this.nome = nome;
		this.ataque = ataque;
		this.defesa = defesa;
		this.elemento = elemento;
		this.id = id;
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

	public String getElemento() {
		return elemento;
	}

}
