package feps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFeps {

	public static Connection getConnection() {
		try {
			Connection c = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Feps;integratedSecurity=true;");

			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar");
			return null;
		}
	}

	public static int getValorSeq(String nome) {
		try {
			Connection c;
			PreparedStatement p;
			ResultSet rs;
			String consultaSQL = "SELECT * FROM Controle_geral WHERE nome = '" + nome + "'";

			c = getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				int valor = rs.getInt("valor") + 1;
				p.close();
				p = c.prepareStatement(
						"UPDATE Controle_geral SET valor = '" + valor + "'" + " WHERE nome = '" + nome + "'");
				p.executeUpdate();

				rs.close();
				p.close();
				c.close();
				
				rs = null;
				p = null;
				c = null;
				
				return valor;
			} else {
				PreparedStatement p2 = c.prepareStatement(
						"INSERT INTO Controle_geral(nome, valor) VALUES (" + "'" + nome + "', '" + 1 + "')");
				p2.executeUpdate();

				p2.close();
				c.close();
				return 1;
			}
		} catch (SQLException sql) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar");
			sql.printStackTrace();
			return -1;
		}
	}
}
