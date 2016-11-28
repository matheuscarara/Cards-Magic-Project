package modelo.mapeadores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MapeadorCartas {
	private Connection bd;

	public MapeadorCartas(Connection bd) {
		this.bd = bd;
	}

	public String carregaCartas() throws SQLException {

		String textoRetorno = "";
		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta.executeQuery("SELECT * FROM CARTAS ORDER BY id;");
		while (retornoBD.next()) {
			textoRetorno += retornoBD.getString("id") + " " + retornoBD.getString("nome") + " "
					+ retornoBD.getInt("ataque") + " " + retornoBD.getInt("defesa") + " "
					+ retornoBD.getString("elemento") + "<br>";
		}
		consulta.close();
		retornoBD.close();
		textoRetorno += "</html>";
		return textoRetorno;

	}

}
