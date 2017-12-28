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
	
	mascArq(PreferenciaFeps.getMascArq()),
	dirCarga(PreferenciaFeps.getDirCarga()),
	dirLido(PreferenciaFeps.getDirLido()),
	refresh(PreferenciaFeps.getTempoRefresh()),
	fechaGTM(PreferenciaFeps.getQtdFechaGTM()),
	mascArqVazio(PreferenciaFeps.getMascArqVazio()),
	tempoMax(PreferenciaFeps.getTemMax()),
	atrasoOrdem(PreferenciaFeps.getAtraso()),
	horaUltimoArq(PreferenciaFeps.getHoraUltimaChamada()),
	dataSistema(PreferenciaFeps.getDataSistema()),
	statusSistema(PreferenciaFeps.getStatus());
	
	String a;
	int b;
	boolean c;
	
	ConstantsFEPS(String status){
		this.a = status;
	}
	
	ConstantsFEPS(int ordem){
		this.b = ordem;
	}
	
	ConstantsFEPS(boolean statusSistema){
		this.c = statusSistema;
	}
	
	public String getStringValue(){
		return a;
	}
	
	public int getIntValue(){
		return b;
	}
	
	public boolean getBooleanValue(){
		return c;
	}
}
