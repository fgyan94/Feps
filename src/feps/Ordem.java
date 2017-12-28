package feps;

public class Ordem {
	private String partNumber, apelido, ordem_serie, ordem_data, seq_dia, ordem_entrada, qtde, seq_gm;
	
	public Ordem(String partNumber, String apelido, String ordem_serie, int seq_dia, String ordem_entrada) {
		super();
		this.partNumber = partNumber;
		this.apelido = apelido;
		this.ordem_serie = ordem_serie;
		this.seq_dia = Integer.toString(seq_dia);
		this.ordem_entrada = ordem_entrada;
	}
	
	public Ordem(String partNumber, String apelido, String ordem_serie, String ordem_data, String ordem_entrada) {
		super();
		this.partNumber = partNumber;
		this.apelido = apelido;
		this.ordem_serie = ordem_serie;
		this.ordem_data = ordem_data;
		this.ordem_entrada = ordem_entrada;
	}
	
	public Ordem(String partNumber, String apelido, String ordem_serie, String ordem_data, int seq_gm) {
		super();
		this.partNumber = partNumber;
		this.apelido = apelido;
		this.ordem_serie = ordem_serie;
		this.ordem_data = ordem_data;
		this.seq_gm = MenuPrincipal.padding(seq_gm, 4);
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
}
