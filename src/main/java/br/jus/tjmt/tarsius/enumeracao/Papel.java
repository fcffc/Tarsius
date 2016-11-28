package br.jus.tjmt.tarsius.enumeracao;

public enum Papel {
	ADMINISTRADOR, GESTOR, INSPETOR, VISITANTE;

	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRADOR:
			return "Administrador";

		case GESTOR:
			return "Gestor";

		case INSPETOR:
			return "Inspetor";

		case VISITANTE:
			return "Visitante";

		default:
			return null;
		}
	}
}
