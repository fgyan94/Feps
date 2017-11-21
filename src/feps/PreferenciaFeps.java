package feps;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		setBounds(0, 0, 1366, 688);
		setBackground(new Color(255, 200, 50));
	}

	private void inicializaComponentes() {
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Broadway", Font.PLAIN, 40));
		
		lblMascArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		lblDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirCarga.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDirLido.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblQtdeGTM.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblMascArqVazio.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblTemMax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));

		lblAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		lblUltimaChamada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaChamada.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		lblDataSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		txtMascArq = new JTextField();
		txtMascArq.setForeground(Color.DARK_GRAY);
		txtMascArq.setEditable(false);
		txtMascArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArq.setHorizontalAlignment(SwingConstants.LEFT);
		txtMascArq.setColumns(10);
		
		txtDirCarga = new JTextField();
		txtDirCarga.setForeground(Color.DARK_GRAY);
		txtDirCarga.setEditable(false);
		txtDirCarga.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirCarga.setHorizontalAlignment(SwingConstants.LEFT);
		txtDirCarga.setColumns(10);
		
		txtDirLido = new JTextField();
		txtDirLido.setForeground(Color.DARK_GRAY);
		txtDirLido.setEditable(false);
		txtDirLido.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDirLido.setHorizontalAlignment(SwingConstants.LEFT);
		txtDirLido.setColumns(10);
		
		txtRefresh = new JTextField();
		txtRefresh.setForeground(Color.DARK_GRAY);
		txtRefresh.setEditable(false);
		txtRefresh.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtRefresh.setHorizontalAlignment(SwingConstants.LEFT);
		txtRefresh.setColumns(10);
		
		txtQtdeGTM = new JTextField();
		txtQtdeGTM.setForeground(Color.DARK_GRAY);
		txtQtdeGTM.setEditable(false);
		txtQtdeGTM.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtQtdeGTM.setHorizontalAlignment(SwingConstants.LEFT);
		txtQtdeGTM.setColumns(10);
		
		txtMascArqVazio = new JTextField();
		txtMascArqVazio.setForeground(Color.DARK_GRAY);
		txtMascArqVazio.setEditable(false);
		txtMascArqVazio.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtMascArqVazio.setHorizontalAlignment(SwingConstants.LEFT);
		txtMascArqVazio.setColumns(10);
		
		txtTemMax = new JTextField();
		txtTemMax.setForeground(Color.DARK_GRAY);
		txtTemMax.setEditable(false);
		txtTemMax.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtTemMax.setHorizontalAlignment(SwingConstants.LEFT);
		txtTemMax.setColumns(10);
		
		txtAtraso = new JTextField();
		txtAtraso.setForeground(Color.DARK_GRAY);
		txtAtraso.setEditable(false);
		txtAtraso.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtAtraso.setHorizontalAlignment(SwingConstants.LEFT);
		txtAtraso.setColumns(10);
		
		txtUltimoArq = new JTextField();
		txtUltimoArq.setForeground(Color.DARK_GRAY);
		txtUltimoArq.setEditable(false);
		txtUltimoArq.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtUltimoArq.setHorizontalAlignment(SwingConstants.LEFT);
		txtUltimoArq.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setForeground(Color.DARK_GRAY);
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);
		txtHora.setColumns(10);
		
		txtDataSistema = new JTextField();
		txtDataSistema.setForeground(Color.DARK_GRAY);
		txtDataSistema.setEditable(false);
		txtDataSistema.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtDataSistema.setHorizontalAlignment(SwingConstants.LEFT);
		txtDataSistema.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setForeground(Color.DARK_GRAY);
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);
		txtStatus.setColumns(10);

		btnSalvar.setFont(new Font("Broadway", Font.PLAIN, 14));
		
		btnCancelar.setFont(new Font("Broadway", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(408)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(272)
							.addComponent(txtMascArq, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblMascArq, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblDirCarga, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtDirCarga, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblDirLido, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtDirLido, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblUltimaChamada, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtUltimoArq, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(418)
					.addComponent(lblDataSistema, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtDataSistema, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(740)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addComponent(txtMascArq, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);
	}
	
	public static String getMascArqVazio(){
		return txtMascArqVazio.getText();
	}
}
