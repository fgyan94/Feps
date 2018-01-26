package feps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
	
	private GroupLayout groupLayout;
	
	private static JLabel lblParametrosDoSistema;
	private static JLabel lblMascArq;
	private static JLabel lblDirCarga;
	private static JLabel lblDirLido;
	private static JLabel lblRefresh;
	private static JLabel lblQtdeGTM;
	private static JLabel lblMascArqVazio;
	private static JLabel lblTemMax;
	private static JLabel lblAtraso;
	private static JLabel lblUltimaChamada;
	private static JLabel lblDataSistema;
	private static JLabel lblStatus;
	private static JLabel lblMilissegundos;
	private static JLabel lblMinutos;

	private static JLabel btnNovo;
	private static JLabel btnEditar;
	private static JLabel btnSalvar;
	private static JLabel btnCancelar;

	private static JLabel btnDirCarga;
	private static JLabel btnDirLido;

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
	
	private static final int MIN_WIDTH = 1366;
	private static final int MIN_HEIGHT = 768;
	
//	private Dimension dimension = new Dimension(1366, 768);
	 private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	public PreferenciaFeps() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
		initializeListeners();
	}

	private void buildPanel() {
		setBounds(0, 0, 1366, 688);
		setBackground(Color.WHITE);

		groupLayout = new GroupLayout(this);
	}
	
	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(393, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(10, MIN_WIDTH, dimension.width))
								.addComponent(lblMascArq, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
								.addGap(calculate(12, MIN_WIDTH, dimension.width))
								.addComponent(txtMascArq, GroupLayout.PREFERRED_SIZE, calculate(327, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
							.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, calculate(550, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblDirCarga, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtDirCarga, GroupLayout.PREFERRED_SIZE, calculate(327, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(2, MIN_WIDTH, dimension.width))
						.addComponent(btnDirCarga, GroupLayout.PREFERRED_SIZE, calculate(25, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblDirLido, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtDirLido, GroupLayout.PREFERRED_SIZE, calculate(327, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(2, MIN_WIDTH, dimension.width))
						.addComponent(btnDirLido, GroupLayout.PREFERRED_SIZE, calculate(25, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(lblMilissegundos, GroupLayout.PREFERRED_SIZE, calculate(195, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(lblMinutos, GroupLayout.PREFERRED_SIZE, calculate(166, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblUltimaChamada, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtUltimoArq, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, calculate(197, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(403, MIN_WIDTH, dimension.width))
						.addComponent(lblDataSistema, GroupLayout.PREFERRED_SIZE, calculate(250, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtDataSistema, GroupLayout.PREFERRED_SIZE, calculate(120, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(18, MIN_WIDTH, dimension.width))
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, calculate(60, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(12, MIN_WIDTH, dimension.width))
						.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, calculate(117, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(530, MIN_WIDTH, dimension.width))
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, calculate(110, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(3, MIN_WIDTH, dimension.width))
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, calculate(110, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(223, MIN_WIDTH, dimension.width))
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, calculate(110, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(3, MIN_WIDTH, dimension.width))
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, calculate(110, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))
			);
	}

	private void buildVerticalLayout() {
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(calculate(0, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(79, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(lblMascArq, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(79, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(txtMascArq, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
							.addComponent(lblParametrosDoSistema, GroupLayout.PREFERRED_SIZE, calculate(80, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDirCarga, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtDirCarga, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(btnDirCarga)))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDirLido, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtDirLido, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(12, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(btnDirLido)))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtRefresh, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMilissegundos, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblQtdeGTM, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtQtdeGTM, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMascArqVazio, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtMascArqVazio, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblTemMax, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtTemMax, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMinutos, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAtraso, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtAtraso, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblUltimaChamada, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtUltimoArq, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
						.addGap(calculate(10, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDataSistema, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(1, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(txtDataSistema, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE))
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(calculate(1, MIN_HEIGHT - 80, dimension.height - 80))
								.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)))
						.addGap(calculate(18, MIN_HEIGHT - 80, dimension.height - 80))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, calculate(40, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)))
			);
	}
	
	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}

	private void initializeComponents() {		
		lblParametrosDoSistema = new JLabel("Parâmetros do Sistema");
		lblMascArq = new JLabel("Máscara do arquivo:");
		lblDirCarga = new JLabel("Diretório de carga:");
		lblDirLido = new JLabel("Diretório lidos:");
		lblRefresh = new JLabel("Tempo do refresh:");
		lblQtdeGTM = new JLabel("Quantidade GTM:");
		lblMascArqVazio = new JLabel("Mascara arquivo vazio:");
		lblTemMax = new JLabel("Tempo máx. chamada:");
		lblAtraso = new JLabel("Indicador de Atraso:");
		lblUltimaChamada = new JLabel("Última chamada/hora:");
		lblDataSistema = new JLabel("Data do sistema:");
		lblStatus = new JLabel("Status:");
		lblMilissegundos = new JLabel("milissegundo(s)");
		lblMinutos = new JLabel("minuto(s)");
		
		btnNovo = new JLabel("novo");
		btnEditar = new JLabel("editar");
		btnSalvar = new JLabel("salvar");
		btnCancelar = new JLabel("cancelar");
		
		btnDirCarga = new JLabel("...");
		btnDirLido = new JLabel("...");
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblMascArq.setForeground(Color.BLACK);
		lblMascArq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArq.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDirCarga.setForeground(Color.BLACK);
		lblDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDirCarga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirLido.setForeground(Color.BLACK);
		lblDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDirLido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefresh.setForeground(Color.BLACK);
		lblRefresh.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQtdeGTM.setForeground(Color.BLACK);
		lblQtdeGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblQtdeGTM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascArqVazio.setForeground(Color.BLACK);
		lblMascArqVazio.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMascArqVazio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setForeground(Color.BLACK);
		lblTemMax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemMax.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblAtraso.setForeground(Color.BLACK);
		lblAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtraso.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblUltimaChamada.setForeground(Color.BLACK);
		lblUltimaChamada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaChamada.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDataSistema.setForeground(Color.BLACK);
		lblDataSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSistema.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMilissegundos.setForeground(Color.BLACK);
		lblMilissegundos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMilissegundos.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblMinutos.setForeground(Color.BLACK);
		lblMinutos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinutos.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnNovo.setForeground(Color.BLACK);
		btnNovo.setHorizontalAlignment(SwingConstants.CENTER);
		btnNovo.setBorder(new LineBorder(Color.BLACK));
		btnNovo.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditar.setBorder(new LineBorder(Color.BLACK));
		btnEditar.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalvar.setBorder(new LineBorder(Color.BLACK));
		btnSalvar.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnSalvar.setVisible(false);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setBorder(new LineBorder(Color.BLACK));
		btnCancelar.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnCancelar.setVisible(false);

		btnDirCarga.setVisible(false);
		btnDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnDirCarga.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDirCarga.setHorizontalAlignment(SwingConstants.CENTER);

		btnDirLido.setVisible(false);
		btnDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnDirLido.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDirLido.setHorizontalAlignment(SwingConstants.CENTER);

		txtMascArq = new JTextField();
		txtMascArq.setBorder(new LineBorder(Color.BLACK));
		txtMascArq.setForeground(Color.BLACK);
		txtMascArq.setMargin(new Insets(0, 10, 0, 0));
		txtMascArq.setEditable(false);
		txtMascArq.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtMascArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirCarga = new JTextField();
		txtDirCarga.setBorder(new LineBorder(Color.BLACK));
		txtDirCarga.setForeground(Color.BLACK);
		txtDirCarga.setMargin(new Insets(0, 10, 0, 0));
		txtDirCarga.setEditable(false);
		txtDirCarga.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDirCarga.setHorizontalAlignment(SwingConstants.LEFT);

		txtDirLido = new JTextField();
		txtDirLido.setBorder(new LineBorder(Color.BLACK));
		txtDirLido.setForeground(Color.BLACK);
		txtDirLido.setMargin(new Insets(0, 10, 0, 0));
		txtDirLido.setEditable(false);
		txtDirLido.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDirLido.setHorizontalAlignment(SwingConstants.LEFT);

		txtRefresh = new JTextField();
		txtRefresh.setBorder(new LineBorder(Color.BLACK));
		txtRefresh.setForeground(Color.BLACK);
		txtRefresh.setMargin(new Insets(0, 10, 0, 0));
		txtRefresh.setEditable(false);
		txtRefresh.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtRefresh.setHorizontalAlignment(SwingConstants.LEFT);

		txtQtdeGTM = new JTextField();
		txtQtdeGTM.setBorder(new LineBorder(Color.BLACK));
		txtQtdeGTM.setForeground(Color.BLACK);
		txtQtdeGTM.setMargin(new Insets(0, 10, 0, 0));
		txtQtdeGTM.setEditable(false);
		txtQtdeGTM.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtQtdeGTM.setHorizontalAlignment(SwingConstants.LEFT);

		txtMascArqVazio = new JTextField();
		txtMascArqVazio.setBorder(new LineBorder(Color.BLACK));
		txtMascArqVazio.setForeground(Color.BLACK);
		txtMascArqVazio.setMargin(new Insets(0, 10, 0, 0));
		txtMascArqVazio.setEditable(false);
		txtMascArqVazio.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtMascArqVazio.setHorizontalAlignment(SwingConstants.LEFT);

		txtTemMax = new JTextField();
		txtTemMax.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTemMax.setForeground(Color.BLACK);
		txtTemMax.setMargin(new Insets(0, 10, 0, 0));
		txtTemMax.setEditable(false);
		txtTemMax.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtTemMax.setHorizontalAlignment(SwingConstants.LEFT);

		txtAtraso = new JTextField();
		txtAtraso.setBorder(new LineBorder(Color.BLACK));
		txtAtraso.setForeground(Color.BLACK);
		txtAtraso.setMargin(new Insets(0, 10, 0, 0));
		txtAtraso.setEditable(false);
		txtAtraso.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtAtraso.setHorizontalAlignment(SwingConstants.LEFT);

		txtUltimoArq = new JTextField();
		txtUltimoArq.setBorder(new LineBorder(Color.BLACK));
		txtUltimoArq.setForeground(Color.BLACK);
		txtUltimoArq.setMargin(new Insets(0, 10, 0, 0));
		txtUltimoArq.setEditable(false);
		txtUltimoArq.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtUltimoArq.setHorizontalAlignment(SwingConstants.LEFT);

		txtHora = new JTextField();
		txtHora.setBorder(new LineBorder(Color.BLACK));
		txtHora.setForeground(Color.BLACK);
		txtHora.setMargin(new Insets(0, 10, 0, 0));
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtHora.setHorizontalAlignment(SwingConstants.LEFT);

		txtDataSistema = new JTextField();
		txtDataSistema.setBorder(new LineBorder(Color.BLACK));
		txtDataSistema.setForeground(Color.BLACK);
		txtDataSistema.setMargin(new Insets(0, 10, 0, 0));
		txtDataSistema.setEditable(false);
		txtDataSistema.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtDataSistema.setHorizontalAlignment(SwingConstants.LEFT);

		txtStatus = new JTextField();
		txtStatus.setBorder(new LineBorder(Color.BLACK));
		txtStatus.setForeground(Color.BLACK);
		txtStatus.setMargin(new Insets(0, 10, 0, 0));
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);
	}

	private void initializeListeners() {
		mouseListenerLabel(btnNovo);
		mouseListenerLabel(btnEditar);
		mouseListenerLabel(btnSalvar);
		mouseListenerLabel(btnCancelar);
		mouseListenerLabel(btnDirCarga);
		mouseListenerLabel(btnDirLido);

		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof JTextField)
				keyListenerTXT((JTextField) getComponent(i));
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

	public void novo() {
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

	private void limpaCampos() {
		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof JTextField)
				((JTextField) getComponent(i)).setText("");
		}
	}

	private void ativaCampos() {
		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof JTextField)
				if (getComponent(i) != txtDirCarga && getComponent(i) != txtDirLido && getComponent(i) != txtDataSistema
					&& getComponent(i) != txtHora && getComponent(i) != txtStatus && getComponent(i) != txtUltimoArq)
						((JTextField) getComponent(i)).setEditable(true);
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
		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof JTextField)
			if (((JTextField) getComponent(i)).getText().equals("") &&((JTextField) getComponent(i)).isVisible() 
					&& ((JTextField) getComponent(i)).isEditable()) {
				Alerta.ativaPopup(((JTextField) getComponent(i)), "Preencha o campo vazio acima", 14, 30);
				((JTextField) getComponent(i)).setMargin(new Insets(0, 10, 0, 0));
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

			ConnectionFeps.closeConnection(null, p, c);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível gravar os parâmetros do sistema!");
		}
	}

	private void cancel() {
		for (int i = 0; i < getComponentCount(); i++) {
			if(getComponent(i) instanceof JTextField)
				Alerta.desativaPopup(((JTextField) getComponent(i)));
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
		} else {
			Object[] options = { "Novo", "Padrão" };
			int resposta = JOptionPane.showOptionDialog(null,
					"Parâmetros ainda não definidos, gostaria de carregar preferências padrão ou criar um novo?",
					null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (resposta == 0)
				novo();
			else
				carregaPadrao();
		}
	}

	private void desativaCampos() {
		for (int i = 0; i < getComponentCount(); i++) {
			if(getComponent(i) instanceof JTextField)
				((JTextField) getComponent(i)).setEditable(false);
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

	public boolean loadPreferences() {
		String consultaSQL = "SELECT * FROM parametros";
		ResultSet rs;
		boolean ok;

		try {
			rs = ConnectionFeps.query(consultaSQL);

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

			ConnectionFeps.closeConnection(rs, null, null);

			return ok;
		} catch (SQLException sql) {
			sql.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível carregar os parâmetros do sistema!");
			return false;
		}

	}

	public void carregaPadrao() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			consultaSQL = "INSERT INTO parametros (masc_arq_gm, diretorio_carga, diretorio_lido, tempo_refresh, qtde_fecha_gtm, mascara_vazio,"
					+ "tempo_max_chamada, atraso_linha, ultima_chamada, ultima_chamada_hora, data_sistema, aberto, ultima_chamada_valida, erro_sequencia) VALUES ("
					+ "'.TXT', 'C:/SVDO', 'C:/SVDO/LIDOS', '2000', '8', '.EDS', '2', '12', '9999', " + null + ", "
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
			
			ConnectionFeps.closeConnection(null, p, c);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar carregar os parâmetros padrão do sistema!");
		}
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

	public String getMascArq() {
		return (String) getParameter("masc_arq_gm");
	}

	public String getDirCarga() {
		return (String) getParameter("diretorio_carga");
	}

	public String getDirLido() {
		return (String) getParameter("diretorio_lido");
	}

	public int getTempoRefresh() {
		return Integer.parseInt((String) getParameter("tempo_refresh"));
	}

	public int getQtdFechaGTM() {
		return Integer.parseInt((String) getParameter("qtde_fecha_gtm"));
	}

	public String getMascArqVazio() {
		return (String) getParameter("mascara_vazio");
	}

	public int getTemMax() {
		return Integer.parseInt((String) getParameter("tempo_max_chamada"));
	}

	public int getAtraso() {
		return Integer.parseInt((String) getParameter("atraso_linha"));
	}

	public String getHoraUltimaChamada() {
		String ret = (String) getParameter("ultima_chamada_hora");
		if(ret == null)
			ret = "";
		return ret;
	}

	public String getDataSistema() {
		return (String) getParameter("data_sistema");
	}

	public boolean getStatus() {
		String ret = ((String) getParameter("aberto"));
		return ret != null && ret.equals("S");
	}

	public static String getTurno(LocalTime varHora) {
		String consultaSQL;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM turno";
			rs = ConnectionFeps.query(consultaSQL);
			
			if (rs.next()) {
				LocalTime horaAtual = varHora;
				LocalTime horaInicioTurno = LocalTime.parse(rs.getString("horainicial"));
				LocalTime horaFinalTurno = LocalTime.parse(rs.getString("horafinal"));
				if (horaAtual.compareTo(horaInicioTurno) >= 0 && horaAtual.compareTo(horaFinalTurno) <= 0)
					return rs.getString("idturno");
			}

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o turno da hora: " + varHora.getHour() + ":" + varHora.getMinute()+ " !");
		}

		return null;
	}
}
