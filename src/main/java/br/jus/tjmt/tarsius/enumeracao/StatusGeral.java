package br.jus.tjmt.tarsius.enumeracao;

public enum StatusGeral {
	NOVO, EM_INSPECAO, INSPECIONADO;

	@Override
	public String toString() {
		switch (this) {
		case NOVO:
			return "Novo";
			
		case EM_INSPECAO:
			return "Em inspeção";
			
		case INSPECIONADO:
			return "Inspecionado";		

		default:
			return null;
		}
	}
}