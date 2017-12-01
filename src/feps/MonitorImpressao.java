package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class MonitorImpressao extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblImpressao, lblOrdensParaMontagem, lblListaModelosProduzidos,
			lblComunicaoFepsRast, btnManualPrint;
	private JTable tblOrdemMontagem, tblModeloProd;
	private FepsModelTable fmtImpressao, fmtMontagem, fmtProduzido;
	private JScrollPane scrOrdemMontagem, scrModeloProd, scrComunicaFepsRast;
	private JEditorPane edtComunicaFepsRast;
	private MonitorCarga monitor;

	public MonitorImpressao() {
		buildPanel();
		initializeComponents();
		initializeListener();
		initializeTables();
		fillTables();
	}

	private void buildPanel() {
		UIManager.put("List.disabledForeground", Color.BLACK);

		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1366, 688);

		this.setLayout(null);
	}

	private void initializeComponents() {

		lblImpressao = new JLabel("Impressão");
		lblOrdensParaMontagem = new JLabel("Ordens para montagem:");
		lblListaModelosProduzidos = new JLabel("Lista modelos produzidos:");
		lblComunicaoFepsRast = new JLabel("<html>Comunicação</br> Serial:</html>");
		btnManualPrint = new JLabel(new ImageIcon("icofeps\\monitorImp\\print_24.png"));

		edtComunicaFepsRast = new JEditorPane();
		edtComunicaFepsRast.setEditable(false);
		scrOrdemMontagem = new JScrollPane();
		scrModeloProd = new JScrollPane();
		scrComunicaFepsRast = new JScrollPane();

		monitor = new MonitorCarga();

		lblImpressao.setBounds(585, 10, 777, 98);
		lblOrdensParaMontagem.setBounds(630, 90, 540, 30);
		lblListaModelosProduzidos.setBounds(630, 447, 570, 30);
		lblComunicaoFepsRast.setBounds(1220, 73, 130, 47);
		btnManualPrint.setBounds(1170, 90, 30, 30);
		scrOrdemMontagem.setBounds(630, 120, 570, 315);
		scrModeloProd.setBounds(630, 477, 570, 200);
		scrComunicaFepsRast.setBounds(1220, 120, 130, 557);

		monitor.setBounds(60, 0, 500, 410);

		lblImpressao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblOrdensParaMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblComunicaoFepsRast.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListaModelosProduzidos.setFont(new Font("Broadway", Font.PLAIN, 17));
		tblOrdemMontagem = new JTable();
		tblModeloProd = new JTable();
		tblOrdemMontagem.setFont(new Font("Calibri", Font.PLAIN, 12));
		tblModeloProd.setFont(new Font("Calibri", Font.PLAIN, 12));

		edtComunicaFepsRast.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblImpressao.setForeground(Color.BLACK);
		lblOrdensParaMontagem.setForeground(Color.BLACK);
		lblListaModelosProduzidos.setForeground(Color.BLACK);
		lblComunicaoFepsRast.setForeground(Color.BLACK);
		tblOrdemMontagem.setForeground(Color.DARK_GRAY);
		tblModeloProd.setForeground(Color.DARK_GRAY);

		edtComunicaFepsRast.setForeground(Color.DARK_GRAY);

		lblImpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdensParaMontagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblComunicaoFepsRast.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdensParaMontagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComunicaoFepsRast.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalTextPosition(SwingConstants.CENTER);
		scrOrdemMontagem.setViewportView(tblOrdemMontagem);
		scrModeloProd.setViewportView(tblModeloProd);
		scrComunicaFepsRast.setViewportView(edtComunicaFepsRast);
		scrOrdemMontagem.setBorder(new LineBorder(Color.BLACK));
		scrModeloProd.setBorder(new LineBorder(Color.BLACK));
		scrComunicaFepsRast.setBorder(new LineBorder(Color.BLACK));

		btnManualPrint.setToolTipText("Impressão manual");
		
		add(lblImpressao);
		add(lblOrdensParaMontagem);
		add(lblComunicaoFepsRast);
		add(lblListaModelosProduzidos);
		add(btnManualPrint);
		add(scrOrdemMontagem);
		add(scrModeloProd);
		add(scrComunicaFepsRast);
		add(monitor);
	}
	
	private void initializeListener() {
		btnManualPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				super.mouseEntered(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnManualPrint.setBorder(null);
			}
		});
	}

	private void initializeTables() {
		createImpressaoTable();
		createMontagemTable();
		createProduzidosTable();

	}

	private void createImpressaoTable() {

		ArrayList<String> coluna = new ArrayList<>();

		coluna.add("Part Number");
		coluna.add("Apelido");
		coluna.add("Série Ordem");
		coluna.add("Sequência Dia");
		coluna.add("Ordem Entrada");

		fmtImpressao = new FepsModelTable(coluna);

	}

	private void createMontagemTable() {

		ArrayList<String> coluna = new ArrayList<>();

		coluna.add("Part Number");
		coluna.add("Apelido");
		coluna.add("Série Ordem");
		coluna.add("Data Ordem");
		coluna.add("Ordem Entrada");

		fmtMontagem = new FepsModelTable(coluna);

		tblOrdemMontagem.setModel(fmtMontagem);

	}

	private void createProduzidosTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add("Part Number");
		coluna.add("Apelido");
		coluna.add("Quantidade");

		fmtProduzido = new FepsModelTable(coluna);

		tblModeloProd.setModel(fmtProduzido);

	}

	public void monitorStart() {
		monitor.start();
	}

	public void monitorStop() {
		monitor.cancelTask();
	}

	private void fillTables() {
		fillTableImpressao();
		fillTableMontagem();
		fillTableProduzido();
	}

	private void fillTableImpressao() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM IMPRESSAO WHERE STATUS = '0' ORDER BY ORDEM_ENTRADA";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm");
					String apelido = rs.getString("apelido");
					String ordem_serie = rs.getString("ordem_conti_serie");
					String seq_dia = rs.getString("seq_dia");
					String ordem_entrada = rs.getString("ordem_entrada");

					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, Integer.parseInt(seq_dia), ordem_entrada);
					lista.add(ordem);

					rs.next();
				}

				fmtImpressao.addOrdemList(lista);
			}

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}

	private void fillTableMontagem() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT Ordem_Conti.*, Status_Cockpit.descricao, GM_Conti.apelido, GM_Conti.Codigo_GM FROM Status_Cockpit, "
					+ "Ordem_Conti LEFT OUTER JOIN GM_Conti ON Ordem_Conti.PART_NUMBER_GM = GM_Conti.CODIGO_GM "
					+ "WHERE Ordem_Conti.Status_cockpit = '001' AND Ordem_Conti.Status_cockpit = STATUS_COCKPIT.codigo ORDER BY Ordem_Conti.Ordem_Entrada";

			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm");
					String apelido = rs.getString("apelido");
					String ordem_serie = rs.getString("ordem_conti_serie");
					String ordem_data = rs.getString("ordem_conti_data");
					String ordem_entrada = rs.getString("ordem_entrada");

					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, ordem_data, ordem_entrada);
					lista.add(ordem);

					rs.next();
				}

				fmtMontagem.addOrdemList(lista);
			}

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}

	private void fillTableProduzido() {
		
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT gm_conti.*, qtde = (SELECT COUNT(*) FROM ordem_conti WHERE part_number_gm = codigo_gm"
					+ "   AND status_cockpit = '001') FROM gm_conti ORDER BY qtde DESC";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					if (Integer.parseInt(rs.getString("qtde")) > 0) {
						String partNumber = rs.getString("codigo_gm");
						String apelido = rs.getString("apelido");
						String quantidade = rs.getString("qtde");

						Ordem ordem = new Ordem(partNumber, apelido, Integer.parseInt(quantidade));
						lista.add(ordem);
					}

					rs.next();
				}

				fmtProduzido.addOrdemList(lista);
			}

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}
	
	private void receiveComm(String bufferSerial) {
		
	}
}
