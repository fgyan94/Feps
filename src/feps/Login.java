package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

	private static JTextField txtUser;
	private static JPasswordField txtSenha;
	private static String user;
	private static char[] senha;
	private JLabel lblUsuario, lblSenha, lblFepsX;
	private JButton btnOk1, btnCancel1;
	private JCheckBox chckbxTrocarSenha;
	private PanelSlider42<JFrame> slider;
	private JPanel panelSlider, login, trocaSenha;

	// Troca Senha
	private JLabel lblSenhaNova1, lblSenhaNova2, lblSenhaConfere;
	private JPasswordField txtSenhaNova1, txtSenhaNova2;
	private JButton btnOk2, btnCancel2;

	Connection c;
	PreparedStatement p;
	ResultSet rs;

	public Login() {
		buildFrame();
		inicializaComponents();
		inicializaListeners();
	}

	private void buildFrame() {
		getContentPane().setLayout(null);
		setBackground(new Color(150, 150, 150));
		setUndecorated(true);
		setBounds(0, 0, 300, 196);
		setOpacity(0.97f);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(150, 150, 150));
	}

	private void inicializaComponents() {
		login = new JPanel();
		trocaSenha = new JPanel();
		txtUser = new JTextField();
		txtSenha = new JPasswordField();
		lblUsuario = new JLabel("Usuário:");
		lblSenha = new JLabel("Senha:");
		lblFepsX = new JLabel("Feps X");
		btnOk1 = new JButton("");
		btnCancel1 = new JButton("Cancelar");
		chckbxTrocarSenha = new JCheckBox("Trocar senha");
		slider = new PanelSlider42<JFrame>(this);
		panelSlider = slider.getBasePanel();
		lblSenhaNova1 = new JLabel("Senha nova:");
		lblSenhaNova2 = new JLabel("Confirmar a senha:");
		lblSenhaConfere = new JLabel("Senhas não conferem");
		txtSenhaNova1 = new JPasswordField();
		txtSenhaNova2 = new JPasswordField();
		btnOk2 = new JButton(new ImageIcon("C:\\Users\\uid38129\\Desktop\\forward_12.png"));
		btnCancel2 = new JButton("Cancelar");

		txtUser.setBounds(109, 46, 86, 25);
		txtSenha.setBounds(109, 97, 86, 25);
		lblUsuario.setBounds(66, 25, 86, 20);
		lblSenha.setBounds(66, 77, 86, 20);
		lblFepsX.setBounds(5, 5, 53, 15);
		btnOk1.setBounds(187, 97, 34, 25);
		btnCancel1.setBounds(107, 149, 85, 25);
		chckbxTrocarSenha.setBounds(109, 122, 105, 16);
		panelSlider.setBounds(0, 0, 300, 196);
		login.setBounds(0, 0, 300, 196);
		trocaSenha.setBounds(0, 0, 300, 196);

		txtUser.setFont(new Font("Palatino", Font.PLAIN, 13));
		txtSenha.setFont(new Font("Palatino", Font.PLAIN, 13));
		lblUsuario.setFont(new Font("Palatino", Font.PLAIN, 15));
		lblSenha.setFont(new Font("Palatino", Font.PLAIN, 15));
		lblFepsX.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 12));
		btnCancel1.setFont(new Font("Palatino", Font.PLAIN, 15));
		chckbxTrocarSenha.setFont(new Font("Palatino", Font.PLAIN, 13));

		lblUsuario.setForeground(new Color(51, 51, 51));
		lblSenha.setForeground(new Color(51, 51, 51));
		lblFepsX.setForeground(new Color(51, 51, 51));
		txtUser.setForeground(new Color(51, 51, 51));
		txtSenha.setForeground(new Color(51, 51, 51));
		chckbxTrocarSenha.setForeground(new Color(51, 51, 51));
		btnCancel1.setForeground(new Color(51, 51, 51));

		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);

		btnOk1.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\forward_12.png"));

		panelSlider.setBackground(new Color(150, 150, 150));
		login.setBackground(new Color(150, 150, 150));
		trocaSenha.setBackground(new Color(150, 150, 150));

		getContentPane().add(panelSlider);

		login.add(txtUser);
		login.add(txtSenha);
		login.add(lblUsuario);
		login.add(lblSenha);
		login.add(lblFepsX);
		login.add(btnOk1);
		login.add(btnCancel1);
		login.add(chckbxTrocarSenha);

		slider.addComponent(login, "login");

		login.setLayout(null);
		panelSlider.setLayout(null);

		// Troca Senha
		lblSenhaNova1 = new JLabel("Senha nova:");
		lblSenhaNova2 = new JLabel("Confirmar a senha:");
		lblSenhaConfere = new JLabel("Senhas não conferem");
		txtSenhaNova1 = new JPasswordField();
		txtSenhaNova2 = new JPasswordField();
		btnOk2 = new JButton(new ImageIcon("C:\\Users\\uid38129\\Desktop\\forward_12.png"));
		btnCancel2 = new JButton("Cancelar");
		lblSenhaNova1.setBounds(66, 25, 86, 20);
		lblSenhaNova2.setBounds(23, 77, 129, 20);
		lblSenhaConfere.setBounds(79, 125, 142, 15);
		txtSenhaNova1.setBounds(109, 46, 86, 25);
		txtSenhaNova2.setBounds(109, 97, 86, 25);
		btnOk2.setBounds(187, 97, 34, 25);
		btnCancel2.setBounds(107, 149, 85, 25);

		lblSenhaNova1.setFont(new Font("Palatino", Font.PLAIN, 15));
		lblSenhaNova2.setFont(new Font("Palatino", Font.PLAIN, 15));
		lblSenhaConfere.setFont(new Font("Palatino", Font.PLAIN, 15));
		txtSenhaNova1.setFont(new Font("Palatino", Font.PLAIN, 15));
		txtSenhaNova2.setFont(new Font("Palatino", Font.PLAIN, 15));
		btnCancel2.setFont(new Font("Palatino", Font.PLAIN, 15));

		lblSenhaNova1.setForeground(new Color(51, 51, 51));
		lblSenhaNova2.setForeground(new Color(51, 51, 51));
		txtSenhaNova1.setForeground(new Color(51, 51, 51));
		txtSenhaNova2.setForeground(new Color(51, 51, 51));
		btnCancel2.setForeground(new Color(51, 51, 51));
		lblSenhaConfere.setForeground(new Color(178, 34, 34));

		lblSenhaNova1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenhaNova2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenhaConfere.setHorizontalAlignment(SwingConstants.CENTER);

		lblSenhaConfere.setVisible(false);

		trocaSenha.setLayout(null);

		trocaSenha.add(lblSenhaNova1);
		trocaSenha.add(lblSenhaNova2);
		trocaSenha.add(lblSenhaConfere);
		trocaSenha.add(txtSenhaNova1);
		trocaSenha.add(txtSenhaNova2);
		trocaSenha.add(btnOk2);
		trocaSenha.add(btnCancel2);

		slider.addComponent(trocaSenha, "trocaSenha");
	}

	private void inicializaListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeConnection();
				super.windowClosing(e);
			}
		});
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUser.selectAll();
				super.focusGained(e);
			}
		});
		txtSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSenha.selectAll();
				super.focusGained(e);
			}
		});

		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					txtSenha.requestFocusInWindow();
				super.keyTyped(e);
			}
		});

		txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnOk1.doClick();
				super.keyTyped(e);
			}
		});

		btnOk1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});

		btnCancel1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Troca Senha
		btnOk2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new String(txtSenhaNova1.getPassword()).length() < 1
						&& new String(txtSenhaNova2.getPassword()).length() < 1) {
					limpaCamposTrocaSenha();
					JOptionPane.showMessageDialog(null, "Há um ou mais campos em branco!");
				} else
					validaNovaSenha();
			}
		});

		txtSenhaNova1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSenhaNova1.selectAll();
				if (new String(txtSenhaNova2.getPassword()).length() < 1)
					lblSenhaConfere.setVisible(false);
				else
					comparaSenha();
				super.focusGained(e);
			}
		});

		txtSenhaNova2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSenhaNova2.selectAll();
				if (new String(txtSenhaNova1.getPassword()).length() < 1)
					lblSenhaConfere.setVisible(false);
				else
					comparaSenha();
				super.focusGained(e);
			}
		});

		txtSenhaNova1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if (new String(txtSenhaNova1.getPassword()).length() < 1
						|| new String(txtSenhaNova2.getPassword()).length() < 1)
					lblSenhaConfere.setVisible(false);
				else {
					comparaSenha();
				}
				super.keyReleased(arg0);
			}
		});

		txtSenhaNova2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				comparaSenha();
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnOk2.doClick();
				super.keyReleased(e);
			}
		});

		btnCancel2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				abreMonitor();
			}
		});
	}

	private void start() {
		user = txtUser.getText();
		senha = txtSenha.getPassword();
		
		if (isOk()) {
			if (trocaSenha()) {
				slider.slideGetVertical(trocaSenha);
			} else {
				closeConnection();
				abreMonitor();
			}

		} else {
			limpaCampos();
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
		}
	}

	public static String criptografa(String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			return String.format("%32x", hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean isOk() {
		boolean b;
		String consultaSQL = "SELECT * FROM Usuario WHERE usuario_codigo = '" + user + "'" + "AND senha = '"
				+ criptografa(new String(senha)) + "'";

		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();
			b = rs.next();

			closeConnection();

			return b;
		} catch (SQLException sqlE) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
			sqlE.printStackTrace();
			return false;
		}
	}

	public static int getUsuario() {
		return Integer.parseInt(user);
	}

	public static String getSenha() {
		return criptografa(new String(senha));
	}

	private boolean trocaSenha() {
		return chckbxTrocarSenha.isSelected();
	}

	private void limpaCampos() {
		txtUser.setText("");
		txtSenha.setText("");
		chckbxTrocarSenha.setSelected(false);
		txtUser.requestFocusInWindow();
	}

	private void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (p != null) {
				p.close();
				p = null;
			} if (c != null) {
				c.close();
				c = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Troca Senha
	private void validaNovaSenha() {
		String senha1 = new String(txtSenhaNova1.getPassword());
		String senha2 = new String(txtSenhaNova2.getPassword());
		String senhaCrypt = Login.criptografa(senha1);
		String loginSenha = Login.getSenha();

		try {
			if (!senhaCrypt.equals(loginSenha)) {
				if (senha1.equals(senha2)) {
					String consultaSQL = "UPDATE Usuario SET senha = '" + senhaCrypt + "'" + "WHERE Usuario_codigo = '"
							+ Login.getUsuario() + "'";

					c = ConnectionFeps.getConnection();
					p = c.prepareStatement(consultaSQL);
					p.executeUpdate();

					closeConnection();

					JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");

					abreMonitor();
				} else {
					limpaCampos();
					JOptionPane.showMessageDialog(null, "As senhas não conferem!");
				}
			} else {
				limpaCampos();
				JOptionPane.showMessageDialog(null,
						"Senha igual à atual, por favor, mude a senha ou cancele a operação!");
			}
		} catch (SQLException sqlE) {

		}
	}

	private void comparaSenha() {
		lblSenhaConfere.setVisible(true);

		String senha1 = new String(txtSenhaNova1.getPassword());
		String senha2 = new String(txtSenhaNova2.getPassword());

		if (!senha1.equals(senha2)) {
			lblSenhaConfere.setText("Senhas não conferem");
			lblSenhaConfere.setForeground(new Color(178, 34, 34));
		} else {
			lblSenhaConfere.setText("Senhas conferem");
			lblSenhaConfere.setForeground(new Color(0, 100, 0));
		}
	}

	private void abreMonitor() {
		dispose();
		new MenuPrincipal().setVisible(true);
	}

	private void limpaCamposTrocaSenha() {
		txtSenhaNova1.setText("");
		txtSenhaNova2.setText("");
		txtSenhaNova1.requestFocusInWindow();
	}
}
