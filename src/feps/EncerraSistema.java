package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class EncerraSistema extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lblEncerrarODia = new JLabel("Encerrar o dia?");
	private JButton btnSim = new JButton("SIM");
	private JButton btnNao = new JButton("NÃO");

	public EncerraSistema() {
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		this.setBounds(0, 0, 220, 120);
		this.setModal(true);
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(155, 155, 100));
		this.getContentPane().setBackground(new Color(155, 155, 100));
		getContentPane().setLayout(null);
	}

	private void initializeComponents() {
		lblEncerrarODia.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncerrarODia.setFont(new Font("Broadway", Font.PLAIN, 14));
		lblEncerrarODia.setBounds(11, 11, 198, 50);
		getContentPane().add(lblEncerrarODia);

		btnSim.setBounds(10, 90, 90, 20);
		getContentPane().add(btnSim);

		btnNao.setBounds(120, 90, 90, 20);
		getContentPane().add(btnNao);
	}

	private void initializeListeners() {
		btnSim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				end();
				clearValues();
				dispose();
			}
		});

		btnNao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void end() {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			consultaSQL = "UPDATE parametros SET aberto = 'N'";
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

	private void clearValues() {
		String consultaSQL, serie;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM gm_conti";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				while (!rs.isAfterLast()) {
					serie = "SERIE_" + rs.getString("apelido_serie");
					consultaSQL = "UPDATE controle_geral SET valor = '0' WHERE nome = '" + serie + "'";
					p = c.prepareStatement(consultaSQL);
					rs.next();
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}
}
