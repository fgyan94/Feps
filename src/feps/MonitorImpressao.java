package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class MonitorImpressao extends JPanel {
	private static final long serialVersionUID = 1L;

	public class SerialComm implements SerialPortEventListener {
		boolean portFound = false;
		SerialPort serialPort;

		public void serialEvent(SerialPortEvent event) {

			switch (event.getEventType()) {
			case SerialPortEvent.BI:
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.RI:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				break;
			case SerialPortEvent.DATA_AVAILABLE:
				int novoDado = 0;
				StringBuilder dados = new StringBuilder();
				try {
					while (novoDado != -1) {
						novoDado = inputStream.read();
						if (novoDado == -1)
							break;
						if ('\r' == (char) novoDado)
							dados.append('\n');
						else
							dados.append((char) novoDado);
					}
					System.out.println(dados);
					receiveComm(dados.substring(0, 1));

				} catch (IOException e) {
				}

				break;
			}
		}

		private String getPortNameByOS() {

			String osname = System.getProperty("os.name", "").toLowerCase();
			if (osname.startsWith("windows")) {
				return "COM1"; // Se estiver no Windows
			} else if (osname.startsWith("linux")) {
				return "/dev/ttyS0"; // Se estiver no Linux
			} else if (osname.startsWith("mac")) {
				return "???"; // No mac eu não sei
			} else {
				System.out.println("Seu S.O. não é tem suporte ainda!"); // Se não for nenhum deles.
				System.exit(1);
				return null;
			}

		}

		private CommPortIdentifier getPortIdentifier(String portName) {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Pega os identificadores de porta
			Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
			// Varre as possíveis portas
			while (portList.hasMoreElements()) {
				CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
				// Verifica se a porta é a serial
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					System.out.println("Available port: " + portId.getName());
					// Verifica se é o identificador correto (COM1 ou ttsy0)
					if (portId.getName().equals(portName)) {
						System.out.println("Found port: " + portName);
						portFound = true;
						return portId;
					}
				}

			}

			return null;

		}

		InputStream inputStream; // O InputStream fica fora porque é utilizado depois
		OutputStream outputStream;

		public void execute() {
			// Pega a porta pelo S.O.
			String portName = getPortNameByOS();
			// Retorna o identificador da porta
			CommPortIdentifier portId = getPortIdentifier(portName);
			// Se o PortId for nulo, não há porta disponível
			if (portId != null) {

				try {
					// Abre a porta serial solicitada
					serialPort = (SerialPort) portId.open(this.getClass().getName(), 2000);

					// Pega o InputStream da Porta Serial
					inputStream = serialPort.getInputStream();
					outputStream = serialPort.getOutputStream();

					// Cria um novo Listener de Eventos
					serialPort.addEventListener(this);

					// Avisa se tive alguma mudança na Porta Serial
					serialPort.notifyOnDataAvailable(true);

					// Passa os parametros da porta serial
					serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
				} catch (PortInUseException e) {

				}
				// Implente sua exception aqui
				catch (IOException e) {

				}
				// Implente sua exception aqui
				catch (UnsupportedCommOperationException e) {
					e.printStackTrace();
				} catch (TooManyListenersException e) { // Implente sua exception aqui}

				}
			} else {
				System.out.println("Porta Serial não disponível");
			}
		}

		public void write(String msg) {
			try {
				outputStream = serialPort.getOutputStream();
				outputStream.write(msg.getBytes());
				Thread.sleep(100);
				outputStream.flush();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class ImpManual extends JDialog {
		private static final long serialVersionUID = 1L;

		private JLabel lblEncerraDia = new JLabel("Imprimir ordem manual?");
		private JLabel btnSim = new JLabel("SIM");
		private JLabel btnNao = new JLabel("NÃO");

		public ImpManual() {
			buildPanel();
			initializeComponents();
			initializeListeners();
		}

		private void buildPanel() {
			this.setBounds(0, 0, 320, 140);
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
			lblEncerraDia.setFont(new Font("Broadway", Font.PLAIN, 20));
			lblEncerraDia.setForeground(Color.LIGHT_GRAY);
			lblEncerraDia.setBounds(10, 10, 300, 90);
			getContentPane().add(lblEncerraDia);

			btnSim.setHorizontalAlignment(SwingConstants.CENTER);
			btnSim.setFont(new Font("Broadway", Font.PLAIN, 14));
			btnSim.setBounds(105, 100, 90, 30);
			btnSim.setForeground(Color.LIGHT_GRAY);
			btnSim.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnSim);

			btnNao.setHorizontalAlignment(SwingConstants.CENTER);
			btnNao.setFont(new Font("Broadway", Font.PLAIN, 14));
			btnNao.setBounds(200, 100, 90, 30);
			btnNao.setForeground(Color.LIGHT_GRAY);
			btnNao.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			getContentPane().add(btnNao);
		}

		private void initializeListeners() {
			btnSim.addMouseListener(mouseListenerLabel(btnSim));
			btnNao.addMouseListener(mouseListenerLabel(btnNao));
		}

		private MouseAdapter mouseListenerLabel(JLabel label) {
			return new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (label == btnSim) {
						imprimeOrdem(MonitorImpressao.S);
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

	static final String S = "S";
	static final String I = "I";

	private SerialComm serialPort = new SerialComm();

	private JLabel lblImpressao, lblOrdensParaMontagem, lblListaModelosProduzidos, lblComunicaoFepsRast, lblDesc, lblSeqDia, btnManualPrint;
	private JTable tblOrdemMontagem, tblModeloProd;
	private FepsModelTable fmtMontagem, fmtProduzido;
	private JScrollPane scrOrdemMontagem, scrModeloProd, scrComunicaFepsRast;
	private JEditorPane edtComunicaFepsRast;
	private MonitorCarga monitor;
	private JCheckBox cbBolha;

	private static Timer timer;
	private static TimerTask task;

	private String idCKP, seqDia, seqGM, modelo, descr, serieAtual, apelidoGM;
	private boolean ckpEnviado, imprimeBolha;

	String numBolha;

	public MonitorImpressao() {
		buildPanel();
		initializeComponents();
		initializeListener();
		configureSerialPort();
		initializeTables();
		fillTables();
		start();
	}

	private void start() {
		cancelTask();
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				fillTableMontagem();
				fillTableProduzido();
			}
		};
		timer.schedule(task, 1000, 10000);
	}

	private void cancelTask() {
		if (timer != null && task != null) {
			timer.cancel();
			task.cancel();

			timer = null;
			task = null;
		}
	}

	private void buildPanel() {
		UIManager.put("List.disabledForeground", Color.BLACK);

		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 1366, 688);

		this.setLayout(null);
	}

	private void initializeComponents() {

		lblImpressao = new JLabel("Impressão");
		lblOrdensParaMontagem = new JLabel("Ordens para montagem:");
		lblListaModelosProduzidos = new JLabel("Lista modelos produzidos:");
		lblComunicaoFepsRast = new JLabel("<html>Comunicação</br> Serial:</html>");
		lblDesc = new JLabel();
		lblSeqDia = new JLabel();
		btnManualPrint = new JLabel(new ImageIcon("icofeps\\monitorImp\\print_24.png"));
		cbBolha = new JCheckBox("Enviar bolhas para a linha");

		edtComunicaFepsRast = new JEditorPane();
		edtComunicaFepsRast.setEditable(false);
		scrOrdemMontagem = new JScrollPane();
		scrModeloProd = new JScrollPane();
		scrComunicaFepsRast = new JScrollPane();

		monitor = new MonitorCarga();

		lblImpressao.setBounds(578, 0, 777, 98);
		lblOrdensParaMontagem.setBounds(561, 89, 230, 30);
		lblListaModelosProduzidos.setBounds(561, 447, 570, 30);
		lblComunicaoFepsRast.setBounds(1173, 72, 150, 47);
		lblDesc.setBounds(60, 430, 500, 100);
		lblSeqDia.setBounds(60, 530, 500, 47);
		btnManualPrint.setBounds(1131, 89, 30, 30);
		scrOrdemMontagem.setBounds(561, 118, 600, 315);
		scrModeloProd.setBounds(561, 476, 600, 200);
		scrComunicaFepsRast.setBounds(1173, 119, 182, 557);
		cbBolha.setBounds(801, 89, 320, 30);

		monitor.setBounds(60, 0, 500, 410);

		lblImpressao.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblOrdensParaMontagem.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblComunicaoFepsRast.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListaModelosProduzidos.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblDesc.setFont(new Font("Broadway", Font.PLAIN, 30));
		lblSeqDia.setFont(new Font("Broadway", Font.PLAIN, 30));
		tblOrdemMontagem = new JTable();
		tblModeloProd = new JTable();
		tblOrdemMontagem.setFont(new Font("Calibri", Font.PLAIN, 15));
		tblModeloProd.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbBolha.setFont(new Font("Broadway", Font.PLAIN, 17));

		edtComunicaFepsRast.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblImpressao.setForeground(Color.BLACK);
		lblOrdensParaMontagem.setForeground(Color.BLACK);
		lblListaModelosProduzidos.setForeground(Color.BLACK);
		lblComunicaoFepsRast.setForeground(Color.BLACK);
		tblOrdemMontagem.setForeground(Color.DARK_GRAY);
		tblModeloProd.setForeground(Color.DARK_GRAY);
		cbBolha.setForeground(Color.DARK_GRAY);

		edtComunicaFepsRast.setForeground(Color.DARK_GRAY);

		lblImpressao.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdensParaMontagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblComunicaoFepsRast.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdensParaMontagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComunicaoFepsRast.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListaModelosProduzidos.setHorizontalTextPosition(SwingConstants.CENTER);

		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeqDia.setHorizontalAlignment(SwingConstants.CENTER);
		cbBolha.setHorizontalAlignment(SwingConstants.RIGHT);

		scrOrdemMontagem.setViewportView(tblOrdemMontagem);
		scrModeloProd.setViewportView(tblModeloProd);
		scrComunicaFepsRast.setViewportView(edtComunicaFepsRast);
		scrOrdemMontagem.setBorder(new LineBorder(Color.BLACK));
		scrModeloProd.setBorder(new LineBorder(Color.BLACK));
		scrComunicaFepsRast.setBorder(new LineBorder(Color.BLACK));

		btnManualPrint.setToolTipText("Impressão manual");

		add(lblImpressao);
		add(lblOrdensParaMontagem);
		add(lblComunicaoFepsRast);
		add(lblListaModelosProduzidos);	
		add(lblDesc);
		add(lblSeqDia);
		add(btnManualPrint);
		add(scrOrdemMontagem);
		add(scrModeloProd);
		add(scrComunicaFepsRast);
		add(cbBolha);
		add(monitor);
		
		ckpEnviado = false;
		imprimeBolha = false;
		numBolha = MenuPrincipal.padding(1, 4);
	}

	private void initializeListener() {
		btnManualPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ImpManual().setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				super.mouseEntered(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnManualPrint.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnManualPrint.setBorder(null);
			}
		});
	}

	private void configureSerialPort() {
		serialPort.execute();
	}

	private void initializeTables() {
		createMontagemTable();
		createProduzidosTable();
	}

	private void createMontagemTable() {

		ArrayList<String> coluna = new ArrayList<>();

		coluna.add("Part Number");
		coluna.add("Apelido");
		coluna.add("Série Ordem");
		coluna.add("Data Ordem");
		coluna.add("Ordem Entrada");

		fmtMontagem = new FepsModelTable(coluna);

		tblOrdemMontagem.setModel(fmtMontagem);
		tblOrdemMontagem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tblOrdemMontagem.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tblOrdemMontagem.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			tblOrdemMontagem.getColumnModel().getColumn(i).setPreferredWidth(108);
			if (fmtMontagem.getColumnName(i).contains("Data") || fmtMontagem.getColumnName(i).contains("Série"))
				tblOrdemMontagem.getColumnModel().getColumn(i).setPreferredWidth((int) 137.5f);
		}

		fmtMontagem.fireTableDataChanged();

	}

	private void createProduzidosTable() {
		ArrayList<String> coluna = new ArrayList<>();

		coluna.add("Part Number");
		coluna.add("Apelido");
		coluna.add("Quantidade");

		fmtProduzido = new FepsModelTable(coluna);

		tblModeloProd.setModel(fmtProduzido);

		for (int i = 0; i < tblModeloProd.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tblModeloProd.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		fmtMontagem.fireTableDataChanged();

	}

	public void monitorStart() {
		monitor.start();
	}

	public void monitorStop() {
		monitor.cancelTask();
	}

	private void fillTables() {
		fillTableMontagem();
		fillTableProduzido();
	}

	private void fillTableMontagem() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		
		fmtMontagem.clear();

		try {
			consultaSQL = "SELECT Ordem_Conti.*, Status_Cockpit.descricao, GM_Conti.apelido, GM_Conti.Codigo_GM FROM Status_Cockpit, "
					+ "Ordem_Conti LEFT OUTER JOIN GM_Conti ON Ordem_Conti.PART_NUMBER_GM = GM_Conti.CODIGO_GM "
					+ "WHERE Ordem_Conti.Status_cockpit = '001' AND Ordem_Conti.Status_cockpit = STATUS_COCKPIT.codigo ORDER BY Ordem_Conti.Ordem_Entrada";

			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm");
					String apelido = rs.getString("apelido");
					String ordem_serie = rs.getString("ordem_conti_serie");
					String ordem_data = rs.getString("ordem_conti_data");
					String ordem_entrada = rs.getString("ordem_entrada");

					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, ordem_data, ordem_entrada);
					lista.add(ordem);

					rs.next();
				}

				fmtMontagem.addOrdemList(lista);
			}

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}

	private void fillTableProduzido() {

		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		fmtProduzido.clear();
		
		try {
			consultaSQL = "SELECT gm_conti.*, qtde = (SELECT COUNT(*) FROM ordem_conti WHERE part_number_gm = codigo_gm"
					+ "   AND status_cockpit = '001') FROM gm_conti ORDER BY qtde DESC";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					if (Integer.parseInt(rs.getString("qtde")) > 0) {
						String partNumber = rs.getString("codigo_gm");
						String apelido = rs.getString("apelido");
						String quantidade = rs.getString("qtde");

						Ordem ordem = new Ordem(partNumber, apelido, Integer.parseInt(quantidade));
						lista.add(ordem);
					}

					rs.next();
				}

				fmtProduzido.addOrdemList(lista);
			}

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}

	private void receiveComm(String bufferSerial) {
		cancelTask();
		if (bufferSerial.equals(S)) {
			edtComunicaFepsRast.setText("Solicitado ordem do RFID\r\n" + edtComunicaFepsRast.getText());
			if (cbBolha.isSelected() || fmtMontagem.getRowCount() < 1) {
				idCKP = MenuPrincipal.padding(99, 3);
				numBolha = MenuPrincipal.padding(1, 4);
				serialPort.write((char) 2 + numBolha + idCKP + (char) 3);
				edtComunicaFepsRast.setText("Enviado comando de bolha\r\n" + edtComunicaFepsRast.getText());
				ckpEnviado = true;
				imprimeBolha = true;
			}

			else if (!cbBolha.isSelected() && fmtMontagem.getRowCount() > 0) {
				String consultaSQL;
				Connection c;
				PreparedStatement p;
				ResultSet rs;

				modelo = (String) fmtMontagem.getValueAt(0, 0);
				seqDia = MenuPrincipal.padding(Integer.parseInt((String) fmtMontagem.getValueAt(0, 4)), 3);
				seqGM = Integer.toString(0);
				serieAtual = (String) fmtMontagem.getValueAt(0, 2);
				apelidoGM = (String) fmtMontagem.getValueAt(0, 1);

				try {
					consultaSQL = "SELECT * FROM ORDEM_GM WHERE ordem_conti_serie = '" + serieAtual + "'";

					c = ConnectionFeps.getConnection();
					p = c.prepareStatement(consultaSQL);
					rs = p.executeQuery();

					if (rs.next())
						seqGM = rs.getString("ordem_gm_doc");
					else
						seqGM = (char) 2 + "B" + seqDia.substring((seqDia.length() - 1) - 3) + idCKP + (char) 3;

					consultaSQL = "SELECT * FROM gm_conti WHERE codigo_gm = '" + modelo + "'";
					p = c.prepareStatement(consultaSQL);
					rs = p.executeQuery();

					if (rs.next()) {
						idCKP = MenuPrincipal.padding(rs.getInt("id"), 3);
						descr = rs.getString("historico");
					}

					String msg = (char) 2 + seqGM + idCKP + (char) 3;
					serialPort.write(msg);

					edtComunicaFepsRast
							.setText("Enviado-" + modelo + "-" + seqGM + "-" + idCKP + "\r\n" + edtComunicaFepsRast.getText());
					
					ckpEnviado = true;
					imprimeBolha = false;

					rs.close();
					p.close();
					c.close();
				} catch (SQLException sqlE) {
					sqlE.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao consultar!");
				}

			}
		} else if (bufferSerial.equals(I)) {
			if(ckpEnviado && imprimeBolha) {
				lblDesc.setText("<html> Carro com bolha - " + numBolha + "<br>");
				lblSeqDia.setVisible(false);
				ckpEnviado = false;
				imprimeBolha = false;
				
				Imprime.imprimeBolha();
				
			} else if (ckpEnviado && !imprimeBolha) {
				edtComunicaFepsRast.setText("Comando de impressão recebido \r\n" + edtComunicaFepsRast.getText());
				lblDesc.setText("<html>" + descr + "<br>");
				lblSeqDia.setText("Sequência dia - " + seqDia);
				ckpEnviado = false;
				
				Imprime.setStatusImpressao(serieAtual, apelidoGM);
				Imprime.imprimeOrdem(serieAtual, apelidoGM);
			}
		}
		start();
	}

	private void imprimeOrdem(String bufferSerial) {
		receiveComm(bufferSerial);
	}
}
