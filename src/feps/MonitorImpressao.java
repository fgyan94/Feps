package feps;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class MonitorImpressao extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblImpressao, lblOrdensParaMontagem, lblOrdensParaImpressao, lblListaModelosProduzidos, lblComunicaoFepsRast;
	private JTable tblOrdemImpressao, tblOrdemMontagem, tblModeloProd;
	private FepsModelTable fmtImpressao, fmtMontagem, fmtProduzido;
	private JScrollPane scrOrdemImpressao, scrOrdemMontagem, scrModeloProd, scrComunicaFepsRast;
	private JEditorPane edtComunicaFepsRast;
	private MonitorCarga monitor;
	
	public MonitorImpressao() {
		buildPanel();
		inicializaComponentes();
		fillTables();
	}

	private void buildPanel() {
		UIManager.put("List.disabledForeground", Color.BLACK);

		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1366, 688);

		this.setLayout(null);
	}

	private void inicializaComponentes() {		
		
		lblImpressao = new JLabel("Impressão");
		lblImpressao.setForeground(Color.BLACK);
		lblOrdensParaImpressao = new JLabel("Ordens para impressão:");
		lblOrdensParaMontagem = new JLabel("Ordens para montagem:");
		lblListaModelosProduzidos = new JLabel("Lista modelos produzidos:");
		lblComunicaoFepsRast = new JLabel("Comunicação Feps/Rastreabilidade:");
		
		edtComunicaFepsRast = new JEditorPane();
		edtComunicaFepsRast.setEditable(false);
		
		scrOrdemImpressao = new JScrollPane();
		scrOrdemMontagem = new JScrollPane();
		scrModeloProd = new JScrollPane();
		scrComunicaFepsRast = new JScrollPane();
		
		monitor = new MonitorCarga();

		lblImpressao.setBounds(585, 10, 777, 98);
		lblOrdensParaImpressao.setBounds(660, 119, 226, 30);
		lblOrdensParaMontagem.setBounds(60, 421, 470, 30);
		lblListaModelosProduzidos.setBounds(660, 331, 500, 30);
		lblComunicaoFepsRast.setBounds(660, 520, 337, 30);

		scrOrdemImpressao.setBounds(660, 148, 660, 172);
		scrOrdemMontagem.setBounds(60, 462, 462, 215);
		scrModeloProd.setBounds(660, 361, 660, 150);
		scrComunicaFepsRast.setBounds(660, 548, 660, 129);
		
		monitor.setBounds(0, 0, 500, 410);
		
		lblImpressao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblOrdensParaImpressao.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblOrdensParaMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblComunicaoFepsRast.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListaModelosProduzidos.setFont(new Font("Broadway", Font.PLAIN, 17));
		
		tblOrdemImpressao = new JTable();
		tblOrdemMontagem = new JTable();
		tblModeloProd = new JTable();
		
		tblOrdemImpressao.setFont(new Font("Arial", Font.PLAIN, 10));
		tblOrdemMontagem.setFont(new Font("Arial", Font.PLAIN, 10));
		tblModeloProd.setFont(new Font("Arial", Font.PLAIN, 10));
		
		edtComunicaFepsRast.setFont(new Font("Broadway", Font.PLAIN, 17));

		lblOrdensParaImpressao.setForeground(Color.BLACK);
		lblOrdensParaMontagem.setForeground(Color.BLACK);
		lblListaModelosProduzidos.setForeground(Color.BLACK);
		lblComunicaoFepsRast.setForeground(Color.BLACK);
		
		tblOrdemImpressao.setForeground(Color.BLACK);
		tblOrdemMontagem.setForeground(Color.BLACK);
		tblModeloProd.setForeground(Color.BLACK);
		
		edtComunicaFepsRast.setForeground(Color.BLACK);
		
		lblImpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdensParaImpressao.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdensParaMontagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblComunicaoFepsRast.setHorizontalAlignment(SwingConstants.LEFT);
		lblListaModelosProduzidos.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblOrdensParaImpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdensParaMontagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComunicaoFepsRast.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalTextPosition(SwingConstants.CENTER);

		scrOrdemImpressao.setViewportView(tblOrdemImpressao);
		scrOrdemMontagem.setViewportView(tblOrdemMontagem);
		scrModeloProd.setViewportView(tblModeloProd);
		scrComunicaFepsRast.setViewportView(edtComunicaFepsRast);
		
		scrOrdemImpressao.setBorder(new LineBorder(Color.BLACK));
		scrOrdemMontagem.setBorder(new LineBorder(Color.BLACK));
		scrModeloProd.setBorder(new LineBorder(Color.BLACK));
		scrComunicaFepsRast.setBorder(new LineBorder(Color.BLACK));
		
		add(lblImpressao);			
		add(lblOrdensParaImpressao);		
		add(lblOrdensParaMontagem);		
		add(lblComunicaoFepsRast);	
		add(lblListaModelosProduzidos);
		add(scrOrdemImpressao);	
		add(scrOrdemMontagem);	
		add(scrModeloProd);	
		add(scrComunicaFepsRast);
		add(monitor);
	}

	public void monitorStart() {
		monitor.start();
	}
	
	public void monitorStop() {
		monitor.cancelTask();;
	}
	
	private void fillTables() {
		fillTableImpressao();
		fillTableMontagem();
		fillTableProduzido();
	}
	
	private void fillTableMontagem() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		
		try {
			consultaSQL = "SELECT Ordem_Conti.*, Status_Cockpit.descricao, GM_Conti.apelido, GM_Conti.Codigo_GM FROM Status_Cockpit, "
					+ "Ordem_Conti LEFT OUTER JOIN GM_Conti ON Ordem_Conti.PART_NUMBER_GM = GM_Conti.CODIGO_GM " + 
					"WHERE Ordem_Conti.Status_cockpit = '001' AND Ordem_Conti.Status_cockpit = STATUS_COCKPIT.codigo ORDER BY Ordem_Conti.Ordem_Entrada";
			
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();
			
			if(rs.next()) {
				while(!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm");
					String apelido = rs.getString("apelido");
					String ordem_serie = rs.getString("ordem_conti_serie");
					String ordem_data = rs.getString("ordem_conti_data");
					String ordem_entrada = rs.getString("ordem_entrada");
					
					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, ordem_data, ordem_entrada); 
					lista.add(ordem);
					
					rs.next();
				}
				
				ArrayList<String> coluna = new ArrayList<>();
				
				coluna.add("Part Number");
				coluna.add("Apelido");
				coluna.add("Série Ordem");
				coluna.add("Data Ordem");
				coluna.add("Ordem Entrada");
				
				fmtMontagem = new FepsModelTable(lista, coluna);
				
				tblOrdemMontagem.setModel(fmtMontagem);
			}
			
		} catch(SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}
	
	private void fillTableImpressao() {
		
	}
	
	private void fillTableProduzido() {
		
	}
}
