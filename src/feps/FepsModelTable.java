package feps;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FepsModelTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Object> linhas;
	private List<String> colunas;

	public FepsModelTable() {
		this.linhas = new ArrayList<Object>();
		this.colunas = new ArrayList<String>();
	}

	public FepsModelTable(List<Object> lista, List<String> coluna) {
		this.linhas = new ArrayList<Object>(lista);
		this.colunas = new ArrayList<String>(coluna);
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
		return null;
	}
}
