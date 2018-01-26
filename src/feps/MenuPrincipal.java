package feps;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


public class MenuPrincipal extends JFrame {

	private class InicializaSistema extends JDialog {
		private static final long serialVersionUID = 1L;

		private JLabel lblUltimaData = new JLabel("Última data:");
		private JLabel lblDataAbertura = new JLabel("Data abertura:");
		private JTextField txtUltimaData = new JTextField();
		private JComboBox<String> cbDataAbertura = new JComboBox<String>();
		private JLabel btnInicializar = new JLabel("Inicializar");
		private JLabel btnCancelar = new JLabel("Cancelar");

		public InicializaSistema() {
			buildPanel();
			initializeComponents();
			initializeListeners();
			loadComponents();
		}

		private void buildPanel() {
			this.setBounds(0, 0, 300, 200);
			this.setModal(true);
			this.setUndecorated(true);
			this.setOpacity(0.95f);
			this.setLocationRelativeTo(null);
			this.setBackground(Color.BLACK);

			this.getContentPane().setLayout(null);
			this.getContentPane().setBackground(Color.BLACK);
		}

		private void initializeComponents() {

			lblUltimaData.setFont(new Font("Stencil", Font.PLAIN, 14));
			lblUltimaData.setBounds(10, 30, 120, 30);
			lblUltimaData.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUltimaData.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(lblUltimaData);

			lblDataAbertura.setFont(new Font("Stencil", Font.PLAIN, 14));
			lblDataAbertura.setBounds(10, 80, 120, 30);
			lblDataAbertura.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataAbertura.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(lblDataAbertura);

			cbDataAbertura.setFont(new Font("Stencil", Font.PLAIN, 14));
			cbDataAbertura.setBounds(140, 80, 120, 30);
			cbDataAbertura.setBackground(Color.LIGHT_GRAY);
			getContentPane().add(cbDataAbertura);

			txtUltimaData.setFont(new Font("Stencil", Font.PLAIN, 14));
			txtUltimaData.setBounds(140, 30, 120, 30);
			txtUltimaData.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
			txtUltimaData.setHorizontalAlignment(SwingConstants.CENTER);
			txtUltimaData.setForeground(Color.LIGHT_GRAY);
			txtUltimaData.setBackground(Color.DARK_GRAY);
			txtUltimaData.setEditable(false);
			getContentPane().add(txtUltimaData);

			btnInicializar.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnInicializar.setBounds(30, 150, 130, 30);
			btnInicializar.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			btnInicializar.setHorizontalAlignment(SwingConstants.CENTER);
			btnInicializar.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(btnInicializar);

			btnCancelar.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnCancelar.setBounds(170, 150, 90, 30);
			btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
			btnCancelar.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(btnCancelar);
		}

		private void initializeListeners() {
			btnInicializar.addMouseListener(mouseListenerLabel(btnInicializar));
			btnCancelar.addMouseListener(mouseListenerLabel(btnCancelar));
		}

		private void loadComponents() {
			loadUltimaData();
			loadComboBox();
			setCellRenderComboBox();
		}

		private void loadUltimaData() {
			String consultaSQL;
			ResultSet rs;

			try {
				consultaSQL = "SELECT * FROM parametros";
				rs = ConnectionFeps.query(consultaSQL);

				if (rs.next()) {
					String sDate = rs.getString("data_sistema");
					if (sDate == null)
						txtUltimaData.setText("");
					else {
						sDate = new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(sDate));
						txtUltimaData.setText(sDate);
					}
				}

				ConnectionFeps.closeConnection(rs, null, null);
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível carregar a última data do sistema!");
			}
		}

