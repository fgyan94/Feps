package feps;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PreferenciaFeps extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblParametrosDoSistema = new JLabel("Parametros do Sistema");
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
	
	private JButton btnSalvar = new JButton("salvar");
	private JButton btnCancelar = new JButton("cancelar");
	
	public PreferenciaFeps() {
		buildPanel();
		inicializaComponentes();
	}

	private void buildPanel() {
		setBounds(0, 0, 1366, 728);
		setBackground(new Color(255, 200, 50));
		setLayout(null);
	}

	private void inicializaComponentes() {
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblParametrosDoSistema.setBounds(408, 50, 550, 100);
		add(lblParametrosDoSistema);
		
		lblMascArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMascArq.setBounds(418, 161, 250, 40);
		add(lblMascArq);
		
		lblDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirCarga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirCarga.setBounds(418, 211, 250, 40);
		add(lblDirCarga);
		
		lblDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirLido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirLido.setBounds(418, 261, 250, 40);
		add(lblDirLido);
		
		lblRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefresh.setBounds(418, 311, 250, 40);
		add(lblRefresh);
		
		lblQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblQtdeGTM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQtdeGTM.setBounds(418, 361, 250, 40);
		add(lblQtdeGTM);
		
		lblMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMascArqVazio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArqVazio.setBounds(418, 411, 250, 40);
		add(lblMascArqVazio);
		
		lblTemMax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblTemMax.setBounds(418, 461, 250, 40);
		add(lblTemMax);

		lblAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblAtraso.setBounds(418, 511, 250, 40);
		add(lblAtraso);
		
		lblUltimaChamada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaChamada.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblUltimaChamada.setBounds(418, 561, 250, 40);
		add(lblUltimaChamada);
		
		lblDataSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDataSistema.setBounds(418, 611, 250, 40);
		add(lblDataSistema);
		
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblStatus.setBounds(800, 611, 60, 40);
		add(lblStatus);
		
		txtMascArq = new JTextField();
		txtMascArq.setForeground(Color.DARK_GRAY);
		txtMascArq.setEditable(false);
		txtMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArq.setHorizontalAlignment(SwingConstants.LEFT);
		txtMascArq.setBounds(680, 161, 290, 40);
		add(txtMascArq);
		txtMascArq.setColumns(10);
		
		txtDirCarga = new JTextField();
		txtDirCarga.setForeground(Color.DARK_GRAY);
		txtDirCarga.setEditable(false);
		txtDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirCarga.setHorizontalAlignment(SwingConstants.LEFT);
		txtDirCarga.setColumns(10);
		txtDirCarga.setBounds(680, 211, 290, 40);
		add(txtDirCarga);
		
		txtDirLido = new JTextField();
		txtDirLido.setForeground(Color.DARK_GRAY);
		txtDirLido.setEditable(false);
		txtDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirLido.setHorizontalAlignment(SwingConstants.LEFT);
		txtDirLido.setColumns(10);
		txtDirLido.setBounds(680, 261, 290, 40);
		add(txtDirLido);
		
		txtRefresh = new JTextField();
		txtRefresh.setForeground(Color.DARK_GRAY);
		txtRefresh.setEditable(false);
		txtRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtRefresh.setHorizontalAlignment(SwingConstants.LEFT);
		txtRefresh.setBounds(680, 311, 120, 40);
		add(txtRefresh);
		txtRefresh.setColumns(10);
		
		txtQtdeGTM = new JTextField();
		txtQtdeGTM.setForeground(Color.DARK_GRAY);
		txtQtdeGTM.setEditable(false);
		txtQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtQtdeGTM.setHorizontalAlignment(SwingConstants.LEFT);
		txtQtdeGTM.setColumns(10);
		txtQtdeGTM.setBounds(680, 361, 120, 40);
		add(txtQtdeGTM);
		
		txtMascArqVazio = new JTextField();
		txtMascArqVazio.setForeground(Color.DARK_GRAY);
		txtMascArqVazio.setEditable(false);
		txtMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArqVazio.setHorizontalAlignment(SwingConstants.LEFT);
		txtMascArqVazio.setColumns(10);
		txtMascArqVazio.setBounds(680, 411, 120, 40);
		add(txtMascArqVazio);
		
		txtTemMax = new JTextField();
		txtTemMax.setForeground(Color.DARK_GRAY);
		txtTemMax.setEditable(false);
		txtTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtTemMax.setHorizontalAlignment(SwingConstants.LEFT);
		txtTemMax.setColumns(10);
		txtTemMax.setBounds(680, 461, 120, 40);
		add(txtTemMax);
		
		txtAtraso = new JTextField();
		txtAtraso.setForeground(Color.DARK_GRAY);
		txtAtraso.setEditable(false);
		txtAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtAtraso.setHorizontalAlignment(SwingConstants.LEFT);
		txtAtraso.setColumns(10);
		txtAtraso.setBounds(680, 511, 120, 40);
		add(txtAtraso);
		
		txtUltimoArq = new JTextField();
		txtUltimoArq.setForeground(Color.DARK_GRAY);
		txtUltimoArq.setEditable(false);
		txtUltimoArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtUltimoArq.setHorizontalAlignment(SwingConstants.LEFT);
		txtUltimoArq.setColumns(10);
		txtUltimoArq.setBounds(680, 561, 120, 40);
		add(txtUltimoArq);
		
		txtHora = new JTextField();
		txtHora.setForeground(Color.DARK_GRAY);
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);
		txtHora.setColumns(10);
		txtHora.setBounds(810, 561, 160, 40);
		add(txtHora);
		
		txtDataSistema = new JTextField();
		txtDataSistema.setForeground(Color.DARK_GRAY);
		txtDataSistema.setEditable(false);
		txtDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDataSistema.setHorizontalAlignment(SwingConstants.LEFT);
		txtDataSistema.setColumns(10);
		txtDataSistema.setBounds(680, 611, 120, 40);
		add(txtDataSistema);
		
		txtStatus = new JTextField();
		txtStatus.setForeground(Color.DARK_GRAY);
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);
		txtStatus.setColumns(10);
		txtStatus.setBounds(860, 611, 110, 40);
		add(txtStatus);

		btnSalvar.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnSalvar.setBounds(740, 680, 110, 40);
		add(btnSalvar);
		
		btnCancelar.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnCancelar.setBounds(860, 680, 110, 40);
		add(btnCancelar);
	}
	
	public static String getMascArqVazio(){
		return txtMascArqVazio.getText();
	}
}
