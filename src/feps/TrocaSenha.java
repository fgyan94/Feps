package feps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class TrocaSenha extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPasswordField txtSenhaAtual, txtNovaSenha1, txtNovaSenha2;
	private JLabel btnSalva, lblTrocarSenha, lblSenhaAtual, lblNovaSenha1, lblNovaSenha2, lblInfoConfereSenha, btnCancela;
	private JButton btnSenhaAtual;
	private String user;
	 
	public TrocaSenha(String user) {
		this.user = user;
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(new Dimension(450, 300));
		this.setBackground(Color.BLACK);
		this.getContentPane().setLayout(null);
		this.setModal(true);
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
	}

	private void initializeComponents() {
		lblTrocarSenha = new JLabel("Trocar Senha");
		lblTrocarSenha.setForeground(Color.WHITE);
		lblTrocarSenha.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblTrocarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrocarSenha.setBounds(12, 12, 426, 56);
		
		lblSenhaAtual = new JLabel("Senha atual:");
		lblSenhaAtual.setForeground(Color.WHITE);
		lblSenhaAtual.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenhaAtual.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblSenhaAtual.setBounds(12, 80, 165, 30);

		lblNovaSenha1 = new JLabel("Nova senha");
		lblNovaSenha1.setVisible(false);
		lblNovaSenha1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaSenha1.setForeground(Color.WHITE);
		lblNovaSenha1.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblNovaSenha1.setBounds(12, 122, 165, 30);
		
		lblNovaSenha2 = new JLabel("Confirma senha:");
		lblNovaSenha2.setVisible(false);
		lblNovaSenha2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaSenha2.setForeground(Color.WHITE);
		lblNovaSenha2.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblNovaSenha2.setBounds(12, 164, 165, 30);
		
		txtSenhaAtual = new JPasswordField();
		txtSenhaAtual.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtSenhaAtual.setBounds(195, 80, 177, 30);

		btnSenhaAtual = new JButton(new ImageIcon("icoFeps\\forward_12.png"));
		btnSenhaAtual.setBounds(374, 80, 30, 30);
		
		txtNovaSenha1 = new JPasswordField();
		txtNovaSenha1.setVisible(false);
		txtNovaSenha1.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNovaSenha1.setBounds(195, 122, 177, 30);
		
		txtNovaSenha2 = new JPasswordField();
		txtNovaSenha2.setVisible(false);
		txtNovaSenha2.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNovaSenha2.setBounds(195, 164, 177, 30);

		lblInfoConfereSenha = new JLabel();
		lblInfoConfereSenha.setVisible(false);
		lblInfoConfereSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoConfereSenha.setForeground(Color.RED);
		lblInfoConfereSenha.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblInfoConfereSenha.setBounds(12, 206, 426, 30);
		
		btnSalva = new JLabel("salvar");
		btnSalva.setVisible(false);
		btnSalva.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		btnSalva.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalva.setForeground(Color.WHITE);
		btnSalva.setFont(new Font("Stencil", Font.PLAIN, 16));
		btnSalva.setBounds(215, 246, 108, 43);
		
		btnCancela = new JLabel("cancelar");
		btnCancela.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancela.setForeground(Color.WHITE);
		btnCancela.setFont(new Font("Stencil", Font.PLAIN, 16));
		btnCancela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		btnCancela.setBounds(330, 246, 108, 43);
		
		getContentPane().add(lblTrocarSenha);
		getContentPane().add(lblSenhaAtual);
		getContentPane().add(lblNovaSenha1);
		getContentPane().add(lblNovaSenha2);
		getContentPane().add(txtSenhaAtual);
		getContentPane().add(btnSenhaAtual);
		getContentPane().add(txtNovaSenha1);
		getContentPane().add(txtNovaSenha2);
		getContentPane().add(lblInfoConfereSenha);
		getContentPane().add(btnSalva);
		getContentPane().add(btnCancela);
	}
	
	private void initializeListeners() {
		btnSenhaAtual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginOk();
			}
		});
		
		txtSenhaAtual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					loginOk();
			}
		});
		
		txtNovaSenha1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtNovaSenha2.requestFocusInWindow();
				super.keyReleased(e);
			}
		});
		
		txtNovaSenha2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					salva();
				else {
					String senha1 = new String(txtNovaSenha1.getPassword());
					String senha2 = new String(txtNovaSenha2.getPassword());
				
					if(senha2.equals(senha1))
						changeInforma(true);
					else
						changeInforma(false);
				}
			}
		});

		addMouseListenerLabel(btnSalva);
		addMouseListenerLabel(btnCancela);
		addFocusListenerText(txtSenhaAtual);
		addFocusListenerText(txtNovaSenha1);
		addFocusListenerText(txtNovaSenha2);
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == btnSalva)
					salva();
				else if(label == btnCancela)
					cancela();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setFont(new Font("Stencil", Font.PLAIN, 15));
				label.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.WHITE));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				label.setFont(new Font("Stencil", Font.PLAIN, 16));
				label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
			}
		});		
	}

	private void changeInforma(boolean igual) {
		if(igual) {
			lblInfoConfereSenha.setText("As senhas conferem");
			lblInfoConfereSenha.setForeground(Color.GREEN);
		} else {
			lblInfoConfereSenha.setText("As senhas não conferem");
			lblInfoConfereSenha.setForeground(Color.RED);
		}
	}
	
	private void addFocusListenerText(JPasswordField txt) {
		txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt.selectAll();
				if(txt == txtNovaSenha1)
					lblInfoConfereSenha.setVisible(false);
				else if(txt == txtNovaSenha2) {
					lblInfoConfereSenha.setVisible(true);
					String senha1 = new String(txtNovaSenha1.getPassword());
					String senha2 = new String(txtNovaSenha2.getPassword());
				
					if(senha2.equals(senha1))
						changeInforma(true);
					else
						changeInforma(false); 
				}
			}
		});		
	}
	
	private void loginOk() {
		if(isLoginOk())
			startNovaSenha();
		else {
			JOptionPane.showMessageDialog(null, "Senha incorreta!");
			txtSenhaAtual.requestFocusInWindow();
		}
	}
	
	private boolean isLoginOk() {
		boolean b = false;;
		ResultSet rs;
		String consultaSQL = "SELECT * FROM Usuario WHERE usuario_codigo = '" + user + "'" + "AND senha = '"
				+ criptografa(new String(txtSenhaAtual.getPassword())) + "'";

		try {
			rs = ConnectionFeps.query(consultaSQL);
			b = rs.next();
			
			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível validar os dados de login!");
			return false;
		}
		return b;
	}
	
	private void startNovaSenha() {
		txtSenhaAtual.setEnabled(false);
		btnSenhaAtual.setEnabled(false);
		
		lblNovaSenha1.setVisible(true);
		lblNovaSenha2.setVisible(true);
		lblInfoConfereSenha.setVisible(true);
		txtNovaSenha1.setVisible(true);
		txtNovaSenha2.setVisible(true);
		btnSalva.setVisible(true);
	}
	
	private void salva() {
		String senha1 = new String(txtNovaSenha1.getPassword());
		String senha2 = new String(txtNovaSenha2.getPassword());
		String senhaCrypt = criptografa(senha1);
		String loginSenha = criptografa(new String(txtSenhaAtual.getPassword()));
		
		if(loginSenha.equals(senhaCrypt)) {
			JOptionPane.showMessageDialog(null, "Nova senha igual à senha atual");
			txtNovaSenha1.requestFocusInWindow();
			txtNovaSenha1.setText("");
			txtNovaSenha2.setText("");
		}
		else {
			if(senha2.equals(senha1)) {
				String consultaSQL = "UPDATE Usuario SET senha = '" + senhaCrypt + "'" + " WHERE Usuario_codigo = '" + user + "'";
				if(ConnectionFeps.update(consultaSQL)) {
					JOptionPane.showMessageDialog(null, "A senha foi atualizada com sucesso!");
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Não foi possível atualizar a senha!");
			} else {
				JOptionPane.showMessageDialog(null, "As senhas não conferem!");
				txtNovaSenha1.requestFocusInWindow();
				txtNovaSenha1.setText("");
				txtNovaSenha2.setText("");
			}		
		}
	}
	
	private void cancela() {
		dispose();
	}
	
	public String criptografa(String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			return String.format("%32x", hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
