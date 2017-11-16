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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class MenuPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static JPanel cardPanel = new JPanel(new CardLayout());
	
	private JPanel main = new JPanel();
	private CardFeps card = new CardFeps();

	private JLabel lblMinimizar = new JLabel("-");
	private JLabel lblFechar = new JLabel("X");

	// Aba "SISTEMA"
	private JLabel lblSistema = new JLabel("SISTEMA");
	private JLabel lblStatusProd = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\play.png"));
	private JLabel lblUsuarios = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\user.png"));
	private JLabel lblManAvan = new JLabel(
			new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\avancedMaintenence.png"));
	private JLabel lblPropriedades = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\tools.png"));

	private JSeparator s1 = new JSeparator();

	// Aba "PRODUÇÃO"
	private JLabel lblProducao = new JLabel("PRODUÇÃO");
	private JLabel lblImpressaoOrdem = new JLabel(
			new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\printOrder.png"));
	private JLabel lblReimpressao = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\reprint.png"));
	private JLabel lblApagarOrdem = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\eraseOrder.png"));

	private JSeparator s2 = new JSeparator();

	// Aba "EXPEDIÇÃO"
	private JLabel lblExpedicao = new JLabel("EXPEDIÇÃO");
	private JLabel lblSaidaGTM = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\saida-gtm.png"));
	private JLabel lblReverseGTM = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\estorno-gtm.png"));

	private JSeparator s3 = new JSeparator();

	// Aba "CONTINGÊNCIA"
	private JLabel lblContingencia = new JLabel("CONTINGÊNCIA");
	private JLabel lblMonitorCarga = new JLabel(
			new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\monitor-carga.png"));
	private JLabel lblOrdemManual = new JLabel(
			new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\manualOrder.png"));
	private JLabel lblOrdemBuffer = new JLabel(
			new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\bufferOrder.png"));
	private JLabel lblSaidaBuffer = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\sendBuffer.png"));

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");

			new MenuPrincipal().setVisible(true);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public MenuPrincipal() {
		setTitle("FEPS");
		buildFrame();
		initializeComponents();
		initializeListeners();
	}

	private void buildFrame() {
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.setPreferredSize(dimension);
		main.setPreferredSize(dimension);
		cardPanel.setPreferredSize(dimension);
		
		this.setBackground(new Color(255, 200, 50));
		main.setBackground(new Color(255, 200, 50));
		cardPanel.setBackground(new Color(255, 200, 50));

		this.setMinimumSize(new Dimension(1366, 768));
		main.setMinimumSize(new Dimension(1366, 768));
		cardPanel.setMinimumSize(new Dimension(1366, 768));
		
		this.setBounds(new Rectangle(new Point(0, 0), dimension));
		main.setBounds(new Rectangle(new Point(0, 0), dimension));
		cardPanel.setBounds(new Rectangle(new Point(0, 0), dimension));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MonitorCarga.closeTask();
				super.windowClosing(e);
			}
		});
	}

	private void initializeComponents() {

		s1.setBounds(360, 12, 9, 876);
		s2.setBounds(720, 12, 9, 876);
		s3.setBounds(1080, 12, 9, 876);

		s1.setForeground(Color.WHITE);
		s2.setForeground(Color.WHITE);
		s3.setForeground(Color.WHITE);

		s1.setBackground(Color.BLACK);
		s2.setBackground(Color.BLACK);
		s3.setBackground(Color.BLACK);

		s1.setOrientation(SwingConstants.VERTICAL);
		s2.setOrientation(SwingConstants.VERTICAL);
		s3.setOrientation(SwingConstants.VERTICAL);

		lblFechar.setFont(new Font("Broadway", Font.PLAIN, 20));
		lblFechar.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMinimizar.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblSistema.setForeground(Color.BLACK);
		lblSistema.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblSistema.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblProducao.setForeground(Color.BLACK);
		lblProducao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblProducao.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblExpedicao.setForeground(Color.BLACK);
		lblExpedicao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblExpedicao.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblContingencia.setForeground(Color.BLACK);
		lblContingencia.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblContingencia.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblStatusProd.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblStatusProd.setText("Iniciar/Encerrar sistema");
		lblStatusProd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStatusProd.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblStatusProd.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStatusProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusProd.setToolTipText("Iniciar/Encerrar Sistema");
		
		lblImpressaoOrdem.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblImpressaoOrdem.setText("Impressão");
		lblImpressaoOrdem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblImpressaoOrdem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImpressaoOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImpressaoOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setToolTipText("Impressão");
		
		lblSaidaGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblSaidaGTM.setText("Saída e emissão de GTM");
		lblSaidaGTM.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSaidaGTM.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setToolTipText("Saída e emissão de GTM");
		
		lblMonitorCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMonitorCarga.setText("Monitor de carga/Impressão");
		lblMonitorCarga.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMonitorCarga.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMonitorCarga.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setToolTipText("Monitor carga");
		
		lblUsuarios.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblUsuarios.setText("Cadastro de Usuários");
		lblUsuarios.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUsuarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setToolTipText("Usuarios");
		
		lblReimpressao.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblReimpressao.setText("Reimpressão");
		lblReimpressao.setVerticalAlignment(SwingConstants.BOTTOM);
		lblReimpressao.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReimpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReimpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblReimpressao.setToolTipText("Reimpressão de ordem");
		
		lblReverseGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblReverseGTM.setText("Estorno de GTM");
		lblReverseGTM.setVerticalAlignment(SwingConstants.BOTTOM);
		lblReverseGTM.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReverseGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReverseGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblReverseGTM.setToolTipText("Estorno GTM");
		
		lblOrdemManual.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblOrdemManual.setText("Ordem Manual");
		lblOrdemManual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrdemManual.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOrdemManual.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemManual.setToolTipText("Ordem Manual");
		
		lblManAvan.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblManAvan.setText("Manutenção avançada");
		lblManAvan.setVerticalAlignment(SwingConstants.BOTTOM);
		lblManAvan.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblManAvan.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManAvan.setHorizontalAlignment(SwingConstants.CENTER);
		lblManAvan.setToolTipText("Manutenção avançada");
		
		lblApagarOrdem.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblApagarOrdem.setText("Apagar Ordem");
		lblApagarOrdem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblApagarOrdem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblApagarOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblApagarOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblApagarOrdem.setToolTipText("Apagar Ordem");
		
		lblOrdemBuffer.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblOrdemBuffer.setText("Ordem Buffer");
		lblOrdemBuffer.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrdemBuffer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOrdemBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemBuffer.setToolTipText("Ordem Buffer");
		
		lblPropriedades.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblPropriedades.setText("Propriedades");
		lblPropriedades.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPropriedades.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblPropriedades.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPropriedades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropriedades.setToolTipText("Propriedades");
		
		lblSaidaBuffer.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblSaidaBuffer.setText("Saída Buffer");
		lblSaidaBuffer.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSaidaBuffer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setToolTipText("Saída Buffer");

		
		GroupLayout gl_main = new GroupLayout(main);
		gl_main.setHorizontalGroup(
			gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup()
					.addGap(dimension.width - 100)
					.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
					.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
					.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
					.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
					.addComponent(lblManAvan, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addGap(dimension.width / 4)
					.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_main.createSequentialGroup()
					.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE)
					.addGap(dimension.width / 2)
					.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE, dimension.width / 4, GroupLayout.PREFERRED_SIZE))
		);
		
		int altura = (int)(dimension.height * 0.03);
		int alturaMenu = (int)((dimension.height - altura) * 0.5);
		int alturaItem = (int)((dimension.height - alturaMenu) / 4);
		
		gl_main.setVerticalGroup(
			gl_main.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main.createSequentialGroup()
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblManAvan, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE, (dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)))
		);
		
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
		lblManAvan.addMouseListener(mouseListenerLabel(lblManAvan));
		lblPropriedades.addMouseListener(mouseListenerLabel(lblPropriedades));

		// PRODUÇÃO
		lblImpressaoOrdem.addMouseListener(mouseListenerLabel(lblImpressaoOrdem));
		lblReimpressao.addMouseListener(mouseListenerLabel(lblReimpressao));
		lblApagarOrdem.addMouseListener(mouseListenerLabel(lblApagarOrdem));

		// EXPEDIÇÃO
		lblSaidaGTM.addMouseListener(mouseListenerLabel(lblSaidaGTM));
		lblReverseGTM.addMouseListener(mouseListenerLabel(lblReverseGTM));

		// CONTINGÊNCIA
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
					System.exit(0);
				else if (label == lblMinimizar)
					setExtendedState(JFrame.ICONIFIED);
				else {

					((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");

					// SISTEMA
					if (label == lblStatusProd)
						;
					else if (label == lblUsuarios)
						;
					else if (label == lblManAvan)
						;
					else if (label == lblPropriedades)
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.PREFERENCES);

					// PRODUÇÃO
					else if (label == lblImpressaoOrdem)
						;
					else if (label == lblReimpressao)
						;
					else if (label == lblApagarOrdem)
						;

					// EXPEDIÇÃO
					else if (label == lblSaidaGTM)
						;
					else if (label == lblReverseGTM)
						;

					// CONTINGÊNCIA
					else if (label == lblMonitorCarga)
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.MONITOR);
					else if (label == lblOrdemManual)
						;
					else if (label == lblOrdemBuffer)
						;
					else if (label == lblSaidaBuffer)
						;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (label.getMousePosition() == null) {
					label.setBorder(null);
				} else
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
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

	public static void getMain() {
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "main");
	}
}
