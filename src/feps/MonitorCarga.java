package feps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class MonitorCarga extends JPanel{
	private static final long serialVersionUID = 1L;

//	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JLabel lblMonitorDeCarga;
	private JTextField txtArquivoEsperado;
	private JLabel lblArquivoEsperado, lblListArquivo, lblGifLoader, lblTotalArq, lblNumTotalArq, lblPlayPause;
	private JButton btnOrdemManual;
	private JScrollPane scrollPane;
	private JList<File> list;
	private DefaultListModel<File> itemList;
	private JLabel lblTempo;
	private JLabel lblNumTempo;
	private boolean statusMonitor = true;

	private static Timer timer;
	private static TimerTask task;

	public MonitorCarga() {
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		setSize(500, 410);
		setBackground(new Color(255, 200, 50));
	}

	private void initializeComponents() {

		lblMonitorDeCarga = new JLabel("Monitor de carga");
		lblGifLoader = new JLabel();
		lblArquivoEsperado = new JLabel("Arquivo esperado:");
		lblListArquivo = new JLabel("Arquivos encontrados:");
		lblTotalArq = new JLabel("Total de arquivos:");
		lblTempo = new JLabel("Tempo:");
		lblNumTempo = new JLabel("00:00:00");
		lblPlayPause = new JLabel(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\pause_24.png"));
		lblTotalArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumTotalArq = new JLabel("0");
		txtArquivoEsperado = new JTextField();
		btnOrdemManual = new JButton("Ordem manual");
		itemList = new DefaultListModel<>();
		list = new JList<File>(itemList);
		list.setFont(new Font("Broadway", Font.PLAIN, 17));
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);

		lblMonitorDeCarga.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblArquivoEsperado.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblListArquivo.setFont(new Font("Broadway", Font.PLAIN, 17));
		txtArquivoEsperado.setFont(new Font("Broadway", Font.PLAIN, 17));
		btnOrdemManual.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblTotalArq.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblNumTotalArq.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblTempo.setFont(new Font("Broadway", Font.PLAIN, 17));
		lblNumTempo.setFont(new Font("Broadway", Font.PLAIN, 17));

		list.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setText(((File) value).getName());
				this.setEnabled(true);
				return this;
			}
		});

		lblPlayPause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblMonitorDeCarga.setForeground(Color.BLACK);
		lblArquivoEsperado.setForeground(Color.BLACK);
		lblListArquivo.setForeground(Color.BLACK);
		lblTotalArq.setForeground(Color.BLACK);
		lblNumTotalArq.setForeground(Color.BLACK);
		txtArquivoEsperado.setForeground(Color.BLACK);
		btnOrdemManual.setForeground(Color.BLACK);
		list.setForeground(Color.BLACK);
		lblTempo.setForeground(Color.BLACK);
		lblNumTempo.setForeground(Color.BLACK);

		lblArquivoEsperado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArquivoEsperado.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMonitorDeCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumTotalArq.setHorizontalAlignment(SwingConstants.CENTER);
		lblTempo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumTempo.setHorizontalAlignment(SwingConstants.CENTER);

		lblGifLoader.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\loaderMonitor.gif"));

		lblListArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblListArquivo.setHorizontalTextPosition(SwingConstants.RIGHT);

		txtArquivoEsperado.setFocusable(false);
		txtArquivoEsperado.setEditable(false);
		list.setEnabled(false);

		txtArquivoEsperado.setText("");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMonitorDeCarga, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(52).addComponent(lblListArquivo).addGap(13)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPlayPause, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGifLoader, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(80)
								.addComponent(lblArquivoEsperado, GroupLayout.PREFERRED_SIZE, 181,
										GroupLayout.PREFERRED_SIZE)
								.addGap(11).addComponent(txtArquivoEsperado, GroupLayout.PREFERRED_SIZE, 163,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(272).addComponent(btnOrdemManual,
								GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(78)
								.addComponent(lblTotalArq, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGap(13).addComponent(lblNumTotalArq, GroupLayout.PREFERRED_SIZE, 163,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(80)
								.addComponent(lblTempo, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGap(11).addComponent(lblNumTempo, GroupLayout.PREFERRED_SIZE, 163,
										GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(10)
				.addComponent(lblMonitorDeCarga, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE).addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblListArquivo,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(1)
								.addComponent(lblGifLoader, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPlayPause, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(14)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblArquivoEsperado, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtArquivoEsperado, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
				.addGap(6).addComponent(btnOrdemManual, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addGap(4)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalArq, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumTotalArq, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblTempo,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNumTempo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))));
		setLayout(groupLayout);
	}

	private void initializeListeners() {
		lblPlayPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (statusMonitor) {
					lblPlayPause.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\play_24.png"));
					lblGifLoader.setVisible(false);
					statusMonitor = false;
					pause();
				} else {
					lblPlayPause.setIcon(new ImageIcon("C:\\Users\\uid38129\\Desktop\\ico feps\\pause_24.png"));
					lblGifLoader.setVisible(true);
					statusMonitor = true;
					start();
				}
				super.mouseClicked(e);
			}
		});
	}

	public void start() {
		JOptionPane.showMessageDialog(null, "monitor visivel");
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				atualizaDir();
			}
		};
		timer.schedule(task, 1000, 1000);
	}

	private void pause() {
		closeTask();
	}

	private void atualizaDir() {
		ArrayList<File> fileDir = recebeFile();
		itemList = new DefaultListModel<>();
		
		txtArquivoEsperado.setText(getExpectedNumDoc());
		
		
		for (int i = 0; i < fileDir.size(); i++) {
			itemList.addElement(fileDir.get(i));
		}

		if (itemList.size() > 0) {
			if (itemList.get(0).getName().equals(txtArquivoEsperado.getText())) {
				txtArquivoEsperado
						.setText(padding(Integer.parseInt(itemList.get(0).getName().substring(0, 4)) + 1, 4) + ".txt");
				criaOrdem(itemList.remove(itemList.indexOf(itemList.firstElement())));
			} else if (itemList.get(0).getName().contains(".EDS")) {
				gravaControleLeitura(itemList.remove(itemList.indexOf(itemList.firstElement())));
			} else
				JOptionPane.showMessageDialog(null, "Arquivo diferente do esperado!");
		}

		list.setModel(itemList);
	}

	private ArrayList<File> recebeFile() {
		File[] tmp = new File("C:\\svdo").listFiles();
		ArrayList<File> ret = new ArrayList<>();

		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].getName().endsWith(".txt") || tmp[i].getName().endsWith(".EDS"))
				ret.add(tmp[i]);
		}

		return ret;
	}

	private String gravaControleLeitura(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String sequencia, nomeArq, stringDoc, consultaSQL;
			Date data;
			Time hora;
			Connection c;
			PreparedStatement p;

			stringDoc = reader.readLine();
			nomeArq = file.getName();
			sequencia = Integer.toString(ConnectionFeps.getValorSeq("CONTROLE_LEITURA"));
			data = Date.valueOf(LocalDate.now());
			hora = Time.valueOf(LocalTime.now());

			if (stringDoc == null)
				stringDoc = "";

			consultaSQL = "INSERT INTO Controle_Leitura (sequencia, doc, data, string_doc)" + "VALUES ('" + sequencia
					+ "', '" + nomeArq + "', '" + new SimpleDateFormat("MM/dd/yyyy").format(data) + " "
					+ new SimpleDateFormat("HH:mm:ss").format(hora) + "', '" + stringDoc + "')";

			copyFile(file, new File("D:\\svdo\\lido\\" + file.getName()));

			reader.close();
			file.delete();

			try {
				c = ConnectionFeps.getConnection();
				p = c.prepareStatement(consultaSQL);
				p.executeUpdate();

				p.close();
				c.close();
			} catch (SQLException sqlE) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar!");
				sqlE.printStackTrace();
			}

			return stringDoc;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void criaOrdem(File file) {
		String doc, numDoc, dataHora, pvi, check, vin, partNumberGM, ordem_serie_vdo;

		doc = gravaControleLeitura(file);

		numDoc = doc.substring(0, 4);
		dataHora = doc.substring(4, 18);
		pvi = doc.substring(18, 28);
		check = doc.substring(28, 29);
		vin = doc.substring(29, 46);
		partNumberGM = doc.substring(46);
		ordem_serie_vdo = "";

		gravaOrdem(numDoc, dataHora, ConstantsFEPS.prodIniciada.getStringValue(), ordem_serie_vdo, pvi, check, vin,
				partNumberGM, ConstantsFEPS.ordemAutomatica.getIntValue(), ConnectionFeps.getValorSeq("ORDEM_GM"));
		inclusaoGTM();
	}

	private void gravaOrdem(String numDoc, String dataHora, String codProducao, String ordem_serie_vdo, String pvi,
			String check, String vin, String partNumberGM, int ordem, int numSeq) {
		Connection c;
		PreparedStatement p;
		Date data = Date.valueOf(LocalDate.now());
		Time hora = Time.valueOf(LocalTime.now());

		String consultaSQL = "INSERT INTO ORDEM_GM (ORDEM_GM_DOC, DATA_HORA, STATUS_CPROD_CODIGO, "
				+ "USUARIO_CODIGO, ORDEM_VDO_SERIE, PVI_CHECK, VIN, PART_NUMBER_GM, "
				+ "ORDEM_GM_ORIGEM, DATA_GM, DATA_INCLUSAO, ORDEM_ENTRADA) VALUES (" + "'" + numDoc + "', " + "'"
				+ dataHora + "', " + "'" + codProducao + "', " + "'" + padding(Login.getUsuario(), 6) + "', '"
				+ ordem_serie_vdo + "', '" + pvi.concat(check) + "', " + "'" + vin + "', " + "'" + partNumberGM + "', "
				+ "'" + ordem + "', " + "'" + data + " " + hora + "', " + "'" + data + " " + hora + "', " + "'" + numSeq
				+ "')";
		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			p.close();
			c.close();
		} catch (SQLException sqlE) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
			sqlE.printStackTrace();
		}
	}

	private void inclusaoGTM() {

	}

	private String padding(int num, int length) {
		String numPad = Integer.toString(num);
		while (numPad.length() < length) {
			numPad = "0" + numPad;
		}

		return numPad;
	}

	private String getExpectedNumDoc() {
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement("SELECT MAX(Ordem_GM_Doc) AS Ordem_GM_Doc FROM Ordem_GM");
			rs = p.executeQuery();

			if (rs.next()) {
				String numDoc = padding(rs.getInt("Ordem_GM_Doc") + 1, 4);

				rs.close();
				p.close();
				c.close();
				return numDoc + ".txt";
			}
			rs.close();
			p.close();
			c.close();

			return "0000.txt";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void copyFile(File source, File destination) throws IOException {
		if (destination.exists())
			destination.delete();
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		FileInputStream input = new FileInputStream(source);
		FileOutputStream output = new FileOutputStream(destination);
		try {
			sourceChannel = input.getChannel();
			destinationChannel = output.getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
		} finally {
			if (sourceChannel != null && sourceChannel.isOpen())
				sourceChannel.close();
			if (destinationChannel != null && destinationChannel.isOpen())
				destinationChannel.close();

			input.close();
			output.close();
		}
	}

	public static void closeTask() {
		timer.cancel();
		task.cancel();
	}
}
