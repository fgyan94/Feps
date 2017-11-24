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

	private JLabel lblHome = new JLabel(new ImageIcon("icofeps\\menu_barra\\home\\home1.png"));
	private JLabel lblUsuario = new JLabel(new ImageIcon("icofeps\\menu_barra\\user\\user1.png"));
	private JLabel lblManAvan = new JLabel(new ImageIcon("icofeps\\menu_barra\\manut\\manut1.png"));
	private JLabel lblPropriedade = new JLabel(new ImageIcon("icofeps\\menu_barra\\tools\\tools1.png"));
	private JLabel lblImpressaoOrdem = new JLabel(new ImageIcon("icofeps\\menu_barra\\imp\\imp1.png"));
	private JLabel lblReimpressao = new JLabel(new ImageIcon("icofeps\\menu_barra\\reimp\\reimp1.png"));
	private JLabel lblApagarOrdem = new JLabel(new ImageIcon("icofeps\\menu_barra\\erase\\erase1.png"));
	private JLabel lblSaidaGTM = new JLabel(new ImageIcon("icofeps\\menu_barra\\gtm\\gtm1.png"));
	private JLabel lblReverseGTM = new JLabel(new ImageIcon("icofeps\\menu_barra\\reverse\\reverse1.png"));
	private JLabel lblMonitorCarga = new JLabel(new ImageIcon("icofeps\\menu_barra\\monitor\\monitor1.png"));
	private JLabel lblOrdemManual = new JLabel(new ImageIcon("icofeps\\menu_barra\\manual\\manual1.png"));
	private JLabel lblOrdemBuffer = new JLabel(new ImageIcon("icofeps\\menu_barra\\inbuffer\\inbuffer1.png"));
	private JLabel lblSaidaBuffer = new JLabel(new ImageIcon("icofeps\\menu_barra\\outbuffer\\outbuffer1.png"));
	
	private final String S_HOME = "home";
	private final String S_USER = "user";
	private final String S_MANUT = "manut";
	private final String S_TOOLS = "tools";
	private final String S_IMP = "imp";
	private final String S_REIMP = "reimp";
	private final String S_ERASE = "erase";
	private final String S_GTM = "gtm";
	private final String S_REVERSE = "reverse";
	private final String S_MONITOR = "monitor";
	private final String S_MANUAL = "manual";
	private final String S_INBUFFER = "inbuffer";
	private final String S_OUTBUFFER = "outbuffer";

	public CardFeps() {
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		this.setBounds(0, 0, 1366, 768);
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		path.setLocation(0, 0);
		path.setSize(1366, 80);
		path.setLayout(null);
		path.setBackground(Color.WHITE);

		cardPanel.setLocation(0, 80);
		cardPanel.setSize(1366, 688);
		cardPanel.setBackground(Color.WHITE);

		add(path);
	}

	public Container getCardPanel() {
		return cardPanel;
	}

	public void monitorStart() {
		monitorImpressao.monitorStart();
	}

	private void initializeComponents() {
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setBounds(228, 0, 70, 70);
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(298, 0, 70, 70);
		lblUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblManAvan.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManAvan.setHorizontalAlignment(SwingConstants.CENTER);
		lblManAvan.setBounds(368, 0, 70, 70);
		lblManAvan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblPropriedade.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPropriedade.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropriedade.setBounds(438, 0, 70, 70);
		lblPropriedade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblImpressaoOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImpressaoOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setBounds(508, 0, 70, 70);
		lblImpressaoOrdem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblReimpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReimpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblReimpressao.setBounds(578, 0, 70, 70);
		lblReimpressao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblApagarOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblApagarOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblApagarOrdem.setBounds(648, 0, 70, 70);
		lblApagarOrdem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblSaidaGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setBounds(718, 0, 70, 70);
		lblSaidaGTM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblReverseGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReverseGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblReverseGTM.setBounds(788, 0, 70, 70);
		lblReverseGTM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblMonitorCarga.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setBounds(858, 0, 70, 70);
		lblMonitorCarga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblOrdemManual.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemManual.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManual.setBounds(928, 0, 70, 70);
		lblOrdemManual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblOrdemBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemBuffer.setBounds(998, 0, 70, 70);
		lblOrdemBuffer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblSaidaBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setBounds(1068, 0, 70, 70);
		lblSaidaBuffer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		monitorImpressao = new MonitorImpressao();
		preferenciaFeps = new PreferenciaFeps();
		cardPanel.add(monitorImpressao, MONITOR);
		cardPanel.add(preferenciaFeps, PREFERENCES);

		path.add(lblHome);
		path.add(lblUsuario);
		path.add(lblManAvan);
		path.add(lblPropriedade);
		path.add(lblImpressaoOrdem);
		path.add(lblReimpressao);
		path.add(lblApagarOrdem);
		path.add(lblSaidaGTM);
		path.add(lblReverseGTM);
		path.add(lblMonitorCarga);
		path.add(lblOrdemManual);
		path.add(lblOrdemBuffer);
		path.add(lblSaidaBuffer);
		add(cardPanel);
	}

	private void initializeListeners() {
		lblHome.addMouseListener(mouseListenerLabel(lblHome, S_HOME));
		lblUsuario.addMouseListener(mouseListenerLabel(lblUsuario, S_USER));
		lblManAvan.addMouseListener(mouseListenerLabel(lblManAvan, S_MANUT));
		lblPropriedade.addMouseListener(mouseListenerLabel(lblPropriedade, S_TOOLS));
		lblImpressaoOrdem.addMouseListener(mouseListenerLabel(lblImpressaoOrdem, S_IMP));
		lblReimpressao.addMouseListener(mouseListenerLabel(lblReimpressao, S_REIMP));
		lblApagarOrdem.addMouseListener(mouseListenerLabel(lblApagarOrdem, S_ERASE));
		lblSaidaGTM.addMouseListener(mouseListenerLabel(lblSaidaGTM, S_GTM));
		lblReverseGTM.addMouseListener(mouseListenerLabel(lblReverseGTM, S_REVERSE));
		lblMonitorCarga.addMouseListener(mouseListenerLabel(lblMonitorCarga, S_MONITOR));
		lblOrdemManual.addMouseListener(mouseListenerLabel(lblOrdemManual, S_MANUAL));
		lblOrdemBuffer.addMouseListener(mouseListenerLabel(lblOrdemBuffer, S_INBUFFER));
		lblSaidaBuffer.addMouseListener(mouseListenerLabel(lblSaidaBuffer, S_OUTBUFFER));
	}

	private MouseAdapter mouseListenerLabel(JLabel label, String nome) {
		return new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				new javax.swing.Timer(30, new ActionListener() {
					int i = 2;

					@Override
					public void actionPerformed(ActionEvent a) {
						label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome  + i + ".png"));
						i--;
						if (i == 0)
							((Timer) a.getSource()).stop();

						else if(label.getMousePosition() == null) {
							new javax.swing.Timer(30, new ActionListener() {
								int i = 2;

								@Override
								public void actionPerformed(ActionEvent a) {
									label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome  + i + ".png"));
									i--;
									if (i == 0)
										((Timer) a.getSource()).stop();
								}
							}).start();
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
						label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome  + i + ".png"));
						i++;
						if (i == 4) {
							((Timer) a.getSource()).stop();
						}
						
						else if(label.getMousePosition() == null) {
							label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome  + 1 + ".png"));
							((Timer) a.getSource()).stop();
						}
					}
				}).start();

				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == lblHome)
					MenuPrincipal.getMain();
				super.mouseClicked(e);
			}
		};
	}
}