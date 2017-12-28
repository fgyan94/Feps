package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class EmissaoGTM extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable tbSequencia;
	private JLabel lblEmissao, lblSerie, lblEnviado, lblAEnviar, lblAtenderTotal, lblGTMCriada;
	private JLabel btnConfirmaSaida, btnGeraGTM, btnAtualiza;
	private JTextField txtSerie, txtAEnviar, txtEnviado, txtAtenderTotal, txtGTMCriada;
	private JScrollPane scrSequencia, scrCockpit, scrSerie;
	private JList<String> listSerie;
	private JTree trCockpit;

	private FepsModelTable fmtSequencia;
	
	private static Timer timer;
	private static TimerTask task;

	public EmissaoGTM() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		createSequenciaTable();
		fillTableSequencia();
	}

	private void buildPanel() {
		setBounds(0, 0, 1366, 688);
		setLayout(null);
		setBackground(Color.WHITE);
	}

	private void initializeComponents() {
		lblEmissao = new JLabel("Emissão de GTM");
		lblEmissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmissao.setFont(new Font("Stencil", Font.PLAIN, 70));
		lblEmissao.setBounds(10, 10, 1346, 90);

		lblSerie = new JLabel("Série do Cockpit:");
		lblSerie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerie.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblSerie.setBounds(383, 320, 200, 30);

		lblEnviado = new JLabel("Cockpits enviados:");
		lblEnviado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviado.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblEnviado.setBounds(274, 382, 200, 30);

		lblAEnviar = new JLabel("Cockpits a enviar:");
		lblAEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAEnviar.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblAEnviar.setBounds(274, 417, 200, 30);

		lblAtenderTotal = new JLabel("Total a atender:");
		lblAtenderTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtenderTotal.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblAtenderTotal.setBounds(781, 382, 200, 30);

		lblGTMCriada = new JLabel("GTM's criadas no dia:");
		lblGTMCriada.setHorizontalAlignment(SwingConstants.CENTER);
		lblGTMCriada.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblGTMCriada.setBounds(781, 417, 200, 30);

		btnConfirmaSaida = new JLabel("Confirmar saída");
		btnConfirmaSaida.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnConfirmaSaida.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmaSaida.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnConfirmaSaida.setBounds(780, 320, 200, 30);

		btnGeraGTM = new JLabel("Gera GTM");
		btnGeraGTM.setHorizontalAlignment(SwingConstants.CENTER);
		btnGeraGTM.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnGeraGTM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnGeraGTM.setBounds(569, 382, 200, 30);

		btnAtualiza = new JLabel("Atualiza");
		btnAtualiza.setHorizontalAlignment(SwingConstants.CENTER);
		btnAtualiza.setFont(new Font("Stencil", Font.PLAIN, 18));
		btnAtualiza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAtualiza.setBounds(569, 417, 200, 30);

		txtSerie = new JTextField();
		txtSerie.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtSerie.setBounds(577, 320, 200, 30);

		txtAEnviar = new JTextField();
		txtAEnviar.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtAEnviar.setEditable(false);
		txtAEnviar.setBounds(467, 417, 100, 30);

		txtEnviado = new JTextField();
		txtEnviado.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtEnviado.setEditable(false);
		txtEnviado.setBounds(467, 382, 100, 30);

		txtAtenderTotal = new JTextField();
		txtAtenderTotal.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtAtenderTotal.setEditable(false);
		txtAtenderTotal.setBounds(981, 382, 100, 30);

		txtGTMCriada = new JTextField();
		txtGTMCriada.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtGTMCriada.setEditable(false);
		txtGTMCriada.setBounds(981, 417, 100, 30);

		tbSequencia = new JTable();
		tbSequencia.setFont(new Font("Calibri", Font.PLAIN, 18));

		listSerie = new JList<>();
		listSerie.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listSerie.setFont(new Font("Calibri", Font.PLAIN, 14));

		trCockpit = new JTree();
		trCockpit.setFont(new Font("Calibri", Font.PLAIN, 18));

		scrSequencia = new JScrollPane();
		scrSequencia.setBounds(278, 102, 810, 210);
		scrSequencia.setViewportView(tbSequencia);

		scrSerie = new JScrollPane();
		scrSerie.setBounds(383, 459, 260, 217);
		scrSerie.setViewportView(listSerie);

		scrCockpit = new JScrollPane();
		scrCockpit.setBounds(645, 459, 335, 217);
		scrCockpit.setViewportView(trCockpit);

		add(lblEmissao);
		add(lblSerie);
		add(lblEnviado);
		add(lblAEnviar);
		add(lblAtenderTotal);
		add(lblGTMCriada);

		add(btnConfirmaSaida);
		add(btnGeraGTM);
		add(btnAtualiza);

		add(txtSerie);
		add(txtAEnviar);
		add(txtEnviado);
		add(txtAtenderTotal);
		add(txtGTMCriada);

		add(scrSequencia);
		add(scrSerie);
		add(scrCockpit);
	}

	private void initializeListeners() {
		addMouseListenerLabel(btnConfirmaSaida);
		addMouseListenerLabel(btnGeraGTM);
		addMouseListenerLabel(btnAtualiza);

		addTextListener(txtSerie);
	}

	private void addMouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == btnConfirmaSaida)
					saidaGTM();
				else if(label == btnAtualiza)
					fillTableSequencia();
				super.mouseClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 17));
				super.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				label.setFont(new Font("Stencil", Font.PLAIN, 18));
				super.mouseReleased(e);
			}
		});
	}

	private void addTextListener(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saidaGTM();
				}
			}
		});
	}
	
	protected void start() {
		cancelTask();
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				fillTableSequencia();
			}
		};
		timer.schedule(task, 1000, 10000);
	}

	protected void cancelTask() {
		if (timer != null && task != null) {
			timer.cancel();
			task.cancel();

			timer = null;
			task = null;
		}
	}

	private void createSequenciaTable() {

		ArrayList<String> coluna = new ArrayList<>();

		coluna.add(FepsModelTable.SEQ_GM);
		coluna.add(FepsModelTable.ORDEM_CONTI_DATA);
		coluna.add(FepsModelTable.APELIDO);
		coluna.add(FepsModelTable.ORDEM_CONTI_SERIE);
		coluna.add(FepsModelTable.PART_NUMBER_GM);

		fmtSequencia = new FepsModelTable(coluna);

		tbSequencia.setModel(fmtSequencia);
		tbSequencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tbSequencia.getColumnCount(); i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tbSequencia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			tbSequencia.getColumnModel().getColumn(i).setPreferredWidth(157);
		}

		tbSequencia.getTableHeader().setReorderingAllowed(false);
		tbSequencia.getTableHeader().setResizingAllowed(false);
		fmtSequencia.fireTableDataChanged();

	}

	private void fillTableSequencia() {
		List<Ordem> lista = new ArrayList<Ordem>();
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT Ordem_GM.*, GM_Conti.apelido FROM GM_Conti RIGHT OUTER JOIN Ordem_GM ON GM_Conti.Codigo_gm  = ordem_gm.Part_Number_GM "
					+ "WHERE (STATUS_CPROD_CODIGO = '001' OR STATUS_CPROD_CODIGO = '002') ORDER BY Ordem_Entrada";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					String partNumber = rs.getString("part_number_gm");
					String apelido = rs.getString("apelido");
					String ordem_serie = rs.getString("ordem_conti_serie");
					String ordem_data = rs.getString("data_hora");
					int seq_gm = rs.getInt("ordem_gm_doc");

					Ordem ordem = new Ordem(partNumber, apelido, ordem_serie, ordem_data, seq_gm);
					lista.add(ordem);

					rs.next();
				}

				fmtSequencia.clear();
				fmtSequencia.addOrdemList(lista);
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

	public void requestFocusTextSequencia() {
		txtSerie.setText("");
		txtSerie.requestFocusInWindow();
	}

	private void saidaGTM() {
		String consultaSQL, serieConti;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		if (txtSerie.getText().length() == 13) {
			try {
				serieConti = txtSerie.getText().toUpperCase();
				consultaSQL = "SELECT * FROM ordem_conti WHERE ordem_conti_serie = '" + serieConti.trim() + "'";
				c = ConnectionFeps.getConnection();
				p = c.prepareStatement(consultaSQL);
				rs = p.executeQuery();

				if (rs.next()) {
					if(rs.getString("Part_Number_GM").trim().equals(fmtSequencia.getValueAt(0, 4))) {
						if(!rs.getString("Status_cockpit").equals("001")) {
							
						} else
							JOptionPane.showMessageDialog(null, "Ordem Continental não impressa!");
					} else
						JOptionPane.showMessageDialog(null, "Sequência fornecida diferente da sequência esperada!");
				} else
					JOptionPane.showMessageDialog(null, "Este número de série não existe!");
				
				requestFocusTextSequencia();
				
				rs.close();
				p.close();
				c.close();
				
				rs = null;
				p = null;
				c = null;				
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao consultar!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Número de série inválido");
		}
	}
}
