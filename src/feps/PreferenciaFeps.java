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
	
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField mascArqVaz;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_9;
	private static JTextField textField_10;
	private static JTextField textField_11;
	
	public PreferenciaFeps() {
		buildPanel();
		inicializaComponentes();
	}

	private void buildPanel() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(255, 200, 50));
		setLayout(null);
		
		JButton btnSalvar = new JButton("salvar");
		btnSalvar.setBounds(273, 626, 81, 26);
		add(btnSalvar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBounds(354, 626, 81, 26);
		add(btnCancelar);
		
		JLabel lblMscaraDoArquivo = new JLabel("Máscara do arquivo:");
		lblMscaraDoArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMscaraDoArquivo.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblMscaraDoArquivo.setBounds(434, 196, 150, 25);
		add(lblMscaraDoArquivo);
		
		JLabel lblDiretrioDeCarga = new JLabel("Diretório de carga:");
		lblDiretrioDeCarga.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblDiretrioDeCarga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiretrioDeCarga.setBounds(273, 333, 150, 25);
		add(lblDiretrioDeCarga);
		
		JLabel lblDiretrioLidos = new JLabel("Diret\u00F3rio lidos:");
		lblDiretrioLidos.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblDiretrioLidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiretrioLidos.setBounds(273, 363, 150, 25);
		add(lblDiretrioLidos);
		
		JLabel lblTempoDoRefresh = new JLabel("Tempo do refresh:");
		lblTempoDoRefresh.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblTempoDoRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTempoDoRefresh.setBounds(273, 393, 150, 25);
		add(lblTempoDoRefresh);
		
		JLabel lblQuantidadeGtm = new JLabel("Quantidade GTM:");
		lblQuantidadeGtm.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblQuantidadeGtm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidadeGtm.setBounds(273, 423, 150, 25);
		add(lblQuantidadeGtm);
		
		JLabel lblMascaraDoArquivo = new JLabel("Mascara arquivo vazio:");
		lblMascaraDoArquivo.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblMascaraDoArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascaraDoArquivo.setBounds(273, 453, 150, 25);
		add(lblMascaraDoArquivo);
		
		textField = new JTextField();
		textField.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(594, 196, 310, 40);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setColumns(10);
		textField_1.setBounds(594, 257, 310, 40);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setColumns(10);
		textField_2.setBounds(594, 319, 310, 40);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setBounds(594, 370, 120, 40);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_4.setHorizontalAlignment(SwingConstants.LEFT);
		textField_4.setColumns(10);
		textField_4.setBounds(594, 423, 120, 40);
		add(textField_4);
		
		mascArqVaz = new JTextField();
		mascArqVaz.setFont(new Font("Palatino", Font.PLAIN, 14));
		mascArqVaz.setHorizontalAlignment(SwingConstants.LEFT);
		mascArqVaz.setColumns(10);
		mascArqVaz.setBounds(433, 453, 120, 25);
		add(mascArqVaz);
		
		JLabel lblTempoMxChamadas = new JLabel("Tempo m\u00E1x. chamada:");
		lblTempoMxChamadas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTempoMxChamadas.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblTempoMxChamadas.setBounds(273, 483, 150, 25);
		add(lblTempoMxChamadas);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_6.setHorizontalAlignment(SwingConstants.LEFT);
		textField_6.setColumns(10);
		textField_6.setBounds(433, 483, 120, 25);
		add(textField_6);
		
		JLabel lblIndicadorDeAtraso = new JLabel("Indicador de Atraso:");
		lblIndicadorDeAtraso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIndicadorDeAtraso.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblIndicadorDeAtraso.setBounds(273, 513, 150, 25);
		add(lblIndicadorDeAtraso);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_7.setHorizontalAlignment(SwingConstants.LEFT);
		textField_7.setColumns(10);
		textField_7.setBounds(433, 513, 120, 25);
		add(textField_7);
		
		JLabel lblltimaChamadahora = new JLabel("\u00DAltima chamada/hora:");
		lblltimaChamadahora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblltimaChamadahora.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblltimaChamadahora.setBounds(273, 543, 150, 25);
		add(lblltimaChamadahora);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_8.setHorizontalAlignment(SwingConstants.LEFT);
		textField_8.setColumns(10);
		textField_8.setBounds(433, 543, 120, 25);
		add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_9.setHorizontalAlignment(SwingConstants.LEFT);
		textField_9.setColumns(10);
		textField_9.setBounds(563, 543, 180, 25);
		add(textField_9);
		
		JLabel lblDataDoSistema = new JLabel("Data do sistema:");
		lblDataDoSistema.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDoSistema.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblDataDoSistema.setBounds(273, 573, 150, 25);
		add(lblDataDoSistema);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_10.setHorizontalAlignment(SwingConstants.LEFT);
		textField_10.setColumns(10);
		textField_10.setBounds(433, 573, 120, 25);
		add(textField_10);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setFont(new Font("Palatino", Font.PLAIN, 14));
		lblStatus.setBounds(563, 573, 50, 25);
		add(lblStatus);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Palatino", Font.PLAIN, 14));
		textField_11.setHorizontalAlignment(SwingConstants.LEFT);
		textField_11.setColumns(10);
		textField_11.setBounds(623, 572, 120, 25);
		add(textField_11);
		
		JLabel lblParametrosDoSistema = new JLabel("Parametros do Sistema");
		lblParametrosDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametrosDoSistema.setForeground(Color.BLACK);
		lblParametrosDoSistema.setFont(new Font("Broadway", Font.PLAIN, 40));
		lblParametrosDoSistema.setBounds(442, 50, 555, 100);
		add(lblParametrosDoSistema);
	}

	private void inicializaComponentes() {
	}
	
	public static String getMascArqVazio(){
		return mascArqVaz.getText();
	}
}
