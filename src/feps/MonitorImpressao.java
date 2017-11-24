package feps;

import java.awt.Color;
import java.awt.Font;

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
	private JScrollPane scrOrdemImpressao, scrOrdemMontagem, scrModeloProd, scrComunicaFepsRast;
	private JEditorPane edtComunicaFepsRast;
	private MonitorCarga monitor;
	
	public MonitorImpressao() {
		buildPanel();
		inicializaComponentes();
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
		
		tblOrdemImpressao = new JTable();
		tblOrdemMontagem = new JTable();
		tblModeloProd = new JTable();
		
		monitor = new MonitorCarga();

		lblImpressao.setBounds(585, 10, 777, 98);
		lblOrdensParaImpressao.setBounds(660, 119, 226, 30);
		lblOrdensParaMontagem.setBounds(60, 421, 470, 30);
		lblListaModelosProduzidos.setBounds(660, 270, 500, 30);
		lblComunicaoFepsRast.setBounds(660, 520, 337, 30);

		scrOrdemImpressao.setBounds(660, 148, 660, 112);
		scrOrdemMontagem.setBounds(60, 462, 462, 215);
		scrModeloProd.setBounds(660, 298, 660, 210);
		scrComunicaFepsRast.setBounds(660, 548, 660, 129);
		
		monitor.setBounds(0, 0, 500, 410);
		
		lblImpressao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblOrdensParaImpressao.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblOrdensParaMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblComunicaoFepsRast.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListaModelosProduzidos.setFont(new Font("Broadway", Font.PLAIN, 17));
		
		tblOrdemImpressao.setFont(new Font("Broadway", Font.PLAIN, 17));
		tblOrdemMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		tblModeloProd.setFont(new Font("Broadway", Font.PLAIN, 17));
		
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
		monitor.cancelTask();
	}
}
