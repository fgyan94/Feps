package feps;

public class GTM {
	private String vin, serieConti;
	private int seq, partNumber, numGtm;
	
	public GTM (int seq, String vin, String serieConti, int partNumber, int numGtm) {
		this.seq = seq;
		this.vin = vin;
		this.serieConti = serieConti;
		this.partNumber = partNumber;
		this.numGtm = numGtm;
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
	
	
}
