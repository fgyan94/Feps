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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CardFeps extends JPanel {
	private static final long serialVersionUID = 1L;

	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	// private Dimension dimension = new Dimension(1366, 768);

	private GroupLayout groupLayout;

	private static final int MIN_WIDTH = 1366;
	private static final int HEIGHT = 80;

	public final String MONITOR = "monitor";
	public final String PREFERENCES = "preferences";
	public final String EMISSAOGTM = "emissaogtm";
	public final String USUARIO = "usuario";
	public final String ESTORNA = "estorna";
	public final String MANUT = "manut";

	private JPanel path = new JPanel();
	private JPanel cardPanel = new JPanel(new CardLayout());

	private MonitorImpressao monitorImpressao;
	private PreferenciaFeps preferenciaFeps;
	private EmissaoGTM emissaogtm;
	private Usuario usuario;
	private EstornaGTM estorna;
	private MaintenenceTable manut;
	
	private JLabel lblMinimizar = new JLabel("-");
	private JLabel lblFechar = new JLabel("X");

	private JLabel lblHome = new JLabel(new ImageIcon("icofeps\\menu_barra\\home\\home1.png"));
	private JLabel lblUsuarios = new JLabel(new ImageIcon("icofeps\\menu_barra\\user\\user1.png"));
	private JLabel lblManTable = new JLabel(new ImageIcon("icofeps\\menu_barra\\manut\\manut1.png"));
	private JLabel lblPropriedades = new JLabel(new ImageIcon("icofeps\\menu_barra\\tools\\tools1.png"));
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
	private final String S_REIMP = "reimp";
	private final String S_ERASE = "erase";
	private final String S_GTM = "gtm";
	private final String S_REVERSE = "reverse";
	private final String S_MONITOR = "monitor";
	private final String S_MANUAL = "manual";
	private final String S_INBUFFER = "inbuffer";
	private final String S_OUTBUFFER = "outbuffer";

	private List<Boolean> privilegio = new ArrayList<>();
	private List<JLabel> listLabel = new ArrayList<>();

	public CardFeps() {
		buildPanel();
		buildGroupLayout();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		this.setSize(dimension);
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		path.setLocation(0, 0);
		path.setSize(dimension.width, 80);
		path.setBackground(Color.WHITE);

		cardPanel.setLocation(0, 80);
		cardPanel.setSize(dimension.width, dimension.height - 80);
		cardPanel.setBackground(Color.WHITE);

		groupLayout = new GroupLayout(path);

		add(path);
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		path.setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(228, MIN_WIDTH, dimension.width))
						.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, calculate(70, MIN_WIDTH, dimension.width),
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE,
								calculate(70, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(210, MIN_WIDTH, dimension.width))
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE,
								calculate(50, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, calculate(50, MIN_WIDTH, dimension.width),
								GroupLayout.PREFERRED_SIZE)));
	}

	private void buildVerticalLayout() {
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE, calculate(70, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, calculate(30, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE)
				.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, calculate(30, HEIGHT, HEIGHT),
						GroupLayout.PREFERRED_SIZE));
	}

	public Container getCardPanel() {
		return cardPanel;
	}

	private void initializeComponents() {

		lblFechar.setForeground(Color.BLACK);
		lblFechar.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblFechar.setHorizontalAlignment(SwingConstants.CENTER);

		lblMinimizar.setForeground(Color.BLACK);
		lblMinimizar.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);

		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblManTable.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblManTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblPropriedades.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPropriedades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropriedades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblReimpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReimpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblReimpressao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblApagarOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblApagarOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblApagarOrdem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblSaidaGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblReverseGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReverseGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblReverseGTM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblMonitorCarga.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblOrdemManual.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemManual.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblOrdemBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemBuffer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblSaidaBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		monitorImpressao = new MonitorImpressao();
		preferenciaFeps = new PreferenciaFeps();
		emissaogtm = new EmissaoGTM();
		usuario = new Usuario();
		estorna = new EstornaGTM();
		manut = new MaintenenceTable();

		listLabel.add(lblUsuarios);
		listLabel.add(lblManTable);
		listLabel.add(lblPropriedades);
		listLabel.add(lblReimpressao);
		listLabel.add(lblApagarOrdem);
		listLabel.add(lblSaidaGTM);
		listLabel.add(lblReverseGTM);
		listLabel.add(lblMonitorCarga);
		listLabel.add(lblOrdemManual);
		listLabel.add(lblOrdemBuffer);
		listLabel.add(lblSaidaBuffer);

		cardPanel.add(monitorImpressao, MONITOR);
		cardPanel.add(preferenciaFeps, PREFERENCES);
		cardPanel.add(emissaogtm, EMISSAOGTM);
		cardPanel.add(usuario, USUARIO);
		cardPanel.add(estorna, ESTORNA);
		cardPanel.add(manut, MANUT);

		add(cardPanel);
	}

	private void initializeListeners() {
		mouseListenerLabel(lblFechar, FECHAR);
		mouseListenerLabel(lblMinimizar, MINIMIZAR);
		mouseListenerLabel(lblHome, S_HOME);
		mouseListenerLabel(lblUsuarios, S_USER);
		mouseListenerLabel(lblManTable, S_MANUT);
		mouseListenerLabel(lblPropriedades, S_TOOLS);
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
				if (nome.equals(FECHAR))
					Login.menu.fechar();
				else if (nome.equals(MINIMIZAR))
					Login.menu.minimizar();
				else {
					if (temPermissao(label)) {
						stop();
						if (label == lblHome)
							MenuPrincipal.getMain();
						else if (loadPreferences()) {
							if (preferenciaFeps.getStatus()) {
								if (label == lblUsuarios) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, USUARIO);
								} else if (label == lblPropriedades) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, PREFERENCES);
									loadPreferences();
								} else if (label == lblMonitorCarga) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, MONITOR);
									monitorStart();
								} else if (label == lblSaidaGTM) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, EMISSAOGTM);
									defineEmissaoGTM();
								} else if (label == lblReverseGTM) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, ESTORNA);
									estornaStart();
								} else if (label == lblManTable) {
									((CardLayout) cardPanel.getLayout()).show(cardPanel, MANUT);
								}
								
							} else
								JOptionPane.showMessageDialog(null, "O dia está fechado!");
						} else
							definePreferencias();
					} else
						JOptionPane.showMessageDialog(null, "Você não tem permissão de acesso nessa área!");
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

	public void monitorStart() {
		monitorImpressao.monitorStart();
	}

	public void emissaoStart() {
		emissaogtm.start();
		emissaogtm.requestFocusTextSequencia();
	}

	public void stop() {
		emissaogtm.stop();
		monitorImpressao.stop();
	}

	public boolean loadPreferences() {
		String consultaSQL;
		ResultSet rs;
		boolean ok = false;

		try {
			consultaSQL = "SELECT * FROM parametros";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				ok = true;
				preferenciaFeps.loadPreferences();
			}

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sql) {
			sql.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível carregar os parâmetros do sistema!");
		}

		return ok;
	}

	public void defineEmissaoGTM() {
			Object[] options = { "Automático", "Manual" };
			int resposta = JOptionPane.showOptionDialog(null,
					"Qual modo você quer iniciar a sessão de emissão de GTM?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (resposta == 0)
				emissaoStart();
			else
				emissaogtm.selectManualTrue();
	}
	
	public void definePreferencias() {
		if (!loadPreferences()) {
			Object[] options = { "Novo", "Padrão" };
			int resposta = JOptionPane.showOptionDialog(null,
					"Parâmetros ainda não definidos, gostaria de carregar preferências padrão ou criar um novo?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (resposta == 0)
				preferencesNovo();
			else
				preferencesCarregaPadrao();

		}
	}

	private boolean temPermissao(JLabel label) {
		String consultaSQL = "SELECT * FROM Usuario WHERE usuario_codigo = '" + Login.getUsuario() + "'";
		ResultSet rs = ConnectionFeps.query(consultaSQL);

		try {
			if (rs.next()) {
				String[] tmpPrivilegio = rs.getString("privilegio").split("");
				privilegio.clear();
				for (int i = 1; i < tmpPrivilegio.length; i++) {
					if (tmpPrivilegio[i].equals(String.valueOf(1)))
						privilegio.add(new Boolean(true));
					else
						privilegio.add(new Boolean(false));
				}
			}
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível retornar se o usuário tem permissão a esta área!");
		}

		return label == lblHome ? true : privilegio.get(listLabel.indexOf(label));
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

	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}

	public void closeSerial() {
		monitorImpressao.closeSerial();
	}
	
	public void estornaStart() {
		estorna.start();
	}
}