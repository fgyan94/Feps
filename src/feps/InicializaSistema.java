package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class InicializaSistema extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lblUltimaData = new JLabel("Última data:");
	private JLabel lblDataAbertura = new JLabel("Data abertura:");
	private JTextField txtUltimaData = new JTextField();
	private JComboBox<String> cbDataAbertura = new JComboBox<String>();
	private JLabel btnInicializar = new JLabel("Inicializar");
	private JLabel btnCancelar = new JLabel("Cancelar");

	public InicializaSistema() {
		buildPanel();
		initializeComponents();
		initializeListeners();
		loadComponents();
	}

	private void buildPanel() {
		this.setBounds(0, 0, 300, 200);
		this.setModal(true);
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(155, 155, 100));

		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(155, 155, 100));
	}

	private void initializeComponents() {

		lblUltimaData.setForeground(Color.BLACK);
		lblUltimaData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimaData.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblUltimaData.setBounds(10, 30, 120, 30);
		lblUltimaData.setForeground(new Color(255, 255, 200));
		getContentPane().add(lblUltimaData);

		lblDataAbertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataAbertura.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblDataAbertura.setBounds(10, 80, 120, 30);
		lblDataAbertura.setForeground(new Color(255, 255, 200));
		getContentPane().add(lblDataAbertura);
		
		cbDataAbertura.setFont(new Font("Broadway", Font.PLAIN, 14));
		cbDataAbertura.setBounds(140, 80, 120, 30);
		cbDataAbertura.setBackground(new Color(255, 255, 200));
		cbDataAbertura.setForeground(new Color(155, 155, 100));
		getContentPane().add(cbDataAbertura);
		
		txtUltimaData.setHorizontalAlignment(SwingConstants.CENTER);
		txtUltimaData.setEditable(false);
		txtUltimaData.setFont(new Font("Broadway", Font.PLAIN, 14));
		txtUltimaData.setBounds(140, 30, 120, 30);
		txtUltimaData.setForeground(new Color(255, 255, 200));
		txtUltimaData.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
		txtUltimaData.setBackground(new Color(155, 155, 100));
		getContentPane().add(txtUltimaData);

		btnInicializar.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnInicializar.setBounds(30, 150, 130, 30);
		btnInicializar.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
		btnInicializar.setHorizontalAlignment(SwingConstants.CENTER);
		btnInicializar.setForeground(new Color(255, 255, 200));
		getContentPane().add(btnInicializar);

		btnCancelar.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnCancelar.setBounds(170, 150, 90, 30);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setForeground(new Color(255, 255, 200));
		getContentPane().add(btnCancelar);
	}

	private void initializeListeners() {
		btnInicializar.addMouseListener(mouseListenerLabel(btnInicializar));
		btnCancelar.addMouseListener(mouseListenerLabel(btnCancelar));
	}

	private void loadComponents() {
		loadUltimaData();
		loadComboBox();
	}

	private void loadUltimaData() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM parametros";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				String sDate = rs.getString("data_sistema");
				sDate = new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(sDate));
				txtUltimaData.setText(sDate);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private void loadComboBox() {
		cbDataAbertura.removeAll();
		
		int dayOfMonth = Integer.parseInt(txtUltimaData.getText().substring(0, 2));
		int month = Integer.parseInt(txtUltimaData.getText().substring(3, 5));
		int year = Integer.parseInt(txtUltimaData.getText().substring(6));
		
		LocalDate data = LocalDate.of(year, month, dayOfMonth);
		data = data.plusDays(1);
		
		for (int i = 0; i < 30; i++) {
			String stringData = new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(data));
			cbDataAbertura.addItem(stringData);
			data = data.plusDays(1);
		}
	}

	private void run() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			String data = (String) cbDataAbertura.getSelectedItem();

			int dayOfMonth = Integer.parseInt(data.substring(0, 2));
			int month = Integer.parseInt(data.substring(3, 5));
			int year = Integer.parseInt(data.substring(6));

			consultaSQL = "UPDATE parametros SET aberto = 'S', data_sistema = '" + LocalDate.of(year, month, dayOfMonth)
					+ "'";

			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			p.close();
			c.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private MouseAdapter mouseListenerLabel(JLabel label) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == btnInicializar) {
					run();
					dispose();
					MenuPrincipal.setIconSystemStatus(true);
				} else
					dispose();
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				label.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 255, 200)));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
			}
		};
	}
}
