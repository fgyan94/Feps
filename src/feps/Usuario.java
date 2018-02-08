package feps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class Usuario extends JPanel {
	private class User {
		String nome;
		int registro;

		public User(String nome, int registro) {
			this.nome = nome;
			this.registro = registro;
		}

		public String getNome() {
			return nome;
		}

		public int getRegistro() {
			return registro;
		}
	}

	private static final long serialVersionUID = 1L;

	// private Dimension dimension = new Dimension(1366, 768);
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	private static final int MIN_WIDTH = 1366;
	private static final int MIN_HEIGHT = 768;

	private int selectedIndex = 1;
	private GroupLayout groupLayout;
	private JLabel lblUser, lblBusca, lblUserInfo, lblNome, lblRegistro, lblPrivilegio;
	private JTextField txtBusca, txtNome, txtRegistro;
	private JScrollPane scrUser, scrPrivilegio;
	private JList<String> listUser;
	private DefaultListModel<String> dlmUser;
	private List<User> userArray;
	private JTable tbPrivilegio;
	private ModelTablePrivilegio modelPrivilegio;
	private JLabel btnEditaPrivilegio, btnSalvaPriv, btnCancelPriv, btnSalvaUser, btnCancelUser, btnTrocaSenha,
			btnEditaUser, btnAddUser, btnDeleta;

	private String nome = "";
	private List<Boolean> bool = new ArrayList<>();

	private List<String> tmpListUser = new ArrayList<>();

	public Usuario() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
		initializeListeners();
		createPrivilegioTable();
		fillLists();
	}

	private void buildPanel() {
		setSize(dimension);
		setBackground(Color.WHITE);
		groupLayout = new GroupLayout(this);
	}

	private void initializeComponents() {
		userArray = new ArrayList<>();

		lblUser = new JLabel("Usuário");
		lblUser.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);

		txtBusca = new JTextField();
		txtBusca.setFont(new Font("Stencil", Font.PLAIN, 16));
		txtBusca.setColumns(10);

		lblBusca = new JLabel("Buscar:");
		lblBusca.setFont(new Font("Stencil", Font.PLAIN, 16));

		scrUser = new JScrollPane();

		listUser = new JList<String>();
		listUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUser.setForeground(Color.DARK_GRAY);
		listUser.setFont(new Font("Calibri", Font.PLAIN, 16));

		dlmUser = new DefaultListModel<>();
		listUser.setModel(dlmUser);
		scrUser.setViewportView(listUser);

		lblUserInfo = new JLabel("Nome usuario");
		lblUserInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserInfo.setFont(new Font("Stencil", Font.PLAIN, 25));

		lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Stencil", Font.PLAIN, 14));

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setForeground(Color.DARK_GRAY);
		txtNome.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblRegistro = new JLabel("Registro:");
		lblRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistro.setFont(new Font("Stencil", Font.PLAIN, 14));

		txtRegistro = new JTextField();
		txtRegistro.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtRegistro.setEditable(false);

		btnEditaUser = new JLabel("editar");
		btnEditaUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEditaUser.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnEditaUser.setHorizontalAlignment(SwingConstants.CENTER);

		btnAddUser = new JLabel("Novo usuário");
		btnAddUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAddUser.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnAddUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnDeleta = new JLabel("Deletar");
		btnDeleta.setBorder(new LineBorder(Color.BLACK));
		btnDeleta.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnDeleta.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrPrivilegio = new JScrollPane();
		scrPrivilegio.setFont(new Font("Stencil", Font.PLAIN, 18));

		tbPrivilegio = new JTable();
		tbPrivilegio.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrPrivilegio.setViewportView(tbPrivilegio);

		lblPrivilegio = new JLabel("Privilégio");
		lblPrivilegio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivilegio.setFont(new Font("Stencil", Font.PLAIN, 16));

		btnEditaPrivilegio = new JLabel("editar");
		btnEditaPrivilegio.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditaPrivilegio.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnEditaPrivilegio.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnSalvaPriv = new JLabel("salvar");
		btnSalvaPriv.setVisible(false);
		btnSalvaPriv.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalvaPriv.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnSalvaPriv.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnCancelPriv = new JLabel("cancelar");
		btnCancelPriv.setVisible(false);
		btnCancelPriv.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelPriv.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnCancelPriv.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnSalvaUser = new JLabel("salvar");
		btnSalvaUser.setVisible(false);
		btnSalvaUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalvaUser.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnSalvaUser.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnCancelUser = new JLabel("cancelar");
		btnCancelUser.setVisible(false);
		btnCancelUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelUser.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnCancelUser.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnTrocaSenha = new JLabel("troca senha");
		btnTrocaSenha.setVisible(true);
		btnTrocaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		btnTrocaSenha.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnTrocaSenha.setBorder(new LineBorder(new Color(0, 0, 0)));
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVertifcalLayout();
		setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, calculate(1346, MIN_WIDTH, dimension.width),
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(285, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(93, MIN_WIDTH, dimension.width)).addComponent(lblNome,
												GroupLayout.PREFERRED_SIZE, calculate(50, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
								.addComponent(lblUserInfo, GroupLayout.PREFERRED_SIZE,
										calculate(360, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(145, MIN_WIDTH, dimension.width)).addComponent(txtNome,
												GroupLayout.PREFERRED_SIZE, calculate(200, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(10, MIN_WIDTH, dimension.width)).addComponent(btnDeleta, 
												GroupLayout.PREFERRED_SIZE, calculate(70, MIN_WIDTH, dimension.width), 
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
										.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE,
												calculate(80, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(2, MIN_WIDTH, dimension.width)).addComponent(txtRegistro,
												GroupLayout.PREFERRED_SIZE, calculate(110, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
										.addComponent(btnSalvaUser, GroupLayout.PREFERRED_SIZE,
												calculate(87, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(10, MIN_WIDTH, dimension.width)).addComponent(btnCancelUser,
												GroupLayout.PREFERRED_SIZE, calculate(87, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
										.addComponent(btnEditaUser, GroupLayout.PREFERRED_SIZE,
												calculate(87, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(10, MIN_WIDTH, dimension.width))
										.addComponent(btnTrocaSenha, GroupLayout.PREFERRED_SIZE,
												calculate(100, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(10, MIN_WIDTH, dimension.width)).addComponent(btnAddUser,
												GroupLayout.PREFERRED_SIZE, calculate(130, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width)).addComponent(lblPrivilegio,
												GroupLayout.PREFERRED_SIZE, calculate(335, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width)).addComponent(scrPrivilegio,
												GroupLayout.PREFERRED_SIZE, calculate(335, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
										.addComponent(btnSalvaPriv, GroupLayout.PREFERRED_SIZE,
												calculate(87, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(10, MIN_WIDTH, dimension.width)).addComponent(btnCancelPriv,
												GroupLayout.PREFERRED_SIZE, calculate(87, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(63, MIN_WIDTH, dimension.width))
										.addComponent(btnEditaPrivilegio, GroupLayout.PREFERRED_SIZE,
												calculate(87, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)))
						.addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblBusca, GroupLayout.PREFERRED_SIZE,
												calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
										.addComponent(txtBusca, GroupLayout.PREFERRED_SIZE,
												calculate(230, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
								.addComponent(scrUser, GroupLayout.PREFERRED_SIZE,
										calculate(300, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))));

	}

	private void buildVertifcalLayout() {
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addComponent(lblUser, GroupLayout.PREFERRED_SIZE,
								calculate(50, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(calculate(54, MIN_HEIGHT - 80, dimension.height - 80))
														.addComponent(lblNome, GroupLayout.PREFERRED_SIZE,
																calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
																GroupLayout.PREFERRED_SIZE))
												.addComponent(lblUserInfo, GroupLayout.PREFERRED_SIZE,
														calculate(57, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(calculate(54, MIN_HEIGHT - 80, dimension.height - 80))
														.addComponent(txtNome, GroupLayout.PREFERRED_SIZE,
																calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
																GroupLayout.PREFERRED_SIZE)))										
										.addGap(calculate(5, MIN_HEIGHT - 80, dimension.height - 80))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtRegistro, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE))
										.addGap(calculate(5, MIN_HEIGHT - 80, dimension.height - 80))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnSalvaUser, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelUser, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnEditaUser, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnTrocaSenha, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAddUser, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE))
										.addGap(calculate(85, MIN_HEIGHT - 80, dimension.height - 80))
										.addComponent(lblPrivilegio, GroupLayout.PREFERRED_SIZE,
												calculate(48, MIN_HEIGHT - 80, dimension.height - 80),
												GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(4, MIN_HEIGHT - 80, dimension.height - 80))
										.addComponent(scrPrivilegio, GroupLayout.PREFERRED_SIZE,
												calculate(250, MIN_HEIGHT - 80, dimension.height - 80),
												GroupLayout.PREFERRED_SIZE)
										.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnSalvaPriv, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelPriv, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnEditaPrivilegio, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(54, MIN_HEIGHT - 80, dimension.height - 80))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnDeleta, GroupLayout.PREFERRED_SIZE, 
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(4, MIN_HEIGHT - 80, dimension.height - 80))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBusca, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtBusca, GroupLayout.PREFERRED_SIZE,
														calculate(30, MIN_HEIGHT - 80, dimension.height - 80),
														GroupLayout.PREFERRED_SIZE))
										.addGap(calculate(13, MIN_HEIGHT - 80, dimension.height - 80))
										.addComponent(scrUser, GroupLayout.PREFERRED_SIZE,
												calculate(540, MIN_HEIGHT - 80, dimension.height - 80),
												GroupLayout.PREFERRED_SIZE)))));
	}

	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}

	private int calculateSizeTable(int columnMinWidth, int tableMinWidth, int tableWidth) {
		return (tableWidth * columnMinWidth) / tableMinWidth;
	}

	private void initializeListeners() {
		listUser.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (listUser.getSelectedIndex() >= 0) {
					txtNome.setText(userArray.get(listUser.getSelectedIndex()).getNome());
					txtRegistro.setText(String.valueOf(userArray.get(listUser.getSelectedIndex()).getRegistro()));
					lblUserInfo.setText(userArray.get(listUser.getSelectedIndex()).getNome() + " - "
							+ userArray.get(listUser.getSelectedIndex()).getRegistro());
					fillListPrivilegio();
				}
			}
		});

		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.selectAll();
			}
		});

		txtBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				ArrayList<String> array = new ArrayList<>();

				for (int i = 0; i < tmpListUser.size(); i++) {
					if (tmpListUser.get(i).contains(txtBusca.getText().toUpperCase()))
						array.add(tmpListUser.get(i));
				}

				dlmUser.clear();

				for (int i = 0; i < array.size(); i++) {
					dlmUser.addElement(array.get(i));
				}
				listUser.setSelectedIndex(0);

				if (dlmUser.isEmpty()) {
					listaVazia(true);
				} else {
					listaVazia(false);
				}

				if (txtBusca.getText().isEmpty()) {
					dlmUser.clear();
					for (int i = 0; i < tmpListUser.size(); i++) {
						dlmUser.addElement(tmpListUser.get(i));
					}
					listUser.setSelectedIndex(0);
				}
			}
		});

		addMouseListenerLabel(btnEditaUser);
		addMouseListenerLabel(btnEditaPrivilegio);
		addMouseListenerLabel(btnCancelUser);
		addMouseListenerLabel(btnCancelPriv);
		addMouseListenerLabel(btnSalvaUser);
		addMouseListenerLabel(btnSalvaPriv);
		addMouseListenerLabel(btnTrocaSenha);
		addMouseListenerLabel(btnAddUser);
		addMouseListenerLabel(btnDeleta);
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (label == btnEditaUser)
					editaUser();
				else if (label == btnEditaPrivilegio)
					editaPrivilegio();
				else if (label == btnCancelUser)
					cancelUser();
				else if (label == btnCancelPriv)
					cancelPriv();
				else if (label == btnSalvaUser)
					saveUser();
				else if (label == btnSalvaPriv)
					savePriv();
				else if (label == btnTrocaSenha)
					trocaSenha();
				else if (label == btnAddUser)
					addUser();
				else if(label == btnDeleta)
					deleta();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 13));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 14));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}
		});
	}

	private void editaUser() {
		selectedIndex = listUser.getSelectedIndex();
		nome = txtNome.getText();
		txtNome.setEditable(true);
		txtNome.requestFocusInWindow();
		listUser.setEnabled(false);
		txtBusca.setEnabled(false);
		btnEditaUser.setVisible(false);
		btnSalvaUser.setVisible(true);
		btnCancelUser.setVisible(true);
		btnTrocaSenha.setVisible(false);
		btnAddUser.setVisible(false);
		btnDeleta.setVisible(false);
	}

	private void editaPrivilegio() {
		selectedIndex = listUser.getSelectedIndex();
		bool.clear();
		for (int i = 0; i < modelPrivilegio.getRowCount(); i++) {
			bool.add((Boolean) modelPrivilegio.getValueAt(i, 0));
		}
		modelPrivilegio.setCheckBoxEditable(true);
		txtBusca.setEnabled(false);
		listUser.setEnabled(false);
		btnEditaPrivilegio.setVisible(false);
		btnSalvaPriv.setVisible(true);
		btnCancelPriv.setVisible(true);
	}

	private void cancelUser() {
		txtNome.setText(nome);
		txtNome.setEditable(false);
		listUser.setEnabled(true);
		listUser.setSelectedIndex(selectedIndex);
		txtBusca.setEnabled(true);
		btnEditaUser.setVisible(true);
		btnSalvaUser.setVisible(false);
		btnCancelUser.setVisible(false);
		btnTrocaSenha.setVisible(true);
		btnAddUser.setVisible(true);
		btnDeleta.setVisible(true);
		requestFocusInWindow();
	}

	private void cancelPriv() {
		modelPrivilegio.setCheckBoxEditable(false);
		listUser.setEnabled(true);
		listUser.setSelectedIndex(selectedIndex);
		txtBusca.setEnabled(true);
		btnEditaPrivilegio.setVisible(true);
		btnSalvaPriv.setVisible(false);
		btnCancelPriv.setVisible(false);
		requestFocusInWindow();
		fillListPrivilegio();
	}

	private void saveUser() {
		String consultaSQL = "UPDATE usuario SET nome = '" + txtNome.getText() + "' WHERE nome = '" + nome + "'";

		if (ConnectionFeps.update(consultaSQL)) {
			fillLists();
			listUser.setEnabled(true);
			listUser.setSelectedIndex(selectedIndex);
			btnEditaUser.setVisible(true);
			btnSalvaUser.setVisible(false);
			btnCancelUser.setVisible(false);
			txtBusca.setEnabled(true);
			txtBusca.setText("");
			btnTrocaSenha.setVisible(true);
			btnAddUser.setVisible(true);
			btnDeleta.setVisible(true);
			requestFocusInWindow();
		} else
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o nome do usuário: " + nome);
	}

	private void savePriv() {
		String consultaSQL = "UPDATE usuario SET privilegio = '" + convertePrivilegio() + "' WHERE usuario_codigo = '"
				+ txtRegistro.getText() + "'";
		if (ConnectionFeps.update(consultaSQL)) {
			bool.clear();
			for (int i = 0; i < modelPrivilegio.getRowCount(); i++) {
				bool.add((Boolean) modelPrivilegio.getValueAt(i, 0));
			}
			modelPrivilegio.setCheckBoxEditable(false);
			listUser.setEnabled(true);
			listUser.setSelectedIndex(selectedIndex);
			btnEditaPrivilegio.setVisible(true);
			btnSalvaPriv.setVisible(false);
			btnCancelPriv.setVisible(false);
			requestFocusInWindow();
			txtBusca.setText("");
			txtBusca.setEnabled(true);
			fillLists();
		} else
			JOptionPane.showMessageDialog(null,
					"Não foi possível atualizar os privilégios do usuário: " + txtNome.getText());
	}
	
	private void deleta() {
		new DeleteUser().setVisible(true);
	}

	private void trocaSenha() {
		new TrocaSenha(txtRegistro.getText()).setVisible(true);
	}

	private void addUser() {
		UsuarioNovo usuario = new UsuarioNovo();
		usuario.setVisible(true);
		usuario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				fillLists();
				txtBusca.requestFocusInWindow();
			}
		});
	}

	private String convertePrivilegio() {
		String ret = "";
		for (int i = 0; i < bool.size(); i++) {
			if ((Boolean) modelPrivilegio.getValueAt(i, 0))
				ret += 1;
			else
				ret += 0;
		}

		return ret;
	}

	private void createPrivilegioTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(ModelTablePrivilegio.ADD);
		coluna.add(ModelTablePrivilegio.PRIVILEGIO);

		modelPrivilegio = new ModelTablePrivilegio(coluna);
		tbPrivilegio.setModel(modelPrivilegio);
		tbPrivilegio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbPrivilegio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (int i = 0; i < tbPrivilegio.getColumnCount(); i++) {

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
							row, column);

					if (!btnEditaPrivilegio.isVisible())
						c.setForeground(Color.BLACK);
					else
						c.setForeground(Color.DARK_GRAY);

					tbPrivilegio.repaint();

					return c;
				}
			};

			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			if (!modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.ADD))
				tbPrivilegio.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			if (modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.ADD))
				tbPrivilegio.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(50, 335, calculate(335, MIN_WIDTH, dimension.width)));
			if (modelPrivilegio.getColumnName(i).equals(ModelTablePrivilegio.PRIVILEGIO))
				tbPrivilegio.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(278, 335, calculate(335, MIN_WIDTH, dimension.width)));
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

		tbPrivilegio.setShowGrid(false);
		tbPrivilegio.repaint();
	}

	private void listaVazia(boolean vazio) {
		if (vazio) {
			lblUserInfo.setText("Não encontrado");
			txtNome.setText("");
			txtRegistro.setText("");
			btnEditaPrivilegio.setVisible(false);
			btnEditaUser.setVisible(false);
			btnTrocaSenha.setVisible(false);
			btnDeleta.setVisible(false);
			modelPrivilegio.clearValueCheckBox();
		} else {
			btnEditaPrivilegio.setVisible(true);
			btnEditaUser.setVisible(true);
			btnTrocaSenha.setVisible(true);
			btnDeleta.setVisible(true);
		}

	}

	private void fillLists() {
		fillListUser();
		fillListPrivilegio();
		listUser.setSelectedIndex(0);
	}

	private void fillListUser() {
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM usuario";
			rs = ConnectionFeps.query(consultaSQL);
			dlmUser.clear();
			tmpListUser.clear();

			while (rs.next()) {
				User user = new User(rs.getString("nome").trim().toUpperCase(), rs.getInt("usuario_codigo"));
				userArray.add(user);
				dlmUser.addElement(user.getNome());
				tmpListUser.add(user.getNome());
			}

			if (dlmUser.isEmpty())
				listaVazia(true);

			tbPrivilegio.repaint();

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a lista de usuário");
		}
	}

	private void fillListPrivilegio() {
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM usuario WHERE usuario_codigo = '" + txtRegistro.getText() + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				String[] tmpPrivilegio = rs.getString("privilegio").split("");
				for (int i = 0; i < tmpPrivilegio.length; i++) {
					if (tmpPrivilegio[i].equals(String.valueOf(1)))
						modelPrivilegio.setValueAt(true, i, 0);
					else
						modelPrivilegio.setValueAt(false, i, 0);
				}
			}

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a table de privilégios!");
		}
	}
	
	private class DeleteUser extends JDialog {
		private static final long serialVersionUID = 1L;

		private JLabel lblEncerraDia = new JLabel("Remover o usuário " + txtNome.getText() + "?");
		private JLabel btnSim = new JLabel("SIM");
		private JLabel btnNao = new JLabel("NÃO");

		public DeleteUser() {
			buildPanel();
			initializeComponents();
			initializeListeners();
		}

		private void buildPanel() {
			this.setBounds(0, 0, 300, 140);
			this.setModal(true);
			this.setUndecorated(true);
			this.setOpacity(0.95f);
			this.setLocationRelativeTo(null);
			this.setBackground(Color.BLACK);
			this.getContentPane().setBackground(Color.BLACK);
			getContentPane().setLayout(null);
		}

		private void initializeComponents() {

			lblEncerraDia.setHorizontalAlignment(SwingConstants.LEFT);
			lblEncerraDia.setFont(new Font("Stencil", Font.PLAIN, 20));
			lblEncerraDia.setForeground(Color.LIGHT_GRAY);
			lblEncerraDia.setBounds(10, 10, 280, 90);
			getContentPane().add(lblEncerraDia);

			btnSim.setHorizontalAlignment(SwingConstants.CENTER);
			btnSim.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnSim.setBounds(105, 100, 90, 30);
			btnSim.setForeground(Color.LIGHT_GRAY);
			btnSim.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnSim);

			btnNao.setHorizontalAlignment(SwingConstants.CENTER);
			btnNao.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnNao.setBounds(200, 100, 90, 30);
			btnNao.setForeground(Color.LIGHT_GRAY);
			btnNao.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnNao);
		}

		private void initializeListeners() {
			btnSim.addMouseListener(mouseListenerLabel(btnSim));
			btnNao.addMouseListener(mouseListenerLabel(btnNao));
		}

		private MouseAdapter mouseListenerLabel(JLabel label) {
			return new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (label == btnSim) {
						deleta();
						dispose();
						fillLists();
						txtBusca.requestFocusInWindow();
					} else
						dispose();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					label.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}
			};
		}
		
		private void deleta() {
			String consultaSQL =  "DELETE usuario WHERE usuario_codigo = '" + txtRegistro.getText() + "'";
			if(ConnectionFeps.update(consultaSQL))
				JOptionPane.showMessageDialog(null, "O usuário " + txtNome.getText() + " - " + txtRegistro.getText() + " foi removido com sucesso!");
			else
				JOptionPane.showMessageDialog(null, "Não foi possível remover o usuário " + txtNome.getText());
		}
	}
}
