package feps;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class CardFeps extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//	private Dimension dimension = new Dimension(1366, 768);

	public final String MONITOR = "monitor";
	public final String PREFERENCES = "preferences";
	public final String EMISSAOGTM = "emissaogtm";

	private JPanel path = new JPanel();
	private JPanel cardPanel = new JPanel(new CardLayout());

	private MonitorImpressao monitorImpressao;
	private PreferenciaFeps preferenciaFeps;
	private EmissaoGTM emissaogtm;

	private JLabel lblMinimizar = new JLabel("-");
	private JLabel lblFechar = new JLabel("X");

	private JLabel lblHome = new JLabel(new ImageIcon("icofeps\\menu_barra\\home\\home1.png"));
	private JLabel lblUsuario = new JLabel(new ImageIcon("icofeps\\menu_barra\\user\\user1.png"));
	private JLabel lblManTable = new JLabel(new ImageIcon("icofeps\\menu_barra\\manut\\manut1.png"));
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

	private final String MINIMIZAR = "minimizar";
	private final String FECHAR = "fechar";
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
		this.setSize(dimension);
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		path.setLocation(0, 0);
		path.setSize(dimension.width, 80);
		path.setLayout(null);
		path.setBackground(Color.WHITE);

		cardPanel.setLocation(0, 80);
		cardPanel.setSize(dimension.width, dimension.height - 80);
		cardPanel.setBackground(Color.WHITE);

		add(path);
	}

	public Container getCardPanel() {
		return cardPanel;
	}

	private void initializeComponents() {
		lblFechar.setForeground(Color.BLACK);
		lblFechar.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblFechar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechar.setBounds(1316, 0, 50, 30);

		lblMinimizar.setForeground(Color.BLACK);
		lblMinimizar.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setBounds(1266, 0, 50, 30);

		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setBounds(228, 0, 70, 70);
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(298, 0, 70, 70);
		lblUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblManTable.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblManTable.setBounds(368, 0, 70, 70);
		lblManTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
		emissaogtm = new EmissaoGTM();
		
		cardPanel.add(monitorImpressao, MONITOR);
		cardPanel.add(preferenciaFeps, PREFERENCES);
		cardPanel.add(emissaogtm, EMISSAOGTM);

		path.add(lblFechar);
		path.add(lblMinimizar);

		path.add(lblHome);
		path.add(lblUsuario);
		path.add(lblManTable);
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
		mouseListenerLabel(lblFechar, FECHAR);
		mouseListenerLabel(lblMinimizar, MINIMIZAR);
		mouseListenerLabel(lblHome, S_HOME);
		mouseListenerLabel(lblUsuario, S_USER);
		mouseListenerLabel(lblManTable, S_MANUT);
		mouseListenerLabel(lblPropriedade, S_TOOLS);
		mouseListenerLabel(lblImpressaoOrdem, S_IMP);
		mouseListenerLabel(lblReimpressao, S_REIMP);
		mouseListenerLabel(lblApagarOrdem, S_ERASE);
		mouseListenerLabel(lblSaidaGTM, S_GTM);
		mouseListenerLabel(lblReverseGTM, S_REVERSE);
		mouseListenerLabel(lblMonitorCarga, S_MONITOR);
		mouseListenerLabel(lblOrdemManual, S_MANUAL);
		mouseListenerLabel(lblOrdemBuffer, S_INBUFFER);
		mouseListenerLabel(lblSaidaBuffer, S_OUTBUFFER);
	}

	private void mouseListenerLabel(JLabel label, String nome) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (!nome.equals(MINIMIZAR) && !nome.equals(FECHAR)) {
					new javax.swing.Timer(30, new ActionListener() {
						int i = 2;

						@Override
						public void actionPerformed(ActionEvent a) {
							label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + i + ".png"));
							i--;
							if (i == 0)
								((Timer) a.getSource()).stop();

							else if (label.getMousePosition() == null) {
								new javax.swing.Timer(30, new ActionListener() {
									int i = 2;

									@Override
									public void actionPerformed(ActionEvent a) {
										label.setIcon(new ImageIcon(
												"icofeps\\menu_barra\\" + nome + "\\" + nome + i + ".png"));
										i--;
										if (i == 0)
											((Timer) a.getSource()).stop();
									}
								}).start();
							}
						}
					}).start();
				} else
					label.setBorder(null);

				super.mouseEntered(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!nome.equals(MINIMIZAR) && !nome.equals(FECHAR)) {
					new javax.swing.Timer(30, new ActionListener() {
						int i = 1;

						@Override
						public void actionPerformed(ActionEvent a) {
							label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + i + ".png"));
							i++;
							if (i == 4) {
								((Timer) a.getSource()).stop();
							}

							else if (label.getMousePosition() == null) {
								label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + 1 + ".png"));
								((Timer) a.getSource()).stop();
							}
						}
					}).start();
				} else
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				stop();
				if (nome.equals(FECHAR))
					fechar();
				else if (nome.equals(MINIMIZAR))
					Login.menu.minimizar();
				else {
					if (label == lblHome)
						MenuPrincipal.getMain();
					if (preferenciaFeps.getStatus()) {
						sistemaAberto(true);
						if (label == lblPropriedade) {
							((CardLayout) cardPanel.getLayout()).show(cardPanel, PREFERENCES);
							loadPreferences();
						}
						else if (label == lblMonitorCarga) {
							((CardLayout) cardPanel.getLayout()).show(cardPanel, MONITOR);
							monitorStart();
						} else if (label == lblSaidaGTM) {
							((CardLayout) cardPanel.getLayout()).show(cardPanel, EMISSAOGTM);
							startEmissaoGTM(); 
						}
					} else
						sistemaAberto(false);
				}
				super.mouseClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (nome.equals(FECHAR))
					label.setFont(new Font("Stencil", Font.PLAIN, 18));
				else if (nome.equals(MINIMIZAR))
					label.setFont(new Font("Stencil", Font.PLAIN, 30));
				else {
					label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + 2 + ".png"));
				}
				super.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (nome.equals(FECHAR) || nome.equals(MINIMIZAR)) {
					if (label.getMousePosition() == null) {
						label.setBorder(null);
					} else
						label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

					if (nome.equals(FECHAR))
						label.setFont(new Font("Stencil", Font.PLAIN, 20));
					else if (nome.equals(MINIMIZAR))
						label.setFont(new Font("Stencil", Font.PLAIN, 40));
				} else {
					if (label.getMousePosition() == null)
						label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + 1 + ".png"));
					else
						label.setIcon(new ImageIcon("icofeps\\menu_barra\\" + nome + "\\" + nome + 3 + ".png"));
				}
				super.mouseReleased(e);
			}
		});
	}

	public void sistemaAberto(boolean aberto) {
		for (int i = 0; i < path.getComponentCount(); i++) {
			if (path.getComponent(i) instanceof JLabel)
				if (((JLabel) path.getComponent(i)) != lblHome && ((JLabel) path.getComponent(i)) != lblFechar
						&& ((JLabel) path.getComponent(i)) != lblMinimizar)
					path.getComponent(i).setVisible(aberto);
		}
	}
	
	private void fechar() {
		stop();
		Login.menu.fechar();
	}

	public void monitorStart() {
		monitorImpressao.monitorStart();
		monitorImpressao.start();
	}

	public void startEmissaoGTM() {
		emissaogtm.start();
		emissaogtm.requestFocusTextSequencia();
	}
	
	public void stop() {
		emissaogtm.cancelTask();
		monitorImpressao.monitorStop();
		monitorImpressao.cancelTask();
		monitorImpressao.clearValues();
	}
	
	public boolean loadPreferences() {
		String consultaSQL;
		ResultSet rs;
		boolean ok = false;

		try {
			consultaSQL = "SELECT * FROM parametros";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				ok = true;
			
			preferenciaFeps.loadPreferences();
			
			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sql) {
			sql.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível carregar os parâmetros do sistema!");
		}
		
		return ok;
	}

	public void preferencesCarregaPadrao() {
		preferenciaFeps.carregaPadrao();
	}
	
	public void preferencesNovo() {
		preferenciaFeps.novo();
	}

	public void clearValues() {
		monitorImpressao.clearValues();
	}
}