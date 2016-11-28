package br.jus.tjmt.tarsius.enumeracao;

public enum StatusInspecao {
	SIM, NAO, NA;

	public String toString() {
		switch (this) {
		case SIM:
			return "Sim";

		case NAO:
			return "Não";

		case NA:
			return "N/A";

		default:
			return null;
		}
	}
}