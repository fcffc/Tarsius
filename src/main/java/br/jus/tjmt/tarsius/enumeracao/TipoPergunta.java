package br.jus.tjmt.tarsius.enumeracao;

public enum TipoPergunta {
	PROCESSO, APLICACAO;

	public String toString() {
		switch (this) {
		case PROCESSO:
			return "Processo";

		case APLICACAO:
			return "Aplicação";

		default:
			return null;
		}
	}
}