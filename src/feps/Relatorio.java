package feps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	private static final String pathToReportPackage = "D:/Yan/git/Feps/src/jasper";
	private static final String BOLHA = "BOLHA";
	private static final String BOLHAPN = "00000000";

	public static void imprimeBolha(String seqBolha) {
		HashMap<String, Object> param = new HashMap<>();

		try {
			param.put("APELIDO", BOLHA);
			param.put("PNCONTI", BOLHA);
			param.put("PNGM", BOLHAPN);
			param.put("CODSEQGMPN", BOLHAPN);
			param.put("SEQDIA", seqBolha);
			param.put("DATAHORA",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).trim());
			param.put("SERIECONTI", BOLHAPN);

			JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "/report.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new JREmptyDataSource());

			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir ordem bolha!");
		}
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
				if(serieConti.contains("VZ"))
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

					seqDia = MenuPrincipal.padding(Integer.parseInt(seqDia.trim()), 4);

					imprimeOrdem(codigoConti, serieConti, partNumber, apelidoConti, seqDia, ordemOrigem);

				}

				consultaSQL = "UPDATE impressao SET status = '1' WHERE ordem_conti_serie = '" + serieConti + "'"
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
		String consultaSQL;
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		HashMap<String, Object> param;
		try {
			consultaSQL = "SELECT * FROM lista_diferenciada WHERE codigo_gm ='" + partNumber + "' ORDER BY lista_ordem";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			String codSeqPn = getSeqGM(serieConti.trim(), ordemOrigem.trim(), seqDia.trim());
			String seqGM = getSeqGM(serieConti.trim(), ordemOrigem.trim(), seqDia.trim());
			String inserido = getInserido(serieConti.trim(), ordemOrigem.trim(), seqDia.trim());

			param = new HashMap<>();

			param.put("APELIDO", apelidoConti.trim());
			param.put("PNCONTI", codConti.trim());
			param.put("PNGM", partNumber.trim());
			param.put("CODSEQGMPN", codSeqPn + partNumber.trim());
			param.put("SEQDIA", seqDia.trim());
			param.put("SEQGM", seqGM);
			param.put("BUFFERMANUAL", inserido);
			param.put("DATAHORA",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).trim());
			param.put("SERIECONTI", serieConti.trim());

			int i = 1;
			while (rs.next()) {
				if (rs.getInt("lista_ordem") == 12) {
					param.put("TR", rs.getString("texto").trim());
					param.put("AMBARTR", rs.getString("ambar").trim());
				} else if (rs.getInt("lista_ordem") == 13) {
					param.put("LD", rs.getString("texto").trim());
					param.put("AMBARLD", rs.getString("ambar").trim());
				}

				else if (rs.getInt("lista_ordem") == 14) {
					param.put("PM", rs.getString("texto").trim());
					param.put("AMBARPM", rs.getString("ambar").trim());
				}

				else if (rs.getInt("lista_ordem") == 15) {
					param.put("AC", rs.getString("texto").trim());
					param.put("AMBARAC", rs.getString("ambar").trim());
				}

				else if (rs.getInt("lista_ordem") == 16) {
					param.put("MANUAL", rs.getString("texto").trim());
				} else {
					param.put("CODVAR" + i, rs.getString("codigo_peca").trim());
					param.put("TXTVAR" + i, rs.getString("texto").trim());
				}
				i += 1;
			}
			rs.close();
			p.close();
			c.close();

			rs = null;
			p = null;
			c = null;

			JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "/report.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new JREmptyDataSource());

			JasperViewer.viewReport(print, false);
			// JasperPrintManager.printReport(print, false);
		} catch (JRException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}

	}

	private static String getInserido(String serieConti, String ordemOrigem, String seqDia) {
		String consultaSQL, ret = "";
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		try {

			consultaSQL = "SELECT ordem_gm_doc FROM ordem_gm WHERE ordem_conti_serie = '" + serieConti + "' AND "
					+ "ordem_gm_origem = '" + ordemOrigem + "'";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next())
				if (ordemOrigem.equals("2"))
					if (rs.getString("ordem_gm_doc").equals(""))
						ret = "Inserido Buffer";
					else
						ret = "Inserido Manual";

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}

		return ret;
	}

	private static String getSeqGM(String serieConti, String ordemOrigem, String seqDia) {
		String consultaSQL, ret = "";
		Connection c;
		PreparedStatement p;
		ResultSet rs;
		try {

			consultaSQL = "SELECT ordem_gm_doc FROM ordem_gm WHERE ordem_conti_serie = '" + serieConti + "' AND "
					+ "ordem_gm_origem = '" + ordemOrigem + "'";
			c = ConnectionFeps.getConnection();
			p = c.prepareStatement(consultaSQL);
			rs = p.executeQuery();

			if (rs.next())
				if (ordemOrigem.equals("1") || !rs.getString("ordem_gm_doc").equals(""))
					ret = rs.getString("ordem_gm_doc");
				else
					ret = "B" + MenuPrincipal.padding(Integer.parseInt(seqDia), 3);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}

		return ret;
	}
}
