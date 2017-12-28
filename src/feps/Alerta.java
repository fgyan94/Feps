package feps;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class Alerta {
	private static Popup popup;
	private static JToolTip tip;
	private static boolean toolTip;
	private static JTextField txt;
	
	private static void showToolTip(JTextField var, String msg, int size, int pos){
		txt = var;
		Point p = txt.getLocationOnScreen();
		tip = txt.createToolTip();
		tip.setTipText(msg);
		tip.setFont(new Font("Stencil", 0, size));
		PopupFactory popupFactory = PopupFactory.getSharedInstance();  
		popup = popupFactory.getPopup(txt, tip, p.x, p.y+pos); 
	}
	
	private static boolean getToolTip(){
		return toolTip;
	}
	
	private static void setToolTip(boolean newValue){
		toolTip = newValue;
	}
	
	private static void showPopup(JTextField txt, String msg, int size, int pos){
		showToolTip(txt, msg, size, pos);
		toolTip = true;
		txt.requestFocusInWindow();
		txt.setMargin(new Insets(0, 10, 0, 0));
		popup.show();
	}
	
	public static void ativaPopup(JTextField txt, String msg, int size, int pos){
		if(!getToolTip()){
			showPopup(txt,msg, size, pos);
		} else {
			setToolTip(true);
		}
	}
	
	public static void desativaPopup(JTextField txt){
		toolTip = false;
		if(popup != null)
			popup.hide();
	}
}
