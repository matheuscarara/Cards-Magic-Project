package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Mapeador {

	private Connection bd = null;

	public Mapeador() {
		inicializaBancoDeDados();
	}

	public Usuario carregarJogador(String login, String senha) throws SQLException {
		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta
				.executeQuery("SELECT * FROM JOGADOR WHERE LOGIN = " + "'" + login + "'AND SENHA = '" + senha + "';");
		retornoBD.next();
		Usuario usuarioRetorno = new Usuario(retornoBD.getString("login"), retornoBD.getString("senha"));
		consulta.close();
		retornoBD.close();
		return usuarioRetorno;
	}

	public void salvarJogador(String login, String senha) throws SQLException {
		Statement criacao = bd.createStatement();
		criacao.executeUpdate("INSERT INTO JOGADOR (LOGIN,SENHA)" + " VALUES ('" + login + "','" + senha + "');");
		criacao.close();
	}

	public void carregaBaralho(Usuario usuario) throws SQLException, ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		Statement montabaralho = bd.createStatement();
		ResultSet retornoBaralho = montabaralho.executeQuery(
				"SELECT CARTAS.NOME, CARTAS.ATAQUE," + "CARTAS.DEFESA, CARTAS.ID, CARTAS.ELEMENTO FROM CARTAS, BARALHO"
						+ " WHERE CARTAS.ID = BARALHO.IDCARTA AND BARALHO.IDJOGADOR = '" + usuario.getLogin() + "';");
		while (retornoBaralho.next()) {
			usuario.adicionaCartaNoBaralho(new Carta(retornoBaralho.getString("nome"), retornoBaralho.getInt("ataque"),
					retornoBaralho.getInt("defesa"), retornoBaralho.getInt("id"),
					retornoBaralho.getString("elemento")));
		}
		montabaralho.close();
		retornoBaralho.close();
	}

	public void adicionarCartaBaralho(Usuario jogador, Integer idCarta)
			throws SQLException, ExcecaoCartaNaoExiste, ExcecaoBaralhoCheio {
		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta.executeQuery("SELECT * FROM CARTAS WHERE ID = '" + idCarta + "';");
		retornoBD.next();
		jogador.adicionaCartaNoBaralho	(new Carta(retornoBD.getString("nome"), retornoBD.getInt("ataque"),
				retornoBD.getInt("defesa"), retornoBD.getInt("id"), retornoBD.getString("elemento")));
		JOptionPane.showMessageDialog(null, "Carta adicionada!");
		consulta.close();
		retornoBD.close();
		Statement adicionado = bd.createStatement();
		adicionado.executeUpdate("INSERT INTO BARALHO (IDCARTA,IDJOGADOR)" + " VALUES ('" + idCarta + "','"
				+ jogador.getLogin() + "');");
		adicionado.close();
	}

	public String carregaCartas() throws SQLException {
		String textoRetorno = "";
		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta.executeQuery("SELECT * FROM CARTAS ORDER BY id;");
		while (retornoBD.next()) {
			textoRetorno += retornoBD.getString("id") + " " + retornoBD.getString("nome") + " "
					+ retornoBD.getInt("ataque") + " " + retornoBD.getInt("defesa") + " "
					+ retornoBD.getString("elemento") + "\n";
		}
		consulta.close();
		retornoBD.close();
		return textoRetorno;
	}

	private void inicializaBancoDeDados() {
		try {
			Class.forName("org.postgresql.Driver");
			setBd(DriverManager.getConnection("jdbc:postgresql://localhost:5432/cards&magic", "postgres", "10203040"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public Connection getBd() {
		return bd;
	}

	private void setBd(Connection bd) {
		this.bd = bd;
	}
}