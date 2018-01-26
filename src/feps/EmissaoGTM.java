package feps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class EmissaoGTM extends JPanel {
	private static final long serialVersionUID = 1L;

	// private Dimension dimension = new Dimension(1366, 768);
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	private JTable tbSequencia, tbGTM;
	private JLabel lblEmissao, lblSerie, lblEnviado, lblAEnviar, lblAtenderTotal, lblGTMCriada;
	private JLabel btnConfirmaSaida, btnGeraGTM, btnAtualiza;
	private JCheckBox chkManual;
	private JTextField txtSerie, txtAEnviar, txtEnviado, txtAtenderTotal, txtGTMCriada;
	private JScrollPane scrSequencia, scrCockpit, scrSerie;
	private JTree trCockpit;
	private DefaultTreeModel trModel;
	private DefaultMutableTreeNode dmtCockpit;
	private Cursor cursor;
	private List<Ordem> listOld = new ArrayList<>();

	private FepsModelTable fmtSequencia, fmtGTM;

	private GroupLayout groupLayout;

	private Timer timer;
	private TimerTask task;

	private Relatorio relatorio;

	private static final String COB_LE = "94759777";
	private static final String COB_LD = "94759779";

	private static final int MIN_WIDTH = 1366;
	private static final int MIN_HEIGHT = 768;

	public EmissaoGTM() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
		initializeListeners();
		createSequenciaTable();
		createSerieTable();
	}

	private void buildPanel() {
		this.setPreferredSize(dimension);
		setBackground(Color.WHITE);

		groupLayout = new GroupLayout(this);
	}

	private void initializeComponents() {
		Image image = new ImageIcon("icofeps\\unavail.png").getImage();
		Point point = new Point(10, 10);
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, point, "unavail");

		relatorio = new Relatorio();

		lblEmissao = new JLabel("Emissão de GTM");
		lblEmissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmissao.setFont(new Font("Stencil", Font.PLAIN, 70));

		lblSerie = new JLabel("Série do Cockpit:");
		lblSerie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerie.setFont(new Font("Stencil", Font.PLAIN, 18));

		lblEnviado = new JLabel("Cockpits enviados:");
		lblEnviado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviado.setFont(new Font("Stencil", Font.PLAIN, 18));

		lblAEnviar = new JLabel("Cockpits a enviar:");
		lblAEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAEnviar.setFont(new Font("Stencil", Font.PLAIN, 18));

		lblAtenderTotal = new JLabel("Total a atender:");
		lblAtenderTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtenderTotal.setFont(new Font("Stencil", Font.PLAIN, 18));

		lblGTMCriada = new JLabel("GTM's criadas no dia:");
		lblGTMCriada.setHorizontalAlignment(SwingConstants.CENTER);
		lblGTMCriada.setFont(new Font("Stencil", Font.PLAIN, 18));

		chkManual = new JCheckBox("Sistema manual");
		chkManual.setFont(new Font("Stencil", Font.PLAIN, 18));

		btnConfirmaSaida = new JLabel("Confirmar saída");
		btnConfirmaSaida.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnConfirmaSaida.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmaSaida.setFont(new Font("Stencil", Font.PLAIN, 18));

		btnGeraGTM = new JLabel("Gera GTM");
		btnGeraGTM.setHorizontalAlignment(SwingConstants.CENTER);
		btnGeraGTM.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnGeraGTM.setForeground(Color.DARK_GRAY);
		btnGeraGTM.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		btnGeraGTM.setCursor(cursor);

		btnAtualiza = new JLabel("Atualiza");
		btnAtualiza.setHorizontalAlignment(SwingConstants.CENTER);
		btnAtualiza.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnAtualiza.setForeground(Color.DARK_GRAY);
		btnAtualiza.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		btnAtualiza.setCursor(cursor);

		txtSerie = new JTextField();
		txtSerie.setFont(new Font("Calibri", Font.PLAIN, 20));

		txtAEnviar = new JTextField();
		txtAEnviar.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtAEnviar.setEditable(false);

		txtEnviado = new JTextField();
		txtEnviado.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtEnviado.setEditable(false);

		txtAtenderTotal = new JTextField();
		txtAtenderTotal.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtAtenderTotal.setEditable(false);

		txtGTMCriada = new JTextField();
		txtGTMCriada.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtGTMCriada.setEditable(false);

		tbSequencia = new JTable();
		tbSequencia.setFont(new Font("Calibri", Font.PLAIN, 18));

		tbGTM = new JTable();
		tbGTM.setFont(new Font("Calibri", Font.PLAIN, 19));

		trCockpit = new JTree();
		trCockpit.setFont(new Font("Calibri", Font.PLAIN, 18));

		scrSequencia = new JScrollPane();
		scrSequencia.setViewportView(tbSequencia);

		scrSerie = new JScrollPane();
		scrSerie.setViewportView(tbGTM);

		scrCockpit = new JScrollPane();
		scrCockpit.setViewportView(trCockpit);

		dmtCockpit = new DefaultMutableTreeNode("Cockpits");
		trModel = new DefaultTreeModel(dmtCockpit);
		trCockpit.setModel(trModel);
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addComponent(lblEmissao, GroupLayout.PREFERRED_SIZE,
								calculate(MIN_WIDTH - 20, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(278, MIN_WIDTH, dimension.width))
						.addComponent(scrSequencia, GroupLayout.PREFERRED_SIZE,
								calculate(810, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
				.addGroup(
						groupLayout.createSequentialGroup().addGap(calculate(299, MIN_WIDTH, dimension.width))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createSequentialGroup().addGap(calculate(194, MIN_WIDTH, dimension.width))
										.addComponent(txtSerie, GroupLayout.PREFERRED_SIZE,
												calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
										.addComponent(lblSerie, GroupLayout.PREFERRED_SIZE,
												calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
								.addGap(calculate(3, MIN_WIDTH, dimension.width))
								.addComponent(btnConfirmaSaida, GroupLayout.PREFERRED_SIZE,
										calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
								.addGap(calculate(22, MIN_WIDTH, dimension.width)).addComponent(chkManual,
										GroupLayout.PREFERRED_SIZE, calculate(170, MIN_WIDTH, dimension.width),
										GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(274, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAEnviar, GroupLayout.PREFERRED_SIZE,
										calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(193, MIN_WIDTH, dimension.width)).addComponent(txtAEnviar,
												GroupLayout.PREFERRED_SIZE, calculate(100, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE)))
						.addGap(calculate(2, MIN_WIDTH, dimension.width))
						.addComponent(btnAtualiza, GroupLayout.PREFERRED_SIZE,
								calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(lblAtenderTotal, GroupLayout.PREFERRED_SIZE,
								calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAtenderTotal, GroupLayout.PREFERRED_SIZE,
								calculate(100, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(274, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnviado, GroupLayout.PREFERRED_SIZE,
										calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(calculate(193, MIN_WIDTH, dimension.width)).addComponent(txtEnviado,
												GroupLayout.PREFERRED_SIZE, calculate(100, MIN_WIDTH, dimension.width),
												GroupLayout.PREFERRED_SIZE)))
						.addGap(calculate(2, MIN_WIDTH, dimension.width))
						.addComponent(btnGeraGTM, GroupLayout.PREFERRED_SIZE,
								calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(lblGTMCriada, GroupLayout.PREFERRED_SIZE,
								calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGTMCriada, GroupLayout.PREFERRED_SIZE,
								calculate(100, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(348, MIN_WIDTH, dimension.width))
						.addComponent(scrSerie, GroupLayout.PREFERRED_SIZE, calculate(335, MIN_WIDTH, dimension.width),
								GroupLayout.PREFERRED_SIZE)
						.addComponent(scrCockpit, GroupLayout.PREFERRED_SIZE,
								calculate(335, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)));
	}

	private void buildVerticalLayout() {
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
				.addComponent(lblEmissao, GroupLayout.PREFERRED_SIZE,
						calculate(90, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
				.addGap(calculate(2, MIN_HEIGHT - 80, dimension.height - 80))
				.addComponent(scrSequencia, GroupLayout.PREFERRED_SIZE,
						calculate(210, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
				.addGap(calculate(4, MIN_HEIGHT - 80, dimension.height - 80))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSerie, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSerie, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirmaSaida, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(5, MIN_HEIGHT - 80, dimension.height - 80)).addComponent(chkManual,
										GroupLayout.PREFERRED_SIZE,
										calculate(20, MIN_HEIGHT - 80, dimension.height - 80),
										GroupLayout.PREFERRED_SIZE)))
				.addGap(calculate(36, MIN_HEIGHT - 80, dimension.height - 80))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAEnviar, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAEnviar, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAtualiza, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAtenderTotal, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAtenderTotal, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
				.addGap(calculate(5, MIN_HEIGHT - 80, dimension.height - 80))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnviado, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEnviado, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGeraGTM, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGTMCriada, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGTMCriada, GroupLayout.PREFERRED_SIZE,
								calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
				.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrSerie, GroupLayout.PREFERRED_SIZE,
								calculate(217, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(scrCockpit, GroupLayout.PREFERRED_SIZE,
								calculate(217, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))));
	}

	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}

	private void initializeListeners() {
		addMouseListenerLabel(btnConfirmaSaida);
		addMouseListenerLabel(btnGeraGTM);
		addMouseListenerLabel(btnAtualiza);
		addTextListener(txtSerie);
		addChkListener();
	}

	private void addChkListener() {

		chkManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chkManual.isSelected()) {
					stop();
					btnGeraGTM.setCursor(Cursor.getDefaultCursor());
					btnGeraGTM.setForeground(Color.BLACK);
					btnGeraGTM.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
					btnAtualiza.setCursor(Cursor.getDefaultCursor());
					btnAtualiza.setForeground(Color.BLACK);
					btnAtualiza.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
					fmtGTM.setCheckBoxEditable(true);
				} else {
					start();
					btnGeraGTM.setCursor(cursor);
					btnGeraGTM.setForeground(Color.DARK_GRAY);
					btnGeraGTM.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
					btnAtualiza.setCursor(cursor);
					btnAtualiza.setForeground(Color.DARK_GRAY);
					btnAtualiza.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
					fmtGTM.setCheckBoxEditable(false);
				}
			}
		});
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (label == btnConfirmaSaida)
					saidaGTM();
				else if (label == btnAtualiza) {
					if (chkManual.isSelected())
						atualiza();
			
				} else if (label == btnGeraGTM) {
					if (chkManual.isSelected())
						forcaSaidaGTM();
				}
				super.mouseClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 17));
				if (label == btnGeraGTM || label == btnAtualiza)
					if (!chkManual.isSelected()) {
						label.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
						label.setFont(new Font("Stencil", Font.PLAIN, 18));
					}
				super.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 18));
				if (label == btnGeraGTM || label == btnAtualiza)
					if (!chkManual.isSelected()) {
						label.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
						label.setFont(new Font("Stencil", Font.PLAIN, 18));
					}
				super.mouseReleased(e);
			}
		});
	}

	private void addTextListener(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saidaGTM();
				}
			}
		});
	}

	protected void start() {
		stop();
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				fillTableSequencia();
				fillTableSerie();
				fillTree();
				getEnviar();
				getTotalDia();
				getTotalEnviado();
				getTotalAtender();
				if (fmtGTM.getRowCount() == getNumFechaGTM())
					geraGTM();
			}
		};
		timer.schedule(task, 0, 10000);
	}

	protected void stop() {
		if (timer != null && task != null) {
			timer.cancel();
			task.cancel();

			timer = null;
			task = null;
		}
	}

	private int calculateSizeTable(int columnMinWidth, int tableMinWidth, int tableWidth) {
		return (tableWidth * columnMinWidth) / tableMinWidth;
	}

	private void atualiza() {
		fillTableSequencia();
		fillTableSerie();
		fillTree();
	}
	
	private void createSequenciaTable() {

		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(FepsModelTable.SEQ_GM);
		coluna.add(FepsModelTable.ORDEM_CONTI_DATA);
		coluna.add(FepsModelTable.APELIDO);
		coluna.add(FepsModelTable.ORDEM_CONTI_SERIE);
		coluna.add(FepsModelTable.PART_NUMBER_GM);
		coluna.add(FepsModelTable.STATUS_COCKPIT);
		coluna.add(FepsModelTable.ORDEM_ENTRADA);

		fmtSequencia = new FepsModelTable(coluna);

		tbSequencia.setModel(fmtSequencia);
		tbSequencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tbSequencia.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tbSequencia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			if (!fmtSequencia.getColumnName(i).contains(FepsModelTable.STATUS_COCKPIT)
					&& !fmtSequencia.getColumnName(i).contains(FepsModelTable.ORDEM_ENTRADA))
				tbSequencia.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(157, 810, calculate(810, MIN_WIDTH, dimension.width)));
			else {
				tbSequencia.getColumnModel().getColumn(i).setWidth(0);
				tbSequencia.getColumnModel().getColumn(i).setMinWidth(0);
				tbSequencia.getColumnModel().getColumn(i).setPreferredWidth(0);
				tbSequencia.getColumnModel().getColumn(i).setMaxWidth(0);
			}
		}

		tbSequencia.getTableHeader().setReorderingAllowed(false);
		tbSequencia.getTableHeader().setResizingAllowed(false);
		fmtSequencia.fireTableDataChanged();

	}

	private void fillTableSequencia() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT Ordem_GM.*, GM_Conti.apelido FROM GM_Conti RIGHT OUTER JOIN Ordem_GM ON GM_Conti.Codigo_gm  = ordem_gm.Part_Number_GM "
					+ "WHERE (STATUS_CPROD_CODIGO = '" + ConstantsFEPS.COCKPIT_IMPRESSA.getStringValue() + "' "
					+ "OR STATUS_CPROD_CODIGO = '" + ConstantsFEPS.COCKPIT_INICIADO.getStringValue()
					+ "') ORDER BY Ordem_Entrada";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm").trim();
					String apelido = rs.getString("apelido").trim();
					String ordem_serie = rs.getString("ordem_conti_serie").trim();
					String ordem_data = rs.getString("data_hora").trim();
					String seq_gm = rs.getString("ordem_gm_doc").trim();
					String status_cockpit = rs.getString("status_cprod_codigo").trim();
					String ordem_entrada = rs.getString("ordem_entrada").trim();

					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, ordem_data, ordem_entrada, null, null,
							seq_gm, status_cockpit);
					lista.add(ordem);

					rs.next();
				}

				fmtSequencia.clear();
				fmtSequencia.addOrdemList(lista);
			} else
				fmtSequencia.clear();

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar dados para a tabela sequencial!");
		}
	}

	private void createSerieTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(FepsModelTable.GERA);
		coluna.add(FepsModelTable.ORDEM_CONTI_SERIE);
		coluna.add(FepsModelTable.DOC_GM);

		fmtGTM = new FepsModelTable(coluna);
		tbGTM.setModel(fmtGTM);
		tbGTM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < tbGTM.getColumnCount(); i++) {

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

					if (chkManual.isSelected())
						c.setForeground(Color.BLACK);
					else
						c.setForeground(Color.DARK_GRAY);

					tbGTM.repaint();

					return c;
				}
			};
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			tbGTM.setRowHeight(20);

			if (!tbGTM.getColumnName(i).contains(FepsModelTable.GERA))
				tbGTM.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

			if (tbGTM.getColumnName(i).contains(FepsModelTable.GERA))
				tbGTM.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(50, 335, calculate(335, MIN_WIDTH, dimension.width)));
			else if (tbGTM.getColumnName(i).contains(FepsModelTable.ORDEM_CONTI_SERIE))
				tbGTM.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(185, 335, calculate(335, MIN_WIDTH, dimension.width)));
			else
				tbGTM.getColumnModel().getColumn(i)
						.setPreferredWidth(calculateSizeTable(91, 335, calculate(335, MIN_WIDTH, dimension.width)));
		}

		tbGTM.getTableHeader().setReorderingAllowed(false);
		tbGTM.getTableHeader().setResizingAllowed(false);
	}

	private void fillTableSerie() {
		String consultaSQL;
		ResultSet rs;
		List<Ordem> lista = new ArrayList<Ordem>();

		try {
			consultaSQL = "SELECT ordem_conti.*, ordem_gm.ordem_gm_doc AS doc_gm, ordem_gm.ordem_entrada AS ordem_entrada, ordem_gm.ordem_conti_serie "
					+ "AS serie_conti FROM ordem_conti, ordem_gm WHERE ordem_conti.ordem_conti_serie = ordem_gm.ordem_conti_serie "
					+ "AND ordem_conti.status_cockpit = '" + ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue() + "' "
					+ "AND ordem_conti.num_gtm = 0 ORDER BY ordem_gm.ordem_entrada";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String serieConti = rs.getString("ordem_conti_serie").trim();
					String gm_doc = rs.getString("doc_gm") == null ? "FORA" : rs.getString("doc_gm").trim();
					String ordem_entrada = rs.getString("ordem_entrada");
					String partNumber = rs.getString("part_number_gm");

					Ordem ordem = new Ordem(partNumber, null, serieConti, null, ordem_entrada, null, null, gm_doc,
							null);
					lista.add(ordem);

					rs.next();
				}

				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).setSelected(true);
				}

				if (!listOld.isEmpty() && listOld.size() <= lista.size())
					for (int i = 0; !listOld.isEmpty(); i++)
						lista.get(i).setSelected(listOld.remove(0).isSelected());

				fmtGTM.clear();
				fmtGTM.addOrdemList(lista);

				listOld = lista;

			} else
				fmtGTM.clear();

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a lista de GTM!");
		}
	}

	private void fillTree() {
		String consultaSQL;
		ResultSet rs;
		
		dmtCockpit.removeAllChildren();
		
		try {
			consultaSQL = "SELECT ordem_conti.*, ordem_gm.ordem_gm_doc, gm_conti.apelido FROM gm_conti, ordem_conti, ordem_gm "
					+ "WHERE ordem_conti.ordem_conti_serie = ordem_gm.ordem_conti_serie AND status_cockpit = '"
					+ ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue() + "'" + " AND ordem_conti.num_gtm = 0 "
					+ "AND ordem_conti.part_number_gm = gm_conti.codigo_gm ORDER BY ordem_conti.part_number_gm";
			rs = ConnectionFeps.query(consultaSQL);
			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String apelido = rs.getString("apelido").trim();
					String partNumber = rs.getString("part_number_gm").trim();
					String serieConti = rs.getString("ordem_conti_serie").trim();
					String docGM = rs.getString("ordem_gm_doc") == null ? "FORA" : rs.getString("ordem_gm_doc").trim();
					List<String> lista = new ArrayList<String>();

					lista.add(serieConti + " - " + docGM);

					rs.next();

					while (!rs.isAfterLast()) {
						if (partNumber.equals(rs.getString("part_number_gm").trim())) {
							serieConti = rs.getString("ordem_conti_serie").trim();
							docGM = rs.getString("ordem_gm_doc") == null ? "FORA" : rs.getString("ordem_gm_doc").trim();
							lista.add(serieConti + " - " + docGM);
							rs.next();
						} else
							break;
					}
					DefaultMutableTreeNode pai = new DefaultMutableTreeNode(apelido + " - " + lista.size());

					while (!lista.isEmpty())
						pai.add(new DefaultMutableTreeNode(lista.remove(0)));

					dmtCockpit.add(pai);
				}
			}
			
			trModel.reload();

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a árvore de cockpits!");
		}
	}

	private void getEnviar() {
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT COUNT(*) FROM ordem_gm, ordem_conti WHERE " + "ordem_gm.status_cprod_codigo = '"
					+ ConstantsFEPS.PROD_ATENDIDA.getStringValue() + "' "
					+ "AND ordem_gm.ordem_conti_serie = ordem_conti.ordem_conti_serie AND "
					+ " ordem_conti.status_cockpit = '" + ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue() + "'";

			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				txtAEnviar.setText(rs.getString(1));

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar total de cockpits a enviar!");
		}
	}

	private void getTotalDia() {
		String consultaSQL, data;
		ResultSet rs;

		try {
			data = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

			consultaSQL = "SELECT COUNT(data_hora) FROM gtm WHERE data_hora >= '" + data + " " + "00:00:00' "
					+ "AND data_hora <= '" + data + " " + "23:29:29'";

			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				txtGTMCriada.setText(rs.getString(1));

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o total de cockpits a enviar!");
		}
	}

	private void getTotalEnviado() {
		String consultaSQL, data;
		ResultSet rs;

		try {
			data = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

			consultaSQL = "SELECT COUNT(*) FROM ordem_conti WHERE status_cockpit = '"
					+ ConstantsFEPS.COCKPIT_ENTREGUE.getStringValue() + "'" + " AND data_impressao >= '" + data + " "
					+ "00:00:00'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				txtEnviado.setText(rs.getString(1));

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o total de GTM's enviadas!");
		}
	}

	private void getTotalAtender() {
		txtAtenderTotal.setText(String.valueOf(tbSequencia.getRowCount()));
	}

	public void requestFocusTextSequencia() {
		txtSerie.setText("");
		txtSerie.requestFocusInWindow();
	}

	private void saidaGTM() {
		String consultaSQL, serieConti;
		ResultSet rs, buffer;

		if (txtSerie.getText().length() == 13) {
			try {
				serieConti = txtSerie.getText().toUpperCase();
				consultaSQL = "SELECT * FROM ordem_conti WHERE ordem_conti_serie = '" + serieConti.trim() + "'";
				rs = ConnectionFeps.query(consultaSQL);

				if (rs.next()) {
					if (rs.getString("Part_Number_GM").trim().equals(fmtSequencia.getValueAt(0, 4).toString())) {
						if (rs.getString("Status_cockpit").equals(ConstantsFEPS.COCKPIT_IMPRESSA.getStringValue()) || rs
								.getString("Status_cockpit").equals(ConstantsFEPS.COCKPIT_BUFFER.getStringValue())) {
							alteraOrdemConti(ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue(),
									rs.getString("ordem_conti_serie"));
							alteraOrdemGM(ConstantsFEPS.PROD_ATENDIDA.getStringValue(),
									rs.getString("ordem_conti_serie"), fmtSequencia.getValueAt(0, 6).toString());
							gravaLogConti(rs.getString("ordem_conti_serie"), ConstantsFEPS.USER_ID.getStringValue(),
									rs.getString("status_cockpit"));
							gravaLogGM(
									fmtSequencia.getValueAt(0, 0).toString() + fmtSequencia.getValueAt(0, 1).toString(),
									ConstantsFEPS.USER_ID.getStringValue(), fmtSequencia.getValueAt(0, 5).toString());
							if (!rs.getString("ordem_conti_serie").trim()
									.equals(fmtSequencia.getValueAt(0, 3).toString())) {

								JOptionPane.showMessageDialog(null, "O cockpit da sequência irá para buffer!");

								consultaSQL = "SELECT * FROM ordem_gm WHERE ordem_conti_serie = '"
										+ rs.getString("ordem_conti_serie") + "'";

								buffer = ConnectionFeps.query(consultaSQL);

								if (buffer.next()) {
									alteraOrdemConti(ConstantsFEPS.COCKPIT_BUFFER.getStringValue(),
											fmtSequencia.getValueAt(0, 3).toString());
									alteraSerie(rs.getString("ordem_conti_serie"),
											fmtSequencia.getValueAt(0, 3).toString(),
											fmtSequencia.getValueAt(0, 0).toString());
								}

								ConnectionFeps.closeConnection(buffer, null, null);
							}

							if (chkManual.isSelected()) {
								fillTableSequencia();
								fillTableSerie();
								fillTree();
							} else
								start();

						} else
							JOptionPane.showMessageDialog(null, "Ordem Continental não impressa ou já utilizada!");
					} else
						JOptionPane.showMessageDialog(null, "Sequência fornecida diferente da sequência esperada!");
				} else
					JOptionPane.showMessageDialog(null, "Este número de série não existe!");

				ConnectionFeps.closeConnection(rs, null, null);
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao tentar executar a saída das GTM's!");
			} catch (IndexOutOfBoundsException ibe) {
				JOptionPane.showMessageDialog(null, "Não há nenhuma ordem na tabela de sequência!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Número de série inválido");
		}

		requestFocusTextSequencia();
	}

	private void alteraOrdemConti(String statusCockpit, String serieConti) {
		String consultaSQL;
		if (statusCockpit.equals(ConstantsFEPS.COCKPIT_BUFFER.getStringValue()))
			if (serieUsada(statusCockpit, serieConti))
				return;

		consultaSQL = "UPDATE ordem_conti SET status_cockpit = '" + statusCockpit + "' WHERE ordem_conti_serie = '"
				+ serieConti + "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível alterar a série: " + serieConti);
	}

	private boolean serieUsada(String statusCockpit, String serieConti) {
		String consultaSQL;
		ResultSet rs;
		boolean result = false;

		try {
			consultaSQL = "SELECT * FROM ordem_conti WHERE ordem_conti_serie = '" + serieConti + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if (rs.getString("status_cockpit").equals(ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue()))
					result = true;
			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não possível consultar se a série " + serieConti + " já foi usada!");
		}

		return result;
	}

	private void alteraOrdemGM(String idProd, String serieConti, String ordemEntrada) {
		String consultaSQL;

		consultaSQL = "UPDATE ordem_gm SET ordem_conti_serie = '" + serieConti + "', status_cprod_codigo = '" + idProd
				+ "' " + "WHERE ordem_entrada = '" + ordemEntrada + "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o status da ordem: " + ordemEntrada);

	}

	private void gravaLogConti(String serieConti, String idUser, String status_cockpit) {
		String consultaSQL;

		LocalDate data = LocalDate.now();
		LocalTime time = LocalTime.now();
		String data_conti = LocalDateTime.of(data, time).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));

		consultaSQL = "INSERT INTO log_conti (sequencia, userid, status_cockpit, data_conti) VALUES ('" + serieConti
				+ "', '" + idUser + "', " + "'" + status_cockpit + "', '" + data_conti + "')";

		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível gravar o log Continental da série: " + serieConti);

	}

	private void gravaLogGM(String seq_gm, String idUser, String status_cockpit) {
		String consultaSQL;

		LocalDate data = LocalDate.now();
		LocalTime time = LocalTime.now();
		String data_conti = LocalDateTime.of(data, time).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));

		consultaSQL = "INSERT INTO log_gm (sequencia, userid, status_cockpit, data_gm) VALUES ('" + seq_gm + "', '"
				+ idUser + "', " + "'" + status_cockpit + "', '" + data_conti + "')";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível gravar o log GM da sequência: " + seq_gm);
	}

	private void alteraSerie(String serieConti, String serieConti_old, String gmDoc) {
		String consultaSQL = "UPDATE ordem_gm SET ordem_conti_serie = '" + serieConti + "' WHERE ordem_conti_serie = '"
				+ serieConti_old + "' AND ordem_gm_doc = '" + gmDoc + "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null,
					"Não foi possível alterar a série: " + serieConti_old + " para a série: " + serieConti);
	}

	private void forcaSaidaGTM() {
		if (fmtGTM.getCountSelected() < getNumFechaGTM()) {
			Object[] options = { "Sim", "Não" };
			int resposta = JOptionPane.showOptionDialog(null, "Número não atingidos de cockpit, emitir mesmo assim?",
					null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (resposta == 1)
				return;
			else
				geraGTM();
		}
	}

	private void geraGTM() {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		txtSerie.setEnabled(false);
		
		List<Ordem> ordemGTM = fmtGTM.getSelected();
		String dataSistema = getDataSistema();
		int lote = ConnectionFeps.getValorSeq("LOTEGM");
		int numGTM = 0;
		int quantidade = 0;
		int qtdeLote = 0;
		String sGTM = null;
		String partNumber, serieConti;


		int i = 0;
		while (i < ordemGTM.size()) {
			partNumber = ordemGTM.get(i).getPartNumber();

			numGTM = ConnectionFeps.getValorSeq("GTM");
			if(sGTM == null)
				sGTM = String.valueOf(numGTM);
			else
				sGTM = sGTM + " - " + numGTM;


			while (i < ordemGTM.size()
					&& Integer.parseInt(partNumber) == Integer.parseInt(ordemGTM.get(i).getPartNumber())) {
				serieConti = ordemGTM.get(i).getOrdem_serie();
				updateOrdemConti(numGTM, serieConti);
				updateOrdemGM(serieConti);
				quantidade += 1;
				i += 1;
			}

			insertGTM(numGTM, partNumber, quantidade, LocalDate.now(), LocalTime.now(), "N", dataSistema, lote,
					getApelido(partNumber));

			quantidade = 0;
		}
		qtdeLote = getQuantidadeLote(lote);

		numGTM = ConnectionFeps.getValorSeq("GTM");
		sGTM = sGTM + " - " + numGTM;
		insertGTM(numGTM, COB_LE, qtdeLote, LocalDate.now(), LocalTime.now(), "N", dataSistema, lote,
				getApelido(COB_LE));

		numGTM = ConnectionFeps.getValorSeq("GTM");
		sGTM = sGTM + " - " + numGTM;
		insertGTM(numGTM, COB_LD, qtdeLote, LocalDate.now(), LocalTime.now(), "N", dataSistema, lote,
				getApelido(COB_LD));

		relatorio.imprimeGTM(lote, dataSistema);
		relatorio.imprimeExtrato(lote);
		
		
		txtSerie.setEnabled(true);
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		atualiza();
		if(chkManual.isSelected())
			JOptionPane.showMessageDialog(null, "A(s) GTM(s) de número(s) " + sGTM + " foram criadas com sucesso!");
	}

	private int getNumFechaGTM() {
		String consultaSQL;
		ResultSet rs;
		int ret = -1;

		consultaSQL = "SELECT qtde_fecha_gtm FROM parametros";
		rs = ConnectionFeps.query(consultaSQL);
		try {
			if (rs.next())
				ret = rs.getInt("qtde_fecha_gtm");

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o número de fechamento de GTM!");
			;
		}
		ConnectionFeps.closeConnection(rs, null, null);

		return ret;

	}

	private String getDataSistema() {
		String consultaSQL = "SELECT data_sistema FROM parametros";
		ResultSet rs = ConnectionFeps.query(consultaSQL);
		String ret = null;

		try {
			if (rs.next())
				ret = rs.getString("data_sistema");

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível buscar a a data do sistema!");
		}

		return ret;
	}

	private int getQuantidadeLote(int lote) {
		String consultaSQL = "SELECT quantidade, part_number FROM gtm WHERE lote = '" + lote + "'";
		ResultSet rs = ConnectionFeps.query(consultaSQL);
		int ret = 0;

		try {
			while (rs.next()) {
				ret += rs.getInt("quantidade");
			}

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Não foi possível buscar na tabela a quantidade de cockpits do lote " + lote);
		}

		return ret;
	}

	private String getApelido(String partNumber) {
		String consultaSQL = "SELECT historico FROM gm_conti WHERE codigo_gm = '" + partNumber + "'";
		ResultSet rs = ConnectionFeps.query(consultaSQL);
		String ret = null;

		try {
			if (rs.next())
				ret = rs.getString("historico");

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível retornar o apelido do material: " + partNumber);
		}

		return ret.trim();
	}

	private void updateOrdemConti(int num_gtm, String contiSerie) {
		String consultaSQL = "UPDATE ordem_conti SET num_gtm = '" + num_gtm + "', " + "status_cockpit = '"
				+ ConstantsFEPS.COCKPIT_ENTREGUE.getStringValue() + "' " + "WHERE ordem_conti_serie = '" + contiSerie
				+ "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o status do cockpit para 'ENTREGUE' "
					+ "na tabela Conti! Série ordem: " + contiSerie);
	}

	private void updateOrdemGM(String contiSerie) {
		String consultaSQL = "UPDATE ordem_gm SET status_cprod_codigo = '"
				+ ConstantsFEPS.COCKPIT_ENTREGUE.getStringValue() + "' " + "WHERE ordem_conti_serie = '" + contiSerie
				+ "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o status do cockpit para 'ENTREGUE' "
					+ "na tabela GM! Série ordem: " + contiSerie);
	}

	private void insertGTM(int numGTM, String partNumber, int quantidade, LocalDate data, LocalTime hora, String export,
			String dataSistema, int lote, String apelido) {
		String consultaSQL = "INSERT INTO gtm (num_gtm, part_number, quantidade, data_hora, export, data_sistema, lote, historico) VALUES ("
				+ "'" + numGTM + "', " + "'" + partNumber + "', " + "'" + quantidade + "', " + "'"
				+ data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " "
				+ hora.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) + "', " + "'" + export + "', " + "'"
				+ dataSistema + "', " + "'" + lote + "', " + "'" + apelido + "')";

		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível inserir no banco a GTM: " + numGTM);
	}
}
