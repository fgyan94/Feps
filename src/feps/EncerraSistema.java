package feps;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class EncerraSistema extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lblEncerraDia = new JLabel("Encerrar o dia?");
	private JLabel btnSim = new JLabel("SIM");
	private JLabel btnNao = new JLabel("NÃO");

	public EncerraSistema() {
		buildPanel();
		initializeComponents();
		initializeListeners();
	}

	private void buildPanel() {
		this.setBounds(0, 0, 300, 140);
		this.setModal(true);
		this.setUndecorated(true);
		this.setOpacity(0.95f);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(155, 155, 100));
		this.getContentPane().setBackground(new Color(155, 155, 100));
		getContentPane().setLayout(null);
	}

	private void initializeComponents() {
		
		lblEncerraDia.setForeground(new Color(255, 255, 200));
		lblEncerraDia.setHorizontalAlignment(SwingConstants.LEFT);
		lblEncerraDia.setFont(new Font("Broadway", Font.PLAIN, 20));
		lblEncerraDia.setBounds(10, 10, 280, 90);
		getContentPane().add(lblEncerraDia);
		
		btnSim.setHorizontalAlignment(SwingConstants.CENTER);
		btnSim.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnSim.setBounds(105, 100, 90, 30);
		btnSim.setForeground(new Color(255, 255, 200));
		btnSim.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
		getContentPane().add(btnSim);
		
		btnNao.setHorizontalAlignment(SwingConstants.CENTER);
		btnNao.setFont(new Font("Broadway", Font.PLAIN, 14));
		btnNao.setBounds(200, 100, 90, 30);
		btnNao.setForeground(new Color(255, 255, 200));
		btnNao.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 200)));
		getContentPane().add(btnNao);
	}

	private void initializeListeners() {
		btnSim.addMouseListener(mouseListenerLabel(btnSim));
		btnNao.addMouseListener(mouseListenerLabel(btnNao));
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
	
	private MouseAdapter mouseListenerLabel(JLabel label) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label == btnSim) {
					end();
					clearValues();
					dispose();
					MenuPrincipal.setIconSystemStatus(false);
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
