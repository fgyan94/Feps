package feps;

import javax.swing.UIManager;

public class IniciaFeps {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
			
			new Login().setVisible(true);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