		private void loadComboBox() {
			cbDataAbertura.removeAll();
			LocalDate data;

			if (txtUltimaData.getText().equals(""))
				data = LocalDate.now();
			else {
				int dayOfMonth = Integer.parseInt(txtUltimaData.getText().substring(0, 2));
				int month = Integer.parseInt(txtUltimaData.getText().substring(3, 5));
				int year = Integer.parseInt(txtUltimaData.getText().substring(6));

				data = LocalDate.of(year, month, dayOfMonth);
				data = data.plusDays(1);
			}

			for (int i = 0; i < 30; i++) {
				String stringData = new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(data));
				cbDataAbertura.addItem(stringData);
				data = data.plusDays(1);
			}
		}

		private void setCellRenderComboBox() {
			cbDataAbertura.setRenderer(new ListCellRenderer<String>() {

				@Override
				public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
						boolean isSelected, boolean cellHasFocus) {

					JLabel renderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value,
							index, isSelected, cellHasFocus);

					if (isSelected) {
						renderer.setForeground(Color.BLACK);
						renderer.setBackground(Color.LIGHT_GRAY);
					}

					if (list.isSelectedIndex(index)) {
						list.setSelectionForeground(Color.BLACK);
						list.setSelectionBackground(Color.LIGHT_GRAY);
					}

					return renderer;
				}
			});

		}

		private void run() {
			String consultaSQL;
			String data = (String) cbDataAbertura.getSelectedItem();

			int dayOfMonth = Integer.parseInt(data.substring(0, 2));
			int month = Integer.parseInt(data.substring(3, 5));
			int year = Integer.parseInt(data.substring(6));

			consultaSQL = "UPDATE parametros SET aberto = 'S', data_sistema = '" + LocalDate.of(year, month, dayOfMonth)
					+ "'";

			if (!ConnectionFeps.update(consultaSQL))
				JOptionPane.showMessageDialog(null, "Não foi possível inicializar o dia!");
		}

		private MouseAdapter mouseListenerLabel(JLabel label) {
			return new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (label == btnInicializar) {
						run();
						change();
						dispose();
					} else
						dispose();

				}

				@Override
				public void mousePressed(MouseEvent e) {
					label.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}
			};
		}
	}
	
	private class EncerraSistema extends JDialog {
		private static final long serialVersionUID = 1L;

		private JLabel lblEncerraDia = new JLabel("Encerrar o dia?");
		private JLabel btnSim = new JLabel("SIM");
		private JLabel btnNao = new JLabel("NÃO");

		public EncerraSistema() {
			buildPanel();
			initializeComponents();
			initializeListeners();
		}

		private void buildPanel() {
			this.setBounds(0, 0, 300, 140);
			this.setModal(true);
			this.setUndecorated(true);
			this.setOpacity(0.95f);
			this.setLocationRelativeTo(null);
			this.setBackground(Color.BLACK);
			this.getContentPane().setBackground(Color.BLACK);
			getContentPane().setLayout(null);
		}

		private void initializeComponents() {

			lblEncerraDia.setHorizontalAlignment(SwingConstants.LEFT);
			lblEncerraDia.setFont(new Font("Stencil", Font.PLAIN, 20));
			lblEncerraDia.setForeground(Color.LIGHT_GRAY);
			lblEncerraDia.setBounds(10, 10, 280, 90);
			getContentPane().add(lblEncerraDia);
			
			btnSim.setHorizontalAlignment(SwingConstants.CENTER);
			btnSim.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnSim.setBounds(105, 100, 90, 30);
			btnSim.setForeground(Color.LIGHT_GRAY);
			btnSim.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnSim);
			
			btnNao.setHorizontalAlignment(SwingConstants.CENTER);
			btnNao.setFont(new Font("Stencil", Font.PLAIN, 14));
			btnNao.setBounds(200, 100, 90, 30);
			btnNao.setForeground(Color.LIGHT_GRAY);
			btnNao.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnNao);
		}

		private void initializeListeners() {
			btnSim.addMouseListener(mouseListenerLabel(btnSim));
			btnNao.addMouseListener(mouseListenerLabel(btnNao));
		}

		private void end() {
			String consultaSQL = "UPDATE parametros SET aberto = 'N'";
			if (!ConnectionFeps.update(consultaSQL))
				JOptionPane.showMessageDialog(null, "Não foi possível encerrar o dia!");
		}

		private void clearValues() {
			String consultaSQL;
			ResultSet rs;
			List<String> serie;

			try {
				consultaSQL = "SELECT * FROM gm_conti";
				rs = ConnectionFeps.query(consultaSQL);
				serie = new ArrayList<>();
				
				if (rs.next()) {
					while (!rs.isAfterLast()) {
						if(!serie.contains("SERIE_" + rs.getString("apelido_serie")))
								serie.add("SERIE_" + rs.getString("apelido_serie"));
						rs.next();
					}					
					
					for(int i = 0; i < serie.size(); i++) {
						consultaSQL = "UPDATE controle_geral SET valor = '0' WHERE nome = '" + serie.get(i).trim() + "'";
						if (!ConnectionFeps.update(consultaSQL)) {
							JOptionPane.showMessageDialog(null, "Não foi possível zerar o controle da série: " + serie.get(i).trim() + "!");
							return;
						}
					}
				}
				
				ConnectionFeps.closeConnection(rs, null, null);
				
				consultaSQL = "UPDATE controle_geral SET valor = '0' WHERE nome = 'SEQ_DIA'";
				if (!ConnectionFeps.update(consultaSQL)) {
					JOptionPane.showMessageDialog(null, "Não foi possível zerar o controle das sequências dia!");
					return;
				}
				

			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível buscar os dados para zerar os valores de controle!");
			}
		}
		
		private MouseAdapter mouseListenerLabel(JLabel label) {
			return new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(label == btnSim) {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						end();
						clearValues();
						change();
						close();
						dispose();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} else
						dispose();
				}
				@Override
				public void mousePressed(MouseEvent e) {
					label.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				}
			};
		}
		
		private void close() {
			card.stop();
			card.clearValues();
		}
	}
	
	private static final long serialVersionUID = 1L;

