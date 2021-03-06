package feps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;

public class Turno extends JPanel {
	private static final long serialVersionUID = 1L;

	private Dimension dimension = new Dimension(1315, 490);
	private JTextField txtCod, txtDescr, txtIni, txtFim, txtTol;

	private JLabel lblCod, lblDescr, lblHoraFinal, lblHoraInicial, lblTolerancia, btnNovo, btnSalva, btnEdita, btnCancela, btnExclui;
	private JPanel dados, lista;

	private JTabbedPane tabbedPane;

	private List<JTextField> textList;
	private List<JLabel> btnList;

	public Turno() {
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
		tabbedPane = new JTabbedPane();
		tabbedPane.setLocation(397, 80);
		tabbedPane.setFont(new Font("Stencil", Font.PLAIN, 18));
		tabbedPane.setSize(new Dimension(611, 377));

		dados = new JPanel();
		lista = new JPanel();

		dados.setLayout(null);
		lista.setLayout(null);

		tabbedPane.addTab("Dados", dados);
		tabbedPane.addTab("Lista", lista);

		lblCod = new JLabel("Codigo Turno:");
		lblCod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCod.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblCod.setBounds(150, 40, 150, 30);
		dados.add(lblCod);

		lblDescr = new JLabel("Descri��o:");
		lblDescr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescr.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblDescr.setBounds(150, 90, 150, 30);
		dados.add(lblDescr);

		lblHoraFinal = new JLabel("Hora Final:");
		lblHoraFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraFinal.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblHoraFinal.setBounds(150, 190, 150, 30);
		dados.add(lblHoraFinal);
		
		lblHoraInicial = new JLabel("Hora Inicial:");
		lblHoraInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraInicial.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblHoraInicial.setBounds(150, 140, 150, 30);
		dados.add(lblHoraInicial);
		
		lblTolerancia = new JLabel("Tolerancia");
		lblTolerancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTolerancia.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblTolerancia.setBounds(150, 240, 150, 30);
		dados.add(lblTolerancia);
		
		txtCod = new JTextField();
		txtCod.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCod.setEditable(false);
		txtCod.setBounds(316, 40, 130, 30);
		dados.add(txtCod);

		txtDescr = new JTextField();
		txtDescr.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDescr.setEditable(false);
		txtDescr.setBounds(316, 90, 130, 30);
		dados.add(txtDescr);
		
		txtIni = new JTextField();
		txtIni.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtIni.setEditable(false);
		txtIni.setBounds(316, 140, 130, 30);
		dados.add(txtIni);
		
		txtFim = new JTextField();
		txtFim.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtFim.setEditable(false);
		txtFim.setBounds(316, 190, 130, 30);
		dados.add(txtFim);
		
		txtTol = new JTextField();
		txtTol.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTol.setEditable(false);
		txtTol.setBounds(316, 240, 130, 30);
		dados.add(txtTol);
		
		btnNovo = new JLabel("Novo");
		btnNovo.setBounds(146, 304, 100, 30);
		btnNovo.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNovo.setHorizontalAlignment(SwingConstants.CENTER);
		btnNovo.setFont(new Font("Stencil", Font.PLAIN, 18));
		dados.add(btnNovo);

		btnSalva = new JLabel("Salvar");
		btnSalva.setBounds(146, 304, 100, 30);
		btnSalva.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSalva.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalva.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnSalva.setVisible(false);
		dados.add(btnSalva);

		btnEdita = new JLabel("Editar");
		btnEdita.setBounds(258, 304, 100, 30);
		btnEdita.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEdita.setHorizontalAlignment(SwingConstants.CENTER);
		btnEdita.setFont(new Font("Stencil", Font.PLAIN, 18));
		dados.add(btnEdita);

		btnCancela = new JLabel("Cancelar");
		btnCancela.setBounds(258, 304, 100, 30);
		btnCancela.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCancela.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancela.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnCancela.setVisible(false);
		dados.add(btnCancela);

		btnExclui = new JLabel("Excluir");
		btnExclui.setBounds(370, 304, 100, 30);
		btnExclui.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnExclui.setHorizontalAlignment(SwingConstants.CENTER);
		btnExclui.setFont(new Font("Stencil", Font.PLAIN, 18));
		dados.add(btnExclui);

		add(tabbedPane);

		textList = new ArrayList<>();
		textList.add(txtCod);
		textList.add(txtDescr);
		textList.add(txtIni);
		textList.add(txtFim);
		textList.add(txtTol);

		btnList = new ArrayList<>();
		btnList.add(btnNovo);
		btnList.add(btnEdita);
		btnList.add(btnSalva);
		btnList.add(btnCancela);
		btnList.add(btnExclui);
		
		
	}

	private void initializeListeners() {
		addMouseListenerText(btnNovo);
		addMouseListenerText(btnEdita);
		addMouseListenerText(btnExclui);
		addMouseListenerText(btnSalva);
		addMouseListenerText(btnCancela);
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
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				btn.setFont(new Font("Stencil", Font.PLAIN, 17));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btn.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				btn.setFont(new Font("Stencil", Font.PLAIN, 18));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
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
		
		txtCod.requestFocusInWindow();
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
		
		txtCod.requestFocusInWindow();
	}

	private void exclui() {

	}

	private void fillTable() {

	}

	private class Cockpit {
		private String status, descr;

		public Cockpit(String status, String descr) {
			this.status = status;
			this.descr = descr;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}
	}

	private class StatusCockpitModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public static final String STATUS = "Status";
		public static final String DESCRICAO = "Descri��o";

		private List<Cockpit> linhas;
		private List<String> colunas;

		public StatusCockpitModel(ArrayList<String> coluna) {
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
				case (STATUS):
					return cockpit.getStatus();
				case (DESCRICAO):
					return cockpit.getDescr();
				default:
					throw new IndexOutOfBoundsException("columnIndex out of bounds");
				}
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Cockpit cockpit = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (STATUS):
				cockpit.setStatus((String) aValue);
				break;
			case (DESCRICAO):
				cockpit.setDescr((String) aValue);
				break;
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}

			fireTableDataChanged();
			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public Cockpit getOrdem(int rowIndex) {
			return linhas.get(rowIndex);
		}

		public void addOrdem(Cockpit cockpit) {
			linhas.add(cockpit);
			int ultimoIndice = getRowCount() - 1;
			fireTableRowsInserted(ultimoIndice, ultimoIndice);
		}

		public void removeOrdem(int rowIndex) {
			linhas.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		public void addOrdemList(List<Cockpit> newList) {
			linhas.addAll(newList);
			fireTableRowsInserted(getRowCount(), getRowCount() + newList.size());
		}

		public void update(List<Cockpit> newList) {
			if (!newList.isEmpty()) {
				Cockpit cockpit = newList.remove(0);
				if (!linhas.contains(cockpit))
					addOrdem(cockpit);
				update(newList);
			}
		}

		public void clear() {
			linhas.clear();
			fireTableDataChanged();
		}

	}
}
