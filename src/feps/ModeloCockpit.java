package feps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class ModeloCockpit extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel dados, lista;
	private JTabbedPane tabbedPane;

	private Dimension dimension = new Dimension(1315, 490);
	
	private JLabel lblBusca, lblCodGM, lblCodConti, lblHistorico, lblApelido, lblApelidoSerie, btnNovo, btnEdita,
			btnExclui, btnSalva, btnCancela, btnProximo, btnAnterior, btnPrimeiro, btnUltimo;
	private JTextField txtCodGM, txtCodConti, txtHistorico, txtApelido, txtApelidoSerie, txtBusca;
	private List<JTextField> textList;
	private List<JLabel> btnList;
	private JTable tbLista;
	private ModelTableCockpit mdLista;
	private JScrollPane scrLista;

	private boolean novo = false;
	private List<Cockpit> tmpCockpit;

	public ModeloCockpit() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		buildTable();
		fillDados();
	}

	private void buildPanel() {
		setSize(dimension);
		setLayout(null);
	}

	private void initializeComponents() {
		tmpCockpit = new ArrayList<>();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 70, 1130, 390);
		add(tabbedPane);

		dados = new JPanel();
		lista = new JPanel();

		dados.setLayout(null);
		lista.setLayout(null);

		tabbedPane.add("   DADOS   ", dados);
		tabbedPane.add("   LISTA   ", lista);

		scrLista = new JScrollPane();
		scrLista.setBounds(250, 30, 800, 310);
		lista.add(scrLista);

		tbLista = new JTable();
		tbLista.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrLista.setViewportView(tbLista);

		lblBusca = new JLabel("BUSCAR:");
		lblBusca.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusca.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblBusca.setBounds(30, 30, 200, 30);
		lista.add(lblBusca);

		txtBusca = new JTextField();
		txtBusca.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusca.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtBusca.setBounds(30, 60, 200, 30);
		lista.add(txtBusca);

		lblCodGM = new JLabel("Código GM:");
		lblCodGM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodGM.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblCodGM.setBounds(338, 44, 170, 30);
		dados.add(lblCodGM);

		lblCodConti = new JLabel("Código Conti:");
		lblCodConti.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodConti.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblCodConti.setBounds(338, 94, 170, 30);
		dados.add(lblCodConti);

		lblHistorico = new JLabel("Histórico:");
		lblHistorico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHistorico.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblHistorico.setBounds(338, 144, 170, 30);
		dados.add(lblHistorico);

		lblApelido = new JLabel("Apelido:");
		lblApelido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApelido.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblApelido.setBounds(338, 194, 170, 30);
		dados.add(lblApelido);

		lblApelidoSerie = new JLabel("Apelido Série:");
		lblApelidoSerie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApelidoSerie.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblApelidoSerie.setBounds(338, 244, 170, 30);
		dados.add(lblApelidoSerie);

		btnNovo = new JLabel("Novo");
		btnNovo.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNovo.setHorizontalAlignment(SwingConstants.CENTER);
		btnNovo.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnNovo.setBounds(322, 317, 171, 30);
		dados.add(btnNovo);

		btnSalva = new JLabel("Salvar");
		btnSalva.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSalva.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalva.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnSalva.setBounds(322, 317, 171, 30);
		btnSalva.setVisible(false);
		dados.add(btnSalva);

		btnEdita = new JLabel("Editar");
		btnEdita.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEdita.setHorizontalAlignment(SwingConstants.CENTER);
		btnEdita.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnEdita.setBounds(505, 317, 171, 30);
		dados.add(btnEdita);

		btnCancela = new JLabel("Cancelar");
		btnCancela.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCancela.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancela.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnCancela.setBounds(505, 317, 171, 30);
		btnCancela.setVisible(false);
		dados.add(btnCancela);

		btnExclui = new JLabel("Excluir");
		btnExclui.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnExclui.setHorizontalAlignment(SwingConstants.CENTER);
		btnExclui.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnExclui.setBounds(688, 317, 171, 30);
		dados.add(btnExclui);

		btnProximo = new JLabel(new ImageIcon("icofeps\\manut\\next_64.png"));
		btnProximo.setBounds(870, 120, 48, 64);
		btnProximo.setHorizontalAlignment(SwingConstants.CENTER);
		btnProximo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnProximo.setToolTipText("Próximo");
		dados.add(btnProximo);

		btnAnterior = new JLabel(new ImageIcon("icofeps\\manut\\prev_64.png"));
		btnAnterior.setBounds(230, 120, 48, 64);
		btnAnterior.setHorizontalAlignment(SwingConstants.CENTER);
		btnAnterior.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAnterior.setToolTipText("Anterior");
		dados.add(btnAnterior);

		btnPrimeiro = new JLabel(new ImageIcon("icofeps\\manut\\first_64.png"));
		btnPrimeiro.setBounds(166, 120, 54, 64);
		btnPrimeiro.setHorizontalAlignment(SwingConstants.CENTER);
		btnPrimeiro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrimeiro.setToolTipText("Primeiro");
		dados.add(btnPrimeiro);

		btnUltimo = new JLabel(new ImageIcon("icofeps\\manut\\last_64.png"));
		btnUltimo.setBounds(928, 120, 54, 64);
		btnUltimo.setHorizontalAlignment(SwingConstants.CENTER);
		btnUltimo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUltimo.setToolTipText("Último");
		dados.add(btnUltimo);

		txtCodGM = new JTextField();
		txtCodGM.setEditable(false);
		txtCodGM.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodGM.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCodGM.setBounds(528, 44, 270, 30);
		txtCodGM.setEnabled(false);
		dados.add(txtCodGM);

		txtCodConti = new JTextField();
		txtCodConti.setEditable(false);
		txtCodConti.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodConti.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCodConti.setBounds(528, 94, 270, 30);
		txtCodConti.setEnabled(false);
		dados.add(txtCodConti);

		txtHistorico = new JTextField();
		txtHistorico.setEditable(false);
		txtHistorico.setHorizontalAlignment(SwingConstants.LEFT);
		txtHistorico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtHistorico.setBounds(528, 144, 270, 30);
		dados.add(txtHistorico);

		txtApelido = new JTextField();
		txtApelido.setEditable(false);
		txtApelido.setHorizontalAlignment(SwingConstants.LEFT);
		txtApelido.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtApelido.setBounds(528, 194, 270, 30);
		dados.add(txtApelido);

		txtApelidoSerie = new JTextField();
		txtApelidoSerie.setEditable(false);
		txtApelidoSerie.setHorizontalAlignment(SwingConstants.LEFT);
		txtApelidoSerie.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtApelidoSerie.setBounds(528, 244, 270, 30);
		dados.add(txtApelidoSerie);

		tabbedPane.setFont(new Font("Stencil", Font.PLAIN, 18));

		textList = new ArrayList<>();
		textList.add(txtCodGM);
		textList.add(txtCodConti);
		textList.add(txtHistorico);
		textList.add(txtApelido);
		textList.add(txtApelidoSerie);

		btnList = new ArrayList<>();
		btnList.add(btnNovo);
		btnList.add(btnSalva);
		btnList.add(btnEdita);
		btnList.add(btnCancela);
		btnList.add(btnExclui);
	}

	private void initializeListeners() {
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedComponent() == lista)
					txtBusca.requestFocusInWindow();
			}
		});
		
		txtBusca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBusca.requestFocusInWindow();
			}
		});
		
		txtBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				ArrayList<Cockpit> array = new ArrayList<>();

				for (int i = 0; i < tmpCockpit.size(); i++) {
					if (tmpCockpit.get(i).getCodigo_gm().contains(txtBusca.getText().toUpperCase()))
						array.add(tmpCockpit.get(i));
					else if (tmpCockpit.get(i).getCodigo_conti().contains(txtBusca.getText().toUpperCase()))
						array.add(tmpCockpit.get(i));
					else if (tmpCockpit.get(i).getHistorico().contains(txtBusca.getText().toUpperCase()))
						array.add(tmpCockpit.get(i));
					else if (tmpCockpit.get(i).getApelido().contains(txtBusca.getText().toUpperCase()))
						array.add(tmpCockpit.get(i));
					else if (tmpCockpit.get(i).getApelido_serie().contains(txtBusca.getText().toUpperCase()))
						array.add(tmpCockpit.get(i));
				}

				mdLista.clear();

				for (int i = 0; i < array.size(); i++) {
					mdLista.add(array.get(i));
				}
				try {
					tbLista.setRowSelectionInterval(0, 0);
				} catch (Exception exc) {
				
				}

				if (txtBusca.getText().isEmpty()) {
					mdLista.clear();
					for (int i = 0; i < tmpCockpit.size(); i++) {
						mdLista.add(tmpCockpit.get(i));
					}
					tbLista.setRowSelectionInterval(0, 0);
				}
			}
		});

		tbLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCodGM.setText((String) mdLista.getValueAt(tbLista.getSelectedRow(), 0));
				txtCodConti.setText((String) mdLista.getValueAt(tbLista.getSelectedRow(), 1));
				txtHistorico.setText((String) mdLista.getValueAt(tbLista.getSelectedRow(), 2));
				txtApelido.setText((String) mdLista.getValueAt(tbLista.getSelectedRow(), 3));
				txtApelidoSerie.setText((String) mdLista.getValueAt(tbLista.getSelectedRow(), 4));
			}
		});

		addMouseListenerBtn(btnNovo);
		addMouseListenerBtn(btnEdita);
		addMouseListenerBtn(btnExclui);
		addMouseListenerBtn(btnSalva);
		addMouseListenerBtn(btnCancela);
		addMouseListenerBtn(btnProximo);
		addMouseListenerBtn(btnAnterior);
		addMouseListenerBtn(btnPrimeiro);
		addMouseListenerBtn(btnUltimo);

		addKeyListenerText(txtCodGM);
		addKeyListenerText(txtCodConti);
		addKeyListenerText(txtHistorico);
		addKeyListenerText(txtApelido);
		addKeyListenerText(txtApelidoSerie);
	}

	private void addMouseListenerBtn(JLabel btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btn == btnNovo)
					novo();
				else if (btn == btnSalva)
					salva();
				else if (btn == btnCancela)
					cancela();
				else if (btn == btnEdita)
					edita();
				else if (btn == btnExclui)
					exclui();
				else if (btn == btnProximo)
					proximo();
				else if (btn == btnAnterior)
					anterior();
				else if (btn == btnPrimeiro)
					primeiro();
				else if (btn == btnUltimo)
					ultimo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btn == btnProximo || btn == btnAnterior) {
					if (btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_48.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_48.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if (btn == btnPrimeiro)
						btnPrimeiro.setIcon(new ImageIcon("icofeps\\manut\\first_48.png"));
					else
						btnUltimo.setIcon(new ImageIcon("icofeps\\manut\\last_48.png"));
				} else {
					btn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
					btn.setFont(new Font("Stencil", Font.PLAIN, 17));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btn == btnProximo || btn == btnAnterior) {
					if (btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_64.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_64.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if (btn == btnPrimeiro)
						btnPrimeiro.setIcon(new ImageIcon("icofeps\\manut\\first_64.png"));
					else
						btnUltimo.setIcon(new ImageIcon("icofeps\\manut\\last_64.png"));
				} else {
					btn.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
					btn.setFont(new Font("Stencil", Font.PLAIN, 18));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btn == btnProximo || btn == btnAnterior) {
					if (btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_64.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_64.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if (btn == btnPrimeiro)
						btnPrimeiro.setIcon(new ImageIcon("icofeps\\manut\\first_64.png"));
					else
						btnUltimo.setIcon(new ImageIcon("icofeps\\manut\\last_64.png"));
				} else {
					btn.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
		});
	}

	private void novo() {
		novo = true;

		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(true);
			else
				btnList.get(i).setVisible(false);
		}

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setEnabled(true);
			textList.get(i).setEditable(true);
			textList.get(i).setText("");
		}

		txtCodGM.requestFocusInWindow();
	}

	private void cancela() {

		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(false);
			else
				btnList.get(i).setVisible(true);
		}

		for (int i = 0; i < textList.size(); i++) {
			if (textList.get(i) == txtCodGM || textList.get(i) == txtCodConti)
				textList.get(i).setEnabled(false);
			else
				textList.get(i).setEditable(false);
			Alerta.desativaPopup(textList.get(i));
		}

		fillDados();

		requestFocusInWindow();
	}

	private void salva() {
		for (int i = 0; i < textList.size(); i++) {
			if (textList.get(i).getText().length() < 1) {
				textList.get(i).requestFocusInWindow();
				Alerta.ativaPopup(textList.get(i), "Preencha esse campo", 20, 20);
				return;
			}
		}

		if (novo)
			save();
		else
			update();

		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(false);
			else
				btnList.get(i).setVisible(true);
		}

		for (int i = 0; i < textList.size(); i++) {
			if (textList.get(i) == txtCodGM || textList.get(i) == txtCodConti)
				textList.get(i).setEnabled(false);
			else
				textList.get(i).setEditable(false);
		}

		fillTable();
		fillDados();

		requestFocusInWindow();
	}

	private void edita() {
		novo = false;

		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(true);
			else
				btnList.get(i).setVisible(false);
		}

		for (int i = 0; i < textList.size(); i++) {
			if (textList.get(i) == txtCodGM || textList.get(i) == txtCodConti)
				textList.get(i).setEnabled(false);
			else
				textList.get(i).setEditable(true);
		}

		txtHistorico.requestFocusInWindow();
	}

	private void save() {
		String consultaSQL = "INSERT INTO gm_conti (codigo_gm, codigo_conti, historico, apelido, apelido_serie) VALUES ("
				+ "'" + txtCodGM.getText() + "', " + "'" + txtCodConti.getText() + "', " + "'" + txtHistorico.getText()
				+ "', " + "'" + txtApelido.getText() + "', " + "'" + txtApelidoSerie.getText() + "')";

		if (ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "O material " + txtCodGM.getText() + " foi criado com sucesso!");
		else
			JOptionPane.showMessageDialog(null, "Não foi possível criar o material " + txtCodGM.getText());

		fillTable();
		fillDados();
	}

	private void update() {
		String consultaSQL = "UPDATE gm_conti SET historico = '" + txtHistorico.getText() + "', apelido = '"
				+ txtApelido.getText() + "', " + "apelido_serie = '" + txtApelidoSerie.getText()
				+ "' WHERE codigo_gm = '" + txtCodGM.getText() + "'";

		if (ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "O material " + txtCodGM.getText() + " foi atualizado com sucesso!");
		else
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o material " + txtCodGM.getText());

		fillTable();
		fillDados();
	}

	private void exclui() {
		new Delete().setVisible(true);
	}

	private void proximo() {
		if (tbLista.getSelectedRow() + 1 == mdLista.getRowCount()) {
			fillDados();
		} else {
			txtCodGM.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() + 1, 0));
			txtCodConti.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() + 1, 1));
			txtHistorico.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() + 1, 2));
			txtApelido.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() + 1, 3));
			txtApelidoSerie.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() + 1, 4));

			tbLista.setRowSelectionInterval(tbLista.getSelectedRow() + 1, tbLista.getSelectedRow() + 1);
		}
	}

	private void anterior() {
		if (tbLista.getSelectedRow() - 1 == -1) {
			txtCodGM.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 0));
			txtCodConti.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 1));
			txtHistorico.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 2));
			txtApelido.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 3));
			txtApelidoSerie.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 4));

			tbLista.setRowSelectionInterval(mdLista.getRowCount() - 1, mdLista.getRowCount() - 1);
		} else {
			txtCodGM.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() - 1, 0));
			txtCodConti.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() - 1, 1));
			txtHistorico.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() - 1, 2));
			txtApelido.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() - 1, 3));
			txtApelidoSerie.setText((String) mdLista.getValueAt(tbLista.getSelectedRow() - 1, 4));

			tbLista.setRowSelectionInterval(tbLista.getSelectedRow() - 1, tbLista.getSelectedRow() - 1);
		}
	}

	private void primeiro() {
		fillDados();
	}

	private void ultimo() {
		txtCodGM.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 0));
		txtCodConti.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 1));
		txtHistorico.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 2));
		txtApelido.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 3));
		txtApelidoSerie.setText((String) mdLista.getValueAt(mdLista.getRowCount() - 1, 4));

		tbLista.setRowSelectionInterval(mdLista.getRowCount() - 1, mdLista.getRowCount() - 1);
	}

	private void buildTable() {
		createTable();
		fillTable();
	}

	private void createTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(ModelTableCockpit.CODIGO_GM);
		coluna.add(ModelTableCockpit.CODIGO_CONTINENTAL);
		coluna.add(ModelTableCockpit.HISTORICO);
		coluna.add(ModelTableCockpit.APELIDO);
		coluna.add(ModelTableCockpit.APELIDO_SERIE);

		mdLista = new ModelTableCockpit(coluna);

		tbLista.setModel(mdLista);
		tbLista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (int i = 0; i < tbLista.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tbLista.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			if (mdLista.getColumnName(i).equals(ModelTableCockpit.HISTORICO))
				tbLista.getColumnModel().getColumn(i).setPreferredWidth(200);
			else
				tbLista.getColumnModel().getColumn(i).setPreferredWidth(142);
		}

		tbLista.getTableHeader().setReorderingAllowed(false);
		tbLista.getTableHeader().setResizingAllowed(false);
		mdLista.fireTableDataChanged();
	}

	private void fillTable() {
		List<Cockpit> lista = new ArrayList<>();
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM gm_conti ORDER BY codigo_gm DESC";
			rs = ConnectionFeps.query(consultaSQL);
			tmpCockpit.clear();
			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String codigo_gm = rs.getString("codigo_gm").trim();
					String codigo_conti = rs.getString("codigo_conti").trim();
					String historico = rs.getString("historico").trim();
					String apelido = rs.getString("apelido").trim();
					String apelido_serie = rs.getString("apelido_serie").trim();

					Cockpit cockpit = new Cockpit(codigo_gm, codigo_conti, historico, apelido, apelido_serie);

					lista.add(cockpit);
					tmpCockpit.add(cockpit);

					rs.next();

				}

				mdLista.clear();
				mdLista.addCockpitList(lista);

			} else
				mdLista.clear();

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a tabela com os dados!");
		}
	}

	private void fillDados() {
		txtCodGM.setText((String) mdLista.getValueAt(0, 0));
		txtCodConti.setText((String) mdLista.getValueAt(0, 1));
		txtHistorico.setText((String) mdLista.getValueAt(0, 2));
		txtApelido.setText((String) mdLista.getValueAt(0, 3));
		txtApelidoSerie.setText((String) mdLista.getValueAt(0, 4));
		tbLista.setRowSelectionInterval(0, 0);
	}

	private void addKeyListenerText(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (txt == txtCodGM) {
					if (!caracteres.contains(evt.getKeyChar() + "") || txt.getText().length() >= 8) {
						evt.consume();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Alerta.desativaPopup(txt);

				if (txt == txtCodGM) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						txtCodConti.requestFocusInWindow();
				} else if (txt == txtCodConti) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						txtHistorico.requestFocusInWindow();
				} else if (txt == txtHistorico) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						txtApelido.requestFocusInWindow();
				} else if (txt == txtApelido) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						txtApelidoSerie.requestFocusInWindow();
				} else if (txt == txtApelidoSerie)
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						salva();
			}
		});
	}

	private class ModelTableCockpit extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public static final String CODIGO_GM = "Código GM";
		public static final String CODIGO_CONTINENTAL = "Código Continental";
		public static final String HISTORICO = "Histórico";
		public static final String APELIDO = "Apelido";
		public static final String APELIDO_SERIE = "Apelido Série";

		private List<Cockpit> linhas;
		private List<String> colunas;

		public ModelTableCockpit(ArrayList<String> coluna) {
			this.colunas = coluna;
			this.linhas = new ArrayList<>();
		}

		@Override
		public int getColumnCount() {
			return this.colunas.size();
		}

		@Override
		public int getRowCount() {
			return this.linhas.size();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return this.colunas.get(columnIndex);
		};

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (linhas.isEmpty())
				return null;
			else {
				Cockpit cockpit = linhas.get(rowIndex);

				switch (getColumnName(columnIndex)) {
				case (CODIGO_GM):
					return cockpit.getCodigo_gm();
				case (CODIGO_CONTINENTAL):
					return cockpit.getCodigo_conti();
				case (HISTORICO):
					return cockpit.getHistorico();
				case (APELIDO):
					return cockpit.getApelido();
				case (APELIDO_SERIE):
					return cockpit.getApelido_serie();
				default:
					throw new IndexOutOfBoundsException("columnIndex out of bounds");
				}
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Cockpit cockpit = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (CODIGO_GM):
				cockpit.setCodigo_gm((String) aValue);
				break;
			case (CODIGO_CONTINENTAL):
				cockpit.setCodigo_conti((String) aValue);
				break;
			case (HISTORICO):
				cockpit.setHistorico((String) aValue);
				break;
			case (APELIDO):
				cockpit.setApelido((String) aValue);
				break;
			case (APELIDO_SERIE):
				cockpit.setApelido_serie((String) aValue);
				break;
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}

			fireTableDataChanged();
			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public void addCockpitList(List<Cockpit> newList) {
			linhas.addAll(newList);
			fireTableRowsInserted(getRowCount(), getRowCount() + newList.size());
		}

		public void clear() {
			linhas.clear();
			fireTableDataChanged();
		}

		public void add(Cockpit cockpit) {
			if (!linhas.contains(cockpit)) {
				linhas.add(cockpit);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
		}
	}

	private class Cockpit {
		private String codigo_gm, codigo_conti, historico, apelido, apelido_serie;

		public Cockpit(String codigo_gm, String codigo_conti, String historico, String apelido, String apelido_serie) {
			this.codigo_gm = codigo_gm;
			this.codigo_conti = codigo_conti;
			this.historico = historico;
			this.apelido = apelido;
			this.apelido_serie = apelido_serie;
		}

		public String getCodigo_gm() {
			return codigo_gm;
		}

		public String getCodigo_conti() {
			return codigo_conti;
		}

		public String getHistorico() {
			return historico;
		}

		public String getApelido() {
			return apelido;
		}

		public String getApelido_serie() {
			return apelido_serie;
		}

		public void setCodigo_gm(String codigo_gm) {
			this.codigo_gm = codigo_gm;
		}

		public void setCodigo_conti(String codigo_conti) {
			this.codigo_conti = codigo_conti;
		}

		public void setHistorico(String historico) {
			this.historico = historico;
		}

		public void setApelido(String apelido) {
			this.apelido = apelido;
		}

		public void setApelido_serie(String apelido_serie) {
			this.apelido_serie = apelido_serie;
		}
	}

	private class Delete extends JDialog {
		private static final long serialVersionUID = 1L;

		private JLabel lblEncerraDia = new JLabel("Remover o material " + txtCodGM.getText() + "?");
		private JLabel btnSim = new JLabel("SIM");
		private JLabel btnNao = new JLabel("NÃO");

		public Delete() {
			buildPanel();
			initializeComponents();
			initializeListeners();
		}

		private void buildPanel() {
			this.setBounds(0, 0, 350, 140);
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
			lblEncerraDia.setBounds(10, 10, 330, 90);
			getContentPane().add(lblEncerraDia);

			btnSim.setHorizontalAlignment(SwingConstants.CENTER);
			btnSim.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnSim.setBounds(155, 100, 90, 30);
			btnSim.setForeground(Color.LIGHT_GRAY);
			btnSim.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnSim);

			btnNao.setHorizontalAlignment(SwingConstants.CENTER);
			btnNao.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnNao.setBounds(250, 100, 90, 30);
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
						fillDados();
						fillTable();
						requestFocusInWindow();
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
			String consultaSQL = "DELETE gm_conti WHERE codigo_gm = '" + txtCodGM.getText() + "'";
			if (ConnectionFeps.update(consultaSQL))
				JOptionPane.showMessageDialog(null, "O material " + txtCodGM.getText() + " foi removido com sucesso!");
			else
				JOptionPane.showMessageDialog(null, "Não foi possível remover o usuário " + txtCodGM.getText());
		}
	}
}
