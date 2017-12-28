package feps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PreferenciaFeps extends JPanel {
	private static final long serialVersionUID = 1L;

	private static JLabel lblParametrosDoSistema = new JLabel("Parâmetros do Sistema");
	private static JLabel lblMascArq = new JLabel("Máscara do arquivo:");
	private static JLabel lblDirCarga = new JLabel("Diretório de carga:");
	private static JLabel lblDirLido = new JLabel("Diretório lidos:");
	private static JLabel lblRefresh = new JLabel("Tempo do refresh:");
	private static JLabel lblQtdeGTM = new JLabel("Quantidade GTM:");
	private static JLabel lblMascArqVazio = new JLabel("Mascara arquivo vazio:");
	private static JLabel lblTemMax = new JLabel("Tempo máx. chamada:");
	private static JLabel lblAtraso = new JLabel("Indicador de Atraso:");
	private static JLabel lblUltimaChamada = new JLabel("Última chamada/hora:");
	private static JLabel lblDataSistema = new JLabel("Data do sistema:");
	private static JLabel lblStatus = new JLabel("Status:");
	private static JLabel lblMilissegundos = new JLabel("milissegundo(s)");
	private static JLabel lblMinutos = new JLabel("minuto(s)");

	private static JLabel btnNovo = new JLabel("novo");
	private static JLabel btnEditar = new JLabel("editar");
	private static JLabel btnSalvar = new JLabel("salvar");
	private static JLabel btnCancelar = new JLabel("cancelar");

	private static JLabel btnDirCarga = new JLabel("...");
	private static JLabel btnDirLido = new JLabel("...");

	private static JTextField txtMascArq;
	private static JTextField txtDirCarga;
	private static JTextField txtDirLido;
	private static JTextField txtRefresh;
	private static JTextField txtQtdeGTM;
	private static JTextField txtMascArqVazio;
	private static JTextField txtTemMax;
	private static JTextField txtAtraso;
	private static JTextField txtUltimoArq;
	private static JTextField txtHora;
	private static JTextField txtDataSistema;
	private static JTextField txtStatus;

	private static ArrayList<JTextField> listTXT = new ArrayList<JTextField>();

	public PreferenciaFeps() {
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		setLayout(null);
		setBounds(0, 0, 1366, 688);
		setBackground(Color.WHITE);
	}

	private void initializeComponents() {
		lblParametrosDoSistema.setBounds(393, 12, 550, 100);
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Stencil", Font.PLAIN, 40));

		lblMascArq.setBounds(403, 91, 250, 40);
		lblMascArq.setForeground(Color.BLACK);
		lblMascArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArq.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblDirCarga.setBounds(403, 141, 250, 40);
		lblDirCarga.setForeground(Color.BLACK);
		lblDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDirCarga.setHorizontalAlignment(SwingConstants.RIGHT);

		lblDirLido.setBounds(403, 191, 250, 40);
		lblDirLido.setForeground(Color.BLACK);
		lblDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDirLido.setHorizontalAlignment(SwingConstants.RIGHT);

		lblRefresh.setBounds(403, 241, 250, 40);
		lblRefresh.setForeground(Color.BLACK);
		lblRefresh.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);

		lblQtdeGTM.setBounds(403, 291, 250, 40);
		lblQtdeGTM.setForeground(Color.BLACK);
		lblQtdeGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblQtdeGTM.setHorizontalAlignment(SwingConstants.RIGHT);

		lblMascArqVazio.setBounds(403, 341, 250, 40);
		lblMascArqVazio.setForeground(Color.BLACK);
		lblMascArqVazio.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMascArqVazio.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTemMax.setBounds(403, 391, 250, 40);
		lblTemMax.setForeground(Color.BLACK);
		lblTemMax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblAtraso.setBounds(403, 441, 250, 40);
		lblAtraso.setForeground(Color.BLACK);
		lblAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtraso.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblUltimaChamada.setBounds(403, 491, 250, 40);
		lblUltimaChamada.setForeground(Color.BLACK);
		lblUltimaChamada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaChamada.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblDataSistema.setBounds(403, 541, 250, 40);
		lblDataSistema.setForeground(Color.BLACK);
		lblDataSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSistema.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblStatus.setBounds(803, 541, 60, 40);
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblMilissegundos.setBounds(797, 241, 195, 40);
		lblMilissegundos.setForeground(Color.BLACK);
		lblMilissegundos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMilissegundos.setFont(new Font("Stencil", Font.PLAIN, 14));

		lblMinutos.setBounds(797, 391, 166, 40);
		lblMinutos.setForeground(Color.BLACK);
		lblMinutos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinutos.setFont(new Font("Stencil", Font.PLAIN, 14));

		btnNovo.setBounds(530, 600, 110, 40);
		btnNovo.setForeground(Color.BLACK);
		btnNovo.setHorizontalAlignment(SwingConstants.CENTER);
		btnNovo.setBorder(new LineBorder(Color.BLACK));
		btnNovo.setFont(new Font("Stencil", Font.PLAIN, 14));

		btnEditar.setBounds(646, 600, 110, 40);
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditar.setBorder(new LineBorder(Color.BLACK));
		btnEditar.setFont(new Font("Stencil", Font.PLAIN, 14));

		btnSalvar.setBounds(762, 600, 110, 40);
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalvar.setBorder(new LineBorder(Color.BLACK));
		btnSalvar.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnSalvar.setVisible(false);

		btnCancelar.setBounds(882, 600, 110, 40);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setBorder(new LineBorder(Color.BLACK));
		btnCancelar.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnCancelar.setVisible(false);

		btnDirCarga.setVisible(false);
		btnDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnDirCarga.setBounds(994, 153, 25, 15);
		btnDirCarga.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDirCarga.setHorizontalAlignment(SwingConstants.CENTER);

		btnDirLido.setVisible(false);
		btnDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnDirLido.setBounds(994, 203, 25, 15);
		btnDirLido.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDirLido.setHorizontalAlignment(SwingConstants.CENTER);

		txtMascArq = new JTextField();
		txtMascArq.setBounds(665, 91, 327, 40);
		txtMascArq.setBorder(new LineBorder(Color.BLACK));
		txtMascArq.setForeground(Color.BLACK);
		txtMascArq.setMargin(new Insets(0, 10, 0, 0));
		txtMascArq.setEditable(false);
		txtMascArq.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtMascArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirCarga = new JTextField();
		txtDirCarga.setBounds(665, 141, 327, 40);
		txtDirCarga.setBorder(new LineBorder(Color.BLACK));
		txtDirCarga.setForeground(Color.BLACK);
		txtDirCarga.setMargin(new Insets(0, 10, 0, 0));
		txtDirCarga.setEditable(false);
		txtDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDirCarga.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirLido = new JTextField();
		txtDirLido.setBounds(665, 191, 327, 40);
		txtDirLido.setBorder(new LineBorder(Color.BLACK));
		txtDirLido.setForeground(Color.BLACK);
		txtDirLido.setMargin(new Insets(0, 10, 0, 0));
		txtDirLido.setEditable(false);
		txtDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDirLido.setHorizontalAlignment(SwingConstants.LEFT);

		txtRefresh = new JTextField();
		txtRefresh.setBounds(665, 241, 120, 40);
		txtRefresh.setBorder(new LineBorder(Color.BLACK));
		txtRefresh.setForeground(Color.BLACK);
		txtRefresh.setMargin(new Insets(0, 10, 0, 0));
		txtRefresh.setEditable(false);
		txtRefresh.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtRefresh.setHorizontalAlignment(SwingConstants.LEFT);

		txtQtdeGTM = new JTextField();
		txtQtdeGTM.setBounds(665, 291, 120, 40);
		txtQtdeGTM.setBorder(new LineBorder(Color.BLACK));
		txtQtdeGTM.setForeground(Color.BLACK);
		txtQtdeGTM.setMargin(new Insets(0, 10, 0, 0));
		txtQtdeGTM.setEditable(false);
		txtQtdeGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtQtdeGTM.setHorizontalAlignment(SwingConstants.LEFT);

		txtMascArqVazio = new JTextField();
		txtMascArqVazio.setBounds(665, 341, 120, 40);
		txtMascArqVazio.setBorder(new LineBorder(Color.BLACK));
		txtMascArqVazio.setForeground(Color.BLACK);
		txtMascArqVazio.setMargin(new Insets(0, 10, 0, 0));
		txtMascArqVazio.setEditable(false);
		txtMascArqVazio.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtMascArqVazio.setHorizontalAlignment(SwingConstants.LEFT);

		txtTemMax = new JTextField();
		txtTemMax.setBounds(665, 391, 120, 40);
		txtTemMax.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTemMax.setForeground(Color.BLACK);
		txtTemMax.setMargin(new Insets(0, 10, 0, 0));
		txtTemMax.setEditable(false);
		txtTemMax.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtTemMax.setHorizontalAlignment(SwingConstants.LEFT);

		txtAtraso = new JTextField();
		txtAtraso.setBounds(665, 441, 120, 40);
		txtAtraso.setBorder(new LineBorder(Color.BLACK));
		txtAtraso.setForeground(Color.BLACK);
		txtAtraso.setMargin(new Insets(0, 10, 0, 0));
		txtAtraso.setEditable(false);
		txtAtraso.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtAtraso.setHorizontalAlignment(SwingConstants.LEFT);

		txtUltimoArq = new JTextField();
		txtUltimoArq.setBounds(665, 491, 120, 40);
		txtUltimoArq.setBorder(new LineBorder(Color.BLACK));
		txtUltimoArq.setForeground(Color.BLACK);
		txtUltimoArq.setMargin(new Insets(0, 10, 0, 0));
		txtUltimoArq.setEditable(false);
		txtUltimoArq.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtUltimoArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtHora = new JTextField();
		txtHora.setBounds(795, 491, 197, 40);
		txtHora.setBorder(new LineBorder(Color.BLACK));
		txtHora.setForeground(Color.BLACK);
		txtHora.setMargin(new Insets(0, 10, 0, 0));
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);

		txtDataSistema = new JTextField();
		txtDataSistema.setBounds(665, 542, 120, 40);
		txtDataSistema.setBorder(new LineBorder(Color.BLACK));
		txtDataSistema.setForeground(Color.BLACK);
		txtDataSistema.setMargin(new Insets(0, 10, 0, 0));
		txtDataSistema.setEditable(false);
		txtDataSistema.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDataSistema.setHorizontalAlignment(SwingConstants.LEFT);

		txtStatus = new JTextField();
		txtStatus.setBounds(875, 542, 117, 40);
		txtStatus.setBorder(new LineBorder(Color.BLACK));
		txtStatus.setForeground(Color.BLACK);
		txtStatus.setMargin(new Insets(0, 10, 0, 0));
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);

		add(btnNovo);
		add(btnEditar);
		add(btnSalvar);
		add(btnCancelar);
		add(btnDirCarga);
		add(btnDirLido);

		add(lblParametrosDoSistema);
		add(lblMascArq);
		add(lblDirCarga);
		add(lblDirLido);
		add(lblRefresh);
		add(lblQtdeGTM);
		add(lblMilissegundos);
		add(lblMascArqVazio);
		add(lblTemMax);
		add(lblMinutos);
		add(lblAtraso);
		add(lblUltimaChamada);
		add(lblDataSistema);
		add(lblStatus);

		add(txtMascArq);
		add(txtDirCarga);
		add(txtDirLido);
		add(txtRefresh);
		add(txtQtdeGTM);
		add(txtMascArqVazio);
		add(txtTemMax);
		add(txtAtraso);
		add(txtUltimoArq);
		add(txtHora);
		add(txtDataSistema);
		add(txtStatus);

		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof JTextField)
				listTXT.add((JTextField) getComponent(i));
		}
	}

	private void initializeListeners() {
		mouseListenerLabel(btnNovo);
		mouseListenerLabel(btnEditar);
		mouseListenerLabel(btnSalvar);
		mouseListenerLabel(btnCancelar);
		mouseListenerLabel(btnDirCarga);
		mouseListenerLabel(btnDirLido);

		for (int i = 0; i < listTXT.size(); i++) {
			keyListenerTXT(listTXT.get(i));
		}

	}

	private void mouseListenerLabel(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (label == btnNovo)
					novo();
				else if (label == btnEditar)
					edita();
				else if (label == btnSalvar)
					save();
				else if (label == btnCancelar)
					cancel();
				else if (label == btnDirCarga)
					selectDir(txtDirCarga);
				else if (label == btnDirLido)
					selectDir(txtDirLido);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setFont(new Font("Stencil", Font.PLAIN, 13));
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (label != btnDirCarga && label != btnDirLido)
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				else
					label.setBorder(null);

				label.setFont(new Font("Stencil", Font.PLAIN, 14));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (label != btnDirCarga && label != btnDirLido)
					label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				else
					label.setBorder(null);
			}
		});
	}

	private void keyListenerTXT(JTextField texto) {
		texto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				Alerta.desativaPopup(texto);
				super.keyReleased(e);
			}
		});
	}

	public static void novo() {
		btnSalvar.setVisible(true);
		btnCancelar.setVisible(true);
		btnDirCarga.setVisible(true);
		btnDirLido.setVisible(true);

		btnNovo.setVisible(false);
		btnEditar.setVisible(false);

		limpaCampos();
		ativaCampos();

		txtDataSistema.setVisible(false);
		lblDataSistema.setVisible(false);
		txtHora.setVisible(false);
		txtStatus.setVisible(false);
		lblStatus.setVisible(false);
		txtUltimoArq.setVisible(false);
		lblUltimaChamada.setVisible(false);

		txtMascArq.requestFocusInWindow();
	}

	private static void limpaCampos() {
		for (int i = 0; i < listTXT.size(); i++) {
			listTXT.get(i).setText("");
		}
	}

	private static void ativaCampos() {
		for (int i = 0; i < listTXT.size(); i++) {
			if (listTXT.get(i) != txtDirCarga && listTXT.get(i) != txtDirLido && listTXT.get(i) != txtDataSistema
					&& listTXT.get(i) != txtHora && listTXT.get(i) != txtStatus && listTXT.get(i) != txtUltimoArq)
				listTXT.get(i).setEditable(true);
		}
	}

	private void edita() {
		btnDirCarga.setVisible(true);
		btnDirLido.setVisible(true);
		btnNovo.setVisible(false);
		btnEditar.setVisible(false);
		btnSalvar.setVisible(true);
		btnCancelar.setVisible(true);
		ativaCampos();
		txtMascArq.requestFocusInWindow();
	}

	private void save() {
		boolean ok = true;
		for (int i = 0; i < listTXT.size(); i++) {
			if (listTXT.get(i).getText().equals("") && listTXT.get(i).isVisible() && listTXT.get(i).isEditable()) {
				Alerta.ativaPopup(listTXT.get(i), "Preencha o campo vazio acima", 14, 30);
				listTXT.get(i).setMargin(new Insets(0, 10, 0, 0));
				ok = false;
				break;
			}
		}

		if (ok)
			savePreferences();
	}

	private void savePreferences() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		
		consultaSQL = "UPDATE parametros SET masc_arq_gm = '" + txtMascArq.getText().toUpperCase() + "', " +
					  "diretorio_carga = '" + txtDirCarga.getText().toUpperCase() + "', " + 
					  "diretorio_lido = '" + txtDirLido.getText().toUpperCase() + "', " +
					  "tempo_refresh = '" +  txtRefresh.getText().toUpperCase() + "', " +
					  "qtde_fecha_gtm = '" + txtQtdeGTM.getText().toUpperCase() + "', " +
					  "mascara_vazio = '" + txtMascArqVazio.getText().toUpperCase() + "', " +
					  "tempo_max_chamada = '" + txtTemMax.getText().toUpperCase() + "', " +
					  "atraso_linha = '" + txtAtraso.getText().toUpperCase() + "'";
		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);

			if (p.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Parâmetros definidos com sucesso!");
				cancel();
			}

			p.close();
			c.close();

			p = null;
			c = null;
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar");
		}
	}

	private void cancel() {
		for (int i = 0; i < listTXT.size(); i++) {
			Alerta.desativaPopup(listTXT.get(i));
		}

		if (loadPreferences()) {
			desativaCampos();
			btnSalvar.setVisible(false);
			btnCancelar.setVisible(false);
			btnDirCarga.setVisible(false);
			btnDirLido.setVisible(false);

			btnNovo.setVisible(true);
			btnEditar.setVisible(true);

			txtDataSistema.setVisible(true);
			lblDataSistema.setVisible(true);
			txtHora.setVisible(true);
			txtStatus.setVisible(true);
			lblStatus.setVisible(true);
			txtUltimoArq.setVisible(true);
			lblUltimaChamada.setVisible(true);

			requestFocusInWindow();
		}
	}

	private static void desativaCampos() {
		for (int i = 0; i < listTXT.size(); i++) {
			listTXT.get(i).setEditable(false);
		}
	}

	private void selectDir(JTextField texto) {
		JFileChooser folderChooser = new JFileChooser("C:\\");
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (folderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			texto.setText(folderChooser.getSelectedFile().getAbsolutePath());
			Alerta.desativaPopup(texto);
		}
	}

	public static boolean loadPreferences() {
		String consultaSQL = "SELECT * FROM parametros";
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		boolean ok;

		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				if (rs.getString("masc_arq_gm") != null)
					txtMascArq.setText(rs.getString("masc_arq_gm").trim());
				else
					txtMascArq.setText(".TXT");
				if (rs.getString("diretorio_carga") != null)
					txtDirCarga.setText(rs.getString("diretorio_carga").trim());
				else
					txtDirCarga.setText("C:\\SVDO");
				if (rs.getString("diretorio_lido") != null)
					txtDirLido.setText(rs.getString("diretorio_lido").trim());
				else
					txtDirLido.setText("C:\\SVDO\\LIDOS");
				if (rs.getString("tempo_refresh") != null)
					txtRefresh.setText(rs.getString("tempo_refresh").trim());
				else
					txtRefresh.setText("2000");
				if (rs.getString("qtde_fecha_gtm") != null)
					txtQtdeGTM.setText(rs.getString("qtde_fecha_gtm").trim());
				else
					txtQtdeGTM.setText("8");
				if (rs.getString("mascara_vazio") != null)
					txtMascArqVazio.setText(rs.getString("mascara_vazio").trim());
				else
					txtMascArqVazio.setText(".EDS");
				if (rs.getString("tempo_max_chamada") != null)
					txtTemMax.setText(rs.getString("tempo_max_chamada").trim());
				else
					txtTemMax.setText("2");
				if (rs.getString("atraso_linha") != null)
					txtAtraso.setText(rs.getString("atraso_linha").trim());
				else
					txtAtraso.setText("12");
				if (rs.getString("ultima_chamada") != null)
					txtUltimoArq.setText(rs.getString("ultima_chamada").trim());
				else
					txtUltimoArq.setText("");
				if (rs.getString("ultima_chamada_hora") != null)
					txtHora.setText(rs.getString("ultima_chamada_hora").trim());
				else
					txtHora.setText("");
				if (rs.getString("data_sistema") != null)
					txtDataSistema.setText(new SimpleDateFormat("dd/MM/yyyy")
							.format(Date.valueOf(rs.getString("data_sistema").trim())));
				else
					txtDataSistema.setText("");
				if (rs.getString("aberto") != null)
					txtStatus.setText(rs.getString("aberto").trim());
				else
					txtStatus.setText("N");
				ok = true;
			} else
				ok = false;

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

			return ok;
		} catch (SQLException sql) {
			sql.printStackTrace();
			return false;
		}

	}

	public static void carregaPadrao() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			consultaSQL = "INSERT INTO parametros (masc_arq_gm, diretorio_carga, diretorio_lido, tempo_refresh, qtde_fecha_gtm, mascara_vazio,"
					+ "tempo_max_chamada, atraso_linha, ultima_chamada, ultima_chamada_hora, data_sistema, aberto, ultima_chamada_valida, erro_sequencia) VALUES ("
					+ "'.TXT', 'C:/SVDO', 'C:/SVDO/LIDOS', '2000', '8', '.EDS', '2', '12', '0', " + null + ", "
					+ null + ", " + "'N'" + ", " + null + ", " + "'N')";
			
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			
			if (p.executeUpdate() > 0) {
				txtDataSistema.setVisible(true);
				lblDataSistema.setVisible(true);
				txtHora.setVisible(true);
				txtStatus.setVisible(true);
				lblStatus.setVisible(true);
				txtUltimoArq.setVisible(true);
				lblUltimaChamada.setVisible(true);
				btnDirCarga.setVisible(false);
				btnDirLido.setVisible(false);
				btnNovo.setVisible(true);
				btnEditar.setVisible(true);
				btnSalvar.setVisible(false);
				btnCancelar.setVisible(false);
				desativaCampos();
				loadPreferences();
				JOptionPane.showMessageDialog(null, "Parâmetros carregados com sucesso!");
			}
			
			p.close();
			c.close();
			
			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private static Object getParameter(String tmp) {
		String consultaSQL = "SELECT * FROM parametros";
		String parametro = null;
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next())
				if(rs.getString(tmp) == null)
					parametro = "";
				else
					parametro = rs.getString(tmp).trim();

			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return parametro;
	}

	public static String getMascArq() {
		return (String) getParameter("masc_arq_gm");
	}

	public static String getDirCarga() {
		return (String) getParameter("diretorio_carga");
	}

	public static String getDirLido() {
		return (String) getParameter("diretorio_lido");
	}

	public static int getTempoRefresh() {
		return Integer.parseInt((String) getParameter("tempo_refresh"));
	}

	public static int getQtdFechaGTM() {
		return Integer.parseInt((String) getParameter("qtde_fecha_gtm"));
	}

	public static String getMascArqVazio() {
		return (String) getParameter("mascara_vazio");
	}

	public static int getTemMax() {
		return Integer.parseInt((String) getParameter("tempo_max_chamada"));
	}

	public static int getAtraso() {
		return Integer.parseInt((String) getParameter("atraso_linha"));
	}

	public static String getHoraUltimaChamada() {
		String ret = (String) getParameter("ultima_chamada_hora");
		if(ret == null)
			ret = "";
		return ret;
	}

	public static String getDataSistema() {
		return (String) getParameter("data_sistema");
	}

	public static boolean getStatus() {
		String ret = ((String) getParameter("aberto"));
		return ret != null && ret.equals("S");
	}

	public static String getTurno(LocalTime varHora) {
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM turno";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				LocalTime horaAtual = varHora;
				LocalTime horaInicioTurno = LocalTime.parse(rs.getString("horainicial"));
				LocalTime horaFinalTurno = LocalTime.parse(rs.getString("horafinal"));
				if (horaAtual.compareTo(horaInicioTurno) >= 0 && horaAtual.compareTo(horaFinalTurno) <= 0)
					return rs.getString("idturno");
			}

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

		return null;
	}
}
