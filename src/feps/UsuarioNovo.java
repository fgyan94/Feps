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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class UsuarioNovo extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lblNovoUsuario, lblRegistro, lblNome, lblSenha, lblConfereSenha, lblPrivilegio, lblInfoConfereSenha,
			btnOK, btnCancela;
	private JTextField txtRegistro, txtNome;
	private JPasswordField txtSenha, txtConfere;
	private ModelTablePrivilegio modelPrivilegio;
	private JTable tbPrivilegio;
	private List<JTextField> listText;
	private List<JPasswordField> listPass;

	public UsuarioNovo() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		createPrivilegioTable();
	}

	private void buildPanel() {
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(new Dimension(700, 500));
		this.setBackground(Color.BLACK);
		this.getContentPane().setLayout(null);
		this.setModal(true);
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
	}

	private void initializeComponents() {
		listText = new ArrayList<>();
		listPass = new ArrayList<>();
		
		lblNovoUsuario = new JLabel();
		lblNovoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoUsuario.setText("Cadastrar Usu\u00E1rio");
		lblNovoUsuario.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblNovoUsuario.setForeground(Color.WHITE);
		lblNovoUsuario.setBounds(10, 10, 678, 90);

		lblNome = new JLabel();
		lblNome.setForeground(Color.WHITE);
		lblNome.setText("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblNome.setBounds(10, 100, 400, 30);

		lblRegistro = new JLabel();
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setText("Registro:");
		lblRegistro.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblRegistro.setForeground(Color.WHITE);
		lblRegistro.setBounds(10, 180, 400, 30);

		lblSenha = new JLabel();
		lblSenha.setText("senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblSenha.setBounds(10, 260, 400, 30);

		lblConfereSenha = new JLabel();
		lblConfereSenha.setText("Digite a senha novamente:");
		lblConfereSenha.setForeground(Color.WHITE);
		lblConfereSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfereSenha.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblConfereSenha.setBounds(10, 340, 400, 30);

		lblPrivilegio = new JLabel();
		lblPrivilegio.setText("Privilégio");
		lblPrivilegio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivilegio.setForeground(Color.WHITE);
		lblPrivilegio.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblPrivilegio.setBounds(410, 100, 280, 30);

		btnOK = new JLabel();
		btnOK.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		btnOK.setText("ok");
		btnOK.setHorizontalAlignment(SwingConstants.CENTER);
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnOK.setBounds(408, 450, 110, 40);

		btnCancela = new JLabel();
		btnCancela.setForeground(Color.WHITE);
		btnCancela.setText("cancelar");
		btnCancela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		btnCancela.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancela.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnCancela.setBounds(528, 450, 110, 40);

		txtRegistro = new JTextField();
		txtRegistro.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtRegistro.setBounds(85, 210, 250, 30);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtNome.setBounds(85, 130, 250, 30);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtSenha.setBounds(85, 290, 250, 30);

		txtConfere = new JPasswordField();
		txtConfere.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtConfere.setBounds(85, 372, 250, 30);

		lblInfoConfereSenha = new JLabel();
		lblInfoConfereSenha.setVisible(false);
		lblInfoConfereSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoConfereSenha.setForeground(Color.RED);
		lblInfoConfereSenha.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblInfoConfereSenha.setBounds(10, 410, 400, 30);

		tbPrivilegio = new JTable();
		tbPrivilegio.setFont(new Font("Calibri", Font.PLAIN, 18));
		tbPrivilegio.setBounds(410, 130, 280, 303);

		getContentPane().add(lblNovoUsuario);
		getContentPane().add(lblNome);
		getContentPane().add(lblRegistro);
		getContentPane().add(lblSenha);
		getContentPane().add(lblConfereSenha);
		getContentPane().add(lblPrivilegio);
		getContentPane().add(btnOK);
		getContentPane().add(btnCancela);
		getContentPane().add(txtRegistro);
		getContentPane().add(txtNome);
		getContentPane().add(txtSenha);
		getContentPane().add(txtConfere);
		getContentPane().add(lblInfoConfereSenha);
		getContentPane().add(tbPrivilegio);
		
		listText.add(txtNome);
		listText.add(txtRegistro);
		listPass.add(txtSenha);
		listPass.add(txtConfere);
		
		txtNome.setFocusTraversalKeysEnabled(false);
		txtRegistro.setFocusTraversalKeysEnabled(false);
		txtSenha.setFocusTraversalKeysEnabled(false);
		txtConfere.setFocusTraversalKeysEnabled(false);
	}

	private void initializeListeners() {
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				Alerta.desativaPopup(txtNome);
				
				if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB)
					txtRegistro.requestFocusInWindow();
			}
		});
		txtRegistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				Alerta.desativaPopup(txtRegistro);
				
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB)
					txtSenha.requestFocusInWindow();
			}
		});
		
		txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Alerta.desativaPopup(txtSenha);
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB)
					txtConfere.requestFocusInWindow();
			}
		});

		txtConfere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Alerta.desativaPopup(txtConfere);
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					if(userOk())
						salva();
					else
						JOptionPane.showMessageDialog(null, "O usuário " + txtRegistro.getText() + " já está cadastrado no sistema!");
				else {
					String senha1 = new String(txtSenha.getPassword());
					String senha2 = new String(txtConfere.getPassword());

					if (senha2.equals(senha1))
						changeInforma(true);
					else
						changeInforma(false);
				}
			}
		});

		addMouseListenerLabel(btnOK);
		addMouseListenerLabel(btnCancela);
		addFocusListenerText(txtNome);
		addFocusListenerText(txtRegistro);
		addFocusListenerPass(txtSenha);
		addFocusListenerPass(txtConfere);
	}

	private void createPrivilegioTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(ModelTablePrivilegio.ADD);
		coluna.add(ModelTablePrivilegio.PRIVILEGIO);

		modelPrivilegio = new ModelTablePrivilegio(coluna);
		modelPrivilegio.setCheckBoxEditable(true);
		
		tbPrivilegio.setModel(modelPrivilegio);
		tbPrivilegio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbPrivilegio.setBackground(Color.WHITE);
		tbPrivilegio.setForeground(Color.BLACK);
		tbPrivilegio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (int i = 0; i < tbPrivilegio.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			if (!modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.ADD))
				tbPrivilegio.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			if (modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.ADD))
				tbPrivilegio.getColumnModel().getColumn(i).setPreferredWidth(50);
			if (modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.PRIVILEGIO))
				tbPrivilegio.getColumnModel().getColumn(i).setPreferredWidth(278);
		}

		tbPrivilegio.getTableHeader().setReorderingAllowed(false);
		tbPrivilegio.getTableHeader().setResizingAllowed(false);

		modelPrivilegio.add(new Privilegio("Inicializa/Encerra Sistema", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Usuários", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Manutenção de Tabelas", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Propriedades", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Reimpressão", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Apagar Ordem", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Saída e Emissão de GTM", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Estorno de GTM", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Monitor de Carga/Impressão", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Ordem Manual", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Ordem Buffer", Boolean.TRUE));
		modelPrivilegio.add(new Privilegio("Saída Buffer", Boolean.TRUE));
		
		tbPrivilegio.setSelectionBackground(Color.BLACK);
		tbPrivilegio.setShowGrid(false);
		tbPrivilegio.setShowHorizontalLines(false);
		tbPrivilegio.setShowVerticalLines(false);
		tbPrivilegio.repaint();
		modelPrivilegio.clearValueCheckBox();
	}

	private void changeInforma(boolean igual) {
		if (igual) {
			lblInfoConfereSenha.setText("As senhas conferem");
			lblInfoConfereSenha.setForeground(Color.GREEN);
		} else {
			lblInfoConfereSenha.setText("As senhas não conferem");
			lblInfoConfereSenha.setForeground(Color.RED);
		}
	}

	private void addFocusListenerText(JTextField txt) {
		txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt.selectAll();
				super.focusGained(e);
			}
		});		
	}

	private void addFocusListenerPass(JPasswordField txt) {
		txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt.selectAll();
				if (txt == txtSenha)
					lblInfoConfereSenha.setVisible(false);
				else if (txt == txtConfere) {
					lblInfoConfereSenha.setVisible(true);
					String senha1 = new String(txtSenha.getPassword());
					String senha2 = new String(txtConfere.getPassword());

					if (senha2.equals(senha1))
						changeInforma(true);
					else
						changeInforma(false);
				}
			}
		});
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (label == btnOK)
					if(userOk())
						salva();
					else
						JOptionPane.showMessageDialog(null, "O usuário " + txtRegistro.getText() + " já está cadastrado no sistema!");
				else if (label == btnCancela)
					cancela();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setFont(new Font("Stencil", Font.PLAIN, 19));
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setFont(new Font("Stencil", Font.PLAIN, 20));
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
			}
		});
	}
	
	private boolean userOk() {
		boolean ret = true;
		String consultaSQL = "SELECT * FROM usuario WHERE usuario_codigo = '" + txtRegistro.getText() + "'";
		ResultSet rs = ConnectionFeps.query(consultaSQL);
		
		try {
			if(rs.next())
				ret = false;
			else {
				txtRegistro.selectAll();
			}
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível consultar se o usuário já existe no banco de dados!");
		}
		
		return ret;
	}

	private void salva() {
		boolean ok = true;
		String senha1 = new String(txtSenha.getPassword());
		String senha2 = new String(txtConfere.getPassword());
		String senhaCrypt = criptografa(senha1);
		
		for(int i = 0; i < listText.size(); i++) {
			if(listText.get(i).getText().isEmpty()) {
				listText.get(i).requestFocusInWindow();
				Alerta.ativaPopup(listText.get(i), "Campo vazio", 20, 30);
				ok = false;
				break;
			} else if (listPass.get(i).getPassword().length <= 0) {
				listText.get(i).requestFocusInWindow();
				Alerta.ativaPopup(listPass.get(i), "Campo vazio", 20, 30);
				ok = false;
				break;
			}
		}
		
		if(!ok)
			return;
		
		if (senha2.equals(senha1)) {
			String consultaSQL = "INSERT INTO usuario (usuario_codigo, nome, senha, privilegio) VALUES ('" + txtRegistro.getText() + "', " + 
									"'" + txtNome.getText() + "', '" + senhaCrypt + "', '" + getPrivilegio() + "')";
			
			if (ConnectionFeps.update(consultaSQL)) {
				JOptionPane.showMessageDialog(null, "O usuário " + txtNome.getText() + " foi cadastrado com sucesso!");
				dispose();
			} else
				JOptionPane.showMessageDialog(null, "Não foi possível atualizar a senha!");
		} else {
			JOptionPane.showMessageDialog(null, "As senhas não conferem!");
			txtSenha.requestFocusInWindow();
			txtSenha.setText("");
			txtConfere.setText("");
		}
	}

	private String getPrivilegio() {
		String privilegio = new String();
		for(int i = 0; i < modelPrivilegio.getSize(); i++) {
			if((boolean) modelPrivilegio.getValueAt(i, 0))
				privilegio = privilegio + "1";
			else
				privilegio = privilegio + "0";
		}
		return privilegio;
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
