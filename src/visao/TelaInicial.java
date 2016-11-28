package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controle.Controle;

@SuppressWarnings("serial")
public class TelaInicial extends TelaJogo {

	public static final Integer XTELA = 400;
	public static final Integer YTITULO = 100;
	public static final Integer YLOGIN = 200;
	public static final Integer YCAMPOLOGIN = 250;
	public static final Integer YSENHA = 300;
	public static final Integer YCAMPOSENHA = 350;
	public static final Integer YBOTAO = 450;
	private JTextField campoLogin;
	private JPasswordField campoSenha;
	private Controle controle;

	public TelaInicial(Controle controle) {
		this.controle = controle;
		criaContentPane();
		configuraTela();
	}

	private void criaContentPane() {
			setContentPane(new JLabel(new ImageIcon("resources/telainicial.jpg")));
			add(etiquetaTitulo());
			add(etiquetaLogin());
			add(etiquetaSenha());
			add(campoLogin());
			add(campoSenha());
			add(botaoLogin());
			add(botaoCadastro());
			setLayout(null);
	}

	private JButton botaoCadastro() {
		JButton botaoCadastro = new JButton("Cadastrar");
		botaoCadastro.setBounds(XTELA + 20, YBOTAO, 100, 30);
		botaoCadastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = campoLogin.getText();
				char[] charSenha = campoSenha.getPassword();
				String senha = new String(charSenha);
				campoLogin.setText("");
				campoSenha.setText("");
				controle.cadastrar(login, senha);
			}
		});
		return botaoCadastro;
	}

	private JButton botaoLogin() {
		JButton botaoLogin = new JButton("Entrar");
		botaoLogin.setBounds(XTELA - 120, YBOTAO, 100, 30);
		botaoLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = campoLogin.getText();
				char[] charSenha = campoSenha.getPassword();
				String senha = new String(charSenha);
				campoLogin.setText("");
				campoSenha.setText("");
				controle.login(login, senha);
			}

		});
		return botaoLogin;
	}

	private JTextField campoLogin() {
		campoLogin = new JTextField();
		campoLogin.setBounds(XTELA - 60, YCAMPOLOGIN, 150, 30);
		return campoLogin;
	}

	private JPasswordField campoSenha() {
		campoSenha = new JPasswordField();
		campoSenha.setBounds(XTELA - 60, YCAMPOSENHA, 150, 30);
		return campoSenha;
	}

	private JLabel etiquetaSenha() {
		JLabel etiquetaSenha = new JLabel(" Senha:");
		etiquetaSenha.setOpaque(Boolean.TRUE);
		etiquetaSenha.setBounds(XTELA - 50, YSENHA, 45, 15);
		return etiquetaSenha;
	}

	private JLabel etiquetaLogin() {
		JLabel etiquetaLogin = new JLabel(" Login:");
		etiquetaLogin.setOpaque(Boolean.TRUE);
		etiquetaLogin.setBounds(XTELA - 50, YLOGIN, 40, 15);
		return etiquetaLogin;
	}

	private JLabel etiquetaTitulo() {
		JLabel etiquetaTitulo = new JLabel(" Iniciar sessão");
		etiquetaTitulo.setOpaque(Boolean.TRUE);
		etiquetaTitulo.setBounds(XTELA - 50, YTITULO, 85, 15);
		return etiquetaTitulo;
	}
}
