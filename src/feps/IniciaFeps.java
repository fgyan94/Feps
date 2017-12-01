package feps;

import com.alee.laf.WebLookAndFeel;

public class IniciaFeps {
	public static void main(String[] args) {
		try {
			WebLookAndFeel.install();
			new Login().setVisible(true);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
