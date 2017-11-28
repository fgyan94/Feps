package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PreferenciaFeps extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblParametrosDoSistema = new JLabel("Parâmetros do Sistema");
	private JLabel lblMascArq = new JLabel("Máscara do arquivo:");
	private JLabel lblDirCarga = new JLabel("Diretório de carga:");
	private JLabel lblDirLido = new JLabel("Diretório lidos:");
	private JLabel lblRefresh = new JLabel("Tempo do refresh:");
	private JLabel lblQtdeGTM = new JLabel("Quantidade GTM:");
	private JLabel lblMascArqVazio = new JLabel("Mascara arquivo vazio:");
	private JLabel lblTemMax = new JLabel("Tempo máx. chamada:");
	private JLabel lblAtraso = new JLabel("Indicador de Atraso:");
	private JLabel lblUltimaChamada = new JLabel("Última chamada/hora:");
	private JLabel lblDataSistema = new JLabel("Data do sistema:");
	private JLabel lblStatus = new JLabel("Status:");
	private JLabel lblMilissegundos = new JLabel("milissegundo(s)");
	private JLabel lblMinutos = new JLabel("minuto(s)");
	private JLabel btnSalvar = new JLabel("salvar");
	private JLabel btnCancelar = new JLabel("cancelar");

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

	public PreferenciaFeps() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		loadPreferences();
	}

	private void buildPanel() {
		setBounds(0, 0, 1366, 688);
		setBackground(Color.WHITE);
	}

	private void initializeComponents() {
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblMascArq.setForeground(Color.BLACK);

		lblMascArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirCarga.setForeground(Color.BLACK);

		lblDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirCarga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirLido.setForeground(Color.BLACK);

		lblDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirLido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefresh.setForeground(Color.BLACK);

		lblRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQtdeGTM.setForeground(Color.BLACK);

		lblQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblQtdeGTM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArqVazio.setForeground(Color.BLACK);

		lblMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMascArqVazio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setForeground(Color.BLACK);

		lblTemMax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblAtraso.setForeground(Color.BLACK);

		lblAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblUltimaChamada.setForeground(Color.BLACK);

		lblUltimaChamada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaChamada.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDataSistema.setForeground(Color.BLACK);

		lblDataSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblStatus.setForeground(Color.BLACK);

		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMilissegundos.setForeground(Color.BLACK);

		lblMilissegundos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMilissegundos.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMinutos.setForeground(Color.BLACK);

		lblMinutos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinutos.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnSalvar.setForeground(Color.BLACK);

		btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalvar.setBorder(new LineBorder(Color.BLACK));
		btnSalvar.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnCancelar.setForeground(Color.BLACK);

		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setBorder(new LineBorder(Color.BLACK));
		btnCancelar.setFont(new Font("Broadway", Font.PLAIN, 14));

		txtMascArq = new JTextField();
		txtMascArq.setBorder(new LineBorder(Color.BLACK));
		txtMascArq.setForeground(Color.BLACK);
		txtMascArq.setEditable(false);
		txtMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirCarga = new JTextField();
		txtDirCarga.setBorder(new LineBorder(Color.BLACK));
		txtDirCarga.setForeground(Color.BLACK);
		txtDirCarga.setEditable(false);
		txtDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirCarga.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirLido = new JTextField();
		txtDirLido.setBorder(new LineBorder(Color.BLACK));
		txtDirLido.setForeground(Color.BLACK);
		txtDirLido.setEditable(false);
		txtDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirLido.setHorizontalAlignment(SwingConstants.LEFT);

		txtRefresh = new JTextField();
		txtRefresh.setBorder(new LineBorder(Color.BLACK));
		txtRefresh.setForeground(Color.BLACK);
		txtRefresh.setEditable(false);
		txtRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtRefresh.setHorizontalAlignment(SwingConstants.LEFT);

		txtQtdeGTM = new JTextField();
		txtQtdeGTM.setBorder(new LineBorder(Color.BLACK));
		txtQtdeGTM.setForeground(Color.BLACK);
		txtQtdeGTM.setEditable(false);
		txtQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtQtdeGTM.setHorizontalAlignment(SwingConstants.LEFT);

		txtMascArqVazio = new JTextField();
		txtMascArqVazio.setBorder(new LineBorder(Color.BLACK));
		txtMascArqVazio.setForeground(Color.BLACK);
		txtMascArqVazio.setEditable(false);
		txtMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArqVazio.setHorizontalAlignment(SwingConstants.LEFT);

		txtTemMax = new JTextField();
		txtTemMax.setBorder(new LineBorder(Color.BLACK));
		txtTemMax.setForeground(Color.BLACK);
		txtTemMax.setEditable(false);
		txtTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtTemMax.setHorizontalAlignment(SwingConstants.LEFT);

		txtAtraso = new JTextField();
		txtAtraso.setBorder(new LineBorder(Color.BLACK));
		txtAtraso.setForeground(Color.BLACK);
		txtAtraso.setEditable(false);
		txtAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtAtraso.setHorizontalAlignment(SwingConstants.LEFT);

		txtUltimoArq = new JTextField();
		txtUltimoArq.setBorder(new LineBorder(Color.BLACK));
		txtUltimoArq.setForeground(Color.BLACK);
		txtUltimoArq.setEditable(false);
		txtUltimoArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtUltimoArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtHora = new JTextField();
		txtHora.setBorder(new LineBorder(Color.BLACK));
		txtHora.setForeground(Color.BLACK);
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);

		txtDataSistema = new JTextField();
		txtDataSistema.setBorder(new LineBorder(Color.BLACK));
		txtDataSistema.setForeground(Color.BLACK);
		txtDataSistema.setEditable(false);
		txtDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDataSistema.setHorizontalAlignment(SwingConstants.LEFT);

		txtStatus = new JTextField();
		txtStatus.setBorder(new LineBorder(Color.BLACK));
		txtStatus.setForeground(Color.BLACK);
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
						.createSequentialGroup().addGap(408)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(272).addComponent(txtMascArq,
										GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, 550,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblMascArq,
										GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblDirCarga, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtDirCarga, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblDirLido, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtDirLido, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMilissegundos,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, 120,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblMinutos, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblUltimaChamada, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtUltimoArq, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(418)
								.addComponent(lblDataSistema, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtDataSistema, GroupLayout.PREFERRED_SIZE, 120,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(740)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 110,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(396, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(50)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(79).addComponent(txtMascArq,
								GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, 100,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(79)
								.addComponent(lblMascArq, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDirCarga, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDirCarga, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDirLido, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDirLido, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblMilissegundos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblMinutos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUltimaChamada, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUltimoArq, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataSistema, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDataSistema, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))));
		setLayout(groupLayout);
	}

	private void initializeListeners() {
		btnSalvar.addMouseListener(mouseListenerLabel(btnSalvar));
		btnCancelar.addMouseListener(mouseListenerLabel(btnCancelar));
	}

	private MouseAdapter mouseListenerLabel(JLabel label) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == btnSalvar)
					save();
				else
					cancel();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
			}
		};
	}
	
	private void save() {
		
	}
	
	private void cancel() {
		
	}

	public static void loadPreferences() {
		String consultaSQL = "SELECT * FROM parametros";
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				txtMascArq.setText(rs.getString("masc_arq_gm"));
				txtDirCarga.setText(rs.getString("diretorio_carga"));
				txtDirLido.setText(rs.getString("diretorio_lido"));
				txtRefresh.setText(rs.getString("tempo_refresh"));
				txtQtdeGTM.setText(rs.getString("qtde_fecha_gtm"));
				txtMascArqVazio.setText(rs.getString("mascara_vazio"));
				txtTemMax.setText(rs.getString("tempo_max_chamada"));
				txtAtraso.setText(rs.getString("atraso_linha"));
				txtUltimoArq.setText(rs.getString("ultima_chamada"));
				txtHora.setText(rs.getString("ultima_chamada_hora"));
				txtDataSistema.setText(rs.getString("data_sistema"));
				txtStatus.setText(rs.getString("aberto"));
			}

			rs.close();
			p.close();
			c.close();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

	}

	private static Object getParameter(String tmp) {
		String consultaSQL = "SELECT * FROM parametros";
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		try {
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next())
				return rs.getString(tmp).trim();

			rs.close();
			p.close();
			c.close();

			return null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
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

	public static int getUltimoArquivo() {
		return Integer.parseInt((String) getParameter("ultima_chamada"));
	}

	public static String getHoraUltimaChamada() {
		return (String) getParameter("ultima_chamada_hora");
	}

	public static String getDataSistema() {
		return (String) getParameter("data_sistema");
	}

	public static boolean getStatus() {
		return (String) getParameter("aberto") == "S";
	}
}
