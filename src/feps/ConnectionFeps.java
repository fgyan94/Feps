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
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível obter a conexão ao banco de dados!");
			return null;
		}
	}

	public static boolean update(String consultaSQL) {
		try {
			Connection c = getConnection();
			PreparedStatement p = c.prepareStatement(consultaSQL);

			c.setAutoCommit(false);
			
			if (p.executeUpdate() > 0) {
				c.commit();
				System.out.println("Commit..." + consultaSQL);
			} else {
				c.rollback();
				System.out.println("Rollback..." + consultaSQL);
			}
			

			closeConnection(null, p, c);

			return true;
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível fazer o update!");
		}
		return false;
	}

	public static ResultSet query(String consultaSQL) {
		try {
			return getConnection().prepareStatement(consultaSQL).executeQuery();
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco de dados!");
		}
		return null;
	}

	public static int getValorSeq(String nome) {
		try {
			ResultSet rs;
			String consultaSQL = "SELECT * FROM Controle_geral WHERE nome = '" + nome + "'";

			rs = query(consultaSQL);

			if (rs.next()) {
				int valor = rs.getInt("valor") + 1;
				consultaSQL = "UPDATE Controle_geral SET valor = '" + valor + "'" + " WHERE nome = '" + nome + "'";
				update(consultaSQL);

				closeConnection(rs, null, null);

				return valor;
			} else {
				consultaSQL = "INSERT INTO Controle_geral(nome, valor) VALUES (" + "'" + nome.trim() + "', '" + 1
						+ "')";
				update(consultaSQL);
				closeConnection(rs, null, null);
				return 1;
			}
		} catch (SQLException sqlE) {
			JOptionPane.showMessageDialog(null, "Não foi possível obter o valor sequencial!");
			sqlE.printStackTrace();
			return -1;
		}
	}

	public static void closeConnection(ResultSet rs, PreparedStatement p, Connection c) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (p != null) {
				p.close();
				p = null;
			}
			if (c != null) {
				c.close();
				c = null;
			}
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível fechar as conexões!");
		}
	}
}
