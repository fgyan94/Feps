package feps;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static JPanel cardPanel = new JPanel(new CardLayout());

	private JPanel main = new JPanel();
	private static CardFeps card = new CardFeps();

	private static JLabel lblMinimizar = new JLabel("-");
	private static JLabel lblFechar = new JLabel("X");

	// Aba "SISTEMA"
	private static JLabel lblSistema = new JLabel("SISTEMA");
	private static JLabel lblStatusProd = new JLabel(getIconSystemStatus());
	private static JLabel lblUsuarios = new JLabel(new ImageIcon("icofeps\\menu\\user.png"));
	private static JLabel lblManTable = new JLabel(new ImageIcon("icofeps\\menu\\avancedMaintenence.png"));
	private static JLabel lblPropriedades = new JLabel(new ImageIcon("icofeps\\menu\\tools.png"));

	// Aba "PRODU��O"
	private static JLabel lblProducao = new JLabel("PRODU��O");
	private static JLabel lblImpressaoOrdem = new JLabel(new ImageIcon("icofeps\\menu\\printOrder.png"));
	private static JLabel lblReimpressao = new JLabel(new ImageIcon("icofeps\\menu\\reprint.png"));
	private static JLabel lblApagarOrdem = new JLabel(new ImageIcon("icofeps\\menu\\eraseOrder.png"));

	// Aba "EXPEDI��O"
	private static JLabel lblExpedicao = new JLabel("EXPEDI��O");
	private static JLabel lblSaidaGTM = new JLabel(new ImageIcon("icofeps\\menu\\saida-gtm.png"));
	private static JLabel lblReverseGTM = new JLabel(new ImageIcon("icofeps\\menu\\estorno-gtm.png"));

	// Aba "CONTING�NCIA"
	private static JLabel lblContingencia = new JLabel("CONTING�NCIA");
	private static JLabel lblMonitorCarga = new JLabel(new ImageIcon("icofeps\\menu\\monitor-carga.png"));
	private static JLabel lblOrdemManual = new JLabel(new ImageIcon("icofeps\\menu\\manualOrder.png"));
	private static JLabel lblOrdemBuffer = new JLabel(new ImageIcon("icofeps\\menu\\bufferOrder.png"));
	private static JLabel lblSaidaBuffer = new JLabel(new ImageIcon("icofeps\\menu\\sendBuffer.png"));

	public MenuPrincipal() {
		setTitle("FEPS");
		buildFrame();
		initializeComponents();
		initializeListeners();
		verificaSistema();
	}

	private void buildFrame() {
		this.setUndecorated(true);
		this.setOpacity(0.9f);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.setPreferredSize(dimension);
		main.setPreferredSize(dimension);
		cardPanel.setPreferredSize(dimension);

		this.setBackground(Color.WHITE);
		main.setBackground(Color.WHITE);
		cardPanel.setBackground(Color.WHITE);

		this.setMinimumSize(new Dimension(1366, 768));
		main.setMinimumSize(new Dimension(1366, 768));
		cardPanel.setMinimumSize(new Dimension(1366, 768));

		this.setBounds(new Rectangle(new Point(0, 0), dimension));
		main.setBounds(new Rectangle(new Point(0, 0), dimension));
		cardPanel.setBounds(new Rectangle(new Point(0, 0), dimension));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (!PreferenciaFeps.loadPreferences()) {
					((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
					((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.PREFERENCES);
					card.sistemaAberto(false);
					Object[] options = { "Novo", "Padr�o" };
					int resposta = JOptionPane.showOptionDialog(null,
							"Par�metros ainda n�o definidos, gostaria de carregar prefer�ncias padr�o ou criar um novo?",
							null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (resposta == 0)
						PreferenciaFeps.novo();
					else
						PreferenciaFeps.carregaPadrao();

				}
				super.windowOpened(e);
			}
		});
	}

	private void initializeComponents() {

		lblFechar.setForeground(Color.BLACK);
		lblFechar.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblFechar.setHorizontalAlignment(SwingConstants.CENTER);

		lblMinimizar.setForeground(Color.BLACK);
		lblMinimizar.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);

		lblSistema.setForeground(Color.BLACK);
		lblSistema.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblSistema.setHorizontalAlignment(SwingConstants.CENTER);

		lblProducao.setForeground(Color.BLACK);
		lblProducao.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblProducao.setHorizontalAlignment(SwingConstants.CENTER);

		lblExpedicao.setForeground(Color.BLACK);
		lblExpedicao.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblExpedicao.setHorizontalAlignment(SwingConstants.CENTER);

		lblContingencia.setForeground(Color.BLACK);
		lblContingencia.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblContingencia.setHorizontalAlignment(SwingConstants.CENTER);

		lblStatusProd.setForeground(Color.BLACK);
		lblStatusProd.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblStatusProd.setText("Iniciar/Encerrar sistema");
		lblStatusProd.setVerticalAlignment(SwingConstants.CENTER);
		lblStatusProd.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblStatusProd.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStatusProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusProd.setToolTipText("Iniciar/Encerrar Sistema");

		lblImpressaoOrdem.setForeground(Color.BLACK);
		lblImpressaoOrdem.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblImpressaoOrdem.setText("Impress�o");
		lblImpressaoOrdem.setVerticalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImpressaoOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImpressaoOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setToolTipText("Impress�o");

		lblSaidaGTM.setForeground(Color.BLACK);
		lblSaidaGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSaidaGTM.setText("Sa�da e emiss�o de GTM");
		lblSaidaGTM.setVerticalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setToolTipText("Sa�da e emiss�o de GTM");

		lblMonitorCarga.setForeground(Color.BLACK);
		lblMonitorCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMonitorCarga.setText("Monitor de carga/Impress�o");
		lblMonitorCarga.setVerticalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMonitorCarga.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setToolTipText("Monitor carga");

		lblUsuarios.setForeground(Color.BLACK);
		lblUsuarios.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblUsuarios.setText("Cadastro de Usu�rios");
		lblUsuarios.setVerticalAlignment(SwingConstants.CENTER);
		lblUsuarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setToolTipText("Usuarios");

		lblReimpressao.setForeground(Color.BLACK);
		lblReimpressao.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblReimpressao.setText("Reimpress�o");
		lblReimpressao.setVerticalAlignment(SwingConstants.CENTER);
		lblReimpressao.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReimpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReimpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblReimpressao.setToolTipText("Reimpress�o de ordem");

		lblReverseGTM.setForeground(Color.BLACK);
		lblReverseGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblReverseGTM.setText("Estorno de GTM");
		lblReverseGTM.setVerticalAlignment(SwingConstants.BOTTOM);
		lblReverseGTM.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReverseGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReverseGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblReverseGTM.setToolTipText("Estorno GTM");

		lblOrdemManual.setForeground(Color.BLACK);
		lblOrdemManual.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblOrdemManual.setText("Ordem Manual");
		lblOrdemManual.setVerticalAlignment(SwingConstants.CENTER);
		lblOrdemManual.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOrdemManual.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemManual.setToolTipText("Ordem Manual");

		lblManTable.setForeground(Color.BLACK);
		lblManTable.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblManTable.setText("Manuten��o de Tabelas");
		lblManTable.setVerticalAlignment(SwingConstants.CENTER);
		lblManTable.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblManTable.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblManTable.setToolTipText("Manuten��o de Tabelas");

		lblApagarOrdem.setForeground(Color.BLACK);
		lblApagarOrdem.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblApagarOrdem.setText("Apagar Ordem");
		lblApagarOrdem.setVerticalAlignment(SwingConstants.CENTER);
		lblApagarOrdem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblApagarOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblApagarOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblApagarOrdem.setToolTipText("Apagar Ordem");

		lblOrdemBuffer.setForeground(Color.BLACK);
		lblOrdemBuffer.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblOrdemBuffer.setText("Ordem Buffer");
		lblOrdemBuffer.setVerticalAlignment(SwingConstants.CENTER);
		lblOrdemBuffer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOrdemBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemBuffer.setToolTipText("Ordem Buffer");

		lblPropriedades.setForeground(Color.BLACK);
		lblPropriedades.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblPropriedades.setText("Propriedades");
		lblPropriedades.setVerticalAlignment(SwingConstants.CENTER);
		lblPropriedades.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblPropriedades.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPropriedades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropriedades.setToolTipText("Propriedades");

		lblSaidaBuffer.setForeground(Color.BLACK);
		lblSaidaBuffer.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSaidaBuffer.setText("Sa�da Buffer");
		lblSaidaBuffer.setVerticalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setToolTipText("Sa�da Buffer");

		GroupLayout gl_main = new GroupLayout(main);
		gl_main.setHorizontalGroup(gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup().addGap(dimension.width - 100)
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
						.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
						.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
						.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
						.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addGap(dimension.width / 4).addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE,
								dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
						.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addGap(dimension.width / 2).addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE,
								dimension.width / 4, GroupLayout.PREFERRED_SIZE)));

		int altura = (int) (dimension.height * 0.03);
		int alturaMenu = (int) ((dimension.height - altura) * 0.5);
		int alturaItem = (int) ((dimension.height - alturaMenu) / 4);

		gl_main.setVerticalGroup(gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup().addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
								.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))));

		main.setLayout(gl_main);

		cardPanel.add(main, "main");
		cardPanel.add(card, "card");

		getContentPane().add(cardPanel);

	}

	private void initializeListeners() {
		lblFechar.addMouseListener(mouseListenerLabel(lblFechar));
		lblMinimizar.addMouseListener(mouseListenerLabel(lblMinimizar));

		// SISTEMA
		lblStatusProd.addMouseListener(mouseListenerLabel(lblStatusProd));
		lblUsuarios.addMouseListener(mouseListenerLabel(lblUsuarios));
		lblManTable.addMouseListener(mouseListenerLabel(lblManTable));
		lblPropriedades.addMouseListener(mouseListenerLabel(lblPropriedades));

		// PRODU��O
		lblImpressaoOrdem.addMouseListener(mouseListenerLabel(lblImpressaoOrdem));
		lblReimpressao.addMouseListener(mouseListenerLabel(lblReimpressao));
		lblApagarOrdem.addMouseListener(mouseListenerLabel(lblApagarOrdem));

		// EXPEDI��O
		lblSaidaGTM.addMouseListener(mouseListenerLabel(lblSaidaGTM));
		lblReverseGTM.addMouseListener(mouseListenerLabel(lblReverseGTM));

		// CONTING�NCIA
		lblMonitorCarga.addMouseListener(mouseListenerLabel(lblMonitorCarga));
		lblOrdemManual.addMouseListener(mouseListenerLabel(lblOrdemManual));
		lblOrdemBuffer.addMouseListener(mouseListenerLabel(lblOrdemBuffer));
		lblSaidaBuffer.addMouseListener(mouseListenerLabel(lblSaidaBuffer));
	}

	private MouseListener mouseListenerLabel(JLabel label) {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (label == lblFechar)
					fechar();
				else if (label == lblMinimizar)
					minimizar();
				else if (label == lblStatusProd)
					if (PreferenciaFeps.getStatus())
						new EncerraSistema().setVisible(true);
					else
						new InicializaSistema().setVisible(true);

				else {

					// SISTEMA
					if (label == lblUsuarios)
						;
					else if (label == lblManTable)
						;
					else if (label == lblPropriedades) {
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.PREFERENCES);
						PreferenciaFeps.loadPreferences();
					}
					// PRODU��O
					else if (label == lblImpressaoOrdem)
						;
					else if (label == lblReimpressao)
						;
					else if (label == lblApagarOrdem)
						;

					// EXPEDI��O
					else if (label == lblSaidaGTM) {
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.EMISSAOGTM);
						card.startEmissaoGTM();
					}
					
					else if (label == lblReverseGTM)
						;

					// CONTING�NCIA
					else if (label == lblMonitorCarga) {
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.MONITOR);
						if (PreferenciaFeps.loadPreferences())
							card.monitorStart();

					} else if (label == lblOrdemManual)
						;
					else if (label == lblOrdemBuffer)
						;
					else if (label == lblSaidaBuffer)
						;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (label == lblFechar)
					label.setFont(new Font("Stencil", Font.PLAIN, 18));
				else if (label == lblMinimizar)
					label.setFont(new Font("Stencil", Font.PLAIN, 30));
				else {
					if (label == lblUsuarios)
						lblUsuarios.setIcon(new ImageIcon("icofeps\\menu\\userClicked.png"));
					else if (label == lblStatusProd) {
						if (PreferenciaFeps.getStatus())
							lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stopClicked.png"));
						else
							lblStatusProd.setIcon(new ImageIcon("icoFeps\\menu\\playClicked.png"));

					} else if (label == lblManTable)
						lblManTable.setIcon(new ImageIcon("icofeps\\menu\\avancedMaintenenceClicked.png"));
					else if (label == lblPropriedades)
						lblPropriedades.setIcon(new ImageIcon("icofeps\\menu\\toolsClicked.png"));

					// PRODU��O
					else if (label == lblImpressaoOrdem)
						lblImpressaoOrdem.setIcon(new ImageIcon("icofeps\\menu\\printOrderClicked.png"));
					else if (label == lblReimpressao)
						lblReimpressao.setIcon(new ImageIcon("icofeps\\menu\\reprintClicked.png"));
					else if (label == lblApagarOrdem)
						lblApagarOrdem.setIcon(new ImageIcon("icofeps\\menu\\eraseOrderClicked.png"));

					// EXPEDI��O
					else if (label == lblSaidaGTM)
						lblSaidaGTM.setIcon(new ImageIcon("icofeps\\menu\\saida-gtmClicked.png"));
					else if (label == lblReverseGTM)
						lblReverseGTM.setIcon(new ImageIcon("icofeps\\menu\\estorno-gtmClicked.png"));

					// CONTING�NCIA
					else if (label == lblMonitorCarga)
						lblMonitorCarga.setIcon(new ImageIcon("icofeps\\menu\\monitor-cargaClicked.png"));
					else if (label == lblOrdemManual)
						lblOrdemManual.setIcon(new ImageIcon("icofeps\\menu\\manualOrderClicked.png"));
					else if (label == lblOrdemBuffer)
						lblOrdemBuffer.setIcon(new ImageIcon("icofeps\\menu\\bufferOrderClicked.png"));
					else if (label == lblSaidaBuffer)
						lblSaidaBuffer.setIcon(new ImageIcon("icofeps\\menu\\sendBufferClicked.png"));

					label.setFont(new Font("Stencil", Font.PLAIN, 13));
					label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (label.getMousePosition() == null) {
					label.setBorder(null);
				} else
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

				if (label == lblFechar)
					label.setFont(new Font("Stencil", Font.PLAIN, 20));
				else if (label == lblMinimizar)
					label.setFont(new Font("Stencil", Font.PLAIN, 40));
				else {
					if (label == lblUsuarios)
						lblUsuarios.setIcon(new ImageIcon("icofeps\\menu\\user.png"));
					else if (label == lblStatusProd) {
						if (PreferenciaFeps.getStatus())
							lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stop.png"));
						else
							lblStatusProd.setIcon(new ImageIcon("icoFeps\\menu\\play.png"));

					} else if (label == lblManTable)
						lblManTable.setIcon(new ImageIcon("icofeps\\menu\\avancedMaintenence.png"));
					else if (label == lblPropriedades)
						lblPropriedades.setIcon(new ImageIcon("icofeps\\menu\\tools.png"));

					// PRODU��O
					else if (label == lblImpressaoOrdem)
						lblImpressaoOrdem.setIcon(new ImageIcon("icofeps\\menu\\printOrder.png"));
					else if (label == lblReimpressao)
						lblReimpressao.setIcon(new ImageIcon("icofeps\\menu\\reprint.png"));
					else if (label == lblApagarOrdem)
						lblApagarOrdem.setIcon(new ImageIcon("icofeps\\menu\\eraseOrder.png"));

					// EXPEDI��O
					else if (label == lblSaidaGTM)
						lblSaidaGTM.setIcon(new ImageIcon("icofeps\\menu\\saida-gtm.png"));
					else if (label == lblReverseGTM)
						lblReverseGTM.setIcon(new ImageIcon("icofeps\\menu\\estorno-gtm.png"));

					// CONTING�NCIA
					else if (label == lblMonitorCarga)
						lblMonitorCarga.setIcon(new ImageIcon("icofeps\\menu\\monitor-carga.png"));
					else if (label == lblOrdemManual)
						lblOrdemManual.setIcon(new ImageIcon("icofeps\\menu\\manualOrder.png"));
					else if (label == lblOrdemBuffer)
						lblOrdemBuffer.setIcon(new ImageIcon("icofeps\\menu\\bufferOrder.png"));
					else if (label == lblSaidaBuffer)
						lblSaidaBuffer.setIcon(new ImageIcon("icofeps\\menu\\sendBuffer.png"));

					label.setFont(new Font("Stencil", Font.PLAIN, 14));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(null);
			}
		};
	}

	public static void verificaSistema() {
		if (PreferenciaFeps.getStatus()) {
			sistemaAberto(true);
			card.sistemaAberto(true);
			lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stop.png"));
		} else {
			sistemaAberto(false);
			card.sistemaAberto(false);
			lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\play.png"));
		}
	}

	public static void getMain() {
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "main");
	}

	private static void sistemaAberto(boolean aberto) {
		lblManTable.setVisible(aberto);
		lblImpressaoOrdem.setVisible(aberto);
		lblReimpressao.setVisible(aberto);
		lblApagarOrdem.setVisible(aberto);
		lblSaidaGTM.setVisible(aberto);
		lblReverseGTM.setVisible(aberto);
		lblMonitorCarga.setVisible(aberto);
		lblOrdemManual.setVisible(aberto);
		lblOrdemBuffer.setVisible(aberto);
		lblSaidaBuffer.setVisible(aberto);
	}

	private static ImageIcon getIconSystemStatus() {
		if (PreferenciaFeps.getStatus())
			return new ImageIcon("icofeps\\menu\\stop.png");
		else
			return new ImageIcon("icofeps\\menu\\play.png");
	}

	public static String padding(int num, int length) {
		String numPad = Integer.toString(num);
		while (numPad.length() < length) {
			numPad = "0" + numPad;
		}

		return numPad;
	}

	public void minimizar() {
		setExtendedState(JFrame.ICONIFIED);
	}

	public void fechar() {
		card.monitorStop();
		dispose();
		System.exit(0);
	}
}
