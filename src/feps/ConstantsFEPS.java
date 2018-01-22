package feps;

public enum ConstantsFEPS {
	PROD_INICIADA("001"),
	PROD_IMPRESSA("002"),
	PROD_ATENDIDA("003"),
	
	COCKPIT_INICIADO("001"),
	COCKPIT_IMPRESSA("002"),
	COCKPIT_BUFFER("003"),
	COCKPIT_CONCLUIDO("004"),
	COCKPIT_ENTREGUE("005"),
	
	ORDEM_AUTOMATICA("1"),
	ORDEM_MANUAL("2"),

	USER_ID(Login.getUsuario());
	
	String var;
	
	ConstantsFEPS(String var){
		this.var = var;
	}
	
	public String getStringValue(){
		return var;
	}
}
