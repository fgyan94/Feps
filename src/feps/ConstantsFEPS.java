package feps;

public enum ConstantsFEPS {
	prodIniciada("001"),
	prodImpressa("002"),
	prodAtendia("003"),
	
	cockpitIniciado("001"),
	cockpitImpressa("002"),
	cockpitBuffer("003"),
	cockpitConcluido("004"),
	cockpitEntregue("005"),
	
	ordemAutomatica(1),
	ordemManual(2),
	
	statusSistema(false),
	
	mascArqVazio(PreferenciaFeps.getMascArqVazio());
	
	String status;
	int ordem;
	boolean varStatusSistema;
	
	ConstantsFEPS(String status){
		this.status = status;
	}
	
	ConstantsFEPS(int ordem){
		this.ordem = ordem;
	}
	
	ConstantsFEPS(boolean statusSistema){
		this.varStatusSistema = statusSistema;
	}
	
	public String getStringValue(){
		return status;
	}
	
	public int getIntValue(){
		return ordem;
	}
	
	public boolean getBooleanValue(){
		return varStatusSistema;
	}
}
