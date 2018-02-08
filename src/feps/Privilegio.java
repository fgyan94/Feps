package feps;

public class Privilegio {
	private String nome;
	private boolean isSelected;
	
	public Privilegio (String nome, boolean isSelected) {
		this.nome = nome;
		this.isSelected = isSelected;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}