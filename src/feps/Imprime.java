package feps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Imprime {
	public static void imprimeBolha() {

	}

	public static void setStatusImpressao(String serieConti, String apelidoGM) {
		String consultaSQL, partNumber, seqDia, status, ordemOrigem, ordemEntrada, turno;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM ordem_conti WHERE ordem_conti_serie = '" + serieConti + "'";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				partNumber = rs.getString("part_number_gm");
				seqDia = rs.getString("sequencia_dia");
				serieConti = rs.getString("ordem_conti_serie");
				ordemOrigem = rs.getString("ordem_conti_origem");
				ordemEntrada = rs.getString("ordem_entrada");
				turno = PreferenciaFeps.getTurno(LocalTime.now());
				status = "0";

				updateOrdem(serieConti, turno);
				insertImpressao(seqDia, partNumber, apelidoGM, serieConti, status, ordemOrigem, ordemEntrada, turno);
				deleteOrdem(serieConti);

			}

			rs.close();
			p.close();
			c.close();

			p = null;
			c = null;
			rs = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private static void updateOrdem(String serieConti, String turno) {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		LocalDate date = LocalDate.parse(ConstantsFEPS.dataSistema.getStringValue());
		LocalTime time = LocalTime.now();

		try {
			consultaSQL = "UPDATE ordem_conti SET status_cockpit = '" + ConstantsFEPS.cockpitImpressa.getStringValue()
					+ "', " + "data_impressao = '" + date + " " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
					+ "', " + "id_turno = '" + turno + "'" + "WHERE ordem_conti_serie = '" + serieConti + "'";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			consultaSQL = "UPDATE ordem_gm  SET status_cprod_codigo = '" + ConstantsFEPS.prodImpressa.getStringValue()
					+ "', " + "id_turno = '" + turno + "'" + "WHERE ordem_conti_serie = '" + serieConti + "'";
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			p.close();
			c.close();

			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar");
		}
	}

	private static void insertImpressao(String seqDia, String partNumber, String apelido, String serieConti,
			String status, String ordemOrigem, String ordemEntrada, String turno) {

		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			consultaSQL = "INSERT INTO impressao (seq_dia, part_number_gm, apelido, ordem_conti_serie, status, ordem_origem, ordem_entrada, id_turno)"
					+ "VALUES ('" + seqDia + "', '" + partNumber + "', '" + apelido + "', '" + serieConti + "', '"
					+ status + "'," + "'" + ordemOrigem + "', '" + ordemEntrada + "', '" + turno + "')";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			p.close();
			c.close();

			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private static void deleteOrdem(String serieConti) {
		String consultaSQL;
		Connection c;
		PreparedStatement p;

		try {
			consultaSQL = "DELETE ordem_conti WHERE ordem_conti_serie = '" + serieConti + "'";

			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			p.executeUpdate();

			p.close();
			c.close();

			p = null;
			c = null;

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}

	}

	public static void imprimeOrdem(String serieConti, String partNumber) {
		String consultaSQL, seqDia, ordemOrigem, codigoConti, apelidoConti;
		Connection c;
		PreparedStatement p;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM impressao WHERE ordem_conti_serie = '" + serieConti + "'";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next()) {
				seqDia = rs.getString("seq_dia");
				ordemOrigem = rs.getString("ordem_origem");

				consultaSQL = "SELECT * FROM gm_conti WHERE codigo_gm = '" + partNumber + "'";
				p = c.prepareStatement(consultaSQL);
				rs = p.executeQuery();

				if (rs.next()) {
					codigoConti = rs.getString("codigo_conti");
					apelidoConti = rs.getString("apelido");

					seqDia = MenuPrincipal.padding(Integer.parseInt(seqDia), 4);

					imprimeOrdem(codigoConti, serieConti, partNumber, apelidoConti, seqDia, ordemOrigem);

				}

				consultaSQL = "UPDATE impressao SET STATUS = '1' WHERE ordem_conti_serie = '" + serieConti + "'"
						+ "AND status = '0'";
				p = c.prepareStatement(consultaSQL);
				p.executeUpdate();

				rs.close();
				p.close();
				c.close();

				rs = null;
				p = null;
				c = null;

			} else
				JOptionPane.showMessageDialog(null, "Não há nenhuma ordem a ser impressa!");

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}
	}

	private static void imprimeOrdem(String codConti, String serieConti, String partNumber, String apelidoConti,
			String seqDia, String ordemOrigem) {

	}
}
