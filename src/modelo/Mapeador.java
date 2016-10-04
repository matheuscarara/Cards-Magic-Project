package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mapeador {

	private Connection bd = null;

	public Mapeador() {
		inicializaBancoDeDados();
	}

	public Usuario carregarJogador(String login, String senha) {
		return null;

	}

	public void salvarJogador(String login, String senha) {

	}

	public void carregaBaralho(Usuario usuario) {

	}

	public void adicionarCartaBaralho(String login, Integer idCarta) {

	}

	public String carregaCartas() {
		return null;
	}

	private void inicializaBancoDeDados() {
		try {
			Class.forName("org.postgresql.Driver");
			bd = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/cards&magic", "postgres",
					"10203040");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}