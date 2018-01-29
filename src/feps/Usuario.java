package feps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Usuario extends JPanel {
	private static final long serialVersionUID = 1L;

	private Dimension dimension = new Dimension(1366, 768);
//	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private String nome, senha;
	private int registro;
	
	public Usuario() {
		buildPanel();
	}

	private void buildPanel() {
		setSize(dimension);
		setBackground(Color.WHITE);
	}
	
}