//	private Dimension dimension = new Dimension(1366, 768);
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static JPanel cardPanel = new JPanel(new CardLayout());

	private GroupLayout groupLayout;
	
	private JPanel main = new JPanel();
	private CardFeps card = new CardFeps();

	private JLabel lblMinimizar = new JLabel("-");
	private JLabel lblFechar = new JLabel("X");

	// Aba "SISTEMA"
	private JLabel lblSistema = new JLabel("SISTEMA");
	private JLabel lblStatusProd = new JLabel(getIconSystemStatus());
	private JLabel lblUsuarios = new JLabel(new ImageIcon("icofeps\\menu\\user.png"));
	private JLabel lblManTable = new JLabel(new ImageIcon("icofeps\\menu\\avancedMaintenence.png"));
	private JLabel lblPropriedades = new JLabel(new ImageIcon("icofeps\\menu\\tools.png"));

	// Aba "PRODUÇÃO"
	private JLabel lblProducao = new JLabel("PRODUÇÃO");
	private JLabel lblImpressaoOrdem = new JLabel(new ImageIcon("icofeps\\menu\\printOrder.png"));
	private JLabel lblReimpressao = new JLabel(new ImageIcon("icofeps\\menu\\reprint.png"));
	private JLabel lblApagarOrdem = new JLabel(new ImageIcon("icofeps\\menu\\eraseOrder.png"));

	// Aba "EXPEDIÇÃO"
	private JLabel lblExpedicao = new JLabel("EXPEDIÇÃO");
	private JLabel lblSaidaGTM = new JLabel(new ImageIcon("icofeps\\menu\\saida-gtm.png"));
	private JLabel lblReverseGTM = new JLabel(new ImageIcon("icofeps\\menu\\estorno-gtm.png"));

	// Aba "CONTINGÊNCIA"
	private JLabel lblContingencia = new JLabel("CONTINGÊNCIA");
	private JLabel lblMonitorCarga = new JLabel(new ImageIcon("icofeps\\menu\\monitor-carga.png"));
	private JLabel lblOrdemManual = new JLabel(new ImageIcon("icofeps\\menu\\manualOrder.png"));
	private JLabel lblOrdemBuffer = new JLabel(new ImageIcon("icofeps\\menu\\bufferOrder.png"));
	private JLabel lblSaidaBuffer = new JLabel(new ImageIcon("icofeps\\menu\\sendBuffer.png"));

	public MenuPrincipal() {
		setTitle("FEPS");
		buildFrame();
		buildGroupLayout();
		initializeComponents();
		initializeListeners();
		change();
	}

	private void buildFrame() {
		this.setUndecorated(true);
		this.setOpacity(0.9f);
		this.setLocationRelativeTo(null);
//		this.setExtendedState(MAXIMIZED_BOTH);
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
		
		groupLayout = new GroupLayout(main);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (!card.loadPreferences()) {
					((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
					((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.PREFERENCES);
					card.definePreferencias();
				}
				super.windowOpened(e);
			}
		});
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		main.setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(dimension.width - 100)
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addGap(dimension.width / 4).addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE,
								dimension.width / 4, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE, dimension.width / 4,
								GroupLayout.PREFERRED_SIZE)
						.addGap(dimension.width / 2).addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE,
								dimension.width / 4, GroupLayout.PREFERRED_SIZE)));
	}

	private void buildVerticalLayout() {
		int altura = (int) (dimension.height * 0.03);
		int alturaMenu = (int) ((dimension.height - altura) * 0.5);
		int alturaItem = (int) ((dimension.height - alturaMenu) / 4);

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMinimizar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechar, GroupLayout.PREFERRED_SIZE, altura, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSistema, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProducao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExpedicao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContingencia, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaMenu) / 5, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStatusProd, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImpressaoOrdem, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSaidaGTM, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMonitorCarga, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsuarios, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReimpressao, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReverseGTM, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrdemManual, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblManTable, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApagarOrdem, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrdemBuffer, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPropriedades, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSaidaBuffer, GroupLayout.PREFERRED_SIZE,
										(dimension.height - alturaItem) / 4, GroupLayout.PREFERRED_SIZE))));
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
		lblImpressaoOrdem.setText("Impressão");
		lblImpressaoOrdem.setVerticalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImpressaoOrdem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImpressaoOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpressaoOrdem.setToolTipText("Impressão");

		lblSaidaGTM.setForeground(Color.BLACK);
		lblSaidaGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblSaidaGTM.setText("Saída e emissão de GTM");
		lblSaidaGTM.setVerticalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaGTM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaGTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaGTM.setToolTipText("Saída e emissão de GTM");

		lblMonitorCarga.setForeground(Color.BLACK);
		lblMonitorCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMonitorCarga.setText("Monitor de carga/Impressão");
		lblMonitorCarga.setVerticalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMonitorCarga.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonitorCarga.setToolTipText("Monitor carga");

		lblUsuarios.setForeground(Color.BLACK);
		lblUsuarios.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblUsuarios.setText("Cadastro de Usuários");
		lblUsuarios.setVerticalAlignment(SwingConstants.CENTER);
		lblUsuarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setToolTipText("Usuarios");

		lblReimpressao.setForeground(Color.BLACK);
		lblReimpressao.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblReimpressao.setText("Reimpressão");
		lblReimpressao.setVerticalAlignment(SwingConstants.CENTER);
		lblReimpressao.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReimpressao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReimpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblReimpressao.setToolTipText("Reimpressão de ordem");

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
		lblManTable.setText("Manutenção de Tabelas");
		lblManTable.setVerticalAlignment(SwingConstants.CENTER);
		lblManTable.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblManTable.setHorizontalTextPosition(SwingConstants.CENTER);
		lblManTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblManTable.setToolTipText("Manutenção de Tabelas");

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
		lblSaidaBuffer.setText("Saída Buffer");
		lblSaidaBuffer.setVerticalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSaidaBuffer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSaidaBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaidaBuffer.setToolTipText("Saída Buffer");

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
					fechar();
				else if (label == lblMinimizar)
					minimizar();
				else if (label == lblStatusProd)
					if (getStatus())
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
						card.loadPreferences();
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.PREFERENCES);
					}
					// PRODUÇÃO
					else if (label == lblImpressaoOrdem)
						;
					else if (label == lblReimpressao)
						;
					else if (label == lblApagarOrdem)
						;

					// EXPEDIÇÃO
					else if (label == lblSaidaGTM) {
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.EMISSAOGTM);
						card.emissaoStart();
					}
					
					else if (label == lblReverseGTM)
						;

					// CONTINGÊNCIA
					else if (label == lblMonitorCarga) {
						((CardLayout) cardPanel.getLayout()).show(cardPanel, "card");
						((CardLayout) card.getCardPanel().getLayout()).show(card.getCardPanel(), card.MONITOR);
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
						if (getStatus())
							lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stopClicked.png"));
						else
							lblStatusProd.setIcon(new ImageIcon("icoFeps\\menu\\playClicked.png"));

					} else if (label == lblManTable)
						lblManTable.setIcon(new ImageIcon("icofeps\\menu\\avancedMaintenenceClicked.png"));
					else if (label == lblPropriedades)
						lblPropriedades.setIcon(new ImageIcon("icofeps\\menu\\toolsClicked.png"));

					// PRODUÇÃO
					else if (label == lblImpressaoOrdem)
						lblImpressaoOrdem.setIcon(new ImageIcon("icofeps\\menu\\printOrderClicked.png"));
					else if (label == lblReimpressao)
						lblReimpressao.setIcon(new ImageIcon("icofeps\\menu\\reprintClicked.png"));
					else if (label == lblApagarOrdem)
						lblApagarOrdem.setIcon(new ImageIcon("icofeps\\menu\\eraseOrderClicked.png"));

					// EXPEDIÇÃO
					else if (label == lblSaidaGTM)
						lblSaidaGTM.setIcon(new ImageIcon("icofeps\\menu\\saida-gtmClicked.png"));
					else if (label == lblReverseGTM)
						lblReverseGTM.setIcon(new ImageIcon("icofeps\\menu\\estorno-gtmClicked.png"));

					// CONTINGÊNCIA
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
						if (getStatus())
							lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stop.png"));
						else
							lblStatusProd.setIcon(new ImageIcon("icoFeps\\menu\\play.png"));

					} else if (label == lblManTable)
						lblManTable.setIcon(new ImageIcon("icofeps\\menu\\avancedMaintenence.png"));
					else if (label == lblPropriedades)
						lblPropriedades.setIcon(new ImageIcon("icofeps\\menu\\tools.png"));

					// PRODUÇÃO
					else if (label == lblImpressaoOrdem)
						lblImpressaoOrdem.setIcon(new ImageIcon("icofeps\\menu\\printOrder.png"));
					else if (label == lblReimpressao)
						lblReimpressao.setIcon(new ImageIcon("icofeps\\menu\\reprint.png"));
					else if (label == lblApagarOrdem)
						lblApagarOrdem.setIcon(new ImageIcon("icofeps\\menu\\eraseOrder.png"));

					// EXPEDIÇÃO
					else if (label == lblSaidaGTM)
						lblSaidaGTM.setIcon(new ImageIcon("icofeps\\menu\\saida-gtm.png"));
					else if (label == lblReverseGTM)
						lblReverseGTM.setIcon(new ImageIcon("icofeps\\menu\\estorno-gtm.png"));

					// CONTINGÊNCIA
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

	public void change() {
		if (getStatus()) {
			sistemaAberto(true);
			lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\stop.png"));
		} else {
			sistemaAberto(false); 
			lblStatusProd.setIcon(new ImageIcon("icofeps\\menu\\play.png"));
		}
	}

	public static void getMain() {
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "main");
	}

	private void sistemaAberto(boolean aberto) {
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

	private ImageIcon getIconSystemStatus() {
		if (getStatus())
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
		card.stop();
		card.closeSerial();
		dispose();
		System.exit(0);
	}
	
	private Object getParameter(String tmp) {
		String consultaSQL = "SELECT * FROM parametros";
		String parametro = null;
		ResultSet rs;
		try {
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if(rs.getString(tmp) == null)
					parametro = "";
				else
					parametro = rs.getString(tmp).trim();

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o parâmetro: " + tmp + "!");
		}

		return parametro;
	}
	
	public boolean getStatus() {
		String ret = ((String) getParameter("aberto"));
		return ret != null && ret.equals("S");
	}
}
