package feps;

import java.awt.Dimension;
import java.awt.Toolkit;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class EstornaGTM extends JPanel {
	private static final long serialVersionUID = 1L;

	private class ModelTableGTM extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		
		public static final String NUM_GTM = "Número GTM";
		public static final String PART_NUMBER = "PartNumber";
		public static final String QTDE = "Quantidade";
		public static final String DATA_HORA = "Data Hora";
		public static final String DATA_SISTEMA = "Data Sistema";
		public static final String LOTE = "Lote";

		private List<String> colunas;
		private List<GTM> linhas;

		public ModelTableGTM(List<String> colunas) {
			this.colunas = colunas;
			linhas = new ArrayList<>();
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
				GTM gtm = linhas.get(rowIndex);

				switch (getColumnName(columnIndex)) {
				case (NUM_GTM):
					return gtm.getNumGtm();
				case (PART_NUMBER):
					return gtm.getPartNumber();
				case (QTDE):
					return gtm.getQtde();
				case (DATA_HORA):
					return gtm.getDataHora();
				case (DATA_SISTEMA):
					return gtm.getDataSistema();
				case (LOTE):
					return gtm.getLote();
				default:
					throw new IndexOutOfBoundsException("columnIndex out of bounds");
				}
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			GTM gtm = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (NUM_GTM):
				gtm.setNumGtm((Integer) aValue);
				break;
			case (PART_NUMBER):
				gtm.setPartNumber((Integer) aValue);
				break;
			case (QTDE):
				gtm.setQtde((Integer) aValue);
				break;
			case (DATA_HORA):
				gtm.setDataHora((String) aValue);
				break;
			case (DATA_SISTEMA):
				gtm.setDataSistema((String) aValue);
				break;
			case (LOTE):
				gtm.setLote((Integer) aValue);
				break;
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}
			
			fireTableDataChanged();
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		public void addOrdemList(List<GTM> newList) {
			linhas.addAll(newList);
			fireTableRowsInserted(getRowCount(), getRowCount() + newList.size());
		}
		
		public void clear() {
			linhas.clear();
			fireTableDataChanged();
		}

		public int getColumnIndex(String nomeColumn) {
			return colunas.indexOf(nomeColumn);
		}
		
//		public void addOrdem(GTM gtm) {
//			linhas.add(gtm);
//			int ultimoIndice = getRowCount() - 1;
//			fireTableRowsInserted(ultimoIndice, ultimoIndice);
//		}

	}

