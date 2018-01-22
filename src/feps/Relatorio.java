package feps;

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
	private static final String SIGLA = "CON";
	private static final String UNIDADE = "PC";

	public void imprimeBolha(String seqBolha) {
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

			JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "/ordem.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new JREmptyDataSource());

			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir ordem bolha!");
		}
	}

	public void setStatusImpressao(String serieConti, String apelidoGM) {
		String consultaSQL, partNumber, seqDia, status, ordemOrigem, ordemEntrada, turno;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM ordem_conti WHERE ordem_conti_serie = '" + serieConti + "' AND "
					+ "status_cockpit = '" + ConstantsFEPS.COCKPIT_INICIADO.getStringValue() + "'";

			rs = ConnectionFeps.query(consultaSQL);

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
				if (serieConti.contains("VZ"))
					deleteOrdem(serieConti);

			}

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao setar o status da ordem de impressão!");
		}
	}

	private void updateOrdem(String serieConti, String turno) {
		String consultaSQL;

		LocalDate date = LocalDate.parse(getDataSistema());
		LocalTime time = LocalTime.now();

		consultaSQL = "UPDATE ordem_conti SET status_cockpit = '" + ConstantsFEPS.COCKPIT_IMPRESSA.getStringValue()
				+ "', " + "data_impressao = '" + date + " " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
				+ "', " + "id_turno = '" + turno + "'" + "WHERE ordem_conti_serie = '" + serieConti + "'";
		ConnectionFeps.update(consultaSQL);

		consultaSQL = "UPDATE ordem_gm  SET status_cprod_codigo = '" + ConstantsFEPS.PROD_IMPRESSA.getStringValue()
				+ "', " + "id_turno = '" + turno + "'" + "WHERE ordem_conti_serie = '" + serieConti + "'";

		ConnectionFeps.update(consultaSQL);
	}

	private String getDataSistema() {
		String consultaSQL = "SELECT * FROM parametros";
		String parametro = null;
		ResultSet rs;
		try {
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if (rs.getString("data_sistema") == null)
					parametro = "";
				else
					parametro = rs.getString("data_sistema").trim();

			ConnectionFeps.closeConnection(rs, null, null);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar o parâmetro: Data do Sistema!");
		}

		return parametro;
	}

	private void insertImpressao(String seqDia, String partNumber, String apelido, String serieConti, String status,
			String ordemOrigem, String ordemEntrada, String turno) {

		String consultaSQL;
		consultaSQL = "INSERT INTO impressao (seq_dia, part_number_gm, apelido, ordem_conti_serie, status, ordem_origem, ordem_entrada, id_turno)"
				+ "VALUES ('" + seqDia + "', '" + partNumber + "', '" + apelido + "', '" + serieConti + "', '" + status
				+ "'," + "'" + ordemOrigem + "', '" + ordemEntrada + "', '" + turno + "')";
		ConnectionFeps.update(consultaSQL);
	}

	private void deleteOrdem(String serieConti) {
		String consultaSQL;

		consultaSQL = "DELETE ordem_conti WHERE ordem_conti_serie = '" + serieConti + "'";

		ConnectionFeps.update(consultaSQL);
	}

	public void imprimeOrdem(String serieConti, String partNumber) {
		String consultaSQL, seqDia, ordemOrigem, codigoConti, apelidoConti;
		ResultSet rs;

		try {
			consultaSQL = "SELECT * FROM impressao WHERE ordem_conti_serie = '" + serieConti + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next()) {
				seqDia = rs.getString("seq_dia");
				ordemOrigem = rs.getString("ordem_origem");

				consultaSQL = "SELECT * FROM gm_conti WHERE codigo_gm = '" + partNumber + "'";
				rs = ConnectionFeps.query(consultaSQL);

				if (rs.next()) {
					codigoConti = rs.getString("codigo_conti");
					apelidoConti = rs.getString("apelido");

					seqDia = MenuPrincipal.padding(Integer.parseInt(seqDia.trim()), 4);

					imprimeOrdem(codigoConti, serieConti, partNumber, apelidoConti, seqDia, ordemOrigem);

				}

				consultaSQL = "UPDATE impressao SET status = '1' WHERE ordem_conti_serie = '" + serieConti + "'"
						+ "AND status = '0'";
				ConnectionFeps.update(consultaSQL);

				ConnectionFeps.closeConnection(rs, null, null);

			} else
				JOptionPane.showMessageDialog(null, "Não há nenhuma ordem a ser impressa!");

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro na impressão de ordem série: " + serieConti + "!");
		}
	}

	private void imprimeOrdem(String codConti, String serieConti, String partNumber, String apelidoConti, String seqDia,
			String ordemOrigem) {
		String consultaSQL;
		ResultSet rs;
		HashMap<String, Object> param;
		try {
			consultaSQL = "SELECT * FROM lista_diferenciada WHERE codigo_gm ='" + partNumber + "' ORDER BY lista_ordem";
			rs = ConnectionFeps.query(consultaSQL);

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

			ConnectionFeps.closeConnection(rs, null, null);

			JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "/ordem.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new JREmptyDataSource());

			JasperViewer.viewReport(print, false);
			// JasperPrintManager.printReport(print, false);
		} catch (JRException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao consultar!");
		}

	}

	private String getInserido(String serieConti, String ordemOrigem, String seqDia) {
		String consultaSQL, ret = "";
		ResultSet rs;
		try {

			consultaSQL = "SELECT ordem_gm_doc FROM ordem_gm WHERE ordem_conti_serie = '" + serieConti + "' AND "
					+ "ordem_gm_origem = '" + ordemOrigem + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if (ordemOrigem.equals("2"))
					if (rs.getString("ordem_gm_doc").equals(""))
						ret = "Inserido Buffer";
					else
						ret = "Inserido Manual";
			
			ConnectionFeps.closeConnection(rs, null, null);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar a ordem: " + ordemOrigem + " se é buffer ou manual!");
		}

		return ret;
	}

	private String getSeqGM(String serieConti, String ordemOrigem, String seqDia) {
		String consultaSQL, ret = "";
		ResultSet rs;
		try {

			consultaSQL = "SELECT ordem_gm_doc FROM ordem_gm WHERE ordem_conti_serie = '" + serieConti + "' AND "
					+ "ordem_gm_origem = '" + ordemOrigem + "'";
			rs = ConnectionFeps.query(consultaSQL);

			if (rs.next())
				if (ordemOrigem.equals("1") || !rs.getString("ordem_gm_doc").equals(""))
					ret = rs.getString("ordem_gm_doc");
				else
					ret = "B" + MenuPrincipal.padding(Integer.parseInt(seqDia), 3);

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar a sequência GM! Série Conti: " + serieConti);
		}

		return ret;
	}
	
	public void imprimeGTM(int lote, String dataSistema) {
		String consultaSQL, numGTM, partNumber, sQuantidade, descricao;
		int quantidade;
		ResultSet rs;
		HashMap<String, Object> param = new HashMap<>();
		
		try {
			consultaSQL = "SELECT num_gtm, part_number, quantidade, historico FROM gtm WHERE lote = '" + lote + "'";
			rs = ConnectionFeps.query(consultaSQL);
			
			while(rs.next()) {
				numGTM = SIGLA + MenuPrincipal.padding(rs.getInt("num_gtm"), 6);
				partNumber = rs.getString("part_number").trim();
				quantidade = rs.getInt("quantidade");
				sQuantidade = MenuPrincipal.padding(rs.getInt("quantidade") * 1000, 9) + UNIDADE;
				descricao = rs.getString("historico").trim();
				
				param.put("GTM", numGTM);
				param.put("PARTNUMBER", partNumber);
				param.put("QUANTIDADE",quantidade);
				param.put("SQUANTIDADE", sQuantidade);
				param.put("DESCR", descricao);
				param.put("DATASISTEMA", dataSistema);
				param.put("HORASISTEMA", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				JasperReport report = JasperCompileManager.compileReport(pathToReportPackage + "/gtm.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, param, new JREmptyDataSource());

				JasperViewer.viewReport(print, false);
			}
			
			ConnectionFeps.closeConnection(rs, null, null);
		} catch(SQLException | JRException sqlE) {
			sqlE.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível imprimir a(s) GTM(s) do lote: " + lote);
		}
		
	}
	
	public void imprimeExtrato(int lote) {
		
	}
}
