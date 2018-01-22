package feps;

public class Ordem {
	private String partNumber, apelido, ordem_serie, ordem_data, seq_dia, ordem_entrada, qtde, seq_gm, status_cockpit;
	private Boolean selected = false;

	public Ordem(String partNumber, String apelido, String ordem_serie, String ordem_data, String ordem_entrada, String seq_dia, 
			String qtde, String seq_gm, String status_cockpit) {
		super();
		this.partNumber = partNumber;
		this.apelido = apelido;
		this.ordem_serie = ordem_serie;
		this.ordem_data = ordem_data;
		this.ordem_entrada = ordem_entrada;
		this.seq_dia = seq_dia;
		this.qtde = qtde;
		this.seq_gm = MenuPrincipal.padding(Integer.parseInt(seq_gm), 4);
		this.status_cockpit = status_cockpit;
	}
	
	public Ordem(String partNumber, String apelido, int quantidade) {
		this.partNumber = partNumber;
		this.apelido = apelido;
		this.qtde = Integer.toString(quantidade);
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getOrdem_serie() {
		return ordem_serie;
	}

	public void setOrdem_serie(String ordem_serie) {
		this.ordem_serie = ordem_serie;
	}

	public String getOrdem_data() {
		return ordem_data;
	}

	public void setOrdem_data(String ordem_data) {
		this.ordem_data = ordem_data;
	}

	public String getSeq_dia() {
		return seq_dia;
	}

	public void setSeq_dia(String seq_dia) {
		this.seq_dia = seq_dia;
	}

	public String getOrdem_entrada() {
		return ordem_entrada;
	}

	public void setOrdem_entrada(String ordem_entrada) {
		this.ordem_entrada = ordem_entrada;
	}

	public String getQtde() {
		return qtde;
	}

	public void setQtde(String qtde) {
		this.qtde = qtde;
	}
	
	public void setSeqGM(String seqGM) {
		this.seq_gm = seqGM;
	}
	
	public String getSeqGM() {
		return this.seq_gm;
	}
	
	public void setStatusCockpit(String status_cockpit) {
		this.status_cockpit = status_cockpit;
	}
	
	public String getStatusCockpit() {
		return this.status_cockpit;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
}
