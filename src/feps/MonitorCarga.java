package feps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class MonitorCarga extends JPanel {
	private static final long serialVersionUID = 1L;

	private GroupLayout groupLayout;

	private JLabel lblMonitorDeCarga;
	private JTextField txtArquivoEsperado;
	private JLabel lblArquivoEsperado, lblListArquivo, lblGifLoader, lblTotalArq, lblNumTotalArq, lblPlayPause,
			btnOrdemManual;
	private JScrollPane scrollPane;
	private JList<File> list;
	private DefaultListModel<File> itemList;
	private JLabel lblTempo;
	private JLabel lblNumTempo;
	private boolean statusMonitor = true;

	private Timer timer, timerCountTempo;
	private TimerTask task, taskCountTempo;

	public MonitorCarga() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
		initializeListeners();
	}

	private void buildPanel() {
		setSize(500, 410);
		setBackground(Color.WHITE);

		groupLayout = new GroupLayout(this);
	}

	private void initializeComponents() {
		lblMonitorDeCarga = new JLabel("Monitor de carga");
		lblGifLoader = new JLabel();
		lblArquivoEsperado = new JLabel("Arquivo esperado:");
		lblListArquivo = new JLabel("Arquivos encontrados:");
		lblTotalArq = new JLabel("Total de arquivos:");
		lblTempo = new JLabel("Tempo:");
		lblNumTempo = new JLabel("00:00:00");
		lblPlayPause = new JLabel(new ImageIcon("icofeps\\pause_24.png"));
		lblTotalArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumTotalArq = new JLabel("0");
		txtArquivoEsperado = new JTextField();
		txtArquivoEsperado.setBorder(new LineBorder(Color.BLACK));
		btnOrdemManual = new JLabel("Ordem manual");
		btnOrdemManual.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		btnOrdemManual.setHorizontalAlignment(SwingConstants.CENTER);
		itemList = new DefaultListModel<>();
		list = new JList<File>(itemList);
		list.setBorder(new LineBorder(Color.BLACK));
		list.setFont(new Font("Stencil", Font.PLAIN, 17));
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);

		txtArquivoEsperado.setBorder(new LineBorder(Color.BLACK));
		txtArquivoEsperado.setForeground(Color.BLACK);
		txtArquivoEsperado.setMargin(new Insets(0, 10, 0, 0));
		txtArquivoEsperado.setEditable(false);
		txtArquivoEsperado.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtArquivoEsperado.setHorizontalAlignment(SwingConstants.LEFT);

		lblMonitorDeCarga.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblArquivoEsperado.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblListArquivo.setFont(new Font("Stencil", Font.PLAIN, 17));
		txtArquivoEsperado.setFont(new Font("Stencil", Font.PLAIN, 17));
		btnOrdemManual.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblTotalArq.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblNumTotalArq.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblTempo.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblNumTempo.setFont(new Font("Stencil", Font.PLAIN, 17));

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

		lblGifLoader.setIcon(new ImageIcon("icofeps\\loaderMonitor.gif"));

		lblListArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblListArquivo.setHorizontalTextPosition(SwingConstants.RIGHT);

		txtArquivoEsperado.setFocusable(false);
		txtArquivoEsperado.setEditable(false);

		list.setEnabled(false);
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
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
						.addGroup(groupLayout.createSequentialGroup().addGap(78)
								.addComponent(lblTotalArq, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGap(13).addComponent(lblNumTotalArq, GroupLayout.PREFERRED_SIZE, 163,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(80)
								.addComponent(lblTempo, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(lblNumTempo, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(80)
								.addComponent(
										lblArquivoEsperado, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnOrdemManual, GroupLayout.PREFERRED_SIZE, 156,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtArquivoEsperado, GroupLayout.PREFERRED_SIZE, 157,
												GroupLayout.PREFERRED_SIZE))
								.addGap(64)));
	}

	private void buildVerticalLayout() {
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
				.addGap(8)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblArquivoEsperado, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtArquivoEsperado, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnOrdemManual, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(4)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalArq, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumTotalArq, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblTempo,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNumTempo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))));
	}

	private void initializeListeners() {
		lblPlayPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (statusMonitor) {
					lblPlayPause.setIcon(new ImageIcon("icofeps\\play_24.png"));
					lblGifLoader.setVisible(false);
					statusMonitor = false;
					pause();
					stopTaskCountTempo();
				} else {
					lblPlayPause.setIcon(new ImageIcon("icofeps\\pause_24.png"));
					lblGifLoader.setVisible(true);
					statusMonitor = true;
					start();
					startTaskCountTempo();
				}
				super.mouseClicked(e);
			}
		});

		btnOrdemManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnOrdemManual.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				btnOrdemManual.setFont(new Font("Stencil", Font.PLAIN, 16));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnOrdemManual.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				btnOrdemManual.setFont(new Font("Stencil", Font.PLAIN, 17));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnOrdemManual.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}
		});
	}

	public void start() {
		txtArquivoEsperado.setText(getExpectedNumDoc());
		cancelTask();
		startTaskCountTempo();
		lblPlayPause.setIcon(new ImageIcon("icofeps\\pause_24.png"));
		lblGifLoader.setVisible(true);

		statusMonitor = true;

		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				atualizaDir();
			}
		};
		timer.schedule(task, 1000, getTempoRefresh());
	}

	private void pause() {
		cancelTask();
	}

	public void cancelTask() {
		if (timer != null && task != null) {
			lblPlayPause.setIcon(new ImageIcon("icofeps\\play_24.png"));
			lblGifLoader.setVisible(false);
			timer.cancel();
			task.cancel();

			timer = null;
			task = null;

			stopTaskCountTempo();
		}
	}

	public void startTaskCountTempo() {
		stopTaskCountTempo();

		timerCountTempo = new Timer();
		taskCountTempo = new TimerTask() {
			@Override
			public void run() {
				lblNumTempo.setText(LocalTime.parse(lblNumTempo.getText()).plusSeconds(1)
						.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				if (LocalTime.parse(lblNumTempo.getText()).getMinute() == 2) {
					JOptionPane.showMessageDialog(null, "TEMPO EXCEDIDO!");
					lblNumTempo.setText("00:00:00");
					startTaskCountTempo();
				}
			}
		};
		timerCountTempo.schedule(taskCountTempo, 1000, 1000);
	}

	public void stopTaskCountTempo() {
		if (timerCountTempo != null && taskCountTempo != null) {
			timerCountTempo.cancel();
			taskCountTempo.cancel();

			timerCountTempo = null;
			taskCountTempo = null;
		}
	}

	private void atualizaDir() {
		ArrayList<File> fileDir = recebeFile();
		itemList = new DefaultListModel<>();

		lblNumTotalArq.setText(getTotalArqDia());

		for (int i = 0; i < fileDir.size(); i++) {
			itemList.addElement(fileDir.get(i));
		}

		if (itemList.size() > 0) {
			lblNumTempo.setText("00:00:00");
			if (itemList.get(0).getName().equals(txtArquivoEsperado.getText())) {
				criaOrdem(itemList.get(0));
			} else if (itemList.get(0).getName().contains(getMascArqVazio())) {
				gravaControleLeitura(itemList.get(0));
			} else {
				gravaErro();
				JOptionPane.showMessageDialog(null, "Arquivo diferente do esperado!");
			}
		}
		list.setModel(itemList);
	}

	private ArrayList<File> recebeFile() {
		File[] tmp = new File(getDirCarga()).listFiles();
		ArrayList<File> ret = new ArrayList<>();

		if (tmp != null) {
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].getName().toUpperCase().endsWith(getMascArq())
						|| tmp[i].getName().toUpperCase().endsWith(getMascArqVazio()))
					ret.add(tmp[i]);
			}
		}

		return ret;
	}

	private String gravaControleLeitura(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String sequencia, nomeArq, stringDoc, consultaSQL, docInterno, arqEsperado;
			Date data;
			Time hora;

			stringDoc = reader.readLine();
			nomeArq = file.getName();
			sequencia = Integer.toString(ConnectionFeps.getValorSeq("CONTROLE_LEITURA"));
			data = Date.valueOf(LocalDate.now());
			hora = Time.valueOf(LocalTime.now());

			if (stringDoc == null) {
				stringDoc = "";
			} else {
				docInterno = stringDoc.substring(0, 4);
				arqEsperado = txtArquivoEsperado.getText().substring(0, 4);
				if (!docInterno.equals(arqEsperado)) {
					gravaErro();
					JOptionPane.showMessageDialog(null, "Arquivo interno diferente do número do doc: DOC: "
							+ arqEsperado + " Num. Interno: " + docInterno);

					reader.close();
					
					return null;
				}

			}

			consultaSQL = "INSERT INTO Controle_Leitura (sequencia, doc, data, string_doc)" + " VALUES ('" + sequencia
					+ "', '" + nomeArq + "', '" + new SimpleDateFormat("MM/dd/yyyy").format(data) + " "
					+ new SimpleDateFormat("HH:mm:ss").format(hora) + "', '" + stringDoc + "')";

			copyFile(file, new File(getDirLido() + "\\" + file.getName()));

			reader.close();
			file.delete();

			if (!ConnectionFeps.update(consultaSQL))
				JOptionPane.showMessageDialog(null, "Não foi possível gravar o controle de leitura! Doc: " + nomeArq);

			return stringDoc;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void criaOrdem(File file) {
		String doc, numDoc, dataHora, pvi, check, vin, partNumberGM, ordem_serie_conti;

		doc = gravaControleLeitura(file);

		if (doc == null)
			return;

		numDoc = doc.substring(0, 4);
		dataHora = doc.substring(4, 18);
		pvi = doc.substring(18, 28);
		check = doc.substring(28, 29);
		vin = doc.substring(29, 46);
		partNumberGM = doc.substring(46);
		ordem_serie_conti = getApelido(partNumberGM);

		gravaOrdem(numDoc, dataHora, ConstantsFEPS.PROD_INICIADA.getStringValue(), ordem_serie_conti, pvi, check, vin,
				partNumberGM, ConstantsFEPS.ORDEM_AUTOMATICA.getStringValue(), ConnectionFeps.getValorSeq("ORDEM_GM"));
		updateParametros();
		txtArquivoEsperado.setText(getExpectedNumDoc());
		itemList.remove(itemList.indexOf(itemList.firstElement()));
	}

	private String getApelido(String partNumberGM) {
		String apelido, consultaSQL;
		ResultSet rs;

		apelido = null;

		try {

			consultaSQL = "SELECT * FROM gm_conti WHERE codigo_gm = '" + partNumberGM + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				apelido = rs.getString("apelido_serie");

			} else
				JOptionPane.showMessageDialog(null, "Esse part number não está cadastrado!");

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Não foi possível buscar o apelido do Part Number GM: " + partNumberGM + "!");
		}

		return apelido;
	}

	private void gravaOrdem(String numDoc, String dataHora, String codProducao, String ordem_serie_conti, String pvi,
			String check, String vin, String partNumberGM, String ordem_origem, int numSeq) {

		Date data = Date.valueOf(LocalDate.now());
		Time hora = Time.valueOf(LocalTime.now());
		String sData = getDataSistema();
		int totalSerieConti = ConnectionFeps.getValorSeq("Serie_" + ordem_serie_conti);
		ordem_serie_conti = ordem_serie_conti.trim() + sData.substring(2, 4) + sData.substring(5, 7)
				+ sData.substring(8) + MenuPrincipal.padding(totalSerieConti, 4);

		String consultaSQL = "INSERT INTO ORDEM_GM (ORDEM_GM_DOC, DATA_HORA, STATUS_CPROD_CODIGO, "
				+ "USUARIO_CODIGO, ORDEM_CONTI_SERIE, PVI_CHECK, VIN, PART_NUMBER_GM, "
				+ "ORDEM_GM_ORIGEM, DATA_GM, DATA_INCLUSAO, ORDEM_ENTRADA) VALUES (" + "'" + numDoc + "', " + "'"
				+ dataHora + "', " + "'" + codProducao + "', " + "'" + Login.getUsuario() + "', '" + ordem_serie_conti
				+ "', '" + pvi.concat(check) + "', " + "'" + vin + "', " + "'" + partNumberGM + "', " + "'"
				+ ordem_origem + "', " + "'" + data + " " + hora + "', " + "'" + data + " " + hora + "', " + "'"
				+ numSeq + "')";
		if (!ConnectionFeps.update(consultaSQL)) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível criar a ordem de montagem " + ordem_serie_conti + "!");
			return;
		}

		inclusaoGTM(ordem_serie_conti, ConstantsFEPS.COCKPIT_INICIADO.getStringValue(), "0", Login.getUsuario(),
				new SimpleDateFormat("MM/dd/yyyy").format(data) + " " + new SimpleDateFormat("HH:mm:ss").format(hora),
				partNumberGM, ConstantsFEPS.ORDEM_AUTOMATICA.getStringValue(),
				ConnectionFeps.getValorSeq("ORDEM_CONTI"), ConnectionFeps.getValorSeq("SEQ_DIA"), numDoc);

		updateParametros(MenuPrincipal.padding(Integer.parseInt(numDoc), 4));
	}

	private String getExpectedNumDoc() {
		String sNumDoc = "0000";
		int numDoc;
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM parametros";
			rs = ConnectionFeps.query(consultaSQL);
			if (rs.next()) {
				numDoc = Integer.parseInt(MenuPrincipal.padding(rs.getInt("ultima_chamada") + 1, 4));

				if (numDoc == 10000)
					sNumDoc = "0000";
				else
					sNumDoc = MenuPrincipal.padding(numDoc, 4);
			}

			ConnectionFeps.closeConnection(rs, null, null);

			return sNumDoc + getMascArq().toLowerCase();
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível retornar o doc esperado!");
		}
		return null;
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

	private String getTotalArqDia() {
		String totalArq, consultaSQL;
		;
		ResultSet rs;

		totalArq = null;

		try {
			consultaSQL = "SELECT COUNT(*) FROM controle_leitura WHERE data >= '" + LocalDate.now() + " " + "00:00:00"
					+ "' AND data <= '" + LocalDate.now() + " " + "23:59:59" + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				totalArq = rs.getString(1);
			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o total de arquivos do dia atual!");
		}

		return totalArq;
	}

	private void gravaErro() {
		String consultaSQL = "UPDATE parametros SET erro_sequencia = 'S'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível gravar no banco o erro de sequência!");
	}

	private void updateParametros(String doc) {
		String consultaSQL = "UPDATE parametros SET ultima_chamada_hora  = '" + LocalDate.now() + " " + LocalTime.now()
				+ "' " + ", ultima_chamada = '" + doc + "', " + "ultima_chamada_valida = '" + LocalDate.now() + " "
				+ LocalTime.now() + "'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar oo último doc lido! Doc GM: " + doc);
	}

	private void updateParametros() {
		String consultaSQL = "UPDATE parametros SET ultima_chamada_hora  = '" + LocalDate.now() + " " + LocalTime.now()
				+ "' " + ", erro_sequencia = 'N'";
		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar a data e hora da última chamada!");
	}

	private void inclusaoGTM(String ordem_conti_serie, String status_cockpit, String num_gtm, String usuario_cod,
			String ordem_conti_data, String part_number_gm, String ordem_origem, int ordem_entrada, int sequencia_dia,
			String numDoc) {
		String consultaSQL = "INSERT INTO ordem_conti (ordem_conti_serie, status_cockpit, num_gtm, usuario_cod, ordem_conti_data, part_number_gm,"
				+ "ordem_conti_origem, ordem_entrada, sequencia_dia, numDoc) VALUES ('" + ordem_conti_serie + "', '"
				+ status_cockpit + "', '" + num_gtm + "', '" + usuario_cod + "', '" + ordem_conti_data + "', '"
				+ part_number_gm + "', '" + ordem_origem + "', '" + ordem_entrada + "', '" + sequencia_dia + "', '"
				+ numDoc + "')";

		if (!ConnectionFeps.update(consultaSQL))
			JOptionPane.showMessageDialog(null,
					"Erro ao tetnar incluir a ordem " + ordem_conti_serie + " ao banco de dados!");
	}

	private Object getParameter(String tmp) {
		String consultaSQL = "SELECT * FROM parametros";
		String parametro = null;
		ResultSet rs;
		try {
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if (rs.getString(tmp) == null)
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

	public int getTempoRefresh() {
		return Integer.parseInt((String) getParameter("tempo_refresh"));
	}

	public String getDataSistema() {
		return (String) getParameter("data_sistema");
	}

	public String getMascArqVazio() {
		return (String) getParameter("mascara_vazio");
	}

	public String getDirCarga() {
		return (String) getParameter("diretorio_carga");
	}

	public String getMascArq() {
		return (String) getParameter("masc_arq_gm");
	}

	public String getDirLido() {
		return (String) getParameter("diretorio_lido");
	}

	public void clearValues() {
		this.txtArquivoEsperado.setText("");
		this.lblNumTempo.setText("00:00:00");
	}
}
