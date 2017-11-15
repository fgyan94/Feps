package feps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;

public class MonitorImpressao extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField txtArquivoEsperado;
	private JLabel lblArquivoEsperado, lblListArquivo, lblGifLoader;
	private JButton btnOrdemManual;
	private JScrollPane scrollPane;
	private JList<File> list;
	private DefaultListModel<File> itemList;
	private Timer timer;
	private TimerTask task;
	private JLabel lblImpresso;
	private JTable table;
	private JLabel lblOrdensParaImpresso;
	private JScrollPane scrollPane_2;
	private JLabel lblOrdensParaMontagem;
	private JTable table_1;
	private JLabel lblListaModelosProduzidos;
	private JScrollPane scrollPane_3;
	private JTable table_2;

//	public static void main(String[] args){
//		try {
//			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
//			new MonitorImpressao().setVisible(true);
//			
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
	
	public MonitorImpressao() {
		buildPanel();
		inicializaComponentes();
		InicializaListeners();
	}

	private void buildPanel() {
		UIManager.put("List.disabledForeground", new Color(51, 51, 51));

		this.setBackground(new Color(255, 200, 50));
		this.setBounds(0, 0, 1440, 820);

		this.setLayout(null);
	}

	private void inicializaComponentes() {
		list = new JList<>();
		list.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setText(((File) value).getName());
				this.setEnabled(true);
				return this;
			}
		});
		
		lblImpresso = new JLabel("Impress\u00E3o");
		lblImpresso.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpresso.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblImpresso.setBounds(500, 10, 939, 98);
		add(lblImpresso);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(660, 160, 660, 210);
		add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		lblOrdensParaImpresso = new JLabel("Ordens para impress\u00E3o:");
		lblOrdensParaImpresso.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdensParaImpresso.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdensParaImpresso.setForeground(new Color(51, 51, 51));
		lblOrdensParaImpresso.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblOrdensParaImpresso.setBounds(660, 130, 226, 30);
		add(lblOrdensParaImpresso);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(60, 500, 470, 310);
		add(scrollPane_2);
		
		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		
		lblOrdensParaMontagem = new JLabel("Ordens para montagem:");
		lblOrdensParaMontagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdensParaMontagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdensParaMontagem.setForeground(new Color(51, 51, 51));
		lblOrdensParaMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblOrdensParaMontagem.setBounds(60, 468, 470, 30);
		add(lblOrdensParaMontagem);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(663, 660, 660, 148);
		add(editorPane);
		
		JLabel lblComunicaoFepsrastreabilidade = new JLabel("Comunica\u00E7\u00E3o Feps/Rastreabilidade:");
		lblComunicaoFepsrastreabilidade.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComunicaoFepsrastreabilidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblComunicaoFepsrastreabilidade.setForeground(new Color(51, 51, 51));
		lblComunicaoFepsrastreabilidade.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblComunicaoFepsrastreabilidade.setBounds(663, 630, 337, 30);
		add(lblComunicaoFepsrastreabilidade);
		
		lblListaModelosProduzidos = new JLabel("Lista modelos produzidos:");
		lblListaModelosProduzidos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblListaModelosProduzidos.setForeground(new Color(51, 51, 51));
		lblListaModelosProduzidos.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListaModelosProduzidos.setBounds(660, 380, 500, 30);
		add(lblListaModelosProduzidos);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(660, 407, 660, 210);
		add(scrollPane_3);
		
		table_2 = new JTable();
		scrollPane_3.setViewportView(table_2);
		
		MonitorCarga panel = new MonitorCarga();
		panel.setBounds(0, 0, 500, 410);
		add(panel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(580, 10, 18, 800);
		add(separator);
	}

	private void InicializaListeners() {
		
	}	

	public void cancelaTask() {
		task.cancel();
		timer.purge();
		timer.cancel();
	}
}
