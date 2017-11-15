package feps;

import java.awt.ScrollPane;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Impressao extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblImpressao, lblOImp, lblOMont, lblCom, lblList;
	private FepsModelTable tbOImp, tbOMont, tbCom, tbList;
	private ScrollPane spImp, spMont, spCom, spList;
	
	
	public Impressao() {
		buildPanel();
		initializeComponents();
	}


	private void buildPanel() {
		setSize(1440, 900);
	}


	private void initializeComponents() {
		lblImpressao = new JLabel("Impress�o");
		lblOImp = new JLabel("Ordens para impress�o:");
		lblOMont = new JLabel("Ordens para montagem:");
		lblCom = new JLabel("Comunica��o FEPS/RASTREABILIDADE:");
		lblList = new JLabel("Lista modelos produzidos:");
		
		tbOImp = new FepsModelTable();
	}

}
