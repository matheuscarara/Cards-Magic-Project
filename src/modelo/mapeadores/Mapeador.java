package modelo.mapeadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.Usuario;
import modelo.excecoes.ExcecaoBaralhoCheio;

public class Mapeador {

	private Connection bd = null;
	private MapeadorUsuario mu;
	private MapeadorBaralho mb;
	private MapeadorCartas mc;

	public Mapeador() {
		inicializaBancoDeDados();
		mu = new MapeadorUsuario(bd);
		mb = new MapeadorBaralho(bd);
		mc = new MapeadorCartas(bd);
	}

	public Usuario carregarUsuario(String login, String senha)
			throws SQLException {
		return mu.carregaUsuario(login, senha);
	}

	public void salvarUsuario(Usuario usuario) throws SQLException {
		mu.salvarUsuario(usuario);
	}

	public void carregaBaralho(Usuario usuario) throws SQLException,
			ExcecaoBaralhoCheio {
		mb.carregaBaralho(usuario);
	}

	public void adicionarCartaBaralho(Usuario usuario, Integer idCarta)
			throws SQLException, ExcecaoBaralhoCheio {
		mb.adicionarCartaBaralho(usuario, idCarta);
	}

	public String carregaCartas() throws SQLException {
		return mc.carregaCartas();
	}

	private void inicializaBancoDeDados() {
		try {
			Class.forName("org.postgresql.Driver");
			setBd(DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/cards&magic", "postgres",
					"10203040"));
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