package feps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FepsModelTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	public static final String PART_NUMBER_GM = "Part Number";
	public static final String APELIDO = "Apelido";
	public static final String ORDEM_CONTI_SERIE = "Série Ordem";
	public static final String SEQ_DIA = "Sequência Dia";
	public static final String ORDEM_ENTRADA = "Ordem Entrada";
	public static final String ORDEM_CONTI_DATA = "Data Ordem";
	public static final String QUANTIDADE = "Quantidade";
	public static final String SEQ_GM = "Sequência GM";
	public static final String STATUS_COCKPIT = "Status Cockpit";
	public static final String GERA = "Gera";
	public static final String DOC_GM = "Doc GM";

	private boolean editable = false;

	private List<Ordem> linhas;
	private List<String> colunas;

	public FepsModelTable(List<Ordem> lista, List<String> coluna) {
		this.linhas = lista;
		this.colunas = coluna;
	}

	public FepsModelTable(ArrayList<String> coluna) {
		this.colunas = coluna;
		this.linhas = new ArrayList<Ordem>();
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
	public Class<?> getColumnClass(int columnIndex) {
		return getColumnName(columnIndex).equals(GERA) ? Boolean.class : String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return getColumnName(columnIndex).equals(GERA) && editable;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (linhas.isEmpty())
			return null;
		else {
			Ordem ordem = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (PART_NUMBER_GM):
				return ordem.getPartNumber();
			case (APELIDO):
				return ordem.getApelido();
			case (ORDEM_CONTI_SERIE):
				return ordem.getOrdem_serie();
			case (SEQ_DIA):
				return ordem.getSeq_dia();
			case (ORDEM_ENTRADA):
				return ordem.getOrdem_entrada();
			case (ORDEM_CONTI_DATA):
				return ordem.getOrdem_data();
			case (QUANTIDADE):
				return ordem.getQtde();
			case (SEQ_GM):
				return ordem.getSeqGM();
			case (STATUS_COCKPIT):
				return ordem.getStatusCockpit();
			case (GERA):
				return ordem.isSelected();
			case (DOC_GM):
				return ordem.getSeqGM();
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Ordem ordem = linhas.get(rowIndex);

		switch (getColumnName(columnIndex)) {
		case (PART_NUMBER_GM):
			ordem.setPartNumber((String) aValue);
			break;
		case (APELIDO):
			ordem.setApelido((String) aValue);
			break;
		case (ORDEM_CONTI_SERIE):
			ordem.setOrdem_serie((String) aValue);
			break;
		case (SEQ_DIA):
			ordem.setSeq_dia((String) aValue);
			break;
		case (ORDEM_ENTRADA):
			ordem.setOrdem_entrada((String) aValue);
			break;
		case (ORDEM_CONTI_DATA):
			ordem.setOrdem_data((String) aValue);
			break;
		case (QUANTIDADE):
			ordem.setQtde((String) aValue);
			break;
		case (SEQ_GM):
			ordem.setSeqGM((String) aValue);
			break;
		case (STATUS_COCKPIT):
			ordem.setStatusCockpit((String) aValue);
			break;
		case (GERA):
			if ((Boolean) aValue)
				for (int i = rowIndex; i >= 0; i--)
					linhas.get(i).setSelected(true);
			else
				for (int i = rowIndex; i < linhas.size(); i++)
					linhas.get(i).setSelected(false);
			break;
		case (DOC_GM):
			ordem.setSeqGM((String) aValue);
			break;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableDataChanged();
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Ordem getOrdem(int rowIndex) {
		return linhas.get(rowIndex);
	}

	public void addOrdem(Ordem ordem) {
		linhas.add(ordem);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeOrdem(int rowIndex) {
		linhas.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void addOrdemList(List<Ordem> newList) {
		linhas.addAll(newList);
		fireTableRowsInserted(getRowCount(), getRowCount() + newList.size());
	}

	public void update(List<Ordem> newList) {
		if (!newList.isEmpty()) {
			Ordem ordem = newList.remove(0);
			if (!linhas.contains(ordem))
				addOrdem(ordem);
			update(newList);
		}
	}

	public int getCountSelected() {
		return getSelected().size();
	}

	public List<Ordem> getSelected() {
		List<Ordem> ret = new ArrayList<Ordem>();
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).isSelected())
				ret.add(linhas.get(i));
		}

		ret.sort(new Comparator<Ordem>() {
			@Override
			public int compare(Ordem o1, Ordem o2) {
				int p1 = Integer.parseInt(o1.getPartNumber());
				int p2 = Integer.parseInt(o2.getPartNumber());
				if (p1 > p2)
					return 1;
				else if (p1 < p2)
					return -1;
				return 0;
			}

		});

		return ret;
	}

	public void setCheckBoxEditable(boolean editable) {
		if (!editable) {
			for (int i = 0; i < linhas.size(); i++) {
				linhas.get(i).setSelected(true);
			}
		}
		this.editable = editable;
	}

	public void clear() {
		linhas.clear();
		fireTableDataChanged();
	}
}
