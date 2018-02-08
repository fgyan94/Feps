package feps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class PecaVariante extends JPanel {
	private static final long serialVersionUID = 1L;

	private Dimension dimension = new Dimension(1315, 490);
	private JLabel lblSequencia, lblCodGM, lblHistorico, lblApelido, lblApelidoSerie, btnNovo, btnEdita, btnExclui,
			btnSalva, btnCancela, btnProximo, btnAnterior, btnPrimeiro, btnUltimo;
	private JTextField txtSeq, txtHistorico, txtApelido, txtApelidoSerie;
	private List<JTextField> textList;
	private List<JLabel> btnList;
	private JTable tbLista;
	private ModelTableComponente mdLista;
	private JScrollPane scrLista;
	private JPanel dados, lista;
	private JTabbedPane tabbedPane;
	private JComboBox<String> cbCodigoGM, cbVariante;

	public PecaVariante() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		fillTable();
	}

	private void buildPanel() {
		setSize(dimension);
		setLayout(null);
	}

	private void initializeComponents() {
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
		scrLista.setBounds(160, 30, 800, 310);
		lista.add(scrLista);

		tbLista = new JTable();
		scrLista.setViewportView(tbLista);

		lblSequencia = new JLabel("Sequência:");
		lblSequencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSequencia.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblSequencia.setBounds(338, 44, 170, 30);
		dados.add(lblSequencia);

		lblCodGM = new JLabel("Código GM:");
		lblCodGM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodGM.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblCodGM.setBounds(338, 94, 170, 30);
		dados.add(lblCodGM);

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
		
		txtSeq = new JTextField();
		txtSeq.setEnabled(false);
		txtSeq.setEditable(false);
		txtSeq.setHorizontalAlignment(SwingConstants.LEFT);
		txtSeq.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtSeq.setBounds(528, 44, 270, 30);
		dados.add(txtSeq);

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
		textList.add(txtSeq);
		textList.add(txtHistorico);
		textList.add(txtApelido);
		textList.add(txtApelidoSerie);

		btnList = new ArrayList<>();
		btnList.add(btnNovo);
		btnList.add(btnSalva);
		btnList.add(btnEdita);
		btnList.add(btnCancela);
		btnList.add(btnExclui);

		cbCodigoGM = new JComboBox<String>();
		cbCodigoGM.setBounds(526, 94, 130, 30);
		dados.add(cbCodigoGM);

		cbVariante = new JComboBox<String>();
		cbVariante.setBounds(668, 94, 130, 30);
		dados.add(cbVariante);

	}

	private void initializeListeners() {
		addMouseListenerText(btnNovo);
		addMouseListenerText(btnEdita);
		addMouseListenerText(btnExclui);
		addMouseListenerText(btnSalva);
		addMouseListenerText(btnCancela);
		addMouseListenerText(btnProximo);
		addMouseListenerText(btnAnterior);
		addMouseListenerText(btnPrimeiro);
		addMouseListenerText(btnUltimo);
	}

	private void addMouseListenerText(JLabel btn) {
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
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btn == btnProximo || btn == btnAnterior) {
					if(btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_48.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_48.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if(btn == btnPrimeiro)
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
					if(btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_64.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_64.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if(btn == btnPrimeiro)
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
					if(btn == btnProximo)
						btnProximo.setIcon(new ImageIcon("icofeps\\manut\\next_64.png"));
					else
						btnAnterior.setIcon(new ImageIcon("icofeps\\manut\\prev_64.png"));
				} else if (btn == btnPrimeiro || btn == btnUltimo) {
					if(btn == btnPrimeiro)
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
		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(true);
			else
				btnList.get(i).setVisible(false);
		}

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setEditable(true);
			textList.get(i).setText("");
		}
		
		txtSeq.setEnabled(true);
		txtSeq.setEditable(true);
		txtSeq.requestFocusInWindow();
	}

	private void cancela() {
		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(false);
			else
				btnList.get(i).setVisible(true);
		}

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setEditable(false);
		}
		
		txtSeq.setEnabled(false);
		txtSeq.setEditable(false);
		requestFocusInWindow();
	}

	private void salva() {
		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(false);
			else
				btnList.get(i).setVisible(true);
		}

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setEditable(false);
		}
		
		txtSeq.setEnabled(false);
		txtSeq.setEditable(false);
		requestFocusInWindow();
	}

	private void edita() {
		for (int i = 0; i < btnList.size(); i++) {
			if (btnList.get(i) == btnSalva || btnList.get(i) == btnCancela)
				btnList.get(i).setVisible(true);
			else
				btnList.get(i).setVisible(false);
		}

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setEditable(true);
		}
		
		txtHistorico.requestFocusInWindow();
	}

	private void exclui() {

	}

	private void proximo() {

	}

	private void anterior() {

	}

	private void fillTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(ModelTableComponente.SEQUENCIA);
		coluna.add(ModelTableComponente.CODIGO_GM);
		coluna.add(ModelTableComponente.CODIGO_PECA);
		coluna.add(ModelTableComponente.HISTORICO);
		coluna.add(ModelTableComponente.ORDENACAO);

		mdLista = new ModelTableComponente(coluna);

		tbLista.setModel(mdLista);
		tbLista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tbLista.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tbLista.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			tbLista.getColumnModel().getColumn(i).setPreferredWidth(158);
		}

		tbLista.getTableHeader().setReorderingAllowed(false);
		tbLista.getTableHeader().setResizingAllowed(false);
		mdLista.fireTableDataChanged();
	}

	private class ModelTableComponente extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public static final String SEQUENCIA = "Sequência";
		public static final String CODIGO_GM = "Código GM";
		public static final String CODIGO_PECA = "Código Peça";
		public static final String HISTORICO = "Histórico";
		public static final String ORDENACAO = "Ordem";

		private List<Componente> linhas;
		private List<String> colunas;

		public ModelTableComponente(ArrayList<String> coluna) {
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
				Componente componente = linhas.get(rowIndex);

				switch (getColumnName(columnIndex)) {
				case (SEQUENCIA):
					return componente.getSequencia();
				case (CODIGO_GM):
					return componente.getCodigo_gm();
				case (CODIGO_PECA):
					return componente.getCodigo_peca();
				case (HISTORICO):
					return componente.getHistorico();
				case (ORDENACAO):
					return componente.getOrdenacao();
				default:
					throw new IndexOutOfBoundsException("columnIndex out of bounds");
				}
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Componente componente = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (SEQUENCIA):
				componente.setSequencia((String) aValue);
				break;
			case (CODIGO_GM):
				componente.setCodigo_gm((String) aValue);
				break;
			case (CODIGO_PECA):
				componente.setCodigo_peca((String) aValue);
				break;
			case (HISTORICO):
				componente.setHistorico((String) aValue);
				break;
			case (ORDENACAO):
				componente.setOrdenacao((String) aValue);
				break;
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}

			fireTableDataChanged();
			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public void addOrdem(Componente componente) {
			linhas.add(componente);
			int ultimoIndice = getRowCount() - 1;
			fireTableRowsInserted(ultimoIndice, ultimoIndice);
		}

		public void removeOrdem(int rowIndex) {
			linhas.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		public void addOrdemList(List<Componente> newList) {
			linhas.addAll(newList);
			fireTableRowsInserted(getRowCount(), getRowCount() + newList.size());
		}

		public void update(List<Componente> newList) {
			if (!newList.isEmpty()) {
				Componente componente = newList.remove(0);
				if (!linhas.contains(componente))
					addOrdem(componente);
				update(newList);
			}
		}

		public void clear() {
			linhas.clear();
			fireTableDataChanged();
		}

	}

	private class Componente {
		private String sequencia, codigo_gm, codigo_peca, historico, ordenacao;

		public Componente(String sequencia, String codigo_gm, String codigo_peca, String historico, String ordenacao) {
			this.sequencia = sequencia;
			this.codigo_gm = codigo_gm;
			this.codigo_peca = codigo_peca;
			this.historico = historico;
			this.ordenacao = ordenacao;
		}

		public String getSequencia() {
			return sequencia;
		}

		public void setSequencia(String sequencia) {
			this.sequencia = sequencia;
		}

		public String getCodigo_gm() {
			return codigo_gm;
		}

		public void setCodigo_gm(String codigo_gm) {
			this.codigo_gm = codigo_gm;
		}

		public String getCodigo_peca() {
			return codigo_peca;
		}

		public void setCodigo_peca(String codigo_peca) {
			this.codigo_peca = codigo_peca;
		}

		public String getHistorico() {
			return historico;
		}

		public void setHistorico(String historico) {
			this.historico = historico;
		}

		public String getOrdenacao() {
			return ordenacao;
		}

		public void setOrdenacao(String ordenacao) {
			this.ordenacao = ordenacao;
		}

	}
}
