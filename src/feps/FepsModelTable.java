package feps;

import java.util.ArrayList;
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
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
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
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
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
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

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
		if(!newList.isEmpty()) {
			Ordem ordem = newList.remove(0);
			if(!linhas.contains(ordem))
				addOrdem(ordem);
			update(newList);
		}
	}
	
	public void clear() {
		linhas.clear();
		fireTableDataChanged();
	}	
}
