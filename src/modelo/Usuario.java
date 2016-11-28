package modelo;

import modelo.excecoes.ExcecaoBaralhoCheio;

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

	public String getSenha() {
		return senha;
	}

	public String mostraBaralho() {
		String baralhoUsuario = "";
		int i;
		for (i = 0; i < getBaralho().getBaralho().size(); i++) {
			baralhoUsuario += getBaralho().getBaralho().get(i).getId();
			baralhoUsuario += " " + getBaralho().getBaralho().get(i).getNome();
			baralhoUsuario += " "
					+ getBaralho().getBaralho().get(i).getAtaque();
			baralhoUsuario += " "
					+ getBaralho().getBaralho().get(i).getDefesa();
			baralhoUsuario += " "
					+ getBaralho().getBaralho().get(i).getElemento();
			baralhoUsuario += "<br/>";
		}
		return "<html>" + i + " Carta(s) no seu baralho <br> <br>" + baralhoUsuario + "</html>";
	}
}