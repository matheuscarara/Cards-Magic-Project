package modelo.mapeadores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Usuario;

public class MapeadorUsuario {
	private Connection bd;

	public MapeadorUsuario(Connection bd) {
		this.bd = bd;
	}

	public Usuario carregaUsuario(String login, String senha) throws SQLException {
		Statement consulta = bd.createStatement();
		ResultSet retornoBD = consulta
				.executeQuery("SELECT * FROM JOGADOR WHERE LOGIN = " + "'" + login + "'AND SENHA = '" + senha + "';");
		retornoBD.next();
		Usuario usuarioRetorno = new Usuario(retornoBD.getString("login"), retornoBD.getString("senha"));
		consulta.close();
		retornoBD.close();
		return usuarioRetorno;
	}

	public void salvarUsuario(Usuario usuario) throws SQLException {
		Statement criacao = bd.createStatement();
		criacao.executeUpdate("INSERT INTO JOGADOR (LOGIN,SENHA)" + " VALUES ('" + usuario.getLogin() + "','"
				+ usuario.getSenha() + "');");
		criacao.close();

	}

}
