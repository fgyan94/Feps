package feps;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class FepsLayout extends CardLayout {
	private static final long serialVersionUID = 1L;
	private String namePanel;
	private JPanel current;
	
	@Override
	public void show(Container parent, String name) {
		this.current = (JPanel) parent;
		this.namePanel = name;
		super.show(parent, name);
	}
	
	public JPanel getCurrent() {
		return current;
	}
}