//	 private Dimension dimension = new Dimension(1366, 768);
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	private static final int MIN_WIDTH = 1366;
	private static final int MIN_HEIGHT = 768;

	private JLabel lblEstornoGTM, lblGTM, btnConfirma, btnEstorna, lblInforma;
	private JTextField txtGTM;
	private JTable tbEstorna;
	private JScrollPane scrollPane;
	private ModelTableGTM model;
	private GroupLayout groupLayout;
	
	private int lote;
	
	public EstornaGTM() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
		initializeListeners();
		createTable();
		start();
	}

	private void buildPanel() {
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		setSize(dimension);
	}

	private void initializeComponents() {
		groupLayout = new GroupLayout(this);
		 
		lblEstornoGTM = new JLabel("Estorno de GTM");
		lblEstornoGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstornoGTM.setFont(new Font("Stencil", Font.PLAIN, 70));

		lblGTM = new JLabel("Última GTM:");
		lblGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGTM.setFont(new Font("Stencil", Font.PLAIN, 22));
		lblGTM.setHorizontalAlignment(SwingConstants.RIGHT);

		btnConfirma = new JLabel("Confirmar");
		btnConfirma.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirma.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnConfirma.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirma.setFont(new Font("Stencil", Font.PLAIN, 22));

		btnEstorna = new JLabel("Estornar");
		btnEstorna.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEstorna.setFont(new Font("Stencil", Font.PLAIN, 22));
		btnEstorna.setHorizontalAlignment(SwingConstants.CENTER);
		btnEstorna.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEstorna.setVisible(false);

		lblInforma = new JLabel("Confirmar a exclus\u00E3o das GTM's abaixo");
		lblInforma.setHorizontalTextPosition(SwingConstants.CENTER);
		lblInforma.setHorizontalAlignment(SwingConstants.CENTER);
		lblInforma.setForeground(Color.RED);
		lblInforma.setFont(new Font("Stencil", Font.PLAIN, 22));

		txtGTM = new JTextField();
		txtGTM.setHorizontalAlignment(SwingConstants.CENTER);
		txtGTM.setEditable(false);
		txtGTM.setFont(new Font("Calibri", Font.PLAIN, 22));

		scrollPane = new JScrollPane();

		tbEstorna = new JTable();
		scrollPane.setViewportView(tbEstorna);
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		setLayout(groupLayout);
	}
	
	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addComponent(lblEstornoGTM, GroupLayout.PREFERRED_SIZE, calculate(1346, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(375, MIN_WIDTH, dimension.width))
						.addComponent(lblGTM, GroupLayout.PREFERRED_SIZE, calculate(190, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addComponent(txtGTM, GroupLayout.PREFERRED_SIZE, calculate(200, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(5, MIN_WIDTH, dimension.width))
						.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, calculate(180, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(280, MIN_WIDTH, dimension.width))
						.addComponent(lblInforma, GroupLayout.PREFERRED_SIZE, calculate(800, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(280, MIN_WIDTH, dimension.width))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, calculate(800, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(900, MIN_WIDTH, dimension.width))
						.addComponent(btnEstorna, GroupLayout.PREFERRED_SIZE, calculate(180, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
			);
	}

	private void buildVerticalLayout() {
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
					.addComponent(lblEstornoGTM, GroupLayout.PREFERRED_SIZE, calculate(80, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
					.addGap(calculate(90, MIN_HEIGHT - 80, dimension.height - 80))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGTM, GroupLayout.PREFERRED_SIZE, calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGTM, GroupLayout.PREFERRED_SIZE, calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
					.addGap(calculate(60, MIN_HEIGHT - 80, dimension.height - 80))
					.addComponent(lblInforma, GroupLayout.PREFERRED_SIZE, calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
					.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
					.addGap(calculate(8, MIN_HEIGHT - 80, dimension.height - 80))
					.addComponent(btnEstorna, GroupLayout.PREFERRED_SIZE, calculate(30, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
		);
		
	}

	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}
	
	private int calculateSizeTable(int columnMinWidth, int tableMinWidth, int tableWidth) {
		return (tableWidth * columnMinWidth) / tableMinWidth;
	}

	private void initializeListeners() {
		addMouseListenerLabel(btnConfirma);
		addMouseListenerLabel(btnEstorna);
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (label == btnConfirma)
					confirmaGTM();
				else if (label == btnEstorna)
					if(estorna())
						JOptionPane.showMessageDialog(null, "As GTM's do lote " + lote + 
																" foram estornadas com sucesso!");
					else
						JOptionPane.showMessageDialog(null, "Não foi possível estornar as GTM's do lote " + 
									(int) model.getValueAt(0, model.getColumnIndex(ModelTableGTM.LOTE)));
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 21));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 22));
			}
		});
	}

	private void confirmaGTM() {
		if(txtGTM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há nenuma GTM para estornar!");
			return;
		}
		String consultaSQL;
		ResultSet rs;
		
		try {
			consultaSQL = "SELECT MAX(lote) AS lote FROM gtm ORDER BY lote";
			rs = ConnectionFeps.query(consultaSQL);
			
			if(rs.next()) {
				fillTableGTM(rs.getInt("lote"));
				btnEstorna.setVisible(true);
			} else
				clearTableEstorna();
			
			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível buscar o lote da última GTM gerada! GTM: " + txtGTM.getText());
		}
	}

	private boolean estorna() {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		lote = (int) model.getValueAt(0, model.getColumnIndex(ModelTableGTM.LOTE));
		String consultaSQL = "SELECT * FROM gtm WHERE lote = '" + lote + "' ORDER BY num_gtm";
		ResultSet rsGTM = ConnectionFeps.query(consultaSQL);
		try {
			if(rsGTM.next()) {
				updateControleGeral(rsGTM.getString("num_gtm"));
				while(!rsGTM.isAfterLast()) {
					ResultSet rsConti = ConnectionFeps.query("SELECT * FROM ordem_conti WHERE num_gtm = '" + rsGTM.getString("num_gtm") + "'");
					while(rsConti.next()) {
						String numGTM = rsConti.getString("num_gtm");
						String codigoCockpitOld = rsConti.getString("status_cockpit");
						String contiSerie = rsConti.getString("ordem_conti_serie");
						updateLogConti(codigoCockpitOld, contiSerie);
						updateStatusCockpit(numGTM);
						updateNumGTM(numGTM);
						updateGM(contiSerie);
					}
					
					rsGTM.next();
					ConnectionFeps.closeConnection(rsConti, null, null);
				}
				
				deleteGTM(lote);
				clearTableEstorna();
				btnEstorna.setVisible(false);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return true;
			}
			
			ConnectionFeps.closeConnection(rsGTM, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível estornas as GTM's do lote: " + lote);
		}
		return false;
	}

	private void deleteGTM(int lote) {
		String consultaSQL = "DELETE gtm WHERE lote = '" + lote + "'";
		lote = -1;
		if(!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível deletar as GTM's do lote: " + lote);
	}

	private void updateLogConti(String codigoCockpitOld, String contiSerie) {
		LocalDate data = LocalDate.now();
		LocalTime time = LocalTime.now();
		String data_conti = LocalDateTime.of(data, time).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
		String consultaSQL = "INSERT INTO log_conti (sequencia, userid, status_cockpit, data_conti) VALUES (" +
							"'" + contiSerie + "', '" + ConstantsFEPS.USER_ID.getStringValue() + "', '" + codigoCockpitOld + "', " + 
							"'" + data_conti + "')";
		if(!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível gravar o log continental da série: " + contiSerie);
	}

	private void updateStatusCockpit(String numGTM) {
		String consultaSQL = "UPDATE ordem_conti SET status_cockpit = '" + ConstantsFEPS.COCKPIT_CONCLUIDO.getStringValue() + "' " + 
								"WHERE num_gtm = '" + numGTM + "'";
		
		if(!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o status do cockpit da GTM: " + numGTM + " na tabela ORDEM_CONTI");
	}

	private void updateNumGTM(String numGTM) {
		String consultaSQL = "UPDATE ordem_conti SET num_gtm = '0' WHERE num_gtm = '" + numGTM + "'";
		
		if(!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível estornar a GTM: " + numGTM);
	} 

	private void updateGM(String serieConti) {
		String consultaSQL = "SELECT * FROM ordem_gm WHERE ordem_conti_serie = '" + serieConti + "'";
		ResultSet rs = ConnectionFeps.query(consultaSQL);
		LocalDate data = LocalDate.now();
		LocalTime time = LocalTime.now();
		String data_gm = LocalDateTime.of(data, time).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
		try {
			if(rs.next()) {
				String sequencia = rs.getString("ordem_gm_doc").concat(rs.getString("data_hora"));
				String status = rs.getString("status_cprod_codigo");
				consultaSQL = "INSERT INTO log_gm (sequencia, userid, status_cockpit, data_gm) VALUES (" + 
								"'" + sequencia + "', '" + ConstantsFEPS.USER_ID.getStringValue() + "', " + 
								"'" + status + "', '" + data_gm + "')";
				if(!ConnectionFeps.update(consultaSQL))
					JOptionPane.showMessageDialog(null, "Não foi possível inserir o log GM da sequência: " + sequencia);
				else {
					consultaSQL = "UPDATE ordem_gm SET status_cprod_codigo = '" + ConstantsFEPS.PROD_ATENDIDA.getStringValue() + "' " + 
									"WHERE ordem_conti_serie = '" + serieConti + "'";
					if(!ConnectionFeps.update(consultaSQL))
						JOptionPane.showMessageDialog(null, "Não foi possível atualizar o status do cockpit da série Continental: " + serieConti);
				}
				
			}
			
			ConnectionFeps.closeConnection(rs, null, null);
		} catch(SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o status do cockpit Série Continental: " + serieConti);
		}
	}

	private void updateControleGeral(String num_gtm) {
		String consultaSQL = "UPDATE controle_geral SET valor = '" + (Integer.parseInt(num_gtm) - 1) + "' " +
							"WHERE nome = 'GTM'";
		if(!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar o controle geral de GTM");	
	}

	private void createTable() {
		List<String> coluna = new ArrayList<>();

		coluna.add(ModelTableGTM.NUM_GTM);
		coluna.add(ModelTableGTM.PART_NUMBER);
		coluna.add(ModelTableGTM.QTDE);
		coluna.add(ModelTableGTM.DATA_HORA);
		coluna.add(ModelTableGTM.DATA_SISTEMA);
		coluna.add(ModelTableGTM.LOTE);

		model = new ModelTableGTM(coluna);
		tbEstorna.setModel(model);

		tbEstorna.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tbEstorna.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tbEstorna.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			if(model.getColumnName(i).equals(ModelTableGTM.NUM_GTM))
				tbEstorna.getColumnModel().getColumn(i).setPreferredWidth(calculateSizeTable(100, 800, calculate(800, MIN_WIDTH, dimension.width)));
			else if(model.getColumnName(i).equals(ModelTableGTM.LOTE))
				tbEstorna.getColumnModel().getColumn(i).setPreferredWidth(calculateSizeTable(80, 800, calculate(800, MIN_WIDTH, dimension.width)));
			else if(model.getColumnName(i).equals(ModelTableGTM.PART_NUMBER))
				tbEstorna.getColumnModel().getColumn(i).setPreferredWidth(calculateSizeTable(120, 800, calculate(800, MIN_WIDTH, dimension.width)));
			else if(model.getColumnName(i).equals(ModelTableGTM.QTDE))
				tbEstorna.getColumnModel().getColumn(i).setPreferredWidth(calculateSizeTable(80, 800, calculate(800, MIN_WIDTH, dimension.width)));
			else
				tbEstorna.getColumnModel().getColumn(i).setPreferredWidth(calculateSizeTable(200, 800, calculate(800, MIN_WIDTH, dimension.width)));				
		}
		
		tbEstorna.getTableHeader().setReorderingAllowed(false);
		tbEstorna.getTableHeader().setResizingAllowed(false);
		model.fireTableDataChanged();
	}

	private String getGTM() {
		String ret = "";
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT MAX(num_gtm) AS num_gtm FROM gtm ORDER BY num_gtm";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				ret = rs.getString("num_gtm") == null ? "" : rs.getString("num_gtm").trim();

			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível buscar a última GTM gerada");
		}

		return ret;
	}

	private void fillTableGTM(int lote) {
		List<GTM> list = new ArrayList<>();
		String consultaSQL;
		ResultSet rs;
		
		try {
			consultaSQL = "SELECT gtm.* , ordem_conti.ordem_conti_serie, ordem_conti.part_number_gm, ordem_gm.ordem_gm_doc, ordem_gm.vin " + 
					"FROM gtm, ordem_gm RIGHT OUTER JOIN ordem_conti ON ordem_gm.ordem_conti_serie = ordem_conti.ordem_conti_serie " + 
					"WHERE gtm.lote = '" + lote + "' AND gtm.num_gtm = ordem_conti.num_gtm " + 
					"ORDER BY ordem_gm.ordem_gm_doc, ordem_gm.data_hora";
			
			rs = ConnectionFeps.query(consultaSQL);
			
			if(rs.next()) {
				while(!rs.isAfterLast()) {
					LocalDate data = LocalDate.parse(rs.getString("data_hora").substring(0, 10));
					LocalTime hora = LocalTime.parse(rs.getString("data_hora").substring(11));
					LocalDate dataS = LocalDate.parse(rs.getString("data_sistema").substring(0, 10));
					
					String vin = rs.getString("vin").trim();
					String serieConti = rs.getString("ordem_conti_serie").trim();
					String dataHora = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + hora.format(DateTimeFormatter.ofPattern("HH:mm"));
					String dataSistema = dataS.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					Integer seq = rs.getInt("ordem_gm_doc");
					Integer partNumber = rs.getInt("part_number");
					Integer numGtm = rs.getInt("num_gtm");
					Integer qtde = rs.getInt("quantidade");
					
					GTM gtm = new GTM(seq, vin, serieConti, partNumber, numGtm, qtde, dataHora, dataSistema, lote);
					list.add(gtm);
					
					rs.next();
				}
				
				model.clear();
				model.addOrdemList(list);
				tbEstorna.repaint();
			}
			
		} catch(SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível preencher a tabela com as GTM's a serem estornadas! Lote: " + lote);
		}
	}
	
	private void clearTableEstorna() {
		txtGTM.setText(getGTM());
		model.clear();
		tbEstorna.repaint();
	}

	protected void start() {
		txtGTM.setText(getGTM());
	}
}
