package feps;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelTablePrivilegio extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Privilegio> linhas;
	private List<String> colunas;
	private boolean editable = false;

	static final String ADD = "";
	static final String PRIVILEGIO = "Privilégio";

	public ModelTablePrivilegio(List<String> colunas) {
		this.linhas = new ArrayList<Privilegio>();
		this.colunas = colunas;
	}

	public void add(Privilegio privilegio) {
		this.linhas.add(privilegio);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void setCheckBoxEditable(boolean editable) {
		if (!editable) {
			for (int i = 0; i < linhas.size(); i++) {
				linhas.get(i).setSelected(true);
			}
		}
		this.editable = editable;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return getColumnName(columnIndex).equals(ADD) && editable;
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
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (linhas.isEmpty())
			return null;
		else {
			Privilegio linha = linhas.get(rowIndex);

			switch (getColumnName(columnIndex)) {
			case (PRIVILEGIO):
				return linha.getNome();
			case (ADD):
				return linha.isSelected();
			default:
				throw new IndexOutOfBoundsException("columnIndex out of bounds");
			}
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Privilegio linha = linhas.get(rowIndex);

		switch (getColumnName(columnIndex)) {
		case (PRIVILEGIO):
			linha.setNome((String) aValue);
			break;
		case (ADD):
			linha.setSelected((boolean) aValue);
			break;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

		fireTableDataChanged();
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void clearValueCheckBox() {
		for(int i = 0; i < linhas.size(); i++) {
			setValueAt(false, i, 0);
		}
	}

	public int getSize() {
		return linhas.size();
	}
}