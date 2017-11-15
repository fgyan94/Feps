package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class TrocaSenha extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblSenhaNova1, lblSenhaNova2, lblSenhaConfere;
	private JPasswordField txtSenhaNova1, txtSenhaNova2;
	private JButton btnOk2, btnCancel2;
	private Connection c;
	private PreparedStatement p;

	public TrocaSenha() {
		buildPanel();
		inicializaComponentes();
		inicializaListeners();
	}
	
	private void buildPanel(){
		setBounds(0, 0, 300, 196);
		setBackground(new Color(150, 150, 150));
		setLayout(null);
	}

	private void inicializaComponentes() {
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

		add(lblSenhaNova1);
		add(lblSenhaNova2);
		add(lblSenhaConfere);
		add(txtSenhaNova1);
		add(txtSenhaNova2);
		add(btnOk2);
		add(btnCancel2);
	}

	private void inicializaListeners() {
		btnOk2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new String(txtSenhaNova1.getPassword()).length() < 1
						&& new String(txtSenhaNova2.getPassword()).length() < 1) {
					limpaCampos();
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
		new MenuPrincipal().setVisible(true);
	}

	private void limpaCampos() {
		txtSenhaNova1.setText("");
		txtSenhaNova2.setText("");
		txtSenhaNova1.requestFocusInWindow();
	}
	
	private void closeConnection(){
		try {
			p.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
