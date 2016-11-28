package br.jus.tjmt.tarsius.enumeracao;

public enum SecaoProcesso {
	ENTRADA, SAIDA, PASSO, TEMPLATE, FERRAMENTA, RESPONSAVEL;
	
	@Override
	public String toString() {
		switch (this) {
		case ENTRADA:
			return "Entrada";
			
		case SAIDA:
			return "Saída";
			
		case PASSO:
			return "Passo";
			
		case TEMPLATE:
			return "Template";
			
		case FERRAMENTA:
			return "Ferramenta";
			
		case RESPONSAVEL:
			return "Responsável";

		default:
			return null;
		}		
	}
}