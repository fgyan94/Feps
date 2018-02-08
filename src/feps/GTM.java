package feps;

public class GTM {
	private String vin, serieConti, dataHora, dataSistema;
	private Integer seq, partNumber, numGtm, qtde, lote;
	
	public GTM (int seq, String vin, String serieConti, Integer partNumber, Integer numGtm, Integer qtde, String dataHora, String dataSistema, Integer lote) {
		this.seq = seq;
		this.vin = vin;
		this.serieConti = serieConti;
		this.partNumber = partNumber;
		this.numGtm = numGtm;
		this.qtde = qtde;
		this.dataHora = dataHora;
		this.dataSistema = dataSistema;
		this.lote = lote;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getSerieConti() {
		return serieConti;
	}

	public void setSerieConti(String serieConti) {
		this.serieConti = serieConti;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getNumGtm() {
		return numGtm;
	}

	public void setNumGtm(int numGtm) {
		this.numGtm = numGtm;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getDataSistema() {
		return dataSistema;
	}

	public void setDataSistema(String dataSistema) {
		this.dataSistema = dataSistema;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}
	
	
}
