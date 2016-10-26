package modelo.mapeadores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Baralho;
import modelo.Carta;
import modelo.Usuario;
import modelo.excecoes.ExcecaoBaralhoCheio;

public class MapeadorBaralho {

	private Connection bd;

	public MapeadorBaralho(Connection bd) {
		this.bd = bd;
	}

	public void carregaBaralho(Usuario usuario) throws SQLException,
			ExcecaoBaralhoCheio {
		Baralho baralho2 = new Baralho();
		Statement montabaralho = bd.createStatement();
		ResultSet retornoBaralho = montabaralho
				.executeQuery("SELECT CARTAS.NOME, CARTAS.ATAQUE,"
						+ "CARTAS.DEFESA, CARTAS.ID, CARTAS.ELEMENTO FROM CARTAS, BARALHO"
						+ " WHERE CARTAS.ID = BARALHO.IDCARTA AND BARALHO.IDJOGADOR = '"
						+ usuario.getLogin() + "' ORDER BY ID;");
		while (retornoBaralho.next()) {
			baralho2.adicionaCarta(new Carta(retornoBaralho.getString("nome"),
					retornoBaralho.getInt("ataque"), retornoBaralho
							.getInt("defesa"), retornoBaralho.getInt("id"),
					retornoBaralho.getString("elemento")));
		}
		usuario.getBaralho().setBaralho(baralho2.getBaralho());
		montabaralho.close();
		retornoBaralho.close();

	}

	public void adicionarCartaBaralho(Usuario usuario, Integer idCarta)
			throws SQLException, ExcecaoBaralhoCheio {

		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta
				.executeQuery("SELECT * FROM CARTAS WHERE ID = '" + idCarta
						+ "';");
		retornoBD.next();
		usuario.adicionaCartaNoBaralho(new Carta(retornoBD.getString("nome"),
				retornoBD.getInt("ataque"), retornoBD.getInt("defesa"),
				retornoBD.getInt("id"), retornoBD.getString("elemento")));
		consulta.close();
		retornoBD.close();
		Statement adicionado = bd.createStatement();
		adicionado.executeUpdate("INSERT INTO BARALHO (IDCARTA,IDJOGADOR)"
				+ " VALUES ('" + idCarta + "','" + usuario.getLogin() + "');");
		adicionado.close();
		}

}
