package br.jus.tjmt.tarsius.enumeracao;

public enum SecaoSistema {
	CONFIABILIDADE, EFICIENCIA, FUNCIONALIDADE, MANUTENIBILIDADE, PORTABILIDADE, USABILIDADE;

	@Override
	public String toString() {
		switch (this) {
		case CONFIABILIDADE:
			return "Confiabilidade";

		case EFICIENCIA:
			return "Eficiência";

		case FUNCIONALIDADE:
			return "Funcionalidade";

		case MANUTENIBILIDADE:
			return "Manutenibilidade";

		case PORTABILIDADE:
			return "Portabilidade";

		case USABILIDADE:
			return "Usabilidade";

		default:
			return null;
		}
	}
}