package feps;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class CardFeps extends JPanel {
	private static final long serialVersionUID = 1L;

	final String MONITOR = "monitor";
	final String PREFERENCES = "preferences";

	private JPanel path = new JPanel();
	private JPanel cardPanel = new JPanel(new CardLayout());
	
	private MonitorImpressao monitorImpressao;
	private PreferenciaFeps preferenciaFeps;

	/**
	 * Create the panel.
	 */
	public CardFeps() {
		buildPanel();
	}

	private void buildPanel() {
		this.setBounds(0, 0, 1366, 768);
		this.setLayout(null);
		this.setBackground(new Color(255, 200, 50));

		path.setLocation(0, 0);
		path.setSize(1366, 80);
		path.setLayout(null);
		path.setBackground(new Color(255, 200, 50));

		cardPanel.setLocation(0, 80);
		cardPanel.setSize(1366, 688);
		cardPanel.setBackground(new Color(255, 200, 50));

		add(path);

		JLabel lblHome = new JLabel();
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\home_1.png"));
		lblHome.setBounds(0, 0, 70, 70);
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				new javax.swing.Timer(30, new ActionListener() {
					int i = 2;

					@Override
					public void actionPerformed(ActionEvent a) {
						lblHome.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\home_" + i + ".png"));
						i--;
						if (i == 0) {
							((Timer) a.getSource()).stop();
						}
					}
				}).start();

				super.mouseEntered(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				new javax.swing.Timer(30, new ActionListener() {
					int i = 1;

					@Override
					public void actionPerformed(ActionEvent a) {
						lblHome.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\home_" + i + ".png"));
						i++;
						if (i == 4) {
							((Timer) a.getSource()).stop();
						}
					}
				}).start();

				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal.getMain();
				super.mouseClicked(e);
			}
		});
		path.add(lblHome);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, 1366, 10);
		path.add(separator);

		monitorImpressao = new MonitorImpressao();
		preferenciaFeps = new PreferenciaFeps();
		cardPanel.add(monitorImpressao, MONITOR);
		cardPanel.add(preferenciaFeps, PREFERENCES);
		add(cardPanel);
	}

	public Container getCardPanel() {
		return cardPanel;
	}

	public void monitorStart() {
		monitorImpressao.monitorStart();
	}
}